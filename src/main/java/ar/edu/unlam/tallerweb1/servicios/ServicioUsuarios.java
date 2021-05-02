package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.DatosDeUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioUsuarios {

    List<DatosDeUsuario> user = new  ArrayList<>() ;

    public String registrar(DatosDeUsuario datos){

        if(!datos.getClave().equals(datos.getRepiteClave())){
            return "Las claves no coinciden,error al registrar";
        }

        if(user.contains(datos)){
            return "Usuario ya existente";
        }
        user.add(datos);
        return "Registro Existoso";

    }

}
