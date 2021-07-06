package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.HttpSessionTest;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioSolicitud;
import ar.edu.unlam.tallerweb1.servicios.ServicioSolicitudImpl;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;

public class ControladorSolicitudesTest extends HttpSessionTest {

	private static ServicioSolicitud service = mock(ServicioSolicitudImpl.class);
	private static ControladorSolicitudes controller = new ControladorSolicitudes(service);

	private static Usuario usuarioEjemplo = new Usuario();

	@Before
	public void init() {
		usuarioEjemplo.setId(1L);
	}

	@Test
	public void testQueSeCargueLaVistaDeSolicitudesDeUsuario() {
		givenExisteUnUsuarioEnSesion();

		ModelAndView vista = whenQueremosVerSusSolicitudes();

		thenObtenemosLaVistaDeseada(vista);
	}

	@Test(expected = UsuarioNoEncontradoException.class)
	public void testQueNoSeCargueLaVistaSiUsuarioNoExiste() {
		givenNoExisteUnUsuarioEnSesion();
		whenQueremosVerSusSolicitudes();
	}

	@Test
	public void testQueSePuedaSolicitarUnirseAUnGrupo() {
		Long idGrupoSolicitado = 3L;

		givenExisteUnUsuarioEnSesion();
		ModelAndView vista = whenSolicitamosUnirnosAUnGrupo(idGrupoSolicitado);

		thenSeEnviaLaSolicitud(vista, idGrupoSolicitado);
	}

	@Test
	public void testQueSePuedaAceptarUnaSolicitud() {
		Long idSolicitudAceptada = 1L;

		givenExisteUnUsuarioEnSesion();
		ModelAndView vista = whenAceptamosSolicitud(idSolicitudAceptada);

		thenLaSolicitudEsAprobada(vista, idSolicitudAceptada);
	}

	@Test
	public void testQueSePuedaRechazarSolicitud() {
		Long idSolicitudRechazada = 2L;

		givenExisteUnUsuarioEnSesion();
		ModelAndView vista = whenRechazamosSolicitud(idSolicitudRechazada);

		thenLaSolicitudEsRechazada(vista, idSolicitudRechazada);
	}

	@Test
	public void testQueSePuedaInvitarUnUsuarioAUnGrupo() {
		String correo = "pepito@gmail.com";
		Long idGrupo = 1L;

		ModelAndView vista = whenInvitamosUnUsuarioAUnGrupo(correo, idGrupo);

		thenSeEnviaCorrectamente(vista, correo, idGrupo);
	}

	private void thenSeEnviaCorrectamente(ModelAndView vista, String correo, Long idGrupo) {
		verify(request().getSession(), atLeast(1)).getAttribute("USUARIO");
		verify(service, times(1)).invitarUsuario(usuarioEjemplo.getId(), correo, idGrupo);
		assertThat(vista).isNotNull();
		assertThat(vista.getViewName()).isEqualTo("redirect:/grupos/" + idGrupo);
	}

	private ModelAndView whenInvitamosUnUsuarioAUnGrupo(String correo, Long idGrupo) {
		when(request().getSession().getAttribute("USUARIO")).thenReturn(usuarioEjemplo);
		return controller.invitarAGrupo(request(), correo, idGrupo);
	}

	/* Metodos Auxiliares */

	private ModelAndView whenRechazamosSolicitud(Long idSolicitudRechazada) {
		return controller.rechazarUnaSolicitud(idSolicitudRechazada, request());
	}

	private void thenLaSolicitudEsRechazada(ModelAndView vista, Long idSolicitudRechazada) {
		assertThat(vista.getViewName()).isEqualTo("redirect:/solicitudes");
		assertThat(vista.getModelMap().get("mensaje")).isEqualTo("Solicitud Rechazada");
		verify(service, times(1)).rechazarSolicitud(idSolicitudRechazada, usuarioEjemplo.getId());
	}

	private void thenLaSolicitudEsAprobada(ModelAndView vista, Long idSolicitudAceptada) {
		assertThat(vista.getViewName()).isEqualTo("redirect:/solicitudes");
		assertThat(vista.getModelMap().get("mensaje")).isEqualTo("Solicitud Aceptada");
		verify(service, times(1)).aprobarSolicitud(idSolicitudAceptada, usuarioEjemplo.getId());
	}

	private ModelAndView whenAceptamosSolicitud(Long idSolicitudAceptada) {
		return controller.aceptarUnaSolicitud(idSolicitudAceptada, request());
	}

	private void thenSeEnviaLaSolicitud(ModelAndView vista, Long idGrupoSolicitado) {
		assertThat(vista.getViewName()).isEqualTo("redirect:/ir-a-home");
		verify(service, times(1)).solicitarInclusionAGrupo(idGrupoSolicitado, usuarioEjemplo.getId());
	}

	private ModelAndView whenSolicitamosUnirnosAUnGrupo(Long idGrupoSolicitado) {
		return controller.solicitarUnirseAGrupo(idGrupoSolicitado, request());
	}

	private void givenNoExisteUnUsuarioEnSesion() {
		when(request().getSession().getAttribute("USUARIO")).thenReturn(null);
	}

	@SuppressWarnings("unchecked")
	private void thenObtenemosLaVistaDeseada(ModelAndView vista) {
		List<Solicitud> solicitudes = (List<Solicitud>) vista.getModel().get("Solicitudes");

		assertThat(vista.getViewName()).isEqualTo("vistaSolicitudes");
		assertThat(solicitudes).isNotNull();
		assertThat(solicitudes).hasSize(2);
	}

	private ModelAndView whenQueremosVerSusSolicitudes() {
		when(service.buscarSolicitudes(anyLong())).thenReturn(Arrays.asList(new Solicitud(), new Solicitud()));
		return controller.verSolicitudes(request());
	}

	private void givenExisteUnUsuarioEnSesion() {
		when(request().getSession().getAttribute("USUARIO")).thenReturn(usuarioEjemplo);
	}

}
