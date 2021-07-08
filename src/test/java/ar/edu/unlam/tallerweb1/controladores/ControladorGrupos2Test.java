package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.HttpSessionTest;
import ar.edu.unlam.tallerweb1.modelo.Archivo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorGrupos2Test  extends HttpSessionTest {

    private static ControladorGrupos controller;
    private static ServicioGrupo serviceGrupo;
    private static ServicioArchivos serviceArchivos;
    private static  ServicioCalificacion serviceCalificacion;
    private static ServicioNotificaciones serviceNotificacion;
    private static ServicioMensajes serviceMensajes;
    private static Usuario usuarioEjemplo = new Usuario();

    @Before
    public void init() {
        serviceGrupo = mock(ServicioGrupoImpl.class);
        serviceArchivos = mock(ServicioArchivosImpl.class);
        serviceCalificacion=mock(ServicioCalificacionesImpl.class);
        serviceNotificacion=mock(ServicioNotificacionesImpl.class);
        serviceMensajes=mock(ServicioMensajesImpl.class);

        controller = new ControladorGrupos(serviceGrupo,serviceNotificacion,serviceMensajes,serviceCalificacion,serviceArchivos);
        usuarioEjemplo.setId(1L);
    }

    @Test
    public void testQuePodamosAccederArchivosDeGrupo() {
        Long idGrupoBuscado = 1L;
        TreeSet<Archivo> archivos=givenListaDeArchivos();
        ModelAndView vistaObtenida = whenBuscoPorLaURLConElIDCorrectoAlArchvos(idGrupoBuscado,archivos);
        thenObtengoLaVistaYElModeloDelArchivo(vistaObtenida);
    }


    private TreeSet<Archivo> givenListaDeArchivos() {
        TreeSet<Archivo>archivos=new TreeSet<>();
        Archivo archivo=new Archivo();
        archivo.setId(1L);
        archivos.add(archivo);
        return archivos;
    }

    private ModelAndView whenBuscoPorLaURLConElIDCorrectoAlArchvos(Long id, TreeSet<Archivo>archivos) {
        when(serviceArchivos.buscarArchivosPorGrupo(id)).thenReturn(archivos);
        return controller.perfilDeGrupoArchivos(id);
    }

    private void thenObtengoLaVistaYElModeloDelArchivo(ModelAndView vistaObtenida) {
        assertThat(vistaObtenida.getViewName()).isEqualTo("vistaGrupo");
        assertThat(vistaObtenida.getModel()).isNotNull();
        assertThat(vistaObtenida.getModel().get("archivos")).isNotNull();
    }
}