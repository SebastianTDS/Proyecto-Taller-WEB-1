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
import ar.edu.unlam.tallerweb1.util.exceptions.FalloAlProcesarSolicitud;
import ar.edu.unlam.tallerweb1.util.exceptions.GrupoInexistenteException;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;
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
		Usuario solicitante = repoUsuario.getUsuarioByID(idUsuario);

		if (solicitado == null)
			throw new GrupoInexistenteException("No se puede enviar solicitud a grupo inexistente");

		if (solicitante == null)
			throw new UsuarioNoEncontradoException("No existe el usuario solicitante!");
		
		if(solicitado.getListaDeUsuarios().contains(solicitante))
			throw new YaEstoyEnElGrupo(idGrupo);

		generarSolicitud(solicitado, solicitante, solicitado.getAdministrador(), TipoSolicitud.INCLUSION_GRUPO);

	}

	@Override
	public void aprobarSolicitud(Long idSolicitudAceptada, Long idUsuario) {
		Solicitud aprobada = repoSolicitudes.buscarSolicitudPor(idSolicitudAceptada, idUsuario);

		if (aprobada == null)
			throw new FalloAlProcesarSolicitud("La Solicitud que intenta aprobar no existe o fue eliminada");

		switch (aprobada.getTipo()) {
		case INCLUSION_GRUPO:
			unirUsuarioSolicitanteAGrupo(aprobada);
			break;
		case INVITACION_GRUPO:
			break;
		}
	}

	@Override
	public void rechazarSolicitud(Long idSolicitudRechazada, Long idUsuario) {
		Solicitud rechazada = repoSolicitudes.buscarSolicitudPor(idSolicitudRechazada, idUsuario);

		if (rechazada == null)
			throw new FalloAlProcesarSolicitud("La Solicitud que intenta rechazar no existe o fue eliminada");
		
		repoSolicitudes.borrarSolicitud(rechazada);
	}

	private void generarSolicitud(Grupo solicitado, Usuario solicitante, Usuario destino, TipoSolicitud tipoSolicitud) {
		Solicitud nuevaSolicitud = new Solicitud();

		nuevaSolicitud.setDestino(destino);
		nuevaSolicitud.setObjetivo(solicitado);
		nuevaSolicitud.setOrigen(solicitante);
		nuevaSolicitud.setTipo(tipoSolicitud);

		repoSolicitudes.cargarSolicitud(nuevaSolicitud);
	}

	private void unirUsuarioSolicitanteAGrupo(Solicitud aprobada) {
		if (aprobada.getObjetivo() == null || aprobada.getOrigen() == null || aprobada.getObjetivo().grupoLleno()) {
			repoSolicitudes.borrarSolicitud(aprobada);
			throw new FalloAlProcesarSolicitud("No es posible aprobar esta solicitud");
		}

		aprobada.getObjetivo().agregarUsuarioAlGrupo(aprobada.getOrigen());
		repoGrupo.actualizarGrupo(aprobada.getObjetivo());
		
		servicioNotificaciones.notificarNuevoIngreso(aprobada.getObjetivo().getId(), aprobada.getOrigen());
		repoSolicitudes.borrarSolicitud(aprobada);
	}

}
