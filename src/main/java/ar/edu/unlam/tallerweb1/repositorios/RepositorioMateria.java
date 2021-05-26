package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Materia;

import java.util.List;

public interface RepositorioMateria {

    List<Materia> buscarTodasLasMaterias();

    Materia buscarMateriaPorId(Long id);

}
