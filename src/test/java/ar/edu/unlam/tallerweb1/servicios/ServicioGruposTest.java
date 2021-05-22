package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Grupo;

public class ServicioGruposTest {

	private static ServicioGrupos service;
	
	@Before
	public void init() {
		service = new ServicioGruposImpl();
	}
	
	@Test
	public void testQueDevuelvaElGrupoBuscado () {
		Long idBuscado = 1L;
		
		Grupo buscado = whenBuscoElGrupoPorSuID(idBuscado);
		
		thenObtengoElGrupo(buscado);
	}
	
	@Test
	public void testQueNoDevuelvaNadaSiNoExisteElGrupo () {
		Long idBuscado = 10L;
		
		Grupo buscado = whenBuscoElGrupoPorSuID(idBuscado);
		
		thenNoObtengoNingunGrupo(buscado);
	}

	private void thenNoObtengoNingunGrupo(Grupo buscado) {
		assertThat(buscado).isNull();
	}

	private void thenObtengoElGrupo(Grupo buscado) {
		assertThat(buscado).isNotNull();
	}

	private Grupo whenBuscoElGrupoPorSuID(Long idBuscado) {
		return service.buscarGrupoPorID(idBuscado);
	}
}
