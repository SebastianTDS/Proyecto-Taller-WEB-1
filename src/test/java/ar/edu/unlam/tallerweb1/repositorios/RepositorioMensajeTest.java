package ar.edu.unlam.tallerweb1.repositorios;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.util.enums.Turno;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;


public class RepositorioMensajeTest extends SpringTest {

    @Autowired
    private RepositorioMensajeImpl repositorio;
    private final Mensaje mensaje1= new Mensaje();
    private final Mensaje mensaje2= new Mensaje();
    private final Mensaje mensaje3= new Mensaje();
    private final Materia nuevaMateria = new Materia();
    private final Carrera nuevaCarrera = new Carrera();

    @Test
    @Transactional
    @Rollback
    public void queSePuedaAgregarUnMensajeAlGrupo(){
        Grupo grupo=givenQueExisteUnGrupoConCarreraYMateria();
        Usuario usuario=givenQueExisteUnUsuario();
        Mensaje mensaje=givenQueExisteUnMensaje(grupo,usuario);
        Mensaje msjGuardado=whenGuardoLosMensajesEnElGrupo(mensaje);
        thenVerificoQueElMensajeSeGuarde(msjGuardado);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaBuscarTodosLosMensajesDeUnGrupo(){
        Grupo grupo=givenQueExisteUnGrupoConCarreraYMateria();
        Usuario usuario=givenQueExisteUnUsuario();
        Mensaje mensaje=givenQueExisteUnMensaje(grupo,usuario);
        Mensaje mensaje2=givenQueExisteUnMensaje2(grupo,usuario);
        Mensaje mensaje3=givenQueExisteUnMensaje3(grupo,usuario);
        whenGuardoLosMensajesEnElGrupo(mensaje);
        whenGuardoLosMensajesEnElGrupo(mensaje2);
        whenGuardoLosMensajesEnElGrupo(mensaje3);
        thenBuscoLosMsjDelGrupo(grupo);
    }


    private void thenBuscoLosMsjDelGrupo(Grupo grupo) {
        assertThat(repositorio.getMensajesByIDGrupo(grupo.getId())).hasSize(2);
    }

    private void thenVerificoQueElMensajeSeGuarde(Mensaje mensaje) {
        assertThat(session().get(Mensaje.class, mensaje.getId())).isNotNull();
    }

    private Mensaje givenQueExisteUnMensaje(Grupo grupo, Usuario usuario) {
        mensaje1.setFecha(LocalDateTime.now().withNano(0));
        mensaje1.setMensaje("Prueba");
        mensaje1.setGrupo(grupo);
        mensaje1.setUsuario(usuario);
        return mensaje1;
    }

    private Mensaje givenQueExisteUnMensaje2(Grupo grupo, Usuario usuario) {
        mensaje2.setFecha(LocalDateTime.now().withNano(0));
        mensaje2.setMensaje("Prueba2");
        mensaje2.setGrupo(grupo);
        mensaje2.setUsuario(usuario);
        return mensaje2;
    }

    private Mensaje givenQueExisteUnMensaje3(Grupo grupo, Usuario usuario) {
        mensaje3.setFecha(LocalDateTime.now().withNano(0));
        mensaje3.setMensaje("Prueba2");
        mensaje3.setGrupo(null);
        mensaje3.setUsuario(usuario);
        return mensaje2;
    }

    private Mensaje whenGuardoLosMensajesEnElGrupo(Mensaje mensaje) {
        repositorio.save(mensaje);
        return mensaje;
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
}
