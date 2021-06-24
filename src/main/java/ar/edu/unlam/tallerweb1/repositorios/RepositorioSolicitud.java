package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Solicitud;

public interface RepositorioSolicitud {

	List<Solicitud> buscarSolicitudesPor(Long idUsuario);

	void cargarSolicitud(Solicitud nuevaSolicitud);

	void borrarSolicitud(Solicitud aprobada);

	Solicitud buscarSolicitudPor(Long idSolicitud, Long idUsuario);

	Solicitud getExistePendiente(Long idUsuario);

}
