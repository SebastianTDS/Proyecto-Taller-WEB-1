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


public class RepositorioArchivoTest extends SpringTest {

    @Autowired
    private RepositorioArchivosImpl repositorio;
    private final Archivo archivo1= new Archivo();
    private final Archivo archivo2= new Archivo();
    private final Archivo archivo3= new Archivo();
    private final Materia nuevaMateria = new Materia();
    private final Carrera nuevaCarrera = new Carrera();

    @Test
    @Transactional
    @Rollback
    public void queSePuedaBuscarTodosLosMensajesDeUnGrupo(){
        Grupo grupo=givenQueExisteUnGrupoConCarreraYMateria();
        Usuario usuario=givenQueExisteUnUsuario();
        Archivo archivo1=givenQueExisteUnArchivo1(grupo,usuario);
        Archivo archivo2=givenQueExisteUnArchivo2(grupo,usuario);
        Archivo archivo3=givenQueExisteUnArchivo3(grupo,usuario);
        whenGuardoLosArchivosEnElGrupo(archivo1);
        whenGuardoLosArchivosEnElGrupo(archivo2);
        whenGuardoLosArchivosEnElGrupo(archivo3);
        thenBuscoLosMsjDelGrupo(grupo);
    }


    private void thenBuscoLosMsjDelGrupo(Grupo grupo) {
        assertThat(repositorio.buscarArchivosPorGrupo(grupo.getId())).hasSize(3);
    }



    private Archivo givenQueExisteUnArchivo1(Grupo grupo, Usuario usuario) {
        archivo1.setFecha(LocalDateTime.now().withNano(0));
        archivo1.setNombre("foto.jpg");
        archivo1.setGrupo(grupo);
        archivo1.setUsuario(usuario);
        return archivo1;
    }

    private Archivo givenQueExisteUnArchivo2(Grupo grupo, Usuario usuario) {
        archivo2.setFecha(LocalDateTime.now().withNano(0));
        archivo2.setNombre("foto.jpg");
        archivo2.setGrupo(grupo);
        archivo2.setUsuario(usuario);
        return archivo2;
    }

    private Archivo givenQueExisteUnArchivo3(Grupo grupo, Usuario usuario) {
        archivo3.setFecha(LocalDateTime.now().withNano(0));
        archivo3.setNombre("foto.jpg");
        archivo3.setGrupo(grupo);
        archivo3.setUsuario(usuario);
        return archivo3;
    }

    private Archivo whenGuardoLosArchivosEnElGrupo(Archivo archivo) {
        session().save(archivo);
        return archivo;
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
