package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Solicitud;

public interface RepositorioSolicitud {

	List<Solicitud> buscarSolicitudesPor(Long idUsuario);

	void cargarSolicitud(Solicitud nuevaSolicitud);

	Solicitud buscarSolicitudPor(Long idSolicitud, Long idUsuario);

	void borrarSolicitud(Solicitud aprobada);

}
