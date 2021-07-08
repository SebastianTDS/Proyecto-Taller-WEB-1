package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unlam.tallerweb1.dto.EventoDTO;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEventos;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;

@RestController
@RequestMapping(value = "/api/eventos")
public class EventosRESTController {
	
	private ServicioEventos servicioEventos;
	
	@Autowired
	public EventosRESTController(ServicioEventos servicioEventos) {
		this.servicioEventos = servicioEventos;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<EventoDTO> verEventos(HttpServletRequest request, @RequestParam Long grupo) {
		validarSesion(request);
		
		return servicioEventos.buscarEventosPor(grupo);
	}

	@RequestMapping(value = "/{id}/nuevo", method = RequestMethod.POST)
	public ResponseEntity<String> anotarEvento(HttpServletRequest request, @ModelAttribute EventoDTO nuevoEvento, @PathVariable Long id) {
		validarSesion(request);
		
		if(servicioEventos.cargarEvento(nuevoEvento, id))
			return new ResponseEntity<String>("Evento Cargado", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Fallo al cargar evento", HttpStatus.BAD_REQUEST);
		
	}
	
	private Usuario validarSesion(HttpServletRequest request) {
		Usuario objetivo = (Usuario) request.getSession().getAttribute("USUARIO");

		if (objetivo == null)
			throw new UsuarioNoEncontradoException("No existe un usuario logueado!");

		return objetivo;
	}

}
