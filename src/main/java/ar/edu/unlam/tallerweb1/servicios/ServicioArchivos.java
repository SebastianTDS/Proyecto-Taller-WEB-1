package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dto.DatosDeMensaje;
import ar.edu.unlam.tallerweb1.modelo.Archivo;
import ar.edu.unlam.tallerweb1.modelo.Mensaje;

import java.util.TreeSet;

public interface ServicioArchivos {
    TreeSet<Archivo> buscarArchivosPorGrupo(Long id);

    }
