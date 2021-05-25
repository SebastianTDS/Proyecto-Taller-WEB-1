package ar.edu.unlam.tallerweb1.repositorioTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupoImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Test @Transactional @Rollback
    public void  queSePuedaBuscarTodosLosgrupos(){
        Grupo losPicatecla1 = givenQueExisteUnGrupo();
        Grupo losPicatecla2 = givenQueExisteUnGrupo();
        Grupo losPicatecla3 = givenQueExisteUnGrupo();

        givenQueGuardoVariosGruposEnElRepositorio(losPicatecla1,losPicatecla2,losPicatecla3);
        List<Grupo> grupos= whenCuandoBuscoTodosLosGrupos();
        thenVerificoTodosQueTodosLosGruposSeMuestren(grupos);
    }

    private void givenQueGuardoVariosGruposEnElRepositorio(Grupo losPicatecla1, Grupo losPicatecla2, Grupo losPicatecla3) {
        repositorio.guardarGrupo(losPicatecla1);
        repositorio.guardarGrupo(losPicatecla2);
        repositorio.guardarGrupo(losPicatecla3);
    }
    private List<Grupo> whenCuandoBuscoTodosLosGrupos() {
        return repositorio.buscarTodos();
    }
    private void thenVerificoTodosQueTodosLosGruposSeMuestren(List<Grupo> grupos) {
        Integer tamano=grupos.size();
       assertThat(3).isEqualTo(tamano);
    }


    private void thenVerificoQueSeaElQueBuscabaMedianteSusId(Long id) {
        Grupo idAComparar= session().get(Grupo.class,id);
        assertThat(idAComparar).isNotNull();
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
