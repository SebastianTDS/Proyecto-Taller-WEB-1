package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Solicitud;

public interface ServicioSolicitud {

	List<Solicitud> buscarSolicitudes(Long idUsuario);

	void solicitarInclusionAGrupo(Long idGrupo, Long idUsuario);

	void aprobarSolicitud(Long idSolicitudAceptada, Long idUsuario);

	void rechazarSolicitud(Long idSolicitudRechazada, Long idUsuario);

}
