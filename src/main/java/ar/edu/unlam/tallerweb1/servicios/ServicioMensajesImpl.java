package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dto.DatosDeMensaje;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.*;
import ar.edu.unlam.tallerweb1.util.exceptions.NoSeEnvioElMensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.TreeSet;

@Service("servicioMensjaes")
@Transactional
public class ServicioMensajesImpl implements ServicioMensajes{

    private final RepositorioGrupo repoGrupo;
    private final RepositorioUsuario repoUsuario;
    private final RepositorioMensaje repoMsj;

    @Autowired
    public ServicioMensajesImpl(RepositorioGrupo repoGrupo,RepositorioUsuario repoUsuario, RepositorioMensaje repoMsj) {
        this.repoGrupo = repoGrupo;
        this.repoUsuario = repoUsuario;
        this.repoMsj = repoMsj;
    }

    @Override
    public void guardarUnMensaje(Long idUsuario, DatosDeMensaje datosMensaje) {
        Grupo grupoAAcceder = repoGrupo.getGrupoByID(datosMensaje.getId());
        Usuario usuarioAInsertar = repoUsuario.getUsuarioByID(idUsuario);
        if (grupoAAcceder == null || usuarioAInsertar == null || verificarDatosDeMSJ(datosMensaje))
            throw new NoSeEnvioElMensaje(grupoAAcceder.getId());
        Mensaje mensajeCreado = crearMensaje(usuarioAInsertar, grupoAAcceder, datosMensaje);
        repoMsj.save(mensajeCreado);
    }

    @Override
    public TreeSet<Mensaje> buscarMensajesDeUnGrupo(Long id) {
        TreeSet<Mensaje> mensajes= new TreeSet<>(repoMsj.getMensajesByIDGrupo(id));

        return mensajes;
    }

    private boolean verificarDatosDeMSJ(DatosDeMensaje datosMensaje) {
        return datosMensaje.getMensaje() == null || datosMensaje.getMensaje().isBlank()
                || datosMensaje.getMensaje().equals("<p><br></p>");
    }
    private Mensaje crearMensaje(Usuario usuarioAInsertar, Grupo grupoAAcceder, DatosDeMensaje datosMensaje) {
        Mensaje mensaje = new Mensaje();
        mensaje.setUsuario(usuarioAInsertar);
        mensaje.setMensaje(datosMensaje.getMensaje());
        mensaje.setFecha(LocalDateTime.now().withNano(0));
        mensaje.setGrupo(grupoAAcceder);
        return mensaje;
    }

}
