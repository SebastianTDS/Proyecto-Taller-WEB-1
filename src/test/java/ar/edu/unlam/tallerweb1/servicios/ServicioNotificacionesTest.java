package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacionImpl;

public class ServicioNotificacionesTest {

	private RepositorioNotificacion repository = mock(RepositorioNotificacionImpl.class);
	private ServicioNotificaciones service = new ServicioNotificacionesImpl(repository);

	@Test
	public void testQueSePuedanObtenerLasNotificacionesDeUnUsuario() {
		Long usuario = 1L;
		
		List<Notificacion> notificaciones = whenBuscoSusNotificaciones(usuario);
		
		thenObtengoTodasSusNotificaciones(notificaciones);
	}

	private void thenObtengoTodasSusNotificaciones(List<Notificacion> notificaciones) {
		assertThat(notificaciones).isNotNull();
	}

	private List<Notificacion> whenBuscoSusNotificaciones(Long usuario) {
		when(repository.getNotificacionesPor(usuario)).thenReturn(new ArrayList<Notificacion>());
		return service.obtenerNotificacionesPor(usuario);
	}

}
