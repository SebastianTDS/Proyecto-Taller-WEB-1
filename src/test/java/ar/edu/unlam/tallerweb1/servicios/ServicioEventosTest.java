package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.dto.EventoDTO;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEventos;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEventosImpl;

public class ServicioEventosTest {

	public final RepositorioEventos repository = mock(RepositorioEventosImpl.class);
	public final ServicioEventos service = new ServicioEventosImpl(repository);
	
	@Test
	public void testQueSePuedaObtenerLosEventosEnDTO () {
		Long idGrupo = 1L;
				
		List<EventoDTO> eventos = whenSolicitoEventosDeUnGrupo(idGrupo);
		
		thenObtengoLaListaDeEventos(eventos);
	}

	private List<EventoDTO> whenSolicitoEventosDeUnGrupo(Long idGrupo) {
		when(repository.buscarEventosPor(idGrupo)).thenReturn(givenListaDeEventos());
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
