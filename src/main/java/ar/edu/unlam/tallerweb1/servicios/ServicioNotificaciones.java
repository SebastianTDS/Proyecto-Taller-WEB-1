package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.dto.DatosDeUsuario;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;

public interface ServicioNotificaciones {

	public List<Notificacion> obtenerNotificacionesPor(Long usuario);

	public void notificarNuevoIngreso(Long id, DatosDeUsuario nuevoIntegrante);
	
}
