package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Archivo;
import org.springframework.stereotype.Service;

import java.util.TreeSet;
@Service
public class ServiciosArchivosImpl implements ServicioArchivos{

    @Override
    public TreeSet<Archivo> buscarArchivosPorGrupo(Long id) {
        return new TreeSet<Archivo>();
    }

}
