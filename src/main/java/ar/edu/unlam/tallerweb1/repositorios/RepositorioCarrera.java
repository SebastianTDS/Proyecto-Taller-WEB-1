package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Carrera;

import java.util.List;

public interface RepositorioCarrera {


    List<Carrera> buscarTodasLasCarreras();

    Carrera buscarCarreraPorId(Long id);
}
