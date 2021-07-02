package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Archivo;
import java.util.TreeSet;

public interface ServicioArchivos {
    TreeSet<Archivo> buscarArchivosPorGrupo(Long id);
    }
