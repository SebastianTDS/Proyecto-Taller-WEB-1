package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dto.DatosDeUsuario;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;
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
		return repoNotificaciones.getNotificacionesPor(usuario);
	}

	@Override
	public void notificarNuevoIngreso(Long id, DatosDeUsuario nuevoIntegrante) {
		Grupo grupoObjetivo = repoGrupo.getGrupoByID(id);

		if (grupoObjetivo == null)
			throw new GrupoInexistenteException("Imposible unirse a grupo inexistente");

		for (Usuario integrante : grupoObjetivo.getListaDeUsuarios()) {
			Notificacion mensaje = new Notificacion();
			mensaje.setUsuario(integrante);

			if (integrante.getId() == nuevoIntegrante.getId())
				mensaje.setTitulo("Te has unido al grupo " + grupoObjetivo.getNombre());
			else
				mensaje.setTitulo(nuevoIntegrante.getNombre() + " se ha unido al grupo " + grupoObjetivo.getNombre());

			repoNotificaciones.guardarNotificacion(mensaje);
		}
	}

}
