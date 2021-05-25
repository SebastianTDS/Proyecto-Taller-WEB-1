package ar.edu.unlam.tallerweb1.ControladoresTest;

import ar.edu.unlam.tallerweb1.controladores.ControladorHome;
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

public class HomeTest {
    private ControladorHome controladorHome;
    private ServicioGrupo servicioGrupo;

    @Before
    public void init(){
        servicioGrupo = mock(ServicioGrupo.class);
        controladorHome = new ControladorHome(servicioGrupo);
    }

    @Test
    public void QueMeRedirigaALaVistaCrearGrupo(){

        ControladorHome controller=givenUnControladorDeHome();

       ModelAndView mvc= whenDoyClickACrearGrupo(controller);

        thenMeMuestraLaPaginaDeCreacionDeGrupo(mvc);
    }

    @Test
    public void QueMeMuestreTodosLosGrupos(){

        List<Grupo>gruposPresistidos=givenGruposPersistidos();
        List<Grupo> grupos=givenPidoTodosLosGrupos(gruposPresistidos);
        ModelAndView mvc= whenGuardoLosGruposEnElModel(grupos);
        thenMeMuestreLosGrupos(mvc,grupos);
    }

    private List<Grupo> givenGruposPersistidos() {
        List<Grupo>gruposPersistidos=new ArrayList<>();
        gruposPersistidos.add(new Grupo());
        return gruposPersistidos;
    }

    private List<Grupo> givenPidoTodosLosGrupos(List<Grupo> gruposPresistidos) {
        when(servicioGrupo.buscarTodos()).thenReturn(gruposPresistidos);
        return servicioGrupo.buscarTodos();
    }

    private ModelAndView whenGuardoLosGruposEnElModel(List grupos) {
        ModelMap model=new ModelMap();
        model.put("grupos",grupos);
        return new ModelAndView("/home",model);
    }

    private void thenMeMuestreLosGrupos(ModelAndView mvc, List<Grupo>grupos) {
        assertThat(grupos).isEqualTo(mvc.getModel().get("grupos"));

    }


    private void thenMeMuestraLaPaginaDeCreacionDeGrupo(ModelAndView mvc ) {

        assertThat("crearGrupoDeTrabajo").isEqualTo(mvc.getModel());

    }

    private ModelAndView whenDoyClickACrearGrupo(ControladorHome controller) {
      return controller.irAlFormulario();
    }

    private ControladorHome givenUnControladorDeHome() {
        return controladorHome;
    }


}
