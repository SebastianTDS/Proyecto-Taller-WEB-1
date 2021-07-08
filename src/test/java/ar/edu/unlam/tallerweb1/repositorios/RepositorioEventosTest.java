package ar.edu.unlam.tallerweb1.repositorios;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Carrera;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Materia;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.util.enums.Turno;

public class RepositorioEventosTest extends SpringTest{

	@Autowired
	private RepositorioEventos repository;
	
	@Test
	@Transactional
	@Rollback
	public void testQueSePuedanObtenerLosEventos() {
		Grupo grupo = givenUnGrupoPersistido();
		
		givenPersistoEventoDeGrupo(grupo, "Reunion", "2021-07-07T10:00:00", "2021-07-07T12:00:00");
		givenPersistoEventoDeGrupo(grupo, "Almuerzo", "2021-07-08T10:00:00", "2021-07-08T12:00:00");
		givenPersistoEventoDeGrupo(grupo, "Presentacion", "2021-07-09T10:00:00", "2021-07-09T12:00:00");
		
		List<Evento> eventos = whenBuscoLosEventosDe(grupo);
		
		thenObtengoTodosSusEventos(eventos, 3);
	}

	private void thenObtengoTodosSusEventos(List<Evento> eventos, Integer cantidad) {
		assertThat(eventos).isNotNull();
		assertThat(eventos).hasSize(cantidad);
	}

	private List<Evento> whenBuscoLosEventosDe(Grupo grupo) {
		return repository.buscarEventosPor(grupo.getId());
	}

	private void givenPersistoEventoDeGrupo(Grupo grupo, String titulo, String inicio, String fin) {
		Evento evento = new Evento();
		
		evento.setGrupo(grupo);
		evento.setTitulo(titulo);
		evento.setInicio(LocalDateTime.parse(inicio));
		evento.setFin(LocalDateTime.parse(fin));
		
		session().save(evento);
	}

	private Grupo givenUnGrupoPersistido() {
		Materia materia = givenExisteUnaMateria();
		Carrera carrera = givenExisteUnaCarrera();
		Grupo grupo = givenExisteUnGrupo();
		Usuario administrador = givenExisteUnUsuario("Manuel");
		
		grupo.setCarrera(carrera);
		grupo.setMateria(materia);
		grupo.setAdministrador(administrador);
		
		grupo.agregarUsuarioAlGrupo(administrador);
		session().save(grupo);
		
		return grupo;
	}
	
	private Usuario givenExisteUnUsuario(String nombre) {
		Usuario admin = new Usuario();
		
		admin.setNombre(nombre);
		session().save(admin);
		
		return admin;
	}

	private Materia givenExisteUnaMateria() {
		Materia nuevaMateria = new Materia();
		
		nuevaMateria.setNombre("Basica 1");
		session().save(nuevaMateria);
		
		return nuevaMateria;
	}

	private Carrera givenExisteUnaCarrera() {
		Carrera nuevaCarrera = new Carrera();
		
		nuevaCarrera.setNombre("Desarrollo WEB");
		session().save(nuevaCarrera);
		
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
