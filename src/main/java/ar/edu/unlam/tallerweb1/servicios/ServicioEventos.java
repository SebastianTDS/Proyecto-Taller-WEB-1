package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.dto.EventoDTO;

public interface ServicioEventos {

	List<EventoDTO> buscarEventosPor(Long idGrupo);

}
