package ar.edu.unlam.tallerweb1.modelos;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

public class EntityMensajeTest extends SpringTest{

	@Test
	@Transactional
	@Rollback
	public void testQueSePuedaPersistirUnMensaje() {
		Usuario usuario = givenUsuarioPersistido();
		Long mensaje = whenPersistimosMensajeDe(usuario);
		
		thenAlBuscarlaEnLaBaseExiste(mensaje);
		
	}

	private void thenAlBuscarlaEnLaBaseExiste(Long mensaje) {
		Mensaje encontrado = session().get(Mensaje.class, mensaje);
		assertThat(encontrado).isNotNull();
	}

	private Long whenPersistimosMensajeDe(Usuario usuario) {
		Mensaje mensaje= new Mensaje();
		mensaje.setMensaje("Manolo se unio se unio a tu grupo");
		mensaje.setUsuario(usuario.getId());
		return (Long) session().save(mensaje);
	}

	private Usuario givenUsuarioPersistido() {
		Usuario miUsuario = new Usuario();
		
		miUsuario.setEmail("marcelo@unlam.edu.ar");
		miUsuario.setPassword("1234");
		session().save(miUsuario);
		
		return miUsuario;
	}

}
