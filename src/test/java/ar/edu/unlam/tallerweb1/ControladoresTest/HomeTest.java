package ar.edu.unlam.tallerweb1.ControladoresTest;

import ar.edu.unlam.tallerweb1.controladores.ControladorHome;
import ar.edu.unlam.tallerweb1.controladores.ControladorLogin;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.*;

public class HomeTest {
    @Test
    public void QueMeRedirigaALaVistaCrearGrupo(){

        ControladorHome controller=givenUnControladorDeHome();

       ModelAndView mvc= whenDoyClickACrearGrupo(controller);

        thenMeMuestraLaPaginaDeCreacionDeGrupo(mvc);
    }



    private void thenMeMuestraLaPaginaDeCreacionDeGrupo(ModelAndView mvc ) {

        assertThat("crearGrupoDeTrabajo").isEqualTo(mvc.getViewName());

    }

    private ModelAndView whenDoyClickACrearGrupo(ControladorHome controller) {
      return controller.irAlFormulario();
    }

    private ControladorHome givenUnControladorDeHome() {
        return new ControladorHome();
    }


}
