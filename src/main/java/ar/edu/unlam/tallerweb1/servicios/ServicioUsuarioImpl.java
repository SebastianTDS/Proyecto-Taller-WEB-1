package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.util.exceptions.CampoVacioException;
import ar.edu.unlam.tallerweb1.util.exceptions.NoCoincidenContraseniasException;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unlam.tallerweb1.dto.DatosDeUsuario;
import javax.transaction.Transactional;

@Service("servicioUsuarios")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

    private RepositorioUsuario repoUsuario;

    @Autowired
    public ServicioUsuarioImpl(RepositorioUsuario repoUsuario){
        this.repoUsuario = repoUsuario;
    }

    @Override
    public void registrar(DatosDeUsuario datos) {
        if(datos.getEmail() == null || datos.getEmail() == "")
            throw new CampoVacioException("Los campos tienen que estar llenos");

        if(repoUsuario.getUsuarioByEmail(datos.getEmail()) != null)
            throw new UsuarioExistenteException("El usuario ya se encuentra registrado");

        if(!(datos.getClave().equals(datos.getRepiteClave())))
            throw new NoCoincidenContraseniasException("Las claves tienen que coincidir");

        Usuario usuario = datos.crearUsuario();

        repoUsuario.guardarUsuario(usuario);
    }

}
