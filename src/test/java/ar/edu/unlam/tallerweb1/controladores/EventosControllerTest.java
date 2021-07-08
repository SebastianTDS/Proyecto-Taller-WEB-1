package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ar.edu.unlam.tallerweb1.HttpSessionTest;
import ar.edu.unlam.tallerweb1.dto.EventoDTO;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEventos;
import ar.edu.unlam.tallerweb1.servicios.ServicioEventosImpl;

public class EventosControllerTest extends HttpSessionTest{

	public final ServicioEventos service = mock(ServicioEventosImpl.class);
	public final EventosRESTController controller = new EventosRESTController(service);
	
	@Test
	public void testQueObtengaListadoDeEventos () {
		Long idGrupo = 1L;
		
		List<EventoDTO> eventos = whenSolicitoEventosDeUnGrupo(idGrupo);
		
		thenObtengoLaListaDeEventos(eventos);
	}
	
	@Test
	public void testQueSePuedaGuardarUnEvento() {
		Long idGrupo = 1L;
		EventoDTO nuevoEvento = givenUnEvento();
		
		ResponseEntity<String> respuesta = whenSolicitoGuardarUnEvento(nuevoEvento, idGrupo);
		
		thenElEventoSePersiste(respuesta, nuevoEvento, idGrupo);
	}

	private void thenElEventoSePersiste(ResponseEntity<String> respuesta, EventoDTO nuevoEvento, Long idGrupo) {
		assertThat(respuesta).isNotNull();
		assertThat(respuesta.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(respuesta.getBody()).isEqualTo("Evento Cargado");
		verify(service, times(1)).cargarEvento(nuevoEvento, idGrupo);
	}

	private ResponseEntity<String> whenSolicitoGuardarUnEvento(EventoDTO nuevoEvento, Long idGrupo) {
		when(request().getSession().getAttribute("USUARIO")).thenReturn(new Usuario());
		return controller.anotarEvento(request(),nuevoEvento, idGrupo);
	}

	private EventoDTO givenUnEvento() {
		EventoDTO nuevoEvento = new EventoDTO();
		
		nuevoEvento.setStart("2021-07-09T10:00:00");
		nuevoEvento.setEnd("2021-07-09T16:00:00");
		nuevoEvento.setTitle("Almuerzo");
		
		return nuevoEvento;
	}

	private void thenObtengoLaListaDeEventos(List<EventoDTO> eventos) {
		assertThat(eventos).isNotNull();
		assertThat(eventos).hasSize(3);
	}

	private List<EventoDTO> whenSolicitoEventosDeUnGrupo(Long idGrupo) {
		when(service.buscarEventosPor(idGrupo)).thenReturn(givenListaDeEventos());
		return controller.verEventos(idGrupo);
	}

	private List<EventoDTO> givenListaDeEventos() {
		List<EventoDTO> eventos = new ArrayList<EventoDTO>();
		
		eventos.add(givenEvento(1L, "Reunion", "2021-07-07T10:00:00", "2021-07-07T12:00:00"));
		eventos.add(givenEvento(2L, "Almuerzo", "2021-07-08T10:00:00", "2021-07-08T12:00:00"));
		eventos.add(givenEvento(3L, "Presentacion", "2021-07-09T10:00:00", "2021-07-09T12:00:00"));
		
		return eventos;
	}

	private EventoDTO givenEvento(Long id, String titulo, String inicio, String fin) {
		EventoDTO evento = new EventoDTO();
		
		evento.setId(id.toString());
		evento.setTitle(titulo);
		evento.setStart(inicio);
		evento.setEnd(fin);
		
		return evento;
	}
	

}
