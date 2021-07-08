package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dto.EventoDTO;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEventos;

@Service
@Transactional
public class ServicioEventosImpl implements ServicioEventos{

	private RepositorioEventos repoEventos;

	@Autowired
	public ServicioEventosImpl(RepositorioEventos repoEventos) {
		this.repoEventos = repoEventos;
	}

	@Override
	public List<EventoDTO> buscarEventosPor(Long idGrupo) {
		List<Evento> eventos = repoEventos.buscarEventosPor(idGrupo);
		
		return eventos.stream()
				.map(evento -> EventoDTO.toDTO(evento))
				.collect(Collectors.toList());
	}

}
