package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupoParaBusqueda;
import ar.edu.unlam.tallerweb1.modelo.Grupo;

import java.util.List;

public interface RepositorioGrupo {


    void guardarGrupo(Grupo grupoNuevo) ;

    Grupo buscarPorId(Long idDelGrupoABuscar);

    List<Grupo> buscarTodos();

    List<Grupo>buscarGrupoPorDatos(DatosDeGrupoParaBusqueda datos);
    
    public Grupo getGrupoByID(Long id);

	public void actualizarGrupo(Grupo objetivo);

	public void eliminarGrupo(Grupo objetivo);

}
