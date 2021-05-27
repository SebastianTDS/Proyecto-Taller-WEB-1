package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.dto.DatosDeGrupoParaBusqueda;
import ar.edu.unlam.tallerweb1.modelo.Carrera;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Materia;

public interface ServicioGrupo {

    Grupo crearGrupo(DatosDeGrupo grupoNuevo);

    List<Grupo> buscarTodos();

    List<Carrera> buscarTodasLasCarreras();

    List<Materia> buscarTodasLasMaterias();

    List<Grupo> buscarGrupoPorDatos(DatosDeGrupoParaBusqueda datosParaBuscarUnGrupo);
    
    Grupo buscarGrupoPorID(Long idBuscado);

	void modificarGrupo(Long id, Grupo formulario);

	void eliminarGrupo(Long idBuscado);
}
