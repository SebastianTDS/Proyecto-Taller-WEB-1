package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.dto.DatosDeUsuario;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupoImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacionImpl;

public class ServicioNotificacionesTest {

	private RepositorioNotificacion repositoryNt = mock(RepositorioNotificacionImpl.class);
	private RepositorioGrupo repositoryGr = mock(RepositorioGrupoImpl.class);
	private ServicioNotificaciones service = new ServicioNotificacionesImpl(repositoryNt, repositoryGr);

	@Test
	public void testQueSePuedanObtenerLasNotificacionesDeUnUsuario() {
		Long usuario = 1L;
		
		List<Notificacion> notificaciones = whenBuscoSusNotificaciones(usuario);
		
		thenObtengoTodasSusNotificaciones(notificaciones);
	}
	
	@Test
	public void testQueSeNotifiqueCuandoUnUsuarioEntraAUnGrupo () {
		Grupo objetivo = givenExisteUnGrupo();
		DatosDeUsuario nuevoIntegrante = givenNuevoIntegrante();
		
		whenIntentamosNotificarASusIntegrantes(objetivo, nuevoIntegrante);
		
		thenSeLesCargaLaNotificacion(objetivo.getIntegrantes().size());
	}

	private void thenSeLesCargaLaNotificacion(Integer veces) {
		verify(repositoryNt, times(veces)).guardarNotificacion(anyObject());
	}

	private void whenIntentamosNotificarASusIntegrantes(Grupo objetivo, DatosDeUsuario nuevoIntegrante) {
		when(repositoryGr.getGrupoByID(objetivo.getId())).thenReturn(objetivo);
		service.notificarNuevoIngreso(objetivo.getId(), nuevoIntegrante);
	}

	private DatosDeUsuario givenNuevoIntegrante() {
		DatosDeUsuario integrante = new DatosDeUsuario();
		
		integrante.setId(1L);
		integrante.setNombre("Manolo");
		
		return integrante;
	}

	private Grupo givenExisteUnGrupo() {
		Grupo objetivo = new Grupo();
		objetivo.setId(1L);
		objetivo.setNombre("Equipo Dinamita");
		objetivo.setIntegrantes(Arrays.asList(givenUnUsuario(5L), givenUnUsuario(4L)));
		return objetivo;
	}

	private Usuario givenUnUsuario(Long id) {
		Usuario integrante = new Usuario();
		integrante.setId(id);
		return integrante;
	}

	private void thenObtengoTodasSusNotificaciones(List<Notificacion> notificaciones) {
		assertThat(notificaciones).isNotNull();
	}

	private List<Notificacion> whenBuscoSusNotificaciones(Long usuario) {
		when(repositoryNt.getNotificacionesPor(usuario)).thenReturn(new ArrayList<Notificacion>());
		return service.obtenerNotificacionesPor(usuario);
	}

}
