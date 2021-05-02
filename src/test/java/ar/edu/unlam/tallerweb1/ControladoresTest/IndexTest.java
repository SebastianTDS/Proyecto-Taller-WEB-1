package ar.edu.unlam.tallerweb1.ControladoresTest;

import ar.edu.unlam.tallerweb1.controladores.ControladorLogin;
import ar.edu.unlam.tallerweb1.controladores.ControladorRegistro;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static ar.edu.unlam.tallerweb1.ControladoresTest.RegistroTest.servicio;

public class IndexTest {


    @Test
    public void QueMePuedaMostrarLaPaginaDeLogin(){
        //dado
        ControladorLogin controladorLogin=new ControladorLogin();
        //cuando
         ModelAndView  mvc= controladorLogin.irALogin();
         //entonces
        Assert.assertEquals("login",(String) mvc.getViewName());
    }

    @Test
    public void QueMePuedaMostrarLaPaginaDeRegistro(){
        //dado
        ControladorRegistro controladorRegistro=new ControladorRegistro(servicio);
        //cuando
        ModelAndView  mvc= controladorRegistro.irARegistro();
        //entonces
        Assert.assertEquals("registro",(String) mvc.getViewName());
    }


}
