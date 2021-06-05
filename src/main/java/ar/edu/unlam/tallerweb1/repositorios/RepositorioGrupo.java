package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;

import java.util.List;

public interface RepositorioGrupo {


    void guardarGrupo(Grupo grupoNuevo) ;

    List<Grupo> buscarTodos();

    List<Grupo>buscarGrupoPorDatos(DatosDeGrupo datos);
    
    public Grupo getGrupoByID(Long id);

	public void actualizarGrupo(Grupo objetivo);

	public void eliminarGrupo(Grupo objetivo);

}
