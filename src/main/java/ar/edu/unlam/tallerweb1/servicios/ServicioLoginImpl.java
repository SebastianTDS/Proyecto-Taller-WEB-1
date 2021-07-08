package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.util.auxClass.Check;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;
import ar.edu.unlam.tallerweb1.dto.DatosDeUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

	private final RepositorioUsuario servicioLoginDao;

	@Autowired
	public ServicioLoginImpl(RepositorioUsuario servicioLoginDao){
		this.servicioLoginDao = servicioLoginDao;
	}

	@Override
	public Usuario consultarUsuario (DatosDeUsuario usuario) {
		Usuario buscado = servicioLoginDao.consultarUsuario(usuario.generarUsuario());
		
		if(Check.isNull(buscado))
			throw new UsuarioNoEncontradoException("Usuario o contrasena incorrectos");
		
		return buscado;
	}

}