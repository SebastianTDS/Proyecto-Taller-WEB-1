package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Carrera;
import ar.edu.unlam.tallerweb1.modelo.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Materia;


import java.util.List;

public interface ServicioGrupo {


    Grupo crearGrupo(DatosDeGrupo grupoNuevo);

    List<Grupo> buscarTodos();


    List<Carrera> buscarTodasLasCarreras();

    List<Materia> buscarTodasLasMaterias();
}
