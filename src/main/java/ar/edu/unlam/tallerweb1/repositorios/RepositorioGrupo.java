package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Grupo;

import java.util.List;

public interface RepositorioGrupo {


    void guardarGrupo(Grupo grupoNuevo) ;

    Grupo buscarPorId(Long idDelGrupoABuscar);


    List<Grupo> buscarTodos();
}
