package ar.edu.unlam.tallerweb1.modelos;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class EntityNotificacionTest extends SpringTest{

	@Test
	@Transactional
	@Rollback
	public void testQueSePuedaPersistirUnaNotificacion() {
		Usuario usuario = givenUsuarioPersistido();
		Long notificacion = whenPersistimosNotificacionDe(usuario);
		
		thenAlBuscarlaEnLaBaseExiste(notificacion);
		
	}

	private void thenAlBuscarlaEnLaBaseExiste(Long notificacion) {
		Notificacion encontrada = session().get(Notificacion.class, notificacion);
		assertThat(encontrada).isNotNull();
	}

	private Long whenPersistimosNotificacionDe(Usuario usuario) {
		Notificacion notif = new Notificacion();
		
		notif.setTitulo("Manolo se unio se unio a tu grupo");
		notif.setUsuario(usuario);
		
		return (Long) session().save(notif);
	}

	private Usuario givenUsuarioPersistido() {
		Usuario miUsuario = new Usuario();
		
		miUsuario.setEmail("seba@unlam.edu.ar");
		miUsuario.setPassword("1234");
		session().save(miUsuario);
		
		return miUsuario;
	}

}
