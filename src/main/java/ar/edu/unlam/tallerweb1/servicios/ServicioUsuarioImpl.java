package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.DatosDeUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("servicioUsuarios")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {


    public String registrar(DatosDeUsuario datos) {
        return "Las claves no coinciden al registrar";
    }
}
