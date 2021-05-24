package ar.edu.unlam.tallerweb1.repositorioTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupoImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.*;
import javax.transaction.Transactional;

public class repositorioGrupoTest extends SpringTest {

    @Autowired
    private RepositorioGrupoImpl repositorio;

    @Test @Transactional @Rollback
    public void  queSePuedaAgregarUnGrupoAlRepositorio(){
        Grupo losPicatecla = givenQueExisteUnGrupo();
        Long idDelGrupo =  whenGuardoElGrupoEnElRepositorio(losPicatecla);
        thenLoPuedoBuscarPorId(idDelGrupo);
    }


    @Test @Transactional @Rollback
    public void  queSePuedaBuscarUnGrupoYDevolverlo(){
            Grupo losPicatecla = givenQueExisteUnGrupo();
              Long idDeLosPicateclas=givenqueGuardoElGrupoEnElRepositorio(losPicatecla);
              thenVerificoQueSeaElQueBuscabaMedianteSusId(idDeLosPicateclas);
        }

    private void thenVerificoQueSeaElQueBuscabaMedianteSusId(Long idDelObjetoRecuperado) {
        assertThat(1L).isEqualTo(idDelObjetoRecuperado);
    }



    private Long givenqueGuardoElGrupoEnElRepositorio(Grupo losPicatecla) {
        repositorio.guardarGrupo(losPicatecla);
        return losPicatecla.getId();
    }


    private Grupo givenQueExisteUnGrupo() {
        return new Grupo();
    }

    private Long whenGuardoElGrupoEnElRepositorio(Grupo losPicatecla) {
                        repositorio.guardarGrupo(losPicatecla);
                        return losPicatecla.getId();
    }

    private void thenLoPuedoBuscarPorId( Long idDelGrupo) {
        Grupo buscado = repositorio.buscarPorId(idDelGrupo);
        assertThat(buscado).isNotNull();
    }

}
