package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSolicitud;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.util.enums.TipoSolicitud;
import ar.edu.unlam.tallerweb1.util.exceptions.FalloAlInvitarUsuario;
import ar.edu.unlam.tallerweb1.util.exceptions.FalloAlProcesarSolicitud;
import ar.edu.unlam.tallerweb1.util.exceptions.GrupoInexistenteException;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioSinPermisosException;
import ar.edu.unlam.tallerweb1.util.exceptions.YaEstoyEnElGrupo;

@Service
@Transactional
public class ServicioSolicitudImpl implements ServicioSolicitud {

	private final RepositorioSolicitud repoSolicitudes;
	private final RepositorioUsuario repoUsuario;
	private final RepositorioGrupo repoGrupo;
	
	private final ServicioNotificaciones servicioNotificaciones;

	@Autowired
	public ServicioSolicitudImpl(RepositorioSolicitud repoSolicitudes, RepositorioGrupo repoGrupo,
			RepositorioUsuario repoUsuario, ServicioNotificaciones servicioNotificaciones) {
		this.servicioNotificaciones = servicioNotificaciones;
				
		this.repoSolicitudes = repoSolicitudes;
		this.repoUsuario = repoUsuario;
		this.repoGrupo = repoGrupo;
	}

	@Override
	public List<Solicitud> buscarSolicitudes(Long idUsuario) {
		return repoSolicitudes.buscarSolicitudesPor(idUsuario);
	}

	@Override
	public void solicitarInclusionAGrupo(Long idGrupo, Long idUsuario) {
		Grupo solicitado = repoGrupo.getGrupoByID(idGrupo);

		if (solicitado == null)
			throw new GrupoInexistenteException("No se puede enviar solicitud a grupo inexistente");

		Usuario solicitante = repoUsuario.getUsuarioByID(idUsuario);
		
		if (solicitante == null)
			throw new UsuarioNoEncontradoException("No existe el usuario solicitante!");
		
		if(solicitado.getListaDeUsuarios().contains(solicitante))
			throw new YaEstoyEnElGrupo(idGrupo);

		generarSolicitud(solicitado, solicitante, solicitado.getAdministrador(), TipoSolicitud.INCLUSION_GRUPO);

	}
	
	@Override
	public void invitarUsuario(Long anfitrion, String correo, Long grupo) {
		Grupo solicitante = repoGrupo.getGrupoByID(grupo);

		if (solicitante == null)
			throw new GrupoInexistenteException("No se puede invitar a grupo inexistente");

		Usuario administrador = repoUsuario.getUsuarioByID(anfitrion);
		
		if (administrador == null)
			throw new UsuarioNoEncontradoException("No existe el usuario que manda la invitacion!");
		
		if(!solicitante.getAdministrador().equals(administrador))
			throw new UsuarioSinPermisosException("No tienes permiso para invitar nuevos miembros", grupo);
		
		Usuario invitado = repoUsuario.getUsuarioByEmail(correo);
		
		if(invitado == null)
			throw new FalloAlInvitarUsuario(grupo);
		
		generarSolicitud(solicitante, administrador, invitado, TipoSolicitud.INVITACION_GRUPO);
	}

	@Override
	public void aprobarSolicitud(Long idSolicitudAceptada, Long idUsuario) {
		Solicitud aprobada = repoSolicitudes.buscarSolicitudPor(idSolicitudAceptada, idUsuario);

		if (aprobada == null)
			throw new FalloAlProcesarSolicitud("La Solicitud que intenta aprobar no existe o fue eliminada");

		unirUsuarioAGrupo(aprobada);
	}

	@Override
	public void rechazarSolicitud(Long idSolicitudRechazada, Long idUsuario) {
		Solicitud rechazada = repoSolicitudes.buscarSolicitudPor(idSolicitudRechazada, idUsuario);

		if (rechazada == null)
			throw new FalloAlProcesarSolicitud("La Solicitud que intenta rechazar no existe o fue eliminada");
		
		repoSolicitudes.borrarSolicitud(rechazada);
	}
	
	private void generarSolicitud(Grupo grupo, Usuario origen, Usuario destino, TipoSolicitud tipoSolicitud) {
		Solicitud nuevaSolicitud = new Solicitud();

		nuevaSolicitud.setDestino(destino);
		nuevaSolicitud.setObjetivo(grupo);
		nuevaSolicitud.setOrigen(origen);
		nuevaSolicitud.setTipo(tipoSolicitud);

		repoSolicitudes.cargarSolicitud(nuevaSolicitud);
	}

	private void unirUsuarioAGrupo(Solicitud aprobada) {
		if (aprobada.getObjetivo() == null || aprobada.getOrigen() == null || aprobada.getObjetivo().grupoLleno()) {
			repoSolicitudes.borrarSolicitud(aprobada);
			throw new FalloAlProcesarSolicitud("No es posible aprobar esta solicitud");
		}

		if(aprobada.getTipo() == TipoSolicitud.INCLUSION_GRUPO) {
			aprobada.getObjetivo().agregarUsuarioAlGrupo(aprobada.getOrigen());
			repoGrupo.actualizarGrupo(aprobada.getObjetivo());
			servicioNotificaciones.notificarNuevoIngreso(aprobada.getObjetivo().getId(), aprobada.getOrigen());
		}
		else if(aprobada.getTipo() == TipoSolicitud.INVITACION_GRUPO) {
			aprobada.getObjetivo().agregarUsuarioAlGrupo(aprobada.getDestino());
			repoGrupo.actualizarGrupo(aprobada.getObjetivo());
			servicioNotificaciones.notificarNuevoIngreso(aprobada.getObjetivo().getId(), aprobada.getDestino());
		}
		
		repoSolicitudes.borrarSolicitud(aprobada);
	}

}
