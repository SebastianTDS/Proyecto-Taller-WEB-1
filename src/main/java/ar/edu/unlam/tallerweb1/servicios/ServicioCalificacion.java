package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Calificacion;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;

import java.util.List;

public interface ServicioCalificacion {

	List<Calificacion> buscarCalificaciones(Long idUsuario);

	void crearCalificacion(Long idGrupo, Long idUsuario);

	void calificar(Long idUsuario, Long idcalificacion, Long calificacionRealizada);

    void crearCalificacionPorElinimarGrupo(Long idGrupo);
}
