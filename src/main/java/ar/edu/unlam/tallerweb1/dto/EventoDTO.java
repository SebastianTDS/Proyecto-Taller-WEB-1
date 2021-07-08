package ar.edu.unlam.tallerweb1.dto;

import java.time.LocalDateTime;

import ar.edu.unlam.tallerweb1.modelo.Evento;

public class EventoDTO {

	private String id;
	private String title;
	private String start;
	private String end;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public static EventoDTO toDTO(Evento evento) {
		EventoDTO dto = new EventoDTO();
		
		dto.setId(evento.getId().toString());
		dto.setTitle(evento.getTitulo());
		dto.setStart(evento.getInicio().toString());
		dto.setEnd(evento.getFin().toString());
		
		return dto;
	}

	public Evento toEvento() {
		Evento evento = new Evento();
		
		evento.setTitulo(this.title);
		evento.setInicio(LocalDateTime.parse(this.start));
		evento.setFin(LocalDateTime.parse(this.end));
		
		return evento;
	}

}
