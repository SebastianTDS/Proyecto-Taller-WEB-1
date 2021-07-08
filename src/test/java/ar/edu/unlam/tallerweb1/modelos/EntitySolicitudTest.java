package ar.edu.unlam.tallerweb1.modelos;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Carrera;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Materia;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.util.enums.TipoSolicitud;
import ar.edu.unlam.tallerweb1.util.enums.Turno;

public class EntitySolicitudTest extends SpringTest{

	@Test
	@Transactional
	@Rollback
	public void testQueSePuedaPersistirUnaSolicitud () {
		Usuario usuario = givenUnUsuario();
		Grupo grupo = givenUnGrupo(usuario);
		Solicitud solicitud = whenPersistimosSolicitud(usuario, grupo);
		
		thenLaEncontramosEnLaDB(solicitud);
	}

	private Grupo givenUnGrupo(Usuario admin) {
		Grupo nuevoGrupo = new Grupo();
		
		nuevoGrupo.setNombre("Grupo A");
		nuevoGrupo.setAdministrador(admin);
		nuevoGrupo.setCantidadMax(2);
		nuevoGrupo.setCarrera(givenUnaCarrera());
		nuevoGrupo.setMateria(givenUnaMateria());
		nuevoGrupo.setCerrado(false);
		nuevoGrupo.setTurno(Turno.MANIANA);
		session().save(nuevoGrupo);
		
		return nuevoGrupo;
	}

	private Materia givenUnaMateria() {
		Materia materia = new Materia();
		
		session().save(materia);
		
		return materia;
	}

	private Carrera givenUnaCarrera() {
		Carrera carrera = new Carrera();
		
		session().save(carrera);
		
		return carrera;
	}

	private void thenLaEncontramosEnLaDB(Solicitud solicitud) {
		Solicitud encontrada = session().get(Solicitud.class, solicitud.getId());
		assertThat(encontrada).isNotNull();
		assertThat(encontrada.getDestino()).isNotNull();
		assertThat(encontrada.getDestino().getEmail()).isEqualTo("pepe@hotmail.com");
	}

	private Solicitud whenPersistimosSolicitud(Usuario usuario, Grupo grupo) {
		Solicitud nuevaSolicitud = new Solicitud();
		
		nuevaSolicitud.setDestino(usuario);
		nuevaSolicitud.setOrigen(usuario);
		nuevaSolicitud.setTipo(TipoSolicitud.INVITACION_GRUPO);
		nuevaSolicitud.setObjetivo(grupo);
		
		session().save(nuevaSolicitud);
		
		return nuevaSolicitud;
	}

	private Usuario givenUnUsuario() {
		Usuario nuevoUsuario = new Usuario();
		
		nuevoUsuario.setEmail("pepe@hotmail.com");
		session().save(nuevoUsuario);
		
		return nuevoUsuario;
	}

}
