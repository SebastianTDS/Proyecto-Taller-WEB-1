package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.unlam.tallerweb1.servicios.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.util.exceptions.GrupoInexistenteException;
import ar.edu.unlam.tallerweb1.util.exceptions.LimiteDeUsuariosFueraDeRango;

public class ControladorGruposTest{

	private static ControladorGrupos controller;
	private static ServicioGrupo service;

	@Before
	public void init() {
		service = mock(ServicioGrupoImpl.class);
		controller = new ControladorGrupos(service, mock(ServicioNotificacionesImpl.class));
	}

	@Test
	public void testQueAlPegarleALaURLTraigaLaVistaDelGrupo() {
		Long idGrupoBuscado = 1L;
		ModelAndView vistaObtenida = whenBuscoPorLaURLConElIDCorrecto(idGrupoBuscado);
		thenObtengoLaVistaYLosDatosDelGrupo(vistaObtenida);
	}

	@Test(expected = GrupoInexistenteException.class)
	public void testQueAlBuscarUnGrupoInexistenteVolvamosAlIndex() {
		Long idGrupoInexistente = 2L;
		whenBuscoPorLaURLConElIDIncorrectoLanzaExcepcion(idGrupoInexistente);
	}

	@Test
	public void testQuePodamosEditarDatosDelGrupo() {
		Long idGrupoBuscado = 1L;
		DatosDeGrupo formulario = givenCompletamosFormulario(idGrupoBuscado);

		ModelAndView cambiosRealizados = whenCargoLaModificacionDeLosDatos(idGrupoBuscado, formulario);

		thenSusDatosSeCambian(cambiosRealizados);
	}

	@Test
	public void testQuePodamosEliminarUnGrupo() {
		Long idGrupoBuscado = 1L;

		ModelAndView postGrupoEliminado = whenEliminoElGrupo(idGrupoBuscado);

		thenElGrupoYaNoExiste(postGrupoEliminado);
	}

	@Test(expected = LimiteDeUsuariosFueraDeRango.class)
	public void testQueArrojeExcepcionAlModificarErroneamente() {
		Long idGrupoBuscado = 1L;
		DatosDeGrupo formulario = givenCompletamosFormularioErroneamente(idGrupoBuscado);

		whenIntentamosModificarGrupoLanzaException(idGrupoBuscado, formulario);

	}

	@Test
	public void testQuePodamosAccederAEdicionDeGrupo() {
		Long idGrupoBuscado = 1L;
		ModelAndView vistaObtenida = whenBuscoPorLaURLConElIDCorrectoAEditar(idGrupoBuscado);

		thenObtengoLaVistaYElModeloDelFormulario(vistaObtenida);
	}

	@Test(expected = GrupoInexistenteException.class)
	public void testQueNoPodamosAccederAEdicionDeGrupoInexistente() {
		Long idGrupoBuscado = 1L;
		whenBuscoPorLaURLDeEdicionConElIDIncorrectoLanzaExcepcion(idGrupoBuscado);

	}

	@Test
	public void testQuePodamosAccederAlForoDeGrupo() {
		Long idGrupoBuscado = 1L;
		ModelAndView vistaObtenida = whenBuscoPorLaURLConElIDCorrectoAlForo(idGrupoBuscado);

		thenObtengoLaVistaYElModeloDelForo(vistaObtenida);
	}




	/* Metodos Auxiliares */
	private ModelAndView whenBuscoPorLaURLConElIDCorrectoAlForo(Long idGrupoBuscado) {
		when(service.buscarGrupoPorID(idGrupoBuscado)).thenReturn(new Grupo());
		return controller.perfilDeGrupoForo(idGrupoBuscado);
	}
	private void thenObtengoLaVistaYElModeloDelForo(ModelAndView vistaObtenida) {
		assertThat(vistaObtenida.getViewName()).isEqualTo("vistaGrupo");
		assertThat(vistaObtenida.getModel().get("msj")).isNotNull();
	}

	private ModelAndView whenBuscoPorLaURLDeEdicionConElIDIncorrectoLanzaExcepcion(Long idGrupoBuscado) {
		doThrow(GrupoInexistenteException.class).when(service).buscarGrupoPorID(idGrupoBuscado);
		return controller.perfilDeGrupoEdicion(idGrupoBuscado);
	}

	private void thenObtengoLaVistaYElModeloDelFormulario(ModelAndView vistaObtenida) {
		assertThat(vistaObtenida.getViewName()).isEqualTo("vistaGrupo");
		assertThat(vistaObtenida.getModel().get("formulario")).isNotNull();
	}

	private ModelAndView whenBuscoPorLaURLConElIDCorrectoAEditar(Long idGrupoBuscado) {
		when(service.buscarGrupoPorID(idGrupoBuscado)).thenReturn(new Grupo());

		return controller.perfilDeGrupoEdicion(idGrupoBuscado);
	}

	private void whenIntentamosModificarGrupoLanzaException(Long idGrupoBuscado, DatosDeGrupo formulario) {
		doThrow(LimiteDeUsuariosFueraDeRango.class).when(service).modificarGrupo(idGrupoBuscado, formulario);
		controller.cambiarDatosGrupo(formulario);
	}

	private ModelAndView whenEliminoElGrupo(Long idGrupoBuscado) {
		return controller.eliminarGrupo(idGrupoBuscado);
	}

	private void thenElGrupoYaNoExiste(ModelAndView postGrupoEliminado) {
		assertThat(postGrupoEliminado.getViewName()).isEqualTo("redirect:/ir-a-home");
		assertThat(postGrupoEliminado.getModel().get("mensaje")).isEqualTo("Grupo eliminado con exito!");
	}

	private void thenSusDatosSeCambian(ModelAndView cambiosRealizados) {
		assertThat(cambiosRealizados.getModel().get("mensaje")).isEqualTo("Datos actualizados");
	}

	private ModelAndView whenCargoLaModificacionDeLosDatos(Long idGrupoBuscado, DatosDeGrupo formulario) {
		return controller.cambiarDatosGrupo(formulario);
	}

	private DatosDeGrupo givenCompletamosFormulario(Long idGrupoBuscado) {
		DatosDeGrupo nuevoGrupo = new DatosDeGrupo();

		nuevoGrupo.setId(idGrupoBuscado);
		nuevoGrupo.setNombre("Grupo de pepe");
		nuevoGrupo.setCantidadMax(2);

		return nuevoGrupo;
	}

	private DatosDeGrupo givenCompletamosFormularioErroneamente(Long idGrupoBuscado) {
		DatosDeGrupo nuevoGrupo = new DatosDeGrupo();

		nuevoGrupo.setId(idGrupoBuscado);
		nuevoGrupo.setCantidadMax(8);

		return nuevoGrupo;
	}

	private ModelAndView whenBuscoPorLaURLConElIDCorrecto(Long idGrupoBuscado) {
		when(service.buscarGrupoPorID(idGrupoBuscado)).thenReturn(new Grupo());

		return controller.perfilDeGrupo(idGrupoBuscado);
	}

	private ModelAndView whenBuscoPorLaURLConElIDIncorrectoLanzaExcepcion(Long idGrupoInexistente) {
		doThrow(GrupoInexistenteException.class).when(service).buscarGrupoPorID(idGrupoInexistente);
		return controller.perfilDeGrupo(idGrupoInexistente);
	}

	private void thenObtengoLaVistaYLosDatosDelGrupo(ModelAndView vistaObtenida) {
		assertThat(vistaObtenida.getViewName()).isEqualTo("vistaGrupo");
		assertThat(vistaObtenida.getModel()).isNotNull();
	}

}
