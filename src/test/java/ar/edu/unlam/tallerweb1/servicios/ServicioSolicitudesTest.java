package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupoImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSolicitud;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSolicitudImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuarioImpl;
import ar.edu.unlam.tallerweb1.util.enums.TipoSolicitud;
import ar.edu.unlam.tallerweb1.util.exceptions.GrupoInexistenteException;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;
import ar.edu.unlam.tallerweb1.util.exceptions.YaEstoyEnElGrupo;

public class ServicioSolicitudesTest {

	private final RepositorioSolicitud repoSolis = mock(RepositorioSolicitudImpl.class);
	private final RepositorioGrupo repoGrupo = mock(RepositorioGrupoImpl.class);
	private final RepositorioUsuario repoUsuario = mock(RepositorioUsuarioImpl.class);
	private final ServicioNotificaciones servicioNotificaciones = mock(ServicioNotificacionesImpl.class);
	private final ServicioSolicitud service = new ServicioSolicitudImpl(repoSolis, repoGrupo, repoUsuario, servicioNotificaciones);
	
	@Test
	public void testQueSeObtenganTodasLasSolicitudesDeUsuario () {
		Long idUsuario = 1L;
		
		List<Solicitud> solicitudes = whenBuscamosSolicitudesPor(idUsuario);
		
		thenObtenemosTodasSusSolicitudes(solicitudes);
	}
	
	@Test
	public void testQueSePuedaSolicitarUnirseAGrupo () {
		Long idUsuario = 1L;
		Long idGrupo = 2L;
		
		whenUnUsuarioSolicitaUnirseAGrupo(idUsuario, idGrupo);
		
		thenSeEnviaSolicitud();
	}
	
	@Test(expected = GrupoInexistenteException.class)
	public void testQueNoSePuedaEnviarSolicitudAGrupoInexistente() {
		Long idUsuario = 1L;
		Long idGrupo = 2L;
		
		whenUnUsuarioSolicitaUnirseAGrupoInexistente(idUsuario, idGrupo);
	}
	
	@Test(expected = UsuarioNoEncontradoException.class)
	public void testQueNoPuedaEnviarSolicitudUsuarioInexistente() {
		Long idUsuario = 1L;
		Long idGrupo = 2L;
		
		whenUnUsuarioInexistenteSolicitaUnirseAGrupo(idUsuario, idGrupo);
	}
	
	@Test(expected = YaEstoyEnElGrupo.class)
	public void testQueNoSePuedaSolicitarUnirseAUnGrupoDelQueYaEsMiembro() {
		Grupo solicitado = givenGrupoConUsuario();
		
		whenUnUsuarioSolicitaUnirseAGrupoDelQueYaFormaParte(solicitado);
	}
	
	@Test
	public void testQueSePuedaAprobarSolicitudDeInclusion() {
		Grupo objetivo = givenUnGrupo();
		Solicitud aprobada = givenUnaSolicitud(TipoSolicitud.INCLUSION_GRUPO, objetivo);

		whenIntentamosAprobarSolicitud(aprobada);
		
		thenElSolicitanteEsAprobadoYLaSolicitudSeLimpia(aprobada);
	}
	
	@Test
	public void testQueSePuedaRechazarSolicitud () {
		Solicitud aprobada = givenUnaSolicitud(TipoSolicitud.INCLUSION_GRUPO, null);
		
		whenRechazamosSolicitud(aprobada);
		
		thenSeLimpiaLaSolicitudDeLaBaseDeDatos(aprobada);
	}
	
	private void whenUnUsuarioSolicitaUnirseAGrupoDelQueYaFormaParte(Grupo solicitado) {
		when(repoGrupo.getGrupoByID(solicitado.getId())).thenReturn(solicitado);
		when(repoUsuario.getUsuarioByID(solicitado.getAdministrador().getId())).thenReturn(solicitado.getAdministrador());
		service.solicitarInclusionAGrupo(solicitado.getId(), solicitado.getAdministrador().getId());
	}

	private Grupo givenGrupoConUsuario() {
		Grupo grupo = new Grupo();
		Usuario usuario = new Usuario();
		
		usuario.setId(1L);
		grupo.setAdministrador(usuario);
		grupo.setId(1L);
		
		grupo.agregarUsuarioAlGrupo(usuario);
		
		return grupo;
	}

	private void thenSeLimpiaLaSolicitudDeLaBaseDeDatos(Solicitud aprobada) {
		verify(repoSolis, times(1)).borrarSolicitud(aprobada);
	}

	private void whenRechazamosSolicitud(Solicitud aprobada) {
		when(repoSolis.buscarSolicitudPor(aprobada.getId(), aprobada.getOrigen().getId())).thenReturn(aprobada);
		service.rechazarSolicitud(aprobada.getId(), aprobada.getOrigen().getId());
	}

	private Grupo givenUnGrupo() {
		Grupo objetivo = new Grupo();
		objetivo.setId(1L);
		objetivo.setCantidadMax(2);
		return objetivo;
	}

	private void thenElSolicitanteEsAprobadoYLaSolicitudSeLimpia(Solicitud aprobada) {
		verify(repoGrupo, times(1)).actualizarGrupo(aprobada.getObjetivo());
		verify(repoSolis, times(1)).borrarSolicitud(aprobada);
		verify(servicioNotificaciones, times(1)).notificarNuevoIngreso(aprobada.getObjetivo().getId(), aprobada.getOrigen());
	}

	private void whenIntentamosAprobarSolicitud(Solicitud aprobada) {
		when(repoSolis.buscarSolicitudPor(aprobada.getId(), aprobada.getOrigen().getId())).thenReturn(aprobada);
		service.aprobarSolicitud(aprobada.getId(), aprobada.getOrigen().getId());
	}

	private void whenUnUsuarioInexistenteSolicitaUnirseAGrupo(Long idUsuario, Long idGrupo) {
		when(repoGrupo.getGrupoByID(idGrupo)).thenReturn(new Grupo());
		when(repoUsuario.getUsuarioByID(idUsuario)).thenReturn(null);
		service.solicitarInclusionAGrupo(idGrupo, idUsuario);
	}

	private void whenUnUsuarioSolicitaUnirseAGrupoInexistente(Long idUsuario, Long idGrupo) {
		when(repoGrupo.getGrupoByID(idGrupo)).thenReturn(null);
		when(repoUsuario.getUsuarioByID(idUsuario)).thenReturn(new Usuario());
		service.solicitarInclusionAGrupo(idGrupo, idUsuario);
	}

	private void thenSeEnviaSolicitud() {
		verify(repoSolis, times(1)).cargarSolicitud(anyObject());
	}

	private void whenUnUsuarioSolicitaUnirseAGrupo(Long idUsuario, Long idGrupo) {
		when(repoGrupo.getGrupoByID(idGrupo)).thenReturn(new Grupo());
		when(repoUsuario.getUsuarioByID(idUsuario)).thenReturn(new Usuario());
		service.solicitarInclusionAGrupo(idGrupo, idUsuario);
	}

	private void thenObtenemosTodasSusSolicitudes(List<Solicitud> solicitudes) {
		assertThat(solicitudes).hasSize(2);
	}

	private List<Solicitud> whenBuscamosSolicitudesPor(Long idUsuario) {
		when(repoSolis.buscarSolicitudesPor(idUsuario)).thenReturn(Arrays.asList(new Solicitud(), new Solicitud()));
		return service.buscarSolicitudes(idUsuario);
	}
	
	private Solicitud givenUnaSolicitud(TipoSolicitud tipoSolicitud, Grupo grupo) {
		Solicitud aprobada = new Solicitud();
		
		aprobada.setId(1L);
		aprobada.setDestino(new Usuario());
		aprobada.setOrigen(new Usuario());
		aprobada.setObjetivo(grupo);
		aprobada.setTipo(tipoSolicitud);
		
		return aprobada;
	}

}
