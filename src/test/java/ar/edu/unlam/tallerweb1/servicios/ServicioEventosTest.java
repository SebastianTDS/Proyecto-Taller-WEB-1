package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.dto.EventoDTO;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEventos;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEventosImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupoImpl;

public class ServicioEventosTest {

	public final RepositorioEventos repoEventos = mock(RepositorioEventosImpl.class);
	public final RepositorioGrupo repoGrupo = mock(RepositorioGrupoImpl.class);
	public final ServicioEventos service = new ServicioEventosImpl(repoEventos, repoGrupo);
	
	@Test
	public void testQueSePuedaObtenerLosEventosEnDTO () {
		Long idGrupo = 1L;
				
		List<EventoDTO> eventos = whenSolicitoEventosDeUnGrupo(idGrupo);
		
		thenObtengoLaListaDeEventos(eventos);
	}
	
	@Test
	public void testQueSePuedaGuardarUnEvento () {
		Long idGrupo = 1L;
		EventoDTO nuevoEvento = givenEventoDTO("2021-07-09T10:00:00", "2021-07-09T16:00:00", "Almuerzo");
		
		whenQuieroCargarEvento(nuevoEvento, idGrupo);
		
		thenElEventoSeGuarda(nuevoEvento, idGrupo);
	}
	
	@Test
	public void testQueNoSeGuardeUnEventoSinGrupo () {
		Long idGrupo = 1L;
		EventoDTO nuevoEvento = givenEventoDTO("2021-07-09T10:00:00", "2021-07-09T16:00:00", "Almuerzo");
		
		whenQuieroCargarEventoConGrupoInvalido(nuevoEvento, idGrupo);
		
		thenElEventoNoSeGuarda(nuevoEvento, idGrupo);
	}
	
	@Test
	public void testQueNoSeGuardeUnEventoConFechasIncoherentes () {
		Long idGrupo = 1L;
		EventoDTO nuevoEvento = givenEventoDTO("2021-07-09T19:00:00", "2021-07-09T12:00:00", "Almuerzo");
		
		whenQuieroCargarEvento(nuevoEvento, idGrupo);
		
		thenElEventoNoSeGuarda(nuevoEvento, idGrupo);
	}
	
	@Test
	public void testQueNoSePuedaGuardarUnEventoPosteriorALaFecha () {
		Long idGrupo = 1L;
		EventoDTO nuevoEvento = givenEventoDTO("2021-07-06T19:00:00", LocalDateTime.now().toString(), "Almuerzo");
		
		whenQuieroCargarEvento(nuevoEvento, idGrupo);
		
		thenElEventoNoSeGuarda(nuevoEvento, idGrupo);
	}

	private void thenElEventoNoSeGuarda(EventoDTO nuevoEvento, Long idGrupo) {
		verify(repoEventos, times(0)).guardarEvento(anyObject());
	}

	private void whenQuieroCargarEventoConGrupoInvalido(EventoDTO nuevoEvento, Long idGrupo) {
		when(repoGrupo.getGrupoByID(idGrupo)).thenReturn(null);
		service.cargarEvento(nuevoEvento, idGrupo);
	}

	private void thenElEventoSeGuarda(EventoDTO nuevoEvento, Long idGrupo) {
		verify(repoEventos, times(1)).guardarEvento(anyObject());
	}

	private void whenQuieroCargarEvento(EventoDTO nuevoEvento, Long idGrupo) {
		when(repoGrupo.getGrupoByID(idGrupo)).thenReturn(new Grupo());
		service.cargarEvento(nuevoEvento, idGrupo);
	}

	private EventoDTO givenEventoDTO(String inicio, String fin, String titulo) {
		EventoDTO nuevoEvento = new EventoDTO();
		
		nuevoEvento.setStart(inicio);
		nuevoEvento.setEnd(fin);
		nuevoEvento.setTitle(titulo);
		
		return nuevoEvento;
	}

	private List<EventoDTO> whenSolicitoEventosDeUnGrupo(Long idGrupo) {
		when(repoEventos.buscarEventosPor(idGrupo)).thenReturn(givenListaDeEventos());
		return service.buscarEventosPor(idGrupo);
	}
	
	private void thenObtengoLaListaDeEventos(List<EventoDTO> eventos) {
		assertThat(eventos).isNotNull();
		assertThat(eventos).hasSize(3);
	}

	private List<Evento> givenListaDeEventos() {
		List<Evento> eventos = new ArrayList<Evento>();
		
		eventos.add(givenEvento(1L, "Reunion", "2021-07-07T10:00:00", "2021-07-07T12:00:00"));
		eventos.add(givenEvento(2L, "Almuerzo", "2021-07-08T10:00:00", "2021-07-08T12:00:00"));
		eventos.add(givenEvento(3L, "Presentacion", "2021-07-09T10:00:00", "2021-07-09T12:00:00"));
		
		return eventos;
	}

	private Evento givenEvento(Long id, String titulo, String inicio, String fin) {
		Evento evento = new Evento();
		
		evento.setId(id);
		evento.setTitulo(titulo);
		evento.setInicio(LocalDateTime.parse(inicio));
		evento.setFin(LocalDateTime.parse(fin));
		
		return evento;
	}
}
