package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.HttpSessionTest;
import ar.edu.unlam.tallerweb1.modelo.Calificacion;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCalificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioCalificacionesImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioSolicitud;
import ar.edu.unlam.tallerweb1.servicios.ServicioSolicitudImpl;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class ControladorCalificacionesTest extends HttpSessionTest{

	private static ServicioCalificacion service = mock(ServicioCalificacionesImpl.class);
	private static ControladorCalificaciones controller = new ControladorCalificaciones(service);
	
	private static Usuario usuarioEjemplo = new Usuario();
	
	@Before
	public void init() {
		usuarioEjemplo.setId(1L);
	}
	
	@Test
	public void testQueSeCargueLaVistaDeCalificacionesDeUsuario () {
		givenExisteUnUsuarioEnSesion();
		
		ModelAndView vista = whenQueremosVerSusCalificaiones();
		
		thenObtenemosLaVistaDeseada(vista);
	}
	@Test
	public void testQueSePuedaCalificar () {
		Long idCalificacion = 1L;
		Long calificaion=100L;

		givenExisteUnUsuarioEnSesion();
		ModelAndView vista = whenCalificamos(idCalificacion,calificaion);

		thenLaCalificaionSeRealiza(vista,idCalificacion,calificaion);
	}

	/* Metodos Auxiliares */

	private void thenLaCalificaionSeRealiza(ModelAndView vista,Long idCalificacion,Long calificacion) {
		assertThat(vista.getViewName()).isEqualTo("redirect:/calificaciones");
		assertThat(vista.getModelMap().get("mensaje")).isEqualTo( "Calificaion realizada");
		verify(service, times(1)).calificar(1L,idCalificacion,calificacion);
	}

	private ModelAndView whenCalificamos(Long IDcalificaion, Long calificacion) {
		return controller.calificar( request(),IDcalificaion,calificacion);
	}


	@SuppressWarnings("unchecked")
	private void thenObtenemosLaVistaDeseada(ModelAndView vista) {
		List<Solicitud> solicitudes = (List<Solicitud>) vista.getModel().get("calificacionesPendientes");
		
		assertThat(vista.getViewName()).isEqualTo("vistaCalificaciones");
		assertThat(solicitudes).isNotNull();
		assertThat(solicitudes).hasSize(2);
	}

	private ModelAndView whenQueremosVerSusCalificaiones() {
		when(service.buscarCalificaciones(anyLong())).thenReturn(Arrays.asList(new Calificacion(), new Calificacion()));
		return controller.verCalificaciones(request());
	}

	private void givenExisteUnUsuarioEnSesion() {
		when(request().getSession().getAttribute("USUARIO")).thenReturn(usuarioEjemplo);
	}

}
