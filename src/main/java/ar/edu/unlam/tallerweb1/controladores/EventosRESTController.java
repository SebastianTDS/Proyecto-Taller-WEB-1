package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unlam.tallerweb1.dto.EventoDTO;
import ar.edu.unlam.tallerweb1.servicios.ServicioEventos;

@RestController
@RequestMapping(value = "/api")
public class EventosRESTController {
	
	private ServicioEventos service;
	
	@Autowired
	public EventosRESTController(ServicioEventos service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/eventos")
	public List<EventoDTO> verEventos(@RequestParam Long grupo) {
		return service.buscarEventosPor(grupo);
	}

}
