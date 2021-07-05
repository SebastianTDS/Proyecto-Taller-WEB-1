package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Calificacion;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;

import java.util.List;

public interface RepositorioCalificacion {
    List<Calificacion> buscarCalificacionesPor(Long idUsuario);

    void cargarCalificacion(Calificacion nuevaSolicitud);

    void borrarCalificacion(Calificacion enviada);

    Calificacion buscarCalificacionPor(Long idSolicitud, Long idUsuario);

    Calificacion getExistePendiente(Long idUsuario);
}
