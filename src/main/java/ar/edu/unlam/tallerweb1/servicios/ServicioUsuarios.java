package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.DatosDeUsuario;
import ar.edu.unlam.tallerweb1.repositorios.repositorioDeUsuariosABorrar;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuarios {

    repositorioDeUsuariosABorrar repo = new repositorioDeUsuariosABorrar();
    public String registrar(DatosDeUsuario datos){

        if(!datos.getClave().equals(datos.getRepiteClave())){
            return "Las claves no coinciden,error al registrar";
        }

        if(repo.verificarUsuarioExistente(datos.getEmail())){
            return "Usuario ya existente";
        }

        return "Registro Existoso";

    }

}
