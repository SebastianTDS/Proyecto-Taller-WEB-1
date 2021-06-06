package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;

public interface RepositorioNotificacion {

	List<Notificacion> getNotificacionesPor(Long idUsuario);

	void guardarNotificacion(Notificacion mensaje);

}
