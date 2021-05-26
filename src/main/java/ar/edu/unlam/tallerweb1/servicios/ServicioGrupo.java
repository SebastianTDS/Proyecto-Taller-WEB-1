package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.*;


import java.util.List;

public interface ServicioGrupo {


    Grupo crearGrupo(DatosDeGrupo grupoNuevo);

    List<Grupo> buscarTodos();


    List<Carrera> buscarTodasLasCarreras();

    List<Materia> buscarTodasLasMaterias();

    List<Grupo> buscarGrupoPorDatos(DatosDeGrupoParaBusqueda datosParaBuscarUnGrupo);
}
