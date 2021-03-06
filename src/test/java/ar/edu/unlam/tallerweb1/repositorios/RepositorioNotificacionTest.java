package ar.edu.unlam.tallerweb1.repositorios;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class RepositorioNotificacionTest extends SpringTest{

	@Autowired
	private RepositorioNotificacion repository;

	@Test
	@Transactional
	@Rollback
	public void testQuePuedaEncontrarLasNotificacionesDeUnUsuario() {
		Usuario manuela = givenExisteUnUsuario("Manuela");
		Usuario jorge = givenExisteUnUsuario("Jorge");
		
		givenExisteUnaNotificacion(manuela);
		givenExisteUnaNotificacion(jorge);
		givenExisteUnaNotificacion(manuela);
		
		List<Notificacion> notificaciones = whenBuscoNotificacionesDe(manuela);
		
		thenObtengoSusNotificaciones(notificaciones, 2);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testQueSeMarquenNotificacionesComoVistas() {
		Usuario manuela = givenExisteUnUsuario("Manuela");
		Usuario jorge = givenExisteUnUsuario("Jorge");
		
		givenExisteUnaNotificacion(manuela);
		givenExisteUnaNotificacion(jorge);
		givenExisteUnaNotificacion(manuela);
		
		whenUsuarioVeSusNotificaciones(manuela);
		
		List<Notificacion> notificaciones = whenBuscoNotificacionesDe(manuela);
		
		thenSusNotificacionesEstanVistas(notificaciones);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testQueEncuentreUnaNotificacionPendienteSiExiste() {
		Usuario manuela = givenExisteUnUsuario("Manuela");
		Usuario jorge = givenExisteUnUsuario("Jorge");
		
		givenExisteUnaNotificacion(manuela);
		givenExisteUnaNotificacion(jorge);
		givenExisteUnaNotificacion(manuela);
		
		Notificacion pendiente = whenBuscoNotificacionesNuevasDeUsuario(manuela);
		
		thenObtengoQueTiene(pendiente);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testNoTraigaNadaEnCasoDeQueNoHayanNuevasNotificaciones() {
		Usuario manuela = givenExisteUnUsuario("Manuela");
		Usuario jorge = givenExisteUnUsuario("Jorge");
		
		givenExisteUnaNotificacion(manuela);
		givenExisteUnaNotificacion(jorge);
		givenExisteUnaNotificacion(manuela);
		
		whenUsuarioVeSusNotificaciones(jorge);
		
		Notificacion pendiente = whenBuscoNotificacionesNuevasDeUsuario(jorge);
		
		thenObtengoQueNoTiene(pendiente);
	}

	private void thenObtengoQueNoTiene(Notificacion pendiente) {
		assertThat(pendiente).isNull();
	}

	private void thenObtengoQueTiene(Notificacion pendiente) {
		assertThat(pendiente).isNotNull();
	}

	private Notificacion whenBuscoNotificacionesNuevasDeUsuario(Usuario usuario) {
		return repository.getExistePendiente(usuario.getId());
	}

	private void thenSusNotificacionesEstanVistas(List<Notificacion> notificaciones) {
		for(Notificacion n : notificaciones) {
			assertThat(n.getVisto()).isTrue();
		}
	}

	private void whenUsuarioVeSusNotificaciones(Usuario usuario) {
		repository.marcarVistoDeUsuario(usuario.getId());
		session().clear();
	}

	private void thenObtengoSusNotificaciones(List<Notificacion> notificaciones, Integer resultadoEsperado) {
		assertThat(notificaciones).isNotNull();
		assertThat(notificaciones).hasSize(resultadoEsperado);
	}

	private List<Notificacion> whenBuscoNotificacionesDe(Usuario usuario) {
		return repository.getNotificacionesPor(usuario.getId());
	}

	private void givenExisteUnaNotificacion(Usuario usuario) {
		Notificacion noti = new Notificacion();
		
		noti.setTitulo(usuario.getEmail() + " se unio a tu grupo!");
		noti.setUsuario(usuario);
		
		repository.guardarNotificacion(noti);
	}

	private Usuario givenExisteUnUsuario(String nombre) {
		Usuario user = new Usuario();
		
		user.setEmail(nombre + "@unlam.edu.ar");
		session().save(user);
		
		return user;
	}

}
