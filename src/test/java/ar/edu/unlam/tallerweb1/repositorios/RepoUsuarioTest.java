package ar.edu.unlam.tallerweb1.repositorios;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class RepoUsuarioTest extends SpringTest{
	
	@Autowired
	private RepositorioUsuario repository;

	@Test
	@Transactional
	@Rollback
	public void testQueSePuedaBuscarUsuarioPorCorreo() {
		Usuario solicitado = givenUnUsuario("manuel@pepe");
		
		Usuario encontrado = whenBuscamosUsuarioPorSuCorreo(solicitado.getEmail());
		
		thenEncontramosAlUsuarioBuscado(solicitado, encontrado);
		
	}

	private void thenEncontramosAlUsuarioBuscado(Usuario solicitado, Usuario encontrado) {
		assertThat(encontrado).isNotNull();
		assertThat(encontrado).isEqualTo(solicitado);
	}

	private Usuario whenBuscamosUsuarioPorSuCorreo(String email) {
		return repository.getUsuarioByEmail(email);
	}

	private Usuario givenUnUsuario(String email) {
		Usuario usuario = new Usuario();
		
		usuario.setEmail(email);
		usuario.setNombre("Pablo");
		usuario.setPassword("1234");
		
		session().save(usuario);
		
		return usuario;
	}

}
