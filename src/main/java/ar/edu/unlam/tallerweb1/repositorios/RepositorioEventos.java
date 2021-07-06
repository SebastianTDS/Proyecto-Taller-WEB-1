package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Evento;

public interface RepositorioEventos {

	List<Evento> buscarEventosPor(Long idGrupo);

}
