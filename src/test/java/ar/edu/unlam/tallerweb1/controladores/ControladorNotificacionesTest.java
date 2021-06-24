package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.HttpSessionTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificaciones;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacionesImpl;

// Test validar pendientes
public class ControladorNotificacionesTest extends HttpSessionTest{

	private static ServicioNotificaciones service = mock(ServicioNotificacionesImpl.class);
	private static ControladorNotificaciones controller = new ControladorNotificaciones(service);
	
	@Test
	public void testQueSeCargueLaVistaDeNotificacionesDeUsuario() {
		givenExisteUnUsuarioEnSesion();
		
		ModelAndView vista = whenSolicitamosVerSusNotificaciones();
		
		thenObtenemosLaVistaDeseada(vista);
	}
	
	@Test
	public void testQueNoSePermitaVistaParaUsuarioInexistente() {
		givenNoExisteUsuarioEnSesion();
		
		ModelAndView vista = whenSolicitamosVerSusNotificaciones();
		
		thenNosRedirigeAlLogin(vista);
	}

	private void thenNosRedirigeAlLogin(ModelAndView vista) {
		assertThat(vista.getViewName()).isEqualTo("redirect:/ir-a-login");
	}

	private void givenNoExisteUsuarioEnSesion() {
		when(request().getSession().getAttribute("USUARIO")).thenReturn(null);
	}

	private void thenObtenemosLaVistaDeseada(ModelAndView vista) {
		assertThat(vista.getViewName()).isEqualTo("vistaNotificaciones");
		assertThat(vista.getModel()).isNotNull();
		verify(request().getSession(), times(1)).setAttribute("PENDIENTES", true);
	}

	private ModelAndView whenSolicitamosVerSusNotificaciones() {
		return controller.verNotificaciones(request());
	}

	private void givenExisteUnUsuarioEnSesion() {
		when(service.hayPendientes(anyLong())).thenReturn(true);
		when(request().getSession().getAttribute("USUARIO")).thenReturn(new Usuario());
	}

}
