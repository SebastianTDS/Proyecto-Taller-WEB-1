package ar.edu.unlam.tallerweb1.repositorios;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.util.enums.TipoSolicitud;

public class RepositorioSolicitudTest extends SpringTest {

	@Autowired
	private RepositorioSolicitud repository;
	
	@Test
	@Transactional
	@Rollback
	public void testQueSePuedaBuscarSolicitudes() {
		Usuario manuela = givenExisteUnUsuario("Manuela");
		Usuario jorge = givenExisteUnUsuario("Jorge");

		givenExisteUnaSolicitud(manuela);
		givenExisteUnaSolicitud(manuela);
		givenExisteUnaSolicitud(jorge);

		List<Solicitud> solicitudes = whenBuscamosLasSolicitudesDe(manuela);

		thenEncontramosTodasSusSolicitudes(solicitudes, 2);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testQueSePuedaRecuperarUnaSolicitudSegunSuIdYDestino () {
		Usuario manuela = givenExisteUnUsuario("Manuela");
		Usuario jorge = givenExisteUnUsuario("Jorge");

		Solicitud obtenida = givenExisteUnaSolicitud(manuela);
		givenExisteUnaSolicitud(manuela);
		givenExisteUnaSolicitud(jorge);

		Solicitud buscada = whenBuscamosLaSolicitud(obtenida, manuela);
		
		thenLaSolicitudEsLaRequerida(buscada, manuela);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testQueSePuedaLimpiarUnaSolicitud () {
		Usuario manuela = givenExisteUnUsuario("Manuela");
		Usuario jorge = givenExisteUnUsuario("Jorge");

		Solicitud solicitudALimpiar = givenExisteUnaSolicitud(manuela);
		givenExisteUnaSolicitud(manuela);
		givenExisteUnaSolicitud(jorge);

		givenEliminamosUnaSolicitud(solicitudALimpiar);
		List<Solicitud> solicitudes = whenBuscamosLasSolicitudesDe(manuela);

		thenEncontramosTodasSusSolicitudes(solicitudes, 1);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testQueEncuentreUnaSolicitudPendienteSiExiste() {
		Usuario manuela = givenExisteUnUsuario("Manuela");
		Usuario jorge = givenExisteUnUsuario("Jorge");
		
		givenExisteUnaSolicitud(manuela);
		givenExisteUnaSolicitud(jorge);
		givenExisteUnaSolicitud(manuela);
		
		Solicitud pendiente = whenBuscoSolicitudesNuevasDeUsuario(manuela);
		
		thenObtengoQueTiene(pendiente);
	}

	private void thenObtengoQueTiene(Solicitud pendiente) {
		assertThat(pendiente).isNotNull();
	}

	private Solicitud whenBuscoSolicitudesNuevasDeUsuario(Usuario manuela) {
		return repository.getExistePendiente(manuela.getId());
	}

	private void givenEliminamosUnaSolicitud(Solicitud solicitudALimpiar) {
		repository.borrarSolicitud(solicitudALimpiar);
	}

	private void thenLaSolicitudEsLaRequerida(Solicitud buscada, Usuario destino) {
		assertThat(buscada).isNotNull();
		assertThat(buscada.getDestino()).isEqualTo(destino);
	}

	private Solicitud whenBuscamosLaSolicitud(Solicitud obtenida, Usuario manuela) {
		return repository.buscarSolicitudPor(obtenida.getId(), manuela.getId());
	}

	private void thenEncontramosTodasSusSolicitudes(List<Solicitud> solicitudes, Integer tamanio) {
		assertThat(solicitudes).isNotNull();
		assertThat(solicitudes).hasSize(tamanio);
	}

	private List<Solicitud> whenBuscamosLasSolicitudesDe(Usuario manuela) {
		return repository.buscarSolicitudesPor(manuela.getId());
	}

	private Solicitud givenExisteUnaSolicitud(Usuario usuario) {
		Solicitud nuevaSolicitud = new Solicitud();
		Usuario origen = givenExisteUnUsuario("Origen");

		nuevaSolicitud.setDestino(usuario);
		nuevaSolicitud.setOrigen(origen);
		nuevaSolicitud.setTipo(TipoSolicitud.INCLUSION_GRUPO);

		session().save(nuevaSolicitud);
		
		return nuevaSolicitud;
	}

	private Usuario givenExisteUnUsuario(String nombre) {
		Usuario user = new Usuario();

		user.setEmail(nombre + "@unlam.edu.ar");
		session().save(user);

		return user;
	}
}
