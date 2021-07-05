package ar.edu.unlam.tallerweb1.modelos;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.util.enums.TipoSolicitud;
import ar.edu.unlam.tallerweb1.util.enums.Turno;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

public class EntityCalificacionTest extends SpringTest {

    @Test
    @Transactional
    @Rollback
    public void testQueSePuedaPersistirUnaCalificacion() {
        Usuario usuario = givenUsuarioPersistido();
        Long id = whenPersistimosCalificacion(usuario);

        thenLaEncontramosEnLaDB(id);
    }

    private void thenLaEncontramosEnLaDB(Long id) {
        Calificacion encontrada = session().get(Calificacion.class, id);
        assertThat(encontrada).isNotNull();
        assertThat(encontrada.getDestino()).isNotNull();
        assertThat(encontrada.getDestino().getEmail()).isEqualTo("marcelo@unlam.edu.ar");
    }

    private Long whenPersistimosCalificacion(Usuario usuario) {

        Calificacion calificacion = new Calificacion();

        calificacion.setCalificacion(100L);
        calificacion.setOrigen(usuario);
        calificacion.setDestino(usuario);

        return (Long) session().save(calificacion);

    }

    private Usuario givenUsuarioPersistido() {
        Usuario miUsuario = new Usuario();

        miUsuario.setEmail("marcelo@unlam.edu.ar");
        miUsuario.setPassword("1234");
        session().save(miUsuario);
        return miUsuario;
    }

}
