package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Archivo;

import java.util.HashSet;

public interface RepositorioArchivos {

    HashSet<Archivo> buscarArchivosPorGrupo(Long id);

}



