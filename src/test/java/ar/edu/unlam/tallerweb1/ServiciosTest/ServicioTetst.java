package ar.edu.unlam.tallerweb1.ServiciosTest;

import ar.edu.unlam.tallerweb1.modelo.DatosDeUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;
import org.junit.Assert;
import org.junit.Test;

public class ServicioTetst {

    ServicioUsuarios servicio = new ServicioUsuarios();
    DatosDeUsuario datos = new DatosDeUsuario();
    private void cargarDatos() {
        datos.setNombre("gonzalo");
        datos.setEmail("k4tnet@outlook.com");
        datos.setClave("1234");
        datos.setRepiteClave("1234");
    }
    @Test
    public void  siLasClavesNoCoicidenNoRegistrar(){
        //dado
        cargarDatos();
        datos.setRepiteClave("1235");
        //cuando
        String resultado = servicio.registrar(datos);
        //entonces
        Assert.assertEquals("Las claves no coinciden,error al registrar",resultado);

    }

    @Test
    public void  siElUsuarioExisteNoRegistrar(){
        //dado

        cargarDatos();
        servicio.registrar(datos);
        //cuando
        String resultado = servicio.registrar(datos);
        //entonces
        Assert.assertEquals("Usuario ya existente",resultado);

    }



}
