package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Materia;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMateriaImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioMateriaTest extends SpringTest {

    @Autowired
    private RepositorioMateriaImpl repositorioMateria;

    @Test
    @Transactional
    @Rollback
    public void queSePuedanBuscarTodasLasMaterias() {
        Materia materia1 = givenQueExisteUnaMateria("Matematica");
        Materia materia2 = givenQueExisteUnaMateria("Programacion 1");
        List<Materia> materiasBuscadas = whenCuandoBuscoTodasLasMaterias();
        thenVerificoElTamanoDeLaLista(materiasBuscadas);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaBuscarUnaMateriaPorId() {
        Materia materia1 = givenQueExisteUnaMateria("Matematica");
        Materia materiaBuscada = whenCuandoBuscoUnaMateria(materia1.getId());
        thenVerificoQueNoSeaNull(materiaBuscada);
    }

    private void thenVerificoQueNoSeaNull(Materia materiaBuscada) {
        assertThat(materiaBuscada).isNotNull();
    }

    private Materia whenCuandoBuscoUnaMateria(Long id) {
        return repositorioMateria.buscarMateriaPorId(id);
    }

    private List<Materia> whenCuandoBuscoTodasLasMaterias() {
        return repositorioMateria.buscarTodasLasMaterias();
    }

    private Materia givenQueExisteUnaMateria(String nombre) {
        Materia materia = new Materia();
        materia.setNombre(nombre);
        session().save(materia);
        return materia;
    }

    private void thenVerificoElTamanoDeLaLista(List<Materia> materiasBuscadas) {
        assertThat(materiasBuscadas).hasSize(2);
    }
}

