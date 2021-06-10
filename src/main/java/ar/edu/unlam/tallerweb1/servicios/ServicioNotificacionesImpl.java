package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;
import ar.edu.unlam.tallerweb1.util.auxClass.Check;
import ar.edu.unlam.tallerweb1.util.exceptions.GrupoInexistenteException;

@Service
@Transactional
public class ServicioNotificacionesImpl implements ServicioNotificaciones {

	private RepositorioNotificacion repoNotificaciones;
	private RepositorioGrupo repoGrupo;

	@Autowired
	public ServicioNotificacionesImpl(RepositorioNotificacion repoNotificaciones, RepositorioGrupo repoGrupo) {
		this.repoNotificaciones = repoNotificaciones;
		this.repoGrupo = repoGrupo;
	}

	@Override
	public List<Notificacion> obtenerNotificacionesPor(Long usuario) {
		List<Notificacion> notificaciones = repoNotificaciones.getNotificacionesPor(usuario);
		repoNotificaciones.marcarVistoDeUsuario(usuario);
		return notificaciones;
	}

	@Override
	public void notificarNuevoIngreso(Long id, Usuario nuevoIntegrante) {
		Grupo grupoObjetivo = repoGrupo.getGrupoByID(id);

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
	public void notificarEliminacionDeGrupo(Long id) {
		Grupo grupoObjetivo = repoGrupo.getGrupoByID(id);

		if (Check.isNull(grupoObjetivo))
			throw new GrupoInexistenteException("Imposible eliminar un grupo inexistente");

		for (Usuario integrante : grupoObjetivo.getListaDeUsuarios()) {
			String titulo = "El grupo \"" + grupoObjetivo.getNombre() + "\" ha sido eliminado";
			notificar(titulo, integrante);
		}
	}
	
	@Override
	public Boolean hayPendientes(Long idUsuario) {
		return !Check.isNull(repoNotificaciones.getExistePendiente(idUsuario));
	}

	private void notificar(String titulo, Usuario destinatario) {
		Notificacion mensaje = new Notificacion();
		mensaje.setUsuario(destinatario);
		mensaje.setTitulo(titulo);
		repoNotificaciones.guardarNotificacion(mensaje);
	}

}
