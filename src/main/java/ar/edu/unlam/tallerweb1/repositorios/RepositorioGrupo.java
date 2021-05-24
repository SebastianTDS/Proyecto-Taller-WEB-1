package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Grupo;

public interface RepositorioGrupo {


    void guardarGrupo(Grupo grupoNuevo) ;

    Grupo buscarPorId(Long idDelGrupoABuscar);


}
