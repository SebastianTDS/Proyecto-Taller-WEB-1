package ar.edu.unlam.tallerweb1.ControladoresTest;

import ar.edu.unlam.tallerweb1.controladores.ControladorHome;
import ar.edu.unlam.tallerweb1.modelo.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.DatosDeGrupoParaBusqueda;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
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
