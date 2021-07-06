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


public class RepositorioUsuarioTest extends SpringTest {

    @Autowired
    private RepositorioUsuarioImpl repositorio;


    @Test
    @Transactional
    @Rollback
    public void queSePuedaAgregarUncalificarUnUsuario(){
        Usuario usuario=givenQueExisteUnUsuario();
        whenActualizoElUsuario(usuario);
        thenVerificoQueElUsuarioSeActualice(usuario);
    }

    private void whenActualizoElUsuario(Usuario usuario) {
        Usuario usuarioActualizar=repositorio.getUsuarioByID(usuario.getId());
        usuarioActualizar.setCalificacion(25L);
       repositorio.actualizarUsuario(usuarioActualizar);
    }

    private void thenVerificoQueElUsuarioSeActualice(Usuario usuario) {
        assertThat(25L).isEqualTo(repositorio.getUsuarioByID(usuario.getId()).getCalificacion());
    }


    private Usuario givenQueExisteUnUsuario() {
        Usuario usuario=new Usuario();
        usuario.setPassword("123");
        usuario.setEmail("casa");
        session().save(usuario);
        return usuario;
    }
}
