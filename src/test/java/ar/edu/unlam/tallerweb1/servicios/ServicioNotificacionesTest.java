package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupoImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacionImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSolicitud;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSolicitudImpl;

public class ServicioNotificacionesTest {

	private RepositorioNotificacion repositoryNt = mock(RepositorioNotificacionImpl.class);
	private RepositorioGrupo repositoryGr = mock(RepositorioGrupoImpl.class);
	private RepositorioSolicitud repositorySl = mock(RepositorioSolicitudImpl.class);
	private ServicioNotificaciones service = new ServicioNotificacionesImpl(repositoryNt, repositoryGr, repositorySl);

	@Test
	public void testQueSePuedanObtenerLasNotificacionesDeUnUsuario() {
		Long usuario = 1L;
		
		List<Notificacion> notificaciones = whenBuscoSusNotificaciones(usuario);
		
		thenObtengoTodasSusNotificaciones(notificaciones);
	}
	
	@Test
	public void testQueSeNotifiqueCuandoUnUsuarioEntraAUnGrupo () {
		Grupo objetivo = givenExisteUnGrupo();
		Usuario nuevoIntegrante = givenNuevoIntegrante();
		
		whenIntentamosNotificarASusIntegrantes(objetivo, nuevoIntegrante);
		
		thenSeLesCargaLaNotificacion(objetivo.getListaDeUsuarios().size());
	}
	
	@Test
	public void testQueSeNotifiqueAlUsuarioCuandoSeEliminaUnoDeSusGrupos () {
		Grupo objetivo = givenExisteUnGrupo();
		
		whenIntentamosNotificarSuEliminacion(objetivo);
		
		thenSeLesCargaLaNotificacion(objetivo.getListaDeUsuarios().size());
	}

	@Test
	public void testQueSeNotifiqueAlUsuarioCuandoSeVaAlguienDelGrupo() {
		Grupo objetivo = givenExisteUnGrupo();
		Usuario nuevoIntegrante = givenNuevoIntegrante();
		objetivo= givenInsertoUsuarioEnGrupo(objetivo,nuevoIntegrante);
		whenNotificamosAlUsuarioQueAlguienSeFueDelGrupo(objetivo, nuevoIntegrante);

		thenSeLesCargaLaNotificacion(objetivo.getListaDeUsuarios().size());
	}



	@Test
	public void testQueTeAviseSiExistenNotificacionesNuevas () {
		Long usuario = 1L;
		
		Boolean hayNotificaciones = whenExisteAlmenosUnaNotificacionNueva(usuario);
		
		thenNosDevuelve(hayNotificaciones, usuario);
	}

	private void thenNosDevuelve(Boolean hayNotificaciones, Long usuario) {
		verify(repositorySl, times(1)).getExistePendiente(usuario);
		verify(repositoryNt, times(1)).getExistePendiente(usuario);
		assertThat(hayNotificaciones).isTrue();
	}

	private Boolean whenExisteAlmenosUnaNotificacionNueva(Long usuario) {
		when(repositoryNt.getExistePendiente(usuario)).thenReturn(null);
		when(repositorySl.getExistePendiente(usuario)).thenReturn(new Solicitud());
		return service.hayPendientes(usuario);
	}

	private void whenIntentamosNotificarSuEliminacion(Grupo objetivo) {
		when(repositoryGr.getGrupoByID(objetivo.getId())).thenReturn(objetivo);
		service.notificarEliminacionDeGrupo(objetivo.getId());
	}
	private void whenNotificamosAlUsuarioQueAlguienSeFueDelGrupo(Grupo objetivo, Usuario usuario) {
		when(repositoryGr.getGrupoByID(objetivo.getId())).thenReturn(objetivo);
		service.notificarRetiroDeGrupo(objetivo.getId(), usuario);
	}
	private void thenSeLesCargaLaNotificacion(Integer veces) {
		verify(repositoryNt, times(veces)).guardarNotificacion(anyObject());
	}

	private void whenIntentamosNotificarASusIntegrantes(Grupo objetivo, Usuario nuevoIntegrante) {
		when(repositoryGr.getGrupoByID(objetivo.getId())).thenReturn(objetivo);
		service.notificarNuevoIngreso(objetivo.getId(), nuevoIntegrante);
	}

	private Usuario givenNuevoIntegrante() {
		Usuario integrante = new Usuario();
		
		integrante.setId(1L);
		integrante.setNombre("Manolo");
		
		return integrante;
	}

	private Grupo givenExisteUnGrupo() {
		Grupo objetivo = new Grupo();
		objetivo.setId(1L);
		objetivo.setNombre("Equipo Dinamita");
		objetivo.setListaDeUsuarios(new HashSet<Usuario>(Arrays.asList(givenUnUsuario(5L), givenUnUsuario(4L))));
		return objetivo;
	}
	private Grupo givenInsertoUsuarioEnGrupo(Grupo objetivo, Usuario nuevoIntegrante) {
		objetivo.agregarUsuarioAlGrupo(nuevoIntegrante);
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
