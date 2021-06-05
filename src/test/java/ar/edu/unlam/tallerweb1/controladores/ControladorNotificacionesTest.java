package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.HttpSessionTest;
import ar.edu.unlam.tallerweb1.dto.DatosDeUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificaciones;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacionesImpl;

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
		
		thenNosRedirigeAlIndex(vista);
	}

	private void thenNosRedirigeAlIndex(ModelAndView vista) {
		assertThat(vista.getViewName()).isEqualTo("redirect:/login");
	}

	private void givenNoExisteUsuarioEnSesion() {
		when(request().getSession().getAttribute("Usuario")).thenReturn(null);
	}

	private void thenObtenemosLaVistaDeseada(ModelAndView vista) {
		assertThat(vista.getViewName()).isEqualTo("vistaNotificaciones");
		assertThat(vista.getModel()).isNotNull();
	}

	private ModelAndView whenSolicitamosVerSusNotificaciones() {
		return controller.verNotificaciones(request());
	}

	private void givenExisteUnUsuarioEnSesion() {
		when(request().getSession().getAttribute("Usuario")).thenReturn(new DatosDeUsuario());
	}

}
