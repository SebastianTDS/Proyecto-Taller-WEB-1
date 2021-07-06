package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Calificacion;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCalificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.util.exceptions.GrupoInexistenteException;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioCalificacionesImpl implements ServicioCalificacion{
    private final RepositorioCalificacion repositorioCalificacion;
    private final RepositorioUsuario repoUsuario;
    private final RepositorioGrupo repoGrupo;
    private final ServicioNotificaciones servicioNotificaciones;

    @Autowired
    public ServicioCalificacionesImpl(RepositorioCalificacion repoCalif, RepositorioGrupo repoGrupo, RepositorioUsuario repoUsuario, ServicioNotificaciones servicioNotificaciones) {
        this.servicioNotificaciones = servicioNotificaciones;
        this.repositorioCalificacion = repoCalif;
        this.repoUsuario = repoUsuario;
        this.repoGrupo = repoGrupo;
    }

    @Override
    public List<Calificacion> buscarCalificaciones(Long idUsuario) {
        return repositorioCalificacion.buscarCalificacionesPor(idUsuario);
    }

    @Override
    public void crearCalificacion(Long idGrupo, Long idUsuario) {
        Grupo grupoSolicitado = repoGrupo.getGrupoByID(idGrupo);
        Usuario solicitante = repoUsuario.getUsuarioByID(idUsuario);

        if (grupoSolicitado == null)
            throw new GrupoInexistenteException("No se puede enviar calificacion a grupo inexistente");

        if (solicitante == null)
            throw new UsuarioNoEncontradoException("No existe el usuario calificante!");

        for (Usuario destino : grupoSolicitado.getListaDeUsuarios()){
            if (destino.getId()!=solicitante.getId()){
                generarCalificacion(solicitante, destino,grupoSolicitado.getNombre());
                generarCalificacion(destino,solicitante,grupoSolicitado.getNombre());
            }
        }
    }

    @Override
    public void calificar(Long idUsuario, Long idcalificacion, Long calificacionRealizada) {
        Usuario usuarioCalificante = repoUsuario.getUsuarioByID(idUsuario);
        Calificacion calificacion=repositorioCalificacion.buscarCalificacionPor(idcalificacion);
        Usuario usuarioCalificado = repoUsuario.getUsuarioByID(calificacion.getOrigen().getId());

        if (usuarioCalificado == null)
            throw new UsuarioNoEncontradoException("No existe el usuario calificado!");
        if (usuarioCalificante == null)
            throw new UsuarioNoEncontradoException("No existe el usuario calificante!");

        if (calificacionRealizada==null|| calificacionRealizada<0)
            calificacionRealizada=0L;
        if (calificacionRealizada>100)
            calificacionRealizada=100L;

        Long calificacionoriginal=usuarioCalificado.getCalificacion();
        if (calificacionoriginal==null)
            calificacionoriginal=0L;
        usuarioCalificado.setCalificacion(calificacionoriginal+calificacionRealizada);

        Long cantidadDeCalificacionesOriginal=usuarioCalificado.getCantidadDeCalificaciones();
        if (cantidadDeCalificacionesOriginal==null)
            cantidadDeCalificacionesOriginal=0L;
        usuarioCalificado.setCantidadDeCalificaciones(cantidadDeCalificacionesOriginal+1L);

        repoUsuario.actualizarUsuario(usuarioCalificado);
        repositorioCalificacion.borrarCalificacion(calificacion);
    }

    private void generarCalificacion(Usuario solicitante, Usuario destino, String nombreDelGrupo) {
        Calificacion calificacion=new Calificacion();

        calificacion.setOrigen(destino);
        calificacion.setDestino(solicitante);
calificacion.setNombreDeGrupo(nombreDelGrupo);
        repositorioCalificacion.cargarCalificacion(calificacion);
    }


}
