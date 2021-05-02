package ar.edu.unlam.tallerweb1.ControladoresTest;

import ar.edu.unlam.tallerweb1.controladores.ControladorRegistro;
import ar.edu.unlam.tallerweb1.modelo.DatosDeUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class RegistroTest {
    //Donde va la clase DatosDeUsuario (modelo o controlador)
        static DatosDeUsuario datos=new DatosDeUsuario();
        static ServicioUsuarios servicio = new ServicioUsuarios();
    @Test
    public void QueSePuedaRegistrarUnUsuarioExistosamente(){
        //given
        ControladorRegistro controller = new ControladorRegistro(servicio);
        //then
        ModelAndView mvc =controller.registrarUsuario(datos);
        //when
        Assert.assertEquals("home",mvc.getViewName());
    }

    @Test
    public void QueNoSePuedaRegistrarPorClavesDistintas(){
        //given
        ControladorRegistro controller = new ControladorRegistro(servicio);
        //then
        ModelAndView mvc =controller.registrarUsuario(datos);
        //when
        Assert.assertEquals("registro",mvc.getViewName());
        Assert.assertEquals("Las claves no coinciden,error al registrar",mvc.getModel().get("error"));
    }

    @Test
    public void QueNoSePuedaRegistrarPorUsuarioExistenteEnLaBaseDeDatos(){
        //given
        ControladorRegistro controller = new ControladorRegistro(servicio);
        //then
        ModelAndView mvc =controller.registrarUsuario(datos);
        //when
        Assert.assertEquals("registro",mvc.getViewName());
        Assert.assertEquals("Usuario ya existente",mvc.getModel().get("error"));
    }

}
