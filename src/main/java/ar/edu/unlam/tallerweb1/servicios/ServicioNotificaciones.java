package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioNotificaciones {

	public List<Notificacion> obtenerNotificacionesPor(Long usuario);

	public void notificarNuevoIngreso(Long idGrupo, Usuario nuevoIntegrante);

	public void notificarEliminacionDeGrupo(Long idGrupo);

	Boolean hayPendientes(Long idUsuario);

}
