package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dto.DatosDeArchivo;
import ar.edu.unlam.tallerweb1.modelo.Archivo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioArchivos;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeSet;

@Service
public class ServicioArchivosImpl implements ServicioArchivos {

    private final RepositorioGrupo repoGrupo;
    private final RepositorioUsuario repoUsuario;
    private final RepositorioArchivos repositorioArchivos;

    @Autowired
    public ServicioArchivosImpl(RepositorioGrupo repoGrupo, RepositorioUsuario repoUsuario, RepositorioArchivos repositorioArchivos) {
        this.repoGrupo = repoGrupo;
        this.repoUsuario = repoUsuario;
        this.repositorioArchivos = repositorioArchivos;
    }


    @Override
    public TreeSet<Archivo> buscarArchivosPorGrupo(Long id) {
        TreeSet<Archivo> archivos = new TreeSet<>(repositorioArchivos.buscarArchivosPorGrupo(id));
        return archivos;
    }

    public String subirArchivoACarpeta(DatosDeArchivo datosDeArchivo, Long idGrupo) {
        String folder = "cargas//";
        final Logger logger=  LoggerFactory.getLogger(ServicioArchivosImpl.class);
   /*     if (!datosDeArchivo.getArchivo().isEmpty()) {
            try {
                byte[] bytes = datosDeArchivo.getArchivo().getBytes();
                //guarda el archivo en la ruta con el nombre especificado
                Path path = Paths.get(folder + datosDeArchivo.getArchivo().getOriginalFilename());
                logger.info("archivo guardado");
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        */
        return "ARCHVO GUARDADO CORRECTAMENTE";
    }


}
