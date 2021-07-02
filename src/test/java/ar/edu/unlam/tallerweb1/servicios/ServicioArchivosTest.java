package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dto.DatosDeMensaje;
import ar.edu.unlam.tallerweb1.modelo.Archivo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ServicioArchivosTest {

    private ServicioArchivos servicioArchivos;
    private RepositorioGrupo repositorioGrupo;
    private RepositorioUsuario repositorioUsuario;
    private RepositorioArchivos repositorioArchivos;

    @Before
    public void init() {
        repositorioUsuario = mock(RepositorioUsuarioImpl.class);
        repositorioGrupo = mock(RepositorioGrupoImpl.class);
        repositorioArchivos = mock(RepositorioArchivosImpl.class);
        servicioArchivos = new ServicioArchivosImpl(repositorioGrupo,repositorioUsuario,repositorioArchivos);
    }

    @Test
    public void queSePuedaBuscarLosArchivosDeUnGrupo() {
        Grupo losPicatecla1 = givenQueExisteUnGrupo();
        HashSet<Archivo> archivosPresistidos = givenQueExistenArchivosPersistidos(losPicatecla1);
        TreeSet<Archivo> archivosEncontrados = whenBuscoLosMsjDeUnGrupo(losPicatecla1,archivosPresistidos);
        thenVerificoQueElGrupoContengaArchivos(archivosEncontrados);
    }

    private void thenVerificoQueElGrupoContengaArchivos(TreeSet<Archivo> archivosEncontrados) {
        assertThat(archivosEncontrados).hasSize(2);
    }

    private TreeSet<Archivo> whenBuscoLosMsjDeUnGrupo(Grupo losPicatecla1, HashSet<Archivo>presistidos) {
        when(repositorioArchivos.buscarArchivosPorGrupo(losPicatecla1.getId())).thenReturn(presistidos);
        return servicioArchivos.buscarArchivosPorGrupo(losPicatecla1.getId());
    }

    private HashSet<Archivo> givenQueExistenArchivosPersistidos(Grupo grupo) {
      HashSet<Archivo> archivos=new HashSet<>();
        Archivo archivo1=new Archivo();
        archivo1.setId(1L);
        archivo1.setGrupo(grupo);
        Archivo archivo2=new Archivo();
        archivo2.setId(4L);
        archivo2.setGrupo(grupo);

        archivos.add(archivo1);
        archivos.add(archivo2);

        return archivos;
    }


    private Grupo givenQueExisteUnGrupo() {
        Grupo grupo = new Grupo();
        grupo.setId(1L);
        grupo.setCantidadMax(2);
        grupo.setCerrado(false);
        return grupo;
    }

    private Usuario givenQueExisteUnUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        return usuario;
    }

}
