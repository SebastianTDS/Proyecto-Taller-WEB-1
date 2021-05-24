package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupos;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGruposImpl;

public class ServicioGruposTest {

	private static ServicioGrupos service;
	private static RepositorioGrupos repository;

	@Before
	public void init() {
		repository = mock(RepositorioGruposImpl.class);
		service = new ServicioGruposImpl(repository);
	}

	@Test
	public void testQueDevuelvaElGrupoBuscado() {
		Long idBuscado = 1L;

		Grupo buscado = whenBuscoElGrupoPorSuID(idBuscado);

		thenObtengoElGrupo(buscado);
	}

	@Test
	public void testQueNoDevuelvaNadaSiNoExisteElGrupo() {
		Long idBuscado = 10L;

		Grupo buscado = whenBuscoElGrupoPorSuID(idBuscado);

		thenNoObtengoNingunGrupo(buscado);
	}

	@Test
	public void testQueSeModifiquenLosDatos() {
		Grupo buscado = givenExisteUnGrupo();
		Grupo nuevosDatos = givenDatosAActualizar();

		whenActualizamosLosDatos(buscado, nuevosDatos);

		thenLosDatosSeModifican(buscado);
	}

	@Test
	public void testQueSeRespetenElMaxYMinDeParticipantes() {
		Grupo buscado = givenExisteUnGrupo();
		Grupo nuevosDatos = givenDatosInvalidosAActualizar();

		whenActualizamosLosDatos(buscado, nuevosDatos);

		thenLosDatosNoSeModifican();
	}
	
	@Test
	public void testQueSePuedaEliminarUnGrupo() {
		Grupo buscado = givenExisteUnGrupo();
		
		whenIntentoEliminarGrupoExistente(buscado);
		
		thenElGrupoYaNoExiste(buscado);
	}

	private Grupo givenExisteUnGrupo() {
		Grupo nuevoGrupo = new Grupo();
		nuevoGrupo.setId(1L);
		return nuevoGrupo;
	}

	private void thenElGrupoYaNoExiste(Grupo buscado) {
		verify(repository, times(1)).eliminarGrupo(buscado);
	}

	private void whenIntentoEliminarGrupoExistente(Grupo buscado) {
		when(repository.getGrupoByID(buscado.getId())).thenReturn(buscado);
		service.eliminarGrupo(buscado.getId());
	}

	private void thenLosDatosNoSeModifican() {
		verify(repository, times(0)).actualizarGrupo(new Grupo());
	}

	private Grupo givenDatosInvalidosAActualizar() {
		Grupo nuevosDatos = new Grupo();

		nuevosDatos.setCtdMaxima(8);

		return nuevosDatos;
	}

	private void thenLosDatosSeModifican(Grupo buscado) {
		verify(repository, times(1)).actualizarGrupo(buscado);
	}

	private void whenActualizamosLosDatos(Grupo buscado, Grupo nuevosDatos) {
		when(repository.getGrupoByID(buscado.getId())).thenReturn(buscado);
		service.modificarGrupo(buscado.getId(), nuevosDatos);
	}

	private Grupo givenDatosAActualizar() {
		Grupo nuevosDatos = new Grupo();

		nuevosDatos.setNombre("Nuevo nombre de grupo");
		nuevosDatos.setDescripcion("Nueva descripcion");

		return nuevosDatos;
	}

	private void thenNoObtengoNingunGrupo(Grupo buscado) {
		assertThat(buscado).isNull();
	}

	private void thenObtengoElGrupo(Grupo buscado) {
		assertThat(buscado).isNotNull();
	}

	private Grupo whenBuscoElGrupoPorSuID(Long idBuscado) {
		when(service.buscarGrupoPorID(1L)).thenReturn(new Grupo());
		return service.buscarGrupoPorID(idBuscado);
	}
}
