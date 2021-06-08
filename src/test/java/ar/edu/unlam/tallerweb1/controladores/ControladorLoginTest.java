package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.HttpSessionTest;
import ar.edu.unlam.tallerweb1.dto.DatosDeUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioLoginImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacionesImpl;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;

public class ControladorLoginTest extends HttpSessionTest {

	private ServicioLogin service = mock(ServicioLoginImpl.class);
	private ControladorLogin controller = new ControladorLogin(service, mock(ServicioNotificacionesImpl.class));

	@Test
	public void testQuePodamosLoguearUnUsuario() {
		DatosDeUsuario usuario = givenCorreoYClaveDeUsuario();

		ModelAndView vista = whenIntentamosLoguearUsuario(usuario);

		thenIniciamosSesionYVamosAlHome(vista);

	}
	
	@Test(expected = UsuarioNoEncontradoException.class)
	public void testQueSiElUsuarioNoExisteLanceExcepcion () {
		DatosDeUsuario usuario = givenCorreoYClaveDeUsuario();

		whenUsuarioNoEsValidoLanzaExcepcion(usuario);
	}

	private void whenUsuarioNoEsValidoLanzaExcepcion(DatosDeUsuario usuario) {
		doThrow(UsuarioNoEncontradoException.class).when(service).consultarUsuario(usuario);
		controller.validarLogin(usuario, request());
	}

	private void thenIniciamosSesionYVamosAlHome(ModelAndView vista) {
		assertThat(vista.getViewName()).isEqualTo("redirect:/ir-a-home");
	}

	private ModelAndView whenIntentamosLoguearUsuario(DatosDeUsuario usuario) {
		when(service.consultarUsuario(usuario)).thenReturn(usuario.generarUsuario());
		return controller.validarLogin(usuario, request());
	}

	private DatosDeUsuario givenCorreoYClaveDeUsuario() {
		DatosDeUsuario usuario = new DatosDeUsuario();
		usuario.setEmail("pepe@hotmail.com");
		usuario.setClave("1234");
		return usuario;
	}

}
