package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupos;
import ar.edu.unlam.tallerweb1.servicios.ServicioGruposImpl;

public class ControladorGruposTest {

	private static ControladorGrupos controller;
	private static ServicioGrupos service;

	@Before
	public void init() {
		service = mock(ServicioGruposImpl.class);
		controller = new ControladorGrupos(service);
	}

	@Test
	public void testQueAlPegarleALaURLTraigaLaVistaDelGrupo() {
		Long idGrupoBuscado = 1L;

		ModelAndView vistaObtenida = whenBuscoPorLaURLConElIDCorrecto(idGrupoBuscado);

		thenObtengoLaVistaYLosDatosDelGrupo(vistaObtenida);
	}

	@Test
	public void testQueAlBuscarUnGrupoInexistenteVolvamosAlIndex() {
		Long idGrupoInexistente = 2L;

		ModelAndView vistaObtenida = whenBuscoPorLaURLConElIDIncorrecto(idGrupoInexistente);

		thenMeRedirigeAlIndex(vistaObtenida);
	}

	@Test
	public void testQuePodamosEditarDatosDelGrupo() {
		Long idGrupoBuscado = 1L;
		Grupo formulario = givenCreamosNuevoGrupo("Basica 1 pepe", "Grupo de basica 1 pepe", 3, false);

		ModelAndView cambiosRealizados = whenCargoLaModificacionDeLosDatos(idGrupoBuscado, formulario);

		thenSusDatosSeCambian(cambiosRealizados);
	}

	@Test
	public void testQuePodamosEliminarUnGrupo() {
		Long idGrupoBuscado = 1L;

		ModelAndView postGrupoEliminado = whenEliminoElGrupo(idGrupoBuscado);

		thenElGrupoYaNoExiste(postGrupoEliminado);
	}

	/* Metodos Auxiliares */

	private ModelAndView whenEliminoElGrupo(Long idGrupoBuscado) {
		return controller.eliminarGrupo(idGrupoBuscado);
	}

	private void thenElGrupoYaNoExiste(ModelAndView postGrupoEliminado) {
		assertThat(postGrupoEliminado.getViewName()).isEqualTo("redirect:/");
		assertThat(postGrupoEliminado.getModel().get("mensaje")).isEqualTo("Grupo eliminado con exito!");
	}

	private void thenSusDatosSeCambian(ModelAndView cambiosRealizados) {
		assertThat(cambiosRealizados.getModel().get("mensaje")).isEqualTo("Datos actualizados");
	}

	private ModelAndView whenCargoLaModificacionDeLosDatos(Long idGrupoBuscado, Grupo formulario) {
		return controller.cambiarDatosGrupo(idGrupoBuscado, formulario);
	}

	private Grupo givenCreamosNuevoGrupo(String nombre, String desc, Integer cantidad, Boolean privado) {
		Grupo nuevoGrupo = new Grupo();

		nuevoGrupo.setId(1L);
		nuevoGrupo.setNombre(nombre);
		nuevoGrupo.setDescripcion(desc);
		nuevoGrupo.setCtdMaxima(cantidad);
		nuevoGrupo.setPrivado(privado);

		return nuevoGrupo;
	}

	private ModelAndView whenBuscoPorLaURLConElIDCorrecto(Long idGrupoBuscado) {
		when(service.buscarGrupoPorID(idGrupoBuscado)).thenReturn(new Grupo());

		return controller.buscarGrupo(idGrupoBuscado);
	}

	private ModelAndView whenBuscoPorLaURLConElIDIncorrecto(Long idGrupoInexistente) {
		when(service.buscarGrupoPorID(idGrupoInexistente)).thenReturn(null);

		return controller.buscarGrupo(idGrupoInexistente);
	}

	private void thenObtengoLaVistaYLosDatosDelGrupo(ModelAndView vistaObtenida) {
		assertThat(vistaObtenida.getViewName()).isEqualTo("vistaGrupo");
		assertThat(vistaObtenida.getModel()).isNotNull();
	}

	private void thenMeRedirigeAlIndex(ModelAndView vistaObtenida) {
		assertThat(vistaObtenida.getViewName()).isEqualTo("redirect:/");
	}

}
