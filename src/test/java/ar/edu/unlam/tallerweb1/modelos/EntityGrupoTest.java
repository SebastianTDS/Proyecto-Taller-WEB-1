package ar.edu.unlam.tallerweb1.modelos;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Carrera;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Materia;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.util.enums.Turno;

public class EntityGrupoTest extends SpringTest{

	@Test @Transactional @Rollback
	public void testQueSePuedaAsignarUnaMateriaAlGrupo () {
		Long id = whenPersistimosGrupoConSusDatos();
		
		thenLaMateriaSeAsignaAlGrupo(id);
	}
	
	@Test @Transactional @Rollback
	public void testQueSePuedaPersistirUnGrupo() {
		Long id = whenPersistimosGrupoConSusDatos();
		
		thenElGrupoSePersisteEnLaDB(id);
	}
	
	@Test @Transactional @Rollback
	public void testQueSeAsigneLaCarreraAlGrupo () {
		Long id = whenPersistimosGrupoConSusDatos();
		
		thenLaCarreraSeAsignaAlGrupo(id);
	}
	
	private void thenLaCarreraSeAsignaAlGrupo(Long id) {
		Grupo grupoEncontrado = session().get(Grupo.class, id);
		Carrera carreraEncontrada = session().get(Carrera.class, id);
		
		assertThat(grupoEncontrado).isNotNull();
		assertThat(carreraEncontrada).isNotNull();
		assertThat(grupoEncontrado.getCarrera().getNombre()).isEqualTo("Desarrollo WEB");
	}

	private void thenElGrupoSePersisteEnLaDB(Long id) {
		Grupo grupoEncontrado = session().get(Grupo.class, id);
		
		assertThat(grupoEncontrado).isNotNull();
		assertThat(grupoEncontrado.getNombre()).isEqualTo("Hola");
	}

	private Long whenPersistimosGrupoConSusDatos() {
		Grupo nuevoGrupo = givenExisteUnGrupo();
		Carrera nuevaCarrera = givenExisteUnaCarrera();
		Materia nuevaMateria = givenExisteUnaMateria();
		Usuario administrador = givenExisteUnAdmin();
		
		whenPersistimosUnGrupo(nuevoGrupo, nuevaCarrera, nuevaMateria, administrador);
		
		return nuevoGrupo.getId();
	}

	private Usuario givenExisteUnAdmin() {
		Usuario admin = new Usuario();
		
		admin.setNombre("Manuel");
		return admin;
	}

	private void whenPersistimosUnGrupo(Grupo nuevoGrupo, Carrera nuevaCarrera, Materia nuevaMateria, Usuario administrador) {
		session().save(nuevaCarrera);
		session().save(nuevaMateria);
		session().save(administrador);
		
		nuevoGrupo.setAdministrador(administrador);
		nuevoGrupo.setCarrera(nuevaCarrera);
		nuevoGrupo.setMateria(nuevaMateria);
		session().save(nuevoGrupo);
	}

	private void thenLaMateriaSeAsignaAlGrupo(Long id) {
		Grupo grupoEncontrado = session().get(Grupo.class, id);
		Materia materiaEncontrada = session().get(Materia.class, id);
		
		assertThat(grupoEncontrado).isNotNull();
		assertThat(materiaEncontrada).isNotNull();
		assertThat(grupoEncontrado.getMateria().getNombre()).isEqualTo("Basica 1");
	}

	private Materia givenExisteUnaMateria() {
		Materia nuevaMateria = new Materia();
		
		nuevaMateria.setNombre("Basica 1");
		return nuevaMateria;
	}

	private Carrera givenExisteUnaCarrera() {
		Carrera nuevaCarrera = new Carrera();
		
		nuevaCarrera.setNombre("Desarrollo WEB");
		return nuevaCarrera;
	}

	private Grupo givenExisteUnGrupo() {
		Grupo nuevoGrupo = new Grupo();
		
		nuevoGrupo.setCantidadMax(2);
		nuevoGrupo.setDescripcion("Desc");
		nuevoGrupo.setNombre("Hola");
		nuevoGrupo.setCerrado(true);
		nuevoGrupo.setTurno(Turno.NOCHE);
		
		return nuevoGrupo;
	}
}
