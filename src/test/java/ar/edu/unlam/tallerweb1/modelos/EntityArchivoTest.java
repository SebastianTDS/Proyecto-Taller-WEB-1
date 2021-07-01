package ar.edu.unlam.tallerweb1.modelos;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.util.enums.Turno;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

public class EntityArchivoTest extends SpringTest{
	private final Materia nuevaMateria = new Materia();
	private final Carrera nuevaCarrera = new Carrera();

	@Test
	@Transactional
	@Rollback
	public void testQueSePuedaPersistirUnArchivo() {
		Usuario usuario = givenUsuarioPersistido();
		Grupo grupo = givenQueExisteUnGrupoConCarreraYMateria();
		Long archivo = whenPersistimosArchivo(usuario,grupo);
		
		thenAlBuscarlaEnLaBaseExiste(archivo);
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
	private void thenAlBuscarlaEnLaBaseExiste(Long idArchivo) {
		Archivo encontrado = session().get(Archivo.class, idArchivo);
		assertThat(encontrado).isNotNull();
	}

	private Long whenPersistimosArchivo(Usuario usuario, Grupo grupo) {
		Archivo archivo=new Archivo();
		archivo.setNombre("foto.jpg");
		archivo.setUsuario(usuario);
		archivo.setGrupo(grupo);
		return (Long) session().save(archivo);
	}

	private Usuario givenUsuarioPersistido() {
		Usuario miUsuario = new Usuario();
		
		miUsuario.setEmail("marcelo@unlam.edu.ar");
		miUsuario.setPassword("1234");
		session().save(miUsuario);
		
		return miUsuario;
	}

}
