package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupoImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuarioImpl;
import ar.edu.unlam.tallerweb1.util.enums.Permiso;
import ar.edu.unlam.tallerweb1.util.exceptions.GrupoInexistenteException;
import ar.edu.unlam.tallerweb1.util.exceptions.NoEsMiembroException;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioSinPermisosException;

public class ValidacionPermisosTest {

	private static ServicioGrupo service;
	private static RepositorioGrupo repoGrupo;
	private static RepositorioUsuario repoUsuario;

	@Before
	public void init() {
		repoGrupo = mock(RepositorioGrupoImpl.class);
		repoUsuario = mock(RepositorioUsuarioImpl.class);
		service = new ServicioGrupoImpl(repoGrupo, null, null, repoUsuario);
	}
	
	@Test(expected = UsuarioNoEncontradoException.class)
	public void testExcepcionSiNoExisteUsuario() {
		Usuario usuario = givenExisteUnUsuario();
		Grupo grupo = givenExisteUnGrupo();
		
		whenVerificamosPermisosConUsuarioInexistente(usuario, grupo);
	}
	
	@Test(expected = GrupoInexistenteException.class)
	public void testExcepcionSiNoExisteGrupo() {
		Usuario usuario = givenExisteUnUsuario();
		Grupo grupo = givenExisteUnGrupo();
		
		whenVerificamosPermisosConGrupoInexistente(usuario, grupo);
	}
	
	@Test(expected = NoEsMiembroException.class)
	public void testExcepcionSiUsuarioNoEsMiembro() {
		Usuario usuario = givenExisteUnUsuario();
		Grupo grupo = givenExisteUnGrupo();
		
		whenVerificamosPermisosPeroUsuarioNoEstaEnGrupo(usuario, grupo);
	}
	
	@Test(expected = UsuarioSinPermisosException.class)
	public void testExcepcionSiUsuarioNoTienePermisoDeModificacion() {
		Usuario usuario = givenExisteUnUsuario();
		Grupo grupo = givenExisteUnGrupoConUsuario(usuario);
		
		whenVerificamosPermisosPeroUsuarioNoLosTiene(usuario, grupo);
	}
	
	private void whenVerificamosPermisosPeroUsuarioNoLosTiene(Usuario usuario, Grupo grupo) {
		when(repoGrupo.getGrupoByID(grupo.getId())).thenReturn(grupo);
		when(repoUsuario.getUsuarioByID(usuario.getId())).thenReturn(usuario);
		
		service.validarPermiso(usuario.getId(), grupo.getId(), Permiso.MODIFICACION);
	}

	private Grupo givenExisteUnGrupoConUsuario(Usuario usuario) {
		Grupo nuevoGrupo = new Grupo();
		
		nuevoGrupo.setId(1L);
		nuevoGrupo.agregarUsuarioAlGrupo(usuario);
		
		return nuevoGrupo;
	}

	/* Metodos Auxiliares */
	
	private Grupo givenExisteUnGrupo() {
		Grupo nuevoGrupo = new Grupo();
		
		nuevoGrupo.setId(1L);
		
		return nuevoGrupo;
	}

	private Usuario givenExisteUnUsuario() {
		Usuario nuevoUsuario = new Usuario();
		
		nuevoUsuario.setId(1L);
		
		return nuevoUsuario;
	}
	
	private void whenVerificamosPermisosConUsuarioInexistente(Usuario usuario, Grupo grupo) {
		when(repoGrupo.getGrupoByID(grupo.getId())).thenReturn(grupo);
		when(repoUsuario.getUsuarioByID(usuario.getId())).thenReturn(null);
		
		service.validarPermiso(usuario.getId(), grupo.getId(), Permiso.VISTA);
	}
	
	private void whenVerificamosPermisosConGrupoInexistente(Usuario usuario, Grupo grupo) {
		when(repoGrupo.getGrupoByID(grupo.getId())).thenReturn(null);
		when(repoUsuario.getUsuarioByID(usuario.getId())).thenReturn(usuario);
		
		service.validarPermiso(usuario.getId(), grupo.getId(), Permiso.VISTA);
	}
	
	private void whenVerificamosPermisosPeroUsuarioNoEstaEnGrupo(Usuario usuario, Grupo grupo) {
		when(repoGrupo.getGrupoByID(grupo.getId())).thenReturn(grupo);
		when(repoUsuario.getUsuarioByID(usuario.getId())).thenReturn(usuario);
		
		service.validarPermiso(usuario.getId(), grupo.getId(), Permiso.VISTA);
		
	}


}
