package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Grupo;

public interface ServicioGrupos {

	Grupo buscarGrupoPorID(Long idBuscado);

	Boolean modificarGrupo(Long id, Grupo formulario);

}
