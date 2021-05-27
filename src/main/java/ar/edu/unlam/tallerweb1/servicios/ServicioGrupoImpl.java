package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.dto.DatosDeGrupoParaBusqueda;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCarrera;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMateria;
import ar.edu.unlam.tallerweb1.util.exceptions.LimiteDeUsuariosIlegalException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("servicioGrupos")
@Transactional
public class ServicioGrupoImpl implements ServicioGrupo {

    private final RepositorioGrupo repositorioGrupoParaElServicio;
    private final RepositorioCarrera repositorioCarreraParaElServicio;
    private final RepositorioMateria repositorioMateriaParaElServicio;


    @Autowired
    public ServicioGrupoImpl(RepositorioGrupo repositorioGrupoParaElServicio, RepositorioCarrera repositorioCarreraParaElServicio, RepositorioMateria repositorioMateriaParaElServicio) {
        this.repositorioGrupoParaElServicio = repositorioGrupoParaElServicio;
        this.repositorioCarreraParaElServicio = repositorioCarreraParaElServicio;
        this.repositorioMateriaParaElServicio = repositorioMateriaParaElServicio;
    }
    
    @Override
	public Grupo buscarGrupoPorID(Long idBuscado) {
		Grupo encontrado = repositorioGrupoParaElServicio.getGrupoByID(idBuscado);

		return encontrado;
	}

	@Override
	public void modificarGrupo(Long id, Grupo formulario) {
		Grupo objetivo = repositorioGrupoParaElServicio.getGrupoByID(id);

		objetivo.actualizar(formulario);

		if (formulario.getCtdMaxima() != null && objetivo.getCtdMaxima() != formulario.getCtdMaxima())
			throw new LimiteDeUsuariosIlegalException(id);

		repositorioGrupoParaElServicio.actualizarGrupo(objetivo);
	}

	@Override
	public void eliminarGrupo(Long idBuscado) {
		Grupo objetivo = repositorioGrupoParaElServicio.getGrupoByID(idBuscado);

		repositorioGrupoParaElServicio.eliminarGrupo(objetivo);
	}

    @Override
    public Grupo crearGrupo(DatosDeGrupo datosDeGrupo) {

        Grupo grupoAPartirDeDatosDeGrupo = crearGrupoAPartirDeDatosDeGrupo(datosDeGrupo);

        if (grupoAPartirDeDatosDeGrupo != null) {
            repositorioGrupoParaElServicio.guardarGrupo(grupoAPartirDeDatosDeGrupo);
        }
        return grupoAPartirDeDatosDeGrupo;
    }

    @Override
    public List<Grupo> buscarTodos() {
        return repositorioGrupoParaElServicio.buscarTodos();
    }

    @Override
    public List<Carrera> buscarTodasLasCarreras() {
        return repositorioCarreraParaElServicio.buscarTodasLasCarreras();
    }

    @Override
    public List<Materia> buscarTodasLasMaterias() {
        return repositorioMateriaParaElServicio.buscarTodasLasMaterias();
    }

    @Override
    public List<Grupo> buscarGrupoPorDatos(DatosDeGrupoParaBusqueda datosParaBuscarUnGrupo) {
        return repositorioGrupoParaElServicio.buscarGrupoPorDatos(datosParaBuscarUnGrupo);
    }



    private Grupo crearGrupoAPartirDeDatosDeGrupo(DatosDeGrupo datosDeGrupo) {
        if (verificarQueCtdEsteEnElRango(datosDeGrupo)) {
            return getGrupo(datosDeGrupo);
        }
        return null;
    }

    private Grupo getGrupo(DatosDeGrupo datosDeGrupo) {
        Grupo grupo = new Grupo();
        Materia materia = repositorioMateriaParaElServicio.buscarMateriaPorId(datosDeGrupo.getMateria());
        Carrera carrera = repositorioCarreraParaElServicio.buscarCarreraPorId(datosDeGrupo.getCarrera());
        grupo.setNombre(datosDeGrupo.getNombre());
        grupo.setTurno(datosDeGrupo.getTurno());
        grupo.setPrivado(datosDeGrupo.getPrivado());
        grupo.setCtdMaxima(datosDeGrupo.getCtdMaxima());
        grupo.setDescripcion(datosDeGrupo.getDescripcion());
        grupo.setMateria(materia);
        grupo.setCarrera(carrera);
        return grupo;
    }

    private boolean verificarQueCtdEsteEnElRango(DatosDeGrupo datosDeGrupo) {
        final Integer CTD_USUARIO_MINIMO = 2;
        final Integer CTD_USUARIO_MAXIMO = 8;

        if (verificarDatosDeGruposNoVacios(datosDeGrupo)) {
            return datosDeGrupo.getCtdMaxima() >= CTD_USUARIO_MINIMO &&
                    datosDeGrupo.getCtdMaxima() <= CTD_USUARIO_MAXIMO;
        }
        return false;

    }

    private boolean verificarDatosDeGruposNoVacios(DatosDeGrupo datosDeGrupo) {

        if (verificarDatosDeGruposNoSeanNulos(datosDeGrupo)) {
            return
                    !datosDeGrupo.getNombre().isBlank() &&
                            !datosDeGrupo.getDescripcion().isBlank() &&
                            verificarQueExistaLaMateriaEnElRepositorio(datosDeGrupo.getMateria()) &&
                            verificarQueExistaLaCarreraEnElRepositorio(datosDeGrupo.getCarrera());
        }
        return false;
    }

    private boolean verificarDatosDeGruposNoSeanNulos(DatosDeGrupo datosDeGrupo) {

        return
                datosDeGrupo.getMateria() != null &
                        datosDeGrupo.getCarrera() != null &&
                        datosDeGrupo.getNombre() != null &&
                        datosDeGrupo.getTurno() != null &&
                        datosDeGrupo.getPrivado() != null &&
                        datosDeGrupo.getCtdMaxima() != null &&
                        datosDeGrupo.getDescripcion() != null;
    }

    private boolean verificarQueExistaLaMateriaEnElRepositorio(Long id) {
        return repositorioMateriaParaElServicio.buscarMateriaPorId(id) != null;
    }

    private boolean verificarQueExistaLaCarreraEnElRepositorio(Long id) {
        return repositorioCarreraParaElServicio.buscarCarreraPorId(id) != null;
    }

}
