package ar.edu.unlam.tallerweb1.controladores;


import ar.edu.unlam.tallerweb1.dto.DatosDeGrupoParaBusqueda;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorHomeTest {
    private ControladorHome controladorHome;
    private ServicioGrupo servicioGrupo;

    @Before
    public void init(){
        servicioGrupo = mock(ServicioGrupo.class);
        controladorHome = new ControladorHome(servicioGrupo);
    }

    @Test
    public void QueMeRedirigaALaVistaCrearGrupo(){

       ModelAndView mvc= whenDoyClickACrearGrupo();
        thenMeMuestraLaPaginaDeCreacionDeGrupo(mvc);
    }
    @Test
    public void QueMeRedirigaALaVistaHomeAlFiltarGrupos(){

        ModelAndView mvc= whenDoyClickAFiltar();
        thenMeMuestraLaPaginaDeGruposFiltrados(mvc);
    }

    @Test
    public void QueMeMuestreTodosLosGrupos(){
        List<Grupo>grupos=givenGruposPersistidos();
        ModelAndView mvc= whenGuardoLosGruposEnElModel(grupos);
        thenMeMuestreLosGrupos(mvc,grupos);
    }

    @Test
    public void QueMeMuestreLosGruposFiltrados(){
        List<Grupo>grupos=givenGruposPersistidos();
        ModelAndView mvc= whenGuardoLosGruposEnElModel(grupos);
        thenMeMuestreLosGrupos(mvc,grupos);
    }

    @Test
    public void QueMeMuestreTodasLasMaterias(){
        List<Materia>materias=givenMateriasPersistidas();
        ModelAndView mvc= whenGuardoLasMateriasEnElModel(materias);
        thenMeMuestreLasMaterias(mvc,materias);
    }
    @Test
    public void QueMeMuestreTodasLasCarreras(){
        List<Carrera>carreras=givenCarrerasPersistidas();
        ModelAndView mvc= whenGuardoLasCarrerasEnElModel(carreras);
        thenMeMuestreLasCarreras(mvc,carreras);
    }

    void thenMeMuestreLasCarreras(ModelAndView mvc, List<Carrera>carreras){
        assertThat(carreras).isEqualTo(mvc.getModel().get("carreras"));
    }

    private List<Carrera> givenCarrerasPersistidas() {
        Carrera carrera=new Carrera();
        List<Carrera>carreras=new ArrayList<>();
        carreras.add(carrera);
        return carreras;
    }

    private ModelAndView whenGuardoLasCarrerasEnElModel(List<Carrera> carreras){
        DatosDeGrupoParaBusqueda datosDeGrupo=new DatosDeGrupoParaBusqueda();
        when(servicioGrupo.buscarTodasLasCarreras()).thenReturn(carreras);
        return controladorHome.buscarGrupos(datosDeGrupo);
    }

    void thenMeMuestreLasMaterias(ModelAndView mvc, List<Materia>materias){
        assertThat(materias).isEqualTo(mvc.getModel().get("materias"));
    }

    private List<Materia> givenMateriasPersistidas() {
        Materia materia=new Materia();
        List<Materia>materias=new ArrayList<>();
        materias.add(materia);
        return materias;
    }

    private ModelAndView whenGuardoLasMateriasEnElModel(List<Materia> materias){
        DatosDeGrupoParaBusqueda datosDeGrupo=new DatosDeGrupoParaBusqueda();
        when(servicioGrupo.buscarTodasLasMaterias()).thenReturn(materias);
        return controladorHome.buscarGrupos(datosDeGrupo);
    }

    private List<Grupo> givenGruposPersistidos() {
        List<Grupo>gruposPersistidos=new ArrayList<>();
        gruposPersistidos.add(new Grupo());
        return gruposPersistidos;
    }


    private ModelAndView whenGuardoLosGruposEnElModel(List<Grupo> grupos) {
        DatosDeGrupoParaBusqueda datosDeGrupo=new DatosDeGrupoParaBusqueda();
        when(servicioGrupo.buscarGrupoPorDatos(datosDeGrupo)).thenReturn(grupos);
        return controladorHome.buscarGrupos(datosDeGrupo);
    }

    private void thenMeMuestreLosGrupos(ModelAndView mvc, List<Grupo>grupos) {
        assertThat(grupos).isEqualTo(mvc.getModel().get("grupos"));
    }

    private void thenMeMuestraLaPaginaDeCreacionDeGrupo(ModelAndView mvc ) {
        assertThat("vistaParaCrearGrupo").isEqualTo(mvc.getViewName());
    }

    private ModelAndView whenDoyClickACrearGrupo() {
      return controladorHome.irAlFormulario();
    }

    private ModelAndView whenDoyClickAFiltar() {
        return controladorHome.buscarGrupos(new DatosDeGrupoParaBusqueda());
    }

    private void thenMeMuestraLaPaginaDeGruposFiltrados(ModelAndView mvc ) {
        assertThat("home").isEqualTo(mvc.getViewName());
    }
}
