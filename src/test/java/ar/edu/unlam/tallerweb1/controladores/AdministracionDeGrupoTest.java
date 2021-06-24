package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.HttpSessionTest;
import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupoImpl;
import ar.edu.unlam.tallerweb1.util.enums.Privacidad;
import ar.edu.unlam.tallerweb1.util.enums.Turno;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;

public class AdministracionDeGrupoTest extends HttpSessionTest{

	private static ControladorCreacionDeGrupo controller;
	private static ServicioGrupo service;

	@Before
	public void init() {
		service = mock(ServicioGrupoImpl.class);
		controller = new ControladorCreacionDeGrupo(service);
	}
	
	@Test
	public void testQueSeAsigneAdministradorAlCrearGrupo () {
		DatosDeGrupo formulario = givenDatosDeCreacionDeGrupo();
		Usuario administrador = givenUsuarioQueCreoGrupo();
		
		ModelAndView vista = whenIntentoCrearElGrupo(formulario, administrador);
		
		thenSeCreaYSeLeAsignaSuAdministrador(vista, formulario);
	}
	
	@Test(expected = UsuarioNoEncontradoException.class)
	public void testQueSoloUnUsuarioValidoPuedaCrearGrupo() {
		DatosDeGrupo formulario = givenDatosDeCreacionDeGrupo();
		
		whenIntentoCrearElGrupoSinUsuario(formulario);
	}

	private void whenIntentoCrearElGrupoSinUsuario(DatosDeGrupo formulario) {
		when(request().getSession().getAttribute("USUARIO")).thenReturn(null);
		doThrow(UsuarioNoEncontradoException.class).when(service).crearGrupo(formulario);
		
		controller.irALaVistaDeGrupoCreado(request(), formulario);
	}

	private void thenSeCreaYSeLeAsignaSuAdministrador(ModelAndView vista, DatosDeGrupo formulario) {
		assertThat(vista.getViewName()).isEqualTo("redirect:/grupos/1");
		assertThat(formulario.getAdministrador().getId()).isEqualTo(1L);
		assertThat(request().getSession().getAttribute("USUARIO")).isEqualTo(formulario.getAdministrador());
	}

	private ModelAndView whenIntentoCrearElGrupo(DatosDeGrupo formulario, Usuario administrador) {
		Grupo generado = formulario.crearGrupoAPartirDeDatosDeGrupo();
		generado.setId(1L);
		
		when(request().getSession().getAttribute("USUARIO")).thenReturn(administrador);
		when(service.crearGrupo(formulario)).thenReturn(generado);
		
		return controller.irALaVistaDeGrupoCreado(request(), formulario);
	}

	private Usuario givenUsuarioQueCreoGrupo() {
		Usuario admin = new Usuario();
		
		admin.setId(1L);
		
		return admin;
	}

	private DatosDeGrupo givenDatosDeCreacionDeGrupo() {
		DatosDeGrupo dto = new DatosDeGrupo();
		
		dto.setCantidadMax(2);
		dto.setNombre("Grupo de la Muerte");
		dto.setDescripcion("Grupo de la app");
		dto.setTurno(Turno.NOCHE);
		dto.setPrivacidad(Privacidad.CERRADO);
		dto.setMateria(1L);
		dto.setCarrera(1L);
		
		return dto;
	}

}
