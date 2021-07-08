package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dto.EventoDTO;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEventos;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;

@Service
@Transactional
public class ServicioEventosImpl implements ServicioEventos{

	private RepositorioEventos repoEventos;
	private RepositorioGrupo repoGrupos;

	@Autowired
	public ServicioEventosImpl(RepositorioEventos repoEventos, RepositorioGrupo repoGrupos) {
		this.repoEventos = repoEventos;
		this.repoGrupos = repoGrupos;
	}

	@Override
	public List<EventoDTO> buscarEventosPor(Long idGrupo) {
		List<Evento> eventos = repoEventos.buscarEventosPor(idGrupo);
		
		return eventos.stream()
				.map(evento -> EventoDTO.toDTO(evento))
				.collect(Collectors.toList());
	}

	@Override
	public Boolean cargarEvento(EventoDTO nuevoEvento, Long id) {
		Grupo objetivo = repoGrupos.getGrupoByID(id);
		
		if(objetivo == null)
			return false;
		
		if(LocalDateTime.parse(nuevoEvento.getEnd()).isBefore(LocalDateTime.parse(nuevoEvento.getStart())))
			return false;
		
		if(LocalDateTime.now().isAfter(LocalDateTime.parse(nuevoEvento.getEnd())) || LocalDateTime.now().isEqual(LocalDateTime.parse(nuevoEvento.getEnd())))
			return false;
		
		Evento evento = nuevoEvento.toEvento();
		evento.setGrupo(objetivo);
		
		repoEventos.guardarEvento(evento);
		return true;
	}


}
