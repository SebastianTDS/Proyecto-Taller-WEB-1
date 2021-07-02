package ar.edu.unlam.tallerweb1.modelos;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.util.enums.Turno;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

public class EntityMensajeTest extends SpringTest{
	private final Materia nuevaMateria = new Materia();
	private final Carrera nuevaCarrera = new Carrera();

	@Test
	@Transactional
	@Rollback
	public void testQueSePuedaPersistirUnMensaje() {
		Usuario usuario = givenUsuarioPersistido();
		Grupo grupo = givenQueExisteUnGrupoConCarreraYMateria();
		Long mensaje = whenPersistimosMensajeDe(usuario,grupo);
		
		thenAlBuscarlaEnLaBaseExiste(mensaje);
	}

	private Grupo givenQueExisteUnGrupoConCarreraYMateria() {
		Grupo nuevoGrupo = new Grupo();
		nuevoGrupo.setCantidadMax(2);
		nuevoGrupo.setDescripcion("Desc");
		nuevoGrupo.setNombre("Hola");
		nuevoGrupo.setCerrado(true);
		nuevoGrupo.setTurno(Turno.NOCHE);
		nuevoGrupo.setAdministrador(givenQueExisteUnUsuario());
		nuevaCarrera.setNombre("Desarrollo web");
		nuevaMateria.setNombre("Basica I");
		session().save(nuevaCarrera);
		session().save(nuevaMateria);
		nuevoGrupo.setCarrera(nuevaCarrera);
		nuevoGrupo.setMateria(nuevaMateria);
		session().save(nuevoGrupo);
		return nuevoGrupo;
	}
	private Usuario givenQueExisteUnUsuario() {
		Usuario usuario=new Usuario();
		usuario.setPassword("123");
		usuario.setEmail("casa");
		session().save(usuario);
		return usuario;
	}
	private void thenAlBuscarlaEnLaBaseExiste(Long mensaje) {
		Mensaje encontrado = session().get(Mensaje.class, mensaje);
		assertThat(encontrado).isNotNull();
	}

	private Long whenPersistimosMensajeDe(Usuario usuario, Grupo grupo) {
		Mensaje mensaje= new Mensaje();
		mensaje.setMensaje("Manolo se unio se unio a tu grupo");
		mensaje.setUsuario(usuario);
		mensaje.setGrupo(grupo);
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
