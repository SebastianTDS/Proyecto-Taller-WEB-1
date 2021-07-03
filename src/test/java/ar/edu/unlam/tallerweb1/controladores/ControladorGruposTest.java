package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import ar.edu.unlam.tallerweb1.HttpSessionTest;
import ar.edu.unlam.tallerweb1.dto.DatosDeMensaje;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;


import ar.edu.unlam.tallerweb1.HttpSessionTest;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.util.enums.Permiso;
import ar.edu.unlam.tallerweb1.util.exceptions.GrupoInexistenteException;
import ar.edu.unlam.tallerweb1.util.exceptions.LimiteDeUsuariosFueraDeRango;
import ar.edu.unlam.tallerweb1.util.exceptions.NoEsMiembroException;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioSinPermisosException;

public class ControladorGruposTest extends HttpSessionTest{

	private static ControladorGrupos controller;
	private static ServicioGrupo service;
	private static Usuario usuarioEjemplo = new Usuario();;

	@Before
	public void init() {
		service = mock(ServicioGrupoImpl.class);
		controller = new ControladorGrupos(service, mock(ServicioNotificacionesImpl.class),mock(ServicioMensajesImpl.class));
		usuarioEjemplo.setId(1L); 
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
	public void testQueElAdminPuedaEditarDatosDelGrupo() {
		Long idGrupoBuscado = 1L;
		DatosDeGrupo formulario = givenCompletamosFormulario(idGrupoBuscado);

		ModelAndView cambiosRealizados = whenCargoLaModificacionDeLosDatos(idGrupoBuscado, formulario);

		thenSusDatosSeCambian(cambiosRealizados);
	}

	@Test
	public void testQueElAdminPuedaEliminarSuGrupo() {
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
	
	@Test(expected = UsuarioSinPermisosException.class)
	public void testQueSiElUsuarioNoTienePermisosNoPuedaEditarGrupo () {
		Long idGrupoBuscado = 1L;
		DatosDeGrupo formulario = givenCompletamosFormulario(idGrupoBuscado);

		whenIntentoCargoLaModificacionDeLosDatos(idGrupoBuscado, formulario);
	}
	
	@Test(expected = NoEsMiembroException.class)
	public void testQueSiElUsuarioNoEsMiembroNoPuedaVerElGrupo() {
		Long idGrupoBuscado = 1L;
		whenBuscoGrupoExistentePeroSinPermiso(idGrupoBuscado);
	}
	
	@Test
	public void testQuePodamosAccederAlForoDeGrupo() {
		Long idGrupoBuscado = 1L;
		ModelAndView vistaObtenida = whenBuscoPorLaURLConElIDCorrectoAlForo(idGrupoBuscado);

		thenObtengoLaVistaYElModeloDelForo(vistaObtenida);
	}
  
	@Test
	public void testQuePodamosInsertarUnMensajeEnElGrupo() {
		Long idGrupoBuscado = 1L;
		DatosDeMensaje datosDeMensaje= givenCompletamosDeMensaje(idGrupoBuscado);

		ModelAndView vista = whenCargoLosDatosDeMensaje(idGrupoBuscado, datosDeMensaje);

		thenObtengoLaVistaYElModeloDelForoDespuesDeEnviarUnMsj(vista);
  }

	/* Metodos Auxiliares */
  
  private void whenBuscoGrupoExistentePeroSinPermiso(Long idGrupoBuscado) {
		Grupo objetivo = new Grupo();
		objetivo.setId(idGrupoBuscado);
		
		when(request().getSession().getAttribute("USUARIO")).thenReturn(usuarioEjemplo);
		when(service.buscarGrupoPorID(idGrupoBuscado)).thenReturn(objetivo);
		doThrow(NoEsMiembroException.class).when(service).validarPermiso(usuarioEjemplo.getId(), idGrupoBuscado, Permiso.VISTA);
		
		controller.perfilDeGrupo(idGrupoBuscado, request());
	}

	private void whenIntentoCargoLaModificacionDeLosDatos(Long idGrupoBuscado, DatosDeGrupo formulario) {
		when(request().getSession().getAttribute("USUARIO")).thenReturn(usuarioEjemplo);
		doThrow(UsuarioSinPermisosException.class).when(service).validarPermiso(usuarioEjemplo.getId(), idGrupoBuscado, Permiso.MODIFICACION);
		
		controller.cambiarDatosGrupo(formulario, request());
  }
  
	private void thenObtengoLaVistaYElModeloDelForoDespuesDeEnviarUnMsj(ModelAndView vistaObtenida) {
		assertThat(vistaObtenida.getViewName()).isEqualTo("redirect:/grupos/1/foro");
	}

	private ModelAndView whenCargoLosDatosDeMensaje(Long idGrupoBuscado, DatosDeMensaje datosDeMensaje) {
		givenUnUsuarioDeLaSesion();
		Usuario  usuario=(Usuario) request().getSession().getAttribute("USUARIO");
		return controller.insertarMensajeEnElForo(request(),datosDeMensaje);
	}

	private DatosDeMensaje givenCompletamosDeMensaje(Long idGrupoBuscado) {
		DatosDeMensaje datosDeMensaje=new DatosDeMensaje();
		datosDeMensaje.setId(1L);
		datosDeMensaje.setMensaje("MENSAJE 1");
		return datosDeMensaje;
	}
	private void givenUnUsuarioDeLaSesion() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		when(request().getSession().getAttribute("USUARIO")).thenReturn(usuario);
	}
  
	private ModelAndView whenBuscoPorLaURLConElIDCorrectoAlForo(Long idGrupoBuscado) {
		when(service.buscarGrupoPorID(idGrupoBuscado)).thenReturn(new Grupo());
		return controller.perfilDeGrupoForo(request(), idGrupoBuscado);
	}
  
	private void thenObtengoLaVistaYElModeloDelForo(ModelAndView vistaObtenida) {
		assertThat(vistaObtenida.getViewName()).isEqualTo("vistaGrupo");
		assertThat(vistaObtenida.getModel().get("msj")).isNotNull();
	}

	private ModelAndView whenBuscoPorLaURLDeEdicionConElIDIncorrectoLanzaExcepcion(Long idGrupoBuscado) {
		doThrow(GrupoInexistenteException.class).when(service).buscarGrupoPorID(idGrupoBuscado);
		when(request().getSession().getAttribute("USUARIO")).thenReturn(usuarioEjemplo);
		return controller.perfilDeGrupoEdicion(idGrupoBuscado, request());
	}

	private void thenObtengoLaVistaYElModeloDelFormulario(ModelAndView vistaObtenida) {
		assertThat(vistaObtenida.getViewName()).isEqualTo("vistaGrupo");
		assertThat(vistaObtenida.getModel().get("formulario")).isNotNull();
		verify(service, times(1)).validarPermiso(anyLong(), anyLong(), eq(Permiso.MODIFICACION));
	}

	private ModelAndView whenBuscoPorLaURLConElIDCorrectoAEditar(Long idGrupoBuscado) {
		when(request().getSession().getAttribute("USUARIO")).thenReturn(usuarioEjemplo);
		when(service.buscarGrupoPorID(idGrupoBuscado)).thenReturn(new Grupo());

		return controller.perfilDeGrupoEdicion(idGrupoBuscado, request());
	}

	private void whenIntentamosModificarGrupoLanzaException(Long idGrupoBuscado, DatosDeGrupo formulario) {
		when(request().getSession().getAttribute("USUARIO")).thenReturn(usuarioEjemplo);
		doThrow(LimiteDeUsuariosFueraDeRango.class).when(service).modificarGrupo(formulario);
		controller.cambiarDatosGrupo(formulario, request());
	}

	private ModelAndView whenEliminoElGrupo(Long idGrupoBuscado) {
		when(request().getSession().getAttribute("USUARIO")).thenReturn(usuarioEjemplo);
		return controller.eliminarGrupo(idGrupoBuscado, request());
	}

	private void thenElGrupoYaNoExiste(ModelAndView postGrupoEliminado) {
		assertThat(postGrupoEliminado.getViewName()).isEqualTo("redirect:/ir-a-home");
		assertThat(postGrupoEliminado.getModel().get("mensaje")).isEqualTo("Grupo eliminado con exito!");
	}

	private void thenSusDatosSeCambian(ModelAndView cambiosRealizados) {
		assertThat(cambiosRealizados.getModel().get("mensaje")).isEqualTo("Datos actualizados");
		verify(service, times(1)).validarPermiso(eq(usuarioEjemplo.getId()), anyLong() , eq(Permiso.MODIFICACION));
	}

	private ModelAndView whenCargoLaModificacionDeLosDatos(Long idGrupoBuscado, DatosDeGrupo formulario) {
		when(request().getSession().getAttribute("Usuario")).thenReturn(usuarioEjemplo);
		return controller.cambiarDatosGrupo(formulario, request());
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
		when(request().getSession().getAttribute("USUARIO")).thenReturn(usuarioEjemplo);
		when(service.buscarGrupoPorID(idGrupoBuscado)).thenReturn(new Grupo());

		return controller.perfilDeGrupo(idGrupoBuscado, request());
	}

	private ModelAndView whenBuscoPorLaURLConElIDIncorrectoLanzaExcepcion(Long idGrupoInexistente) {
		when(request().getSession().getAttribute("USUARIO")).thenReturn(usuarioEjemplo);
		doThrow(GrupoInexistenteException.class).when(service).buscarGrupoPorID(idGrupoInexistente);
		return controller.perfilDeGrupo(idGrupoInexistente, request());
	}

	private void thenObtengoLaVistaYLosDatosDelGrupo(ModelAndView vistaObtenida) {
		assertThat(vistaObtenida.getViewName()).isEqualTo("vistaGrupo");
		assertThat(vistaObtenida.getModel()).isNotNull();
	}

}
