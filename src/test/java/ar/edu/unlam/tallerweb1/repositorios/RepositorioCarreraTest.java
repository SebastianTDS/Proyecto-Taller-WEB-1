package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Carrera;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCarreraImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class RepositorioCarreraTest extends SpringTest {

    @Autowired
    private RepositorioCarreraImpl repositorioCarrera;

    @Test
    @Transactional
    @Rollback
    public void queSePuedanBuscarTodasLasCarreras() {
        Carrera carrera1 = givenQueExisteUnaCarrera("Desarrollo web");
        Carrera carrera2 = givenQueExisteUnaCarrera("Desarrollo movil");
        List<Carrera> carrerasBuscadas = whenCuandoBuscoTodasLasCarreras();
        thenVerificoElTamanoDeLaLista(carrerasBuscadas);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaBuscarUnaCarreraPorId() {
        Carrera carrera1 = givenQueExisteUnaCarrera("Desarrollo WEb");
        Carrera carreraBuscada = whenCuandoBuscoUnaCarrera(carrera1.getId());
        thenVerificoQueNoSeaNull(carreraBuscada);
    }

    private void thenVerificoQueNoSeaNull(Carrera carreraBuscada) {
        assertThat(carreraBuscada).isNotNull();
    }

    private Carrera whenCuandoBuscoUnaCarrera(Long id) {
        return repositorioCarrera.buscarCarreraPorId(id);
    }

    private List<Carrera> whenCuandoBuscoTodasLasCarreras() {
        return repositorioCarrera.buscarTodasLasCarreras();
    }

    private Carrera givenQueExisteUnaCarrera(String nombre) {
        Carrera carrera = new Carrera();
        carrera.setNombre(nombre);
        session().save(carrera);
        return carrera;
    }

    private void thenVerificoElTamanoDeLaLista(List<Carrera> carrerasBuscadas) {
        assertThat(carrerasBuscadas).hasSize(2);
    }
}
