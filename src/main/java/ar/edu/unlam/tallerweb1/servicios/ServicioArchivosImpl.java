package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Archivo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioArchivos;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.TreeSet;

@Service
public class ServicioArchivosImpl implements ServicioArchivos{

    private final RepositorioGrupo repoGrupo;
    private final RepositorioUsuario repoUsuario;
    private final RepositorioArchivos repositorioArchivos;

    @Autowired
    public ServicioArchivosImpl(RepositorioGrupo repoGrupo,RepositorioUsuario repoUsuario, RepositorioArchivos repositorioArchivos) {
        this.repoGrupo = repoGrupo;
        this.repoUsuario = repoUsuario;
        this.repositorioArchivos = repositorioArchivos;
    }


    @Override
    public TreeSet<Archivo> buscarArchivosPorGrupo(Long id) {
       TreeSet<Archivo>archivos=new TreeSet<>(repositorioArchivos.buscarArchivosPorGrupo(id));
        return archivos;
    }

}
