package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupoImpl;
import ar.edu.unlam.tallerweb1.util.exceptions.GrupoInexistenteException;
import ar.edu.unlam.tallerweb1.util.exceptions.LimiteDeUsuariosFueraDeRango;

public class ServicioGruposTest {

	private static ServicioGrupo service;
	private static RepositorioGrupo repository;

	@Before
	public void init() {
		repository = mock(RepositorioGrupoImpl.class);
		service = new ServicioGrupoImpl(repository, null, null, null);
	}

	@Test
	public void testQueDevuelvaElGrupoBuscado() {
		Long idBuscado = 1L;

		Grupo buscado = whenBuscoElGrupoPorSuID(idBuscado);

		thenObtengoElGrupo(buscado);
	}

	@Test(expected = GrupoInexistenteException.class)
	public void testQueNoDevuelvaNadaSiNoExisteElGrupo() {
		Long idBuscado = 10L;

		whenBuscoElGrupoPorSuIDErroneoLanzaExepcion(idBuscado);
	}

	@Test
	public void testQueSeModifiquenLosDatos() {
		Grupo buscado = givenExisteUnGrupo();
		DatosDeGrupo nuevosDatos = givenDatosAActualizar(buscado.getId());

		whenIntentamosActualizamosLosDatos(buscado, nuevosDatos);

		thenLosDatosSeModifican(buscado);
	}

	@Test(expected = LimiteDeUsuariosFueraDeRango.class)
	public void testQueSeRespetenElMaxYMinDeParticipantes() {
		Grupo buscado = givenExisteUnGrupo();
		DatosDeGrupo nuevosDatos = givenDatosInvalidosAActualizar(buscado.getId());

		whenIntentamosActualizarLanzaExcepcion(buscado, nuevosDatos);
	}
	
	@Test(expected = GrupoInexistenteException.class)
	public void testQueSePuedaEliminarUnGrupo() {
		Grupo buscado = givenExisteUnGrupo();
		
		whenIntentoEliminarGrupoExistente(buscado);
	}

	private Grupo givenExisteUnGrupo() {
		Grupo nuevoGrupo = new Grupo();
		nuevoGrupo.setId(1L);
		return nuevoGrupo;
	}

	private void whenIntentoEliminarGrupoExistente(Grupo buscado) {
		when(repository.getGrupoByID(buscado.getId())).thenReturn(null);
		service.eliminarGrupo(buscado.getId());
	}

	private DatosDeGrupo givenDatosInvalidosAActualizar(Long idGrupo) {
		DatosDeGrupo nuevosDatos = new DatosDeGrupo();

		nuevosDatos.setId(idGrupo);
		nuevosDatos.setCantidadMax(8);

		return nuevosDatos;
	}
	
	private void whenIntentamosActualizamosLosDatos(Grupo buscado, DatosDeGrupo nuevosDatos) {
		when(repository.getGrupoByID(buscado.getId())).thenReturn(buscado);
		service.modificarGrupo(nuevosDatos);
	}

	private void thenLosDatosSeModifican(Grupo buscado) {
		verify(repository, times(1)).actualizarGrupo(buscado);
	}

	private void whenIntentamosActualizarLanzaExcepcion(Grupo buscado, DatosDeGrupo nuevosDatos) {
		when(repository.getGrupoByID(buscado.getId())).thenReturn(buscado);
		service.modificarGrupo(nuevosDatos);
	}

	private DatosDeGrupo givenDatosAActualizar(Long idGrupo) {
		DatosDeGrupo nuevosDatos = new DatosDeGrupo();

		nuevosDatos.setId(idGrupo);
		nuevosDatos.setNombre("Nuevo nombre de grupo");
		nuevosDatos.setDescripcion("Nueva descripcion");

		return nuevosDatos;
	}

	private void thenObtengoElGrupo(Grupo buscado) {
		assertThat(buscado).isNotNull();
	}

	private Grupo whenBuscoElGrupoPorSuID(Long idBuscado) {
		when(repository.getGrupoByID(1L)).thenReturn(new Grupo());
		return service.buscarGrupoPorID(idBuscado);
	}
	
	private Grupo whenBuscoElGrupoPorSuIDErroneoLanzaExepcion(Long idBuscado) {
		when(repository.getGrupoByID(1L)).thenReturn(null);
		return service.buscarGrupoPorID(idBuscado);
	}
}
