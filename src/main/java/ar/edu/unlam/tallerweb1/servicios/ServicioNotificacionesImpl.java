package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSolicitud;
import ar.edu.unlam.tallerweb1.util.auxClass.Check;
import ar.edu.unlam.tallerweb1.util.exceptions.GrupoInexistenteException;

@Service
@Transactional
public class ServicioNotificacionesImpl implements ServicioNotificaciones {

	private RepositorioNotificacion repoNotificaciones;
	private RepositorioSolicitud repoSolicitudes;
	private RepositorioGrupo repoGrupo;

	@Autowired
	public ServicioNotificacionesImpl(RepositorioNotificacion repoNotificaciones, RepositorioGrupo repoGrupo, RepositorioSolicitud repoSolicitudes) {
		this.repoNotificaciones = repoNotificaciones;
		this.repoSolicitudes = repoSolicitudes;
		this.repoGrupo = repoGrupo;
	}

	@Override
	public List<Notificacion> obtenerNotificacionesPor(Long usuario) {
		List<Notificacion> notificaciones = repoNotificaciones.getNotificacionesPor(usuario);
		repoNotificaciones.marcarVistoDeUsuario(usuario);
		return notificaciones;
	}

	@Override
	public void notificarNuevoIngreso(Long idGrupo, Usuario nuevoIntegrante) {
		Grupo grupoObjetivo = repoGrupo.getGrupoByID(idGrupo);

		if (Check.isNull(grupoObjetivo))
			throw new GrupoInexistenteException("Imposible unirse a grupo inexistente");

		for (Usuario integrante : grupoObjetivo.getListaDeUsuarios()) {
			String titulo = integrante.getId() == nuevoIntegrante.getId()
					? "Te has unido al grupo \"" + grupoObjetivo.getNombre() + "\""
					: nuevoIntegrante.getNombre() + " se ha unido al grupo \"" + grupoObjetivo.getNombre() + "\"";

			notificar(titulo, integrante);
		}
	}

	@Override
	public void notificarEliminacionDeGrupo(Long idGrupo) {
		Grupo grupoObjetivo = repoGrupo.getGrupoByID(idGrupo);

		if (Check.isNull(grupoObjetivo))
			throw new GrupoInexistenteException("Imposible eliminar un grupo inexistente");

		for (Usuario integrante : grupoObjetivo.getListaDeUsuarios()) {
			String titulo = "El grupo \"" + grupoObjetivo.getNombre() + "\" ha sido eliminado";
			notificar(titulo, integrante);
		}
	}
	
	@Override
	public Boolean hayPendientes(Long idUsuario) {
		return !Check.isNull(repoNotificaciones.getExistePendiente(idUsuario))
				|| !Check.isNull(repoSolicitudes.getExistePendiente(idUsuario));
	}

	@Override
	public void notificarRetiroDeGrupo(Long id, Usuario usuarioARetirar) {
		Grupo grupoObjetivo = repoGrupo.getGrupoByID(id);

		if (Check.isNull(grupoObjetivo))
			throw new GrupoInexistenteException("Imposible unirse a grupo inexistente");
		if (!grupoObjetivo.getListaDeUsuarios().contains(usuarioARetirar))
			throw new UsuarioNoEncontradoException("Imposible Salirse de un grupo que no estoy");

		for (Usuario integrante : grupoObjetivo.getListaDeUsuarios()) {
			String titulo = integrante.getId() == usuarioARetirar.getId()
					? "Te has retirado del grupo \"" + grupoObjetivo.getNombre() + "\""
					: usuarioARetirar.getNombre() + " se ha retirado del grupo \"" + grupoObjetivo.getNombre() + "\"";

			notificar(titulo, integrante);
		}
	}

	private void notificar(String titulo, Usuario destinatario) {
		Notificacion mensaje = new Notificacion();
		mensaje.setUsuario(destinatario);
		mensaje.setTitulo(titulo);
		repoNotificaciones.guardarNotificacion(mensaje);
	}

}
