package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCarrera;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMateria;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.util.auxClass.Check;
import ar.edu.unlam.tallerweb1.util.exceptions.FalloAlUnirseAlGrupo;
import ar.edu.unlam.tallerweb1.util.exceptions.FormularioDeGrupoIncompleto;
import ar.edu.unlam.tallerweb1.util.exceptions.GrupoInexistenteException;
import ar.edu.unlam.tallerweb1.util.exceptions.YaEstoyEnElGrupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("servicioGrupos")
@Transactional
public class ServicioGrupoImpl implements ServicioGrupo {

    private final RepositorioGrupo repoGrupo;
    private final RepositorioCarrera repoCarrera;
    private final RepositorioMateria repoMateria;
    private final RepositorioUsuario repoUsuario;

    @Autowired
    public ServicioGrupoImpl(RepositorioGrupo repoGrupo, RepositorioCarrera repoCarrera, RepositorioMateria repoMateria, RepositorioUsuario repoUsuario) {
        this.repoGrupo = repoGrupo;
        this.repoCarrera = repoCarrera;
        this.repoMateria = repoMateria;
        this.repoUsuario = repoUsuario;
    }

    @Override
    public Grupo buscarGrupoPorID(Long idBuscado) {
        Grupo encontrado = repoGrupo.getGrupoByID(idBuscado);

        if (Check.isNull(encontrado))
            throw new GrupoInexistenteException("Grupo buscado no encontrado");

        return encontrado;
    }

    @Override
    public void modificarGrupo(Long id, DatosDeGrupo formulario) {
        Grupo objetivo = repoGrupo.getGrupoByID(id);

        if (Check.isNull(objetivo))
            throw new GrupoInexistenteException("No se puede modificar un grupo inexistente");

        objetivo.actualizar(formulario);

        repoGrupo.actualizarGrupo(objetivo);
    }

    @Override
    public void eliminarGrupo(Long idBuscado) {
        Grupo objetivo = repoGrupo.getGrupoByID(idBuscado);

        if (Check.isNull(objetivo))
            throw new GrupoInexistenteException("No se puede eliminar un grupo inexistente");
        repoGrupo.eliminarGrupo(objetivo);
    }

    @Override
    public void IngresarUsuarioAlGrupo(Long idUsuario, Long idGrupo) {
        Grupo grupoAAcceder = repoGrupo.getGrupoByID(idGrupo);
        Usuario usuarioAInsertar = repoUsuario.getUsuarioByID(idUsuario);
        Integer cantidadActual=grupoAAcceder.getListaDeUsuarios().size();
        if (grupoAAcceder == null || usuarioAInsertar == null ||cantidadActual>=grupoAAcceder.getCantidadMax())
            throw new FalloAlUnirseAlGrupo();
        verificarSiElUsuarioYaEstaEnElGrupo(grupoAAcceder, usuarioAInsertar);
        grupoAAcceder.agregarUsuarioAlGrupo(usuarioAInsertar);
        repoGrupo.actualizarGrupo(grupoAAcceder);
    }

    private void verificarSiElUsuarioYaEstaEnElGrupo(Grupo grupoAAcceder, Usuario usuarioAInsertar) {
        for (Grupo grupoActual : usuarioAInsertar.getListaDeGrupos()) {
            if (grupoActual.getId().equals(grupoAAcceder.getId()))
                throw new YaEstoyEnElGrupo(grupoActual.getId());
        }
    }

    @Override
    public List<Grupo> buscarTodosMisGrupos(Usuario usuarioSesion) {
        return repoGrupo.buscarTodosMisGrupos(usuarioSesion);
    }

    @Override
    public Grupo crearGrupo(DatosDeGrupo datosDeGrupo) {
        Grupo grupoGenerado = datosDeGrupo.crearGrupoAPartirDeDatosDeGrupo();
        if (grupoGenerado == null) {
            throw new FormularioDeGrupoIncompleto();
        }
        materiaNoSeaNull(grupoGenerado,datosDeGrupo.getMateria());
        carreraNoSeaNull(grupoGenerado,datosDeGrupo.getCarrera());
        repoGrupo.guardarGrupo(grupoGenerado);
        return grupoGenerado;
    }

    @Override
    public List<Grupo> buscarTodos() {
        return repoGrupo.buscarTodos();
    }

    @Override
    public List<Carrera> buscarTodasLasCarreras() {
        return repoCarrera.buscarTodasLasCarreras();
    }

    @Override
    public List<Materia> buscarTodasLasMaterias() {
        return repoMateria.buscarTodasLasMaterias();
    }

    @Override
    public List<Grupo> buscarGrupoPorDatos(DatosDeGrupo datosParaBuscarUnGrupo) {
        return repoGrupo.buscarGrupoPorDatos(datosParaBuscarUnGrupo);
    }

    private void materiaNoSeaNull(Grupo grupoGenerado,Long idMateria){
       Materia materiaEncontrada = repoMateria.buscarMateriaPorId(idMateria);
       if(materiaEncontrada==null)
           throw new FormularioDeGrupoIncompleto();
           grupoGenerado.setMateria(materiaEncontrada);
    }

    private void carreraNoSeaNull(Grupo grupoGenerado,Long idCarrera){
        Carrera carreraEncontrada = repoCarrera.buscarCarreraPorId(idCarrera);
        if(carreraEncontrada==null)
            throw new FormularioDeGrupoIncompleto();
        grupoGenerado.setCarrera(carreraEncontrada);
    }


}