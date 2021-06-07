package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.dto.DatosDeUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuarioImpl;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;

public class ServicoLoginTest {

	private RepositorioUsuario repo = mock(RepositorioUsuarioImpl.class);
	private ServicioLogin service = new ServicioLoginImpl(repo);
	
	@Test
	public void testQueSeEncuentreElUsuarioBuscado() {
		DatosDeUsuario buscado = givenUnUsuarioABuscar();
		
		Usuario encontrado = whenBuscoAlUsuario(buscado);
		
		thenMeDevuelveUnUsuario(encontrado);
	}
	
	@Test(expected = UsuarioNoEncontradoException.class)
	public void testQueNoSeEncuentreElUsuarioBuscado() {
		DatosDeUsuario buscado = givenUnUsuarioABuscar();
		
		whenIntentoBuscarloLanzaExcepcion(buscado);
	}
	
	private void whenIntentoBuscarloLanzaExcepcion(DatosDeUsuario buscado) {
		when(repo.consultarUsuario(buscado.generarUsuario())).thenReturn(null);
		service.consultarUsuario(buscado);
	}

	private void thenMeDevuelveUnUsuario(Usuario encontrado) {
		assertThat(encontrado).isNotNull();
		assertThat(encontrado.getEmail()).isEqualTo("pepe@gmail.com");
	}

	private Usuario whenBuscoAlUsuario(DatosDeUsuario buscado) {
		when(repo.consultarUsuario(buscado.generarUsuario())).thenReturn(buscado.generarUsuario());
		return service.consultarUsuario(buscado);
	}

	private DatosDeUsuario givenUnUsuarioABuscar() {
		DatosDeUsuario buscado = new DatosDeUsuario();
		buscado.setEmail("pepe@gmail.com");
		buscado.setClave("1234");
		return buscado;
	}

	

}
