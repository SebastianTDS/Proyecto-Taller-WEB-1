package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Calificacion;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.util.enums.TipoSolicitud;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioCalificacionTest extends SpringTest {

	@Autowired
	private RepositorioCalificacion repository;
	
	@Test
	@Transactional
	@Rollback
	public void testQueSePuedaBuscarCalificaciones() {
		Usuario manuela = givenExisteUnUsuario("Manuela");
		Usuario jorge = givenExisteUnUsuario("Jorge");

		givenExisteUnaCalificacion(manuela);
		givenExisteUnaCalificacion(manuela);
		givenExisteUnaCalificacion(jorge);

		List<Calificacion> calificacions = whenBuscamosLasCalificacionesDe(manuela);

		thenEncontramosTodasSusCalificaciones(calificacions, 2);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testQueSePuedaRecuperarUnaCalificacionSegunSuIdYDestino () {
		Usuario manuela = givenExisteUnUsuario("Manuela");
		Usuario jorge = givenExisteUnUsuario("Jorge");

		Calificacion calificacion = givenExisteUnaCalificacion(manuela);
		givenExisteUnaCalificacion(manuela);
		givenExisteUnaCalificacion(jorge);

		Calificacion buscada = whenBuscamosLaCañificacion(calificacion, manuela);

		thenLaCalificaionEsLaRequerida(buscada, manuela);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testQueSePuedaLimpiarUnaCalificaion () {
		Usuario manuela = givenExisteUnUsuario("Manuela");
		Usuario jorge = givenExisteUnUsuario("Jorge");

		Calificacion calificacionALimpiar = givenExisteUnaCalificacion(manuela);
		givenExisteUnaCalificacion(manuela);
		givenExisteUnaCalificacion(jorge);

		givenEliminamosUnaCalificacion(calificacionALimpiar);
		List<Calificacion> solicitudes = whenBuscamosLasCalificacionesDe(manuela);

		thenEncontramosTodasSusCalificaciones(solicitudes, 1);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testQueEncuentreUnaCalificacionPendienteSiExiste() {
		Usuario manuela = givenExisteUnUsuario("Manuela");
		Usuario jorge = givenExisteUnUsuario("Jorge");

		givenExisteUnaCalificacion(manuela);
		givenExisteUnaCalificacion(jorge);
		givenExisteUnaCalificacion(manuela);
		
		Calificacion pendiente = whenBuscoCalificacionesNuevasDeUsuario(manuela);
		
		thenObtengoQueTiene(pendiente);
	}

	private void thenObtengoQueTiene(Calificacion pendiente) {
		assertThat(pendiente).isNotNull();
	}

	private Calificacion whenBuscoCalificacionesNuevasDeUsuario(Usuario manuela) {
		return repository.getExistePendiente(manuela.getId());
	}

	private void givenEliminamosUnaCalificacion(Calificacion calificacionALimpiar) {
		repository.borrarCalificacion(calificacionALimpiar);
	}

	private void thenLaCalificaionEsLaRequerida(Calificacion buscada, Usuario destino) {
		assertThat(buscada).isNotNull();
		assertThat(buscada.getDestino()).isEqualTo(destino);
	}

	private Calificacion whenBuscamosLaCañificacion(Calificacion obtenida, Usuario manuela) {
		return repository.buscarCalificacionPor(obtenida.getId(), manuela.getId());
	}

	private void thenEncontramosTodasSusCalificaciones(List<Calificacion> calificaiones, Integer tamanio) {
		assertThat(calificaiones).isNotNull();
		assertThat(calificaiones).hasSize(tamanio);
	}

	private List<Calificacion> whenBuscamosLasCalificacionesDe(Usuario manuela) {
		return repository.buscarCalificacionesPor(manuela.getId());
	}

	private Calificacion givenExisteUnaCalificacion(Usuario usuario) {
		Calificacion calificacion=new Calificacion();

		Usuario origen = givenExisteUnUsuario("Origen");

		calificacion.setDestino(usuario);
		calificacion.setOrigen(origen);

		session().save(calificacion);
		
		return calificacion;
	}

	private Usuario givenExisteUnUsuario(String nombre) {
		Usuario user = new Usuario();

		user.setEmail(nombre + "@unlam.edu.ar");
		session().save(user);

		return user;
	}
}
