package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dto.DatosDeUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioLogin {

	Usuario consultarUsuario(DatosDeUsuario usuario);
}
