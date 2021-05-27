package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.dto.DatosDeGrupoParaBusqueda;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.util.enums.Privacidad;
import ar.edu.unlam.tallerweb1.util.enums.Turno;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.*;
import javax.transaction.Transactional;
import java.util.List;

public class RepositorioGrupoTest extends SpringTest {

    @Autowired
    private RepositorioGrupoImpl repositorio;

    @Test @Transactional @Rollback
    public void  queSePuedaAgregarUnGrupoAlRepositorio(){
        Grupo losPicatecla = givenQueExisteUnGrupoConCarreraYMateria();
        Long idDelGrupo =  whenGuardoElGrupoEnElRepositorio(losPicatecla);
        thenLoPuedoBuscarPorId(idDelGrupo);
    }

    @Test @Transactional @Rollback
    public void  queSePuedaBuscarUnGrupoYDevolverlo(){
            Grupo losPicatecla = givenQueExisteUnGrupoConCarreraYMateria();
              Long idDeLosPicateclas=whenGuardoElGrupoEnElRepositorio(losPicatecla);
              thenVerificoQueSeaElQueBuscabaMedianteSusId(idDeLosPicateclas);

        }

    @Test @Transactional @Rollback
    public void  queSePuedaBuscarTodosLosgrupos(){
        Grupo losPicatecla1 = givenQueExisteUnGrupoConCarreraYMateria();
        Grupo losPicatecla2 = givenQueExisteUnGrupoConCarreraYMateria();
        Grupo losPicatecla3 = givenQueExisteUnGrupoConCarreraYMateria();

        givenQueGuardoVariosGruposEnElRepositorio(losPicatecla1,losPicatecla2,losPicatecla3);
        List<Grupo> grupos= whenCuandoBuscoTodosLosGrupos();
        thenVerificoTodosQueTodosLosGruposSeMuestren(grupos);
    }

    @Test @Transactional @Rollback
    public void  queSePuedaBuscarFiltrandoLosgruposPorPrivacidad(){
        Grupo losPicatecla1 = givenQueExisteUnGrupoConCarreraYMateria();
        givenQueGuardoUnGruposEnElRepositorio(losPicatecla1);
        DatosDeGrupoParaBusqueda datosDeGrupoParaBusqueda=givenQueExisteDatosParaLaBusquedaPorPrivacidad();
        List<Grupo> grupos= whenCuandoBuscoFiltrandoLosGrupos(datosDeGrupoParaBusqueda);
        thenVerificoQueLosGruposFiltradosSeMuestren(grupos);
    }

    @Test @Transactional @Rollback
    public void  queSePuedaBuscarFiltrandoLosgruposPorTurno(){
        Grupo losPicatecla1 = givenQueExisteUnGrupoConCarreraYMateria();
        givenQueGuardoUnGruposEnElRepositorio(losPicatecla1);
        DatosDeGrupoParaBusqueda datosDeGrupoParaBusqueda=givenQueExisteDatosParaLaBusquedaPorTurno();
        List<Grupo> grupos= whenCuandoBuscoFiltrandoLosGrupos(datosDeGrupoParaBusqueda);
        thenVerificoQueLosGruposFiltradosSeMuestren(grupos);
    }

    @Test @Transactional @Rollback
    public void  queSePuedaBuscarFiltrandoLosgruposPorNombre(){
        Grupo losPicatecla1 = givenQueExisteUnGrupoConCarreraYMateria();
        givenQueGuardoUnGruposEnElRepositorio(losPicatecla1);
        DatosDeGrupoParaBusqueda datosDeGrupoParaBusqueda=givenQueExisteDatosParaLaBusquedaPorNombre();
        List<Grupo> grupos= whenCuandoBuscoFiltrandoLosGrupos(datosDeGrupoParaBusqueda);
        thenVerificoQueLosGruposFiltradosSeMuestren(grupos);
    }



    private DatosDeGrupoParaBusqueda givenQueExisteDatosParaLaBusquedaPorNombre() {
        DatosDeGrupoParaBusqueda datosDeGrupoParaBusqueda=new DatosDeGrupoParaBusqueda();
        datosDeGrupoParaBusqueda.setNombre("Hol");
        return  datosDeGrupoParaBusqueda;
    }

    private DatosDeGrupoParaBusqueda givenQueExisteDatosParaLaBusquedaPorTurno() {
        DatosDeGrupoParaBusqueda datosDeGrupoParaBusqueda=new DatosDeGrupoParaBusqueda();
        datosDeGrupoParaBusqueda.setTurno(Turno.NOCHE);
        return  datosDeGrupoParaBusqueda;
    }

    private DatosDeGrupoParaBusqueda givenQueExisteDatosParaLaBusquedaPorPrivacidad() {
        DatosDeGrupoParaBusqueda datosDeGrupoParaBusqueda=new DatosDeGrupoParaBusqueda();
        datosDeGrupoParaBusqueda.setPrivacidad(Privacidad.PRIVADO);
        return  datosDeGrupoParaBusqueda;
    }

    private void thenVerificoQueLosGruposFiltradosSeMuestren(List<Grupo> grupos) {
        assertThat(grupos).hasSize(1);
    }

    private List<Grupo> whenCuandoBuscoFiltrandoLosGrupos(DatosDeGrupoParaBusqueda datosDeGrupoParaBusqueda) {
        return repositorio.buscarGrupoPorDatos(datosDeGrupoParaBusqueda);
    }


    private void givenQueGuardoUnGruposEnElRepositorio(Grupo losPicatecla1) {
        repositorio.guardarGrupo(losPicatecla1);
    }

    private List<Grupo> whenCuandoBuscoTodosLosGrupos() {
        return repositorio.buscarTodos();
    }

    private void givenQueGuardoVariosGruposEnElRepositorio(Grupo losPicatecla1, Grupo losPicatecla2, Grupo losPicatecla3) {
        repositorio.guardarGrupo(losPicatecla1);
        repositorio.guardarGrupo(losPicatecla2);
        repositorio.guardarGrupo(losPicatecla3);
    }

    private void thenVerificoTodosQueTodosLosGruposSeMuestren(List<Grupo> grupos) {
        Integer tamano=grupos.size();
       assertThat(3).isEqualTo(tamano);
    }

    private void thenVerificoQueSeaElQueBuscabaMedianteSusId(Long id) {
        Grupo idAComparar= session().get(Grupo.class,id);
        assertThat(idAComparar).isNotNull();
    }

    private Grupo givenQueExisteUnGrupoConCarreraYMateria() {
        Grupo nuevoGrupo = new Grupo();
        Carrera nuevaCarrera = new Carrera();
        Materia nuevaMateria = new Materia();
        nuevoGrupo.setCtdMaxima(2);
        nuevoGrupo.setDescripcion("Desc");
        nuevoGrupo.setNombre("Hola");
        nuevoGrupo.setPrivado(true);
        nuevoGrupo.setTurno(Turno.NOCHE);
        session().save(nuevaCarrera);
        session().save(nuevaMateria);
        nuevaCarrera.setNombre("Desarrollo web");
        nuevaMateria.setNombre("Basica I");
        nuevoGrupo.setCarrera(nuevaCarrera);
        nuevoGrupo.setMateria(nuevaMateria);
        session().save(nuevoGrupo);
        return nuevoGrupo;
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
