package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Calificacion;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.*;
import ar.edu.unlam.tallerweb1.util.enums.TipoSolicitud;
import ar.edu.unlam.tallerweb1.util.exceptions.GrupoInexistenteException;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;
import ar.edu.unlam.tallerweb1.util.exceptions.YaEstoyEnElGrupo;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

public class ServicioCalificacionesTest {

    private final RepositorioCalificacion repoCalif = mock(RepositorioCalificacionImpl.class);
    private final RepositorioGrupo repoGrupo = mock(RepositorioGrupoImpl.class);
    private final RepositorioUsuario repoUsuario = mock(RepositorioUsuarioImpl.class);
    private final ServicioNotificaciones servicioNotificaciones = mock(ServicioNotificacionesImpl.class);
    private final ServicioCalificacion service = new ServicioCalificacionesImpl(repoCalif, repoGrupo, repoUsuario, servicioNotificaciones);

    private Usuario usuario1 = new Usuario();
    private Usuario usuario2 = new Usuario();
    private Usuario usuario3 = new Usuario();
    private Usuario usuario4 = new Usuario();

    @Test
    public void testQueSeObtenganTodasLasSolicitudesDeUsuario() {
        Long idUsuario = 1L;

        List<Calificacion> calificacions = whenBuscamosCalificacionesPor(idUsuario);

        thenObtenemosTodasSusCalificaciones(calificacions);
    }

    @Test
    public void testQueSeCrearCalificaciones() {
        Long idUsuario = 1L;

        Grupo grupo = givenGrupoConUsuarios();
        whenUnUsuarioSolicitaCalificar(idUsuario, grupo);

        thenSeEnviaSolicitud(grupo);
    }

    @Test
    public void testQueSePuedaCalificar() {
        Long idUsuario = 1L;
        Long calificacionRealizada=100L;
        Calificacion calificacion = givenExisteCalificacion();
        whenCalificoUsuario(idUsuario, calificacion,calificacionRealizada);

        thenElUsuarioDestinoFueCalificadoYSeBorraLacalificacion(usuario1, calificacion,calificacionRealizada);
    }

    private void thenElUsuarioDestinoFueCalificadoYSeBorraLacalificacion(Usuario usuario2, Calificacion calificacion, Long calificacionRealizada) {
        assertThat(1L).isEqualTo(usuario2.getCantidadDeCalificaciones());
        assertThat(calificacionRealizada).isEqualTo(usuario2.getCalificacion());
        verify(repoUsuario, times(1)).actualizarUsuario(anyObject());
        verify(repoCalif, times(1)).borrarCalificacion(anyObject());
    }


    private void whenCalificoUsuario(Long idUsuario, Calificacion calificacion, Long calificaionRealizada) {
        when(repoUsuario.getUsuarioByID(idUsuario)).thenReturn(usuario1);
        when(repoUsuario.getUsuarioByID(calificacion.getDestino().getId())).thenReturn(usuario2);
        when(repoCalif.buscarCalificacionPor(calificacion.getId())).thenReturn(calificacion);
        service.calificar(idUsuario, calificacion.getId(), calificaionRealizada);
    }

    private Calificacion givenExisteCalificacion() {
        Calificacion calificacion = new Calificacion();
        usuario1.setId(1L);
        usuario2.setId(2L);
        calificacion.setOrigen(usuario1);
        calificacion.setDestino(usuario2);
        return calificacion;
    }

    private void whenUnUsuarioSolicitaCalificar(Long idUsuario, Grupo grupo) {
        when(repoGrupo.getGrupoByID(grupo.getId())).thenReturn(grupo);
        when(repoUsuario.getUsuarioByID(idUsuario)).thenReturn(usuario1);
        service.crearCalificacion(grupo.getId(), idUsuario);
    }

    private void thenSeEnviaSolicitud(Grupo grupo) {
        int repeticiones = (grupo.getListaDeUsuarios().size() - 1) * 2;
        verify(repoCalif, times(repeticiones)).cargarCalificacion(anyObject());
    }

    private void thenObtenemosTodasSusCalificaciones(List<Calificacion> calificacions) {
        assertThat(calificacions).hasSize(2);
    }

    private List<Calificacion> whenBuscamosCalificacionesPor(Long idUsuario) {
        when(repoCalif.buscarCalificacionesPor(idUsuario)).thenReturn(Arrays.asList(new Calificacion(), new Calificacion()));
        return service.buscarCalificaciones(idUsuario);
    }

    private Grupo givenGrupoConUsuarios() {
        Grupo grupo = new Grupo();
        usuario1.setId(1L);
        usuario2.setId(2L);
        usuario3.setId(3L);
        usuario4.setId(4L);
        grupo.agregarUsuarioAlGrupo(usuario1);
        grupo.agregarUsuarioAlGrupo(usuario2);
        grupo.agregarUsuarioAlGrupo(usuario3);
        grupo.agregarUsuarioAlGrupo(usuario4);
        return grupo;
    }
}
