package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dto.DatosDeArchivo;
import ar.edu.unlam.tallerweb1.modelo.Archivo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TreeSet;

public interface ServicioArchivos {
    TreeSet<Archivo> buscarArchivosPorGrupo(Long id);

    void subirArchivoACarpeta(DatosDeArchivo datosDeArchivo, String path) throws IOException;

    void descargarArchivo(String downloadFolder, Long idArchivo, HttpServletResponse response) throws IOException;

    void borrarArchivo(Long idArchivo, String path);
}