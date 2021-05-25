package ar.edu.unlam.tallerweb1.persistenciaTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.*;

import javax.transaction.Transactional;

public class PersistenciaGrupoTest extends SpringTest {



    @Test @Transactional @Rollback
    public void poderGuardarUnGrupo(){

        Grupo grupoPicatecla = givenExisteUnGrupo();
        Long idDelGrupo=whenGuardoElGrupo(grupoPicatecla);
        thenLoPuedoBuscarPorId(idDelGrupo);
    }

    private Grupo givenExisteUnGrupo() {
        return new Grupo();
    }

    private Long whenGuardoElGrupo(Grupo grupoPicatecla) {
        session().save(grupoPicatecla);
        return grupoPicatecla.getId();
    }

    private void thenLoPuedoBuscarPorId(Long idDelGrupo) {
        Grupo buscado=session().get(Grupo.class, idDelGrupo);
        assertThat(buscado).isNotNull();
    }





}
