package ar.edu.unlam.tallerweb1.repositorios;

//import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Grupo;

public interface RepositorioGrupos {

	public Grupo getGrupoByID(Long id);

	public void actualizarGrupo(Grupo objetivo);

	public void eliminarGrupo(Grupo objetivo);

}
