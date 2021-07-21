package ar.edu.unlam.tallerweb1.servicios;
import ar.edu.unlam.tallerweb1.dto.DatosDeMensaje;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ServicioMensajesTest {

    private ServicioMensajes servicioMensajes;
    private RepositorioGrupo repositorioGrupo;
    private RepositorioUsuario repositorioUsuario;
    private RepositorioMensajeImpl repositorioMsj;

    @Before
    public void init() {
        repositorioUsuario = mock(RepositorioUsuarioImpl.class);
        repositorioGrupo = mock(RepositorioGrupoImpl.class);
        repositorioMsj = mock(RepositorioMensajeImpl.class);
        servicioMensajes = new ServicioMensajesImpl(repositorioGrupo,repositorioUsuario,repositorioMsj);
    }

    @Test
    public void queSePuedaBuscarLosMensjaesDeUnGrupo() {
        Grupo losPicatecla1 = givenQueExisteUnGrupoConMensajes();
        List<Mensaje> mensajesPresistidos = givenQueExistenMensajesPersistidos(losPicatecla1);
        TreeSet<Mensaje> mensajesEncontrados = whenBuscoLosMsjDeUnGrupo(losPicatecla1,mensajesPresistidos);
        thenVerificoQueElGrupoContengaMensajes(mensajesEncontrados);
    }

    private TreeSet<Mensaje> whenBuscoLosMsjDeUnGrupo(Grupo losPicatecla1,List<Mensaje>msjPresistidos) {
        when(repositorioMsj.getMensajesByIDGrupo(losPicatecla1.getId())).thenReturn(msjPresistidos);
        return servicioMensajes.buscarMensajesDeUnGrupo(losPicatecla1.getId());
    }

    private List<Mensaje> givenQueExistenMensajesPersistidos(Grupo grupo) {
       List<Mensaje> mensajes=new ArrayList<>();
        Mensaje mensaje1=new Mensaje();
        mensaje1.setId(16L);



        mensajes.add(mensaje1);


        return mensajes;
    }

    @Test
    public void queSePuedaEnviarUnMensajeAlGrupo() {
        Grupo buscado = givenQueExisteUnGrupo();
        Usuario usuario = givenQueExisteUnUsuario();
        DatosDeMensaje datosMensaje = givenQueExisteUnDatosDeMensaje(buscado);
        whenGuardoElMensaje(buscado,usuario,datosMensaje);
        thenVerificoQueSeGuardeElMensaje();
    }

    private void thenVerificoQueSeGuardeElMensaje() {
       verify(repositorioMsj, times(1)).save(anyObject());
    }

    private void thenVerificoQueElGrupoContengaMensajes(TreeSet<Mensaje> mensajes) {
        assertThat(mensajes).hasSize(1);
    }

    private Grupo givenQueExisteUnGrupoConMensajes() {
        Grupo grupo = new Grupo();
        grupo.setId(1L);
        return grupo;
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

    private DatosDeMensaje givenQueExisteUnDatosDeMensaje(Grupo grupo) {
        DatosDeMensaje mensaje = new DatosDeMensaje();
        mensaje.setMensaje("MSJ DE PRUEBA");
        mensaje.setId(grupo.getId());
        return mensaje;
    }

    void whenGuardoElMensaje(Grupo buscado, Usuario usuario, DatosDeMensaje mensaje) {
        when(repositorioGrupo.getGrupoByID(1L)).thenReturn(buscado);
        when(repositorioUsuario.getUsuarioByID(usuario.getId())).thenReturn(usuario);
        servicioMensajes.guardarUnMensaje(usuario.getId(), mensaje);
    }
}
