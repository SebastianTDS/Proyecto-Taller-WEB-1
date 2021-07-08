package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dto.DatosDeArchivo;
import ar.edu.unlam.tallerweb1.modelo.Archivo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioArchivos;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.util.exceptions.ArchivoNoEncontradoException;
import ar.edu.unlam.tallerweb1.util.exceptions.GrupoInexistenteException;
import ar.edu.unlam.tallerweb1.util.exceptions.NoSePudoEliminarElArchivoException;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeSet;

@Service
@Transactional
public class ServicioArchivosImpl implements ServicioArchivos {

    private final RepositorioArchivos repositorioArchivos;
    private final RepositorioGrupo repositorioGrupo;
    private final RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioArchivosImpl(RepositorioArchivos repositorioArchivos,RepositorioGrupo repositorioGrupo,RepositorioUsuario repositorioUsuario) {
        this.repositorioArchivos = repositorioArchivos;
        this.repositorioGrupo = repositorioGrupo;
        this.repositorioUsuario=repositorioUsuario;
    }

    @Override
    public TreeSet<Archivo> buscarArchivosPorGrupo(Long id) {
        return new TreeSet<>(repositorioArchivos.buscarArchivosPorGrupo(id));
    }
    @Override
    public void subirArchivoACarpeta(DatosDeArchivo datosDeArchivo, String path) throws IOException {
        Long idArchivo=crearRegistroDeArchivo(datosDeArchivo);
        guardarArchivo(idArchivo,datosDeArchivo,path);

    }

    @Override
    public void descargarArchivo(String downloadFolder, Long idArchivo, HttpServletResponse response) throws IOException {
        Archivo archivo = repositorioArchivos.buscarArchivosPorId(idArchivo);

        if(archivo==null){
            throw new ArchivoNoEncontradoException("No se encontro el Archivo");
        }

        descargar(downloadFolder,archivo,response);

    }

    @Override
    public void borrarArchivo(Long idArchivo, String path) {
        Archivo archivo = repositorioArchivos.buscarArchivosPorId(idArchivo);
        repositorioArchivos.borrarArchivoPorId(archivo);
        eliminarArchivo(path);
    }

    private void eliminarArchivo(String path) {
        File file =new File(path);
        if(!file.delete()){
            throw new NoSePudoEliminarElArchivoException("No se pudo eliminar el archivo");
        }
    }

    private void descargar(String carpetaDeDescarga, Archivo archivo, HttpServletResponse response) throws IOException {
        Path file = Paths.get(carpetaDeDescarga, String.valueOf(archivo.getId()));
        int index = archivo.getNombreOriginal().lastIndexOf('.');
        String extension = archivo.getNombreOriginal().substring(index+1);
        // Check if file exists
        if (Files.exists(file)) {
            // set content type
            response.setContentType("application/"+ extension);
            // add response header
            response.addHeader("Content-Disposition", "attachment; filename="+archivo.getNombreOriginal());
            response.setContentLength((int) file.toFile().length());
            try {
                // copies all bytes from a file to an output stream
                Files.copy(file, response.getOutputStream());
                // flushes output stream
                response.getOutputStream().flush();
            } catch (IOException e) {
                System.out.println("Error :- " + e.getMessage());
            }
        }
    }

    private Long crearRegistroDeArchivo(DatosDeArchivo datosDeArchivo) {
        Archivo archivo= new Archivo();
        Grupo grupo =repositorioGrupo.getGrupoByID(datosDeArchivo.getIdGrupo());
        Usuario usuario=repositorioUsuario.getUsuarioByID(datosDeArchivo.getIdUsuario());
        if(grupo==null){
            throw new GrupoInexistenteException("No existe el grupo");
        }
        if(usuario==null){
            throw new UsuarioNoEncontradoException("No existe el usuario");
        }
        archivo.setGrupo(grupo);
        archivo.setUsuario(usuario);
        archivo.setNombreOriginal(datosDeArchivo.getArchivo().getOriginalFilename());
        archivo.setNombre(datosDeArchivo.getNombre());
        return repositorioArchivos.crearRegistroDeArchivo(archivo);
    }

    private void guardarArchivo(Long idArchivo,DatosDeArchivo datosDeArchivo,String path) throws IOException {
        MultipartFile file = datosDeArchivo.getArchivo();
        byte[] bytes = file.getBytes();
        BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(
                path+ File.separator+idArchivo));
        stream.write(bytes);
        stream.flush();
        stream.close();

    }




}
