package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.dto.DatosDeArchivo;
import ar.edu.unlam.tallerweb1.modelo.Archivo;

import java.util.HashSet;

public interface RepositorioArchivos {

    HashSet<Archivo> buscarArchivosPorGrupo(Long id);

    Long crearRegistroDeArchivo(Archivo archivo);

    Archivo buscarArchivosPorId(Long idArchivo);

    void borrarArchivoPorId(Archivo archivo);
}



