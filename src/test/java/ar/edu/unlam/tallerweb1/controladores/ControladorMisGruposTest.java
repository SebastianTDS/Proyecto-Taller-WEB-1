package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Carrera;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Materia;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioLoginImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorMisGruposTest {
    private static ControladorMisGrupos controladorMisGrupos;
    private static ServicioGrupo servicioGrupo;
    private static HttpServletRequest request;
    private static ServicioLogin servicioLogin;
    private static HttpSession session;

    @Before
    public void init() {
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        servicioLogin = mock(ServicioLoginImpl.class);
        when(request.getSession()).thenReturn(session);
        servicioGrupo = mock(ServicioGrupoImpl.class);
        controladorMisGrupos = new ControladorMisGrupos(servicioGrupo);
    }

    @Test
    public void QueMeRedirigaALaVistaCrearGrupo() {
        givenUnUsuarioDeLaSesion();
        ModelAndView mvc = whenDoyClickAMisGrupos();
        thenMeMuestraLaPaginaDeMisGrupos(mvc);
    }

    @Test
    public void QueMeMuestreTodasLasMaterias() {
        givenUnUsuarioDeLaSesion();
        List<Materia> materias = givenMateriasPersistidas();
        ModelAndView mvc = whenGuardoLasMateriasEnElModel(materias);
        thenMeMuestreLasMaterias(mvc, materias);
    }

    @Test
    public void QueMeMuestreTodasLasCarreras() {
        givenUnUsuarioDeLaSesion();
        List<Carrera> carreras = givenCarrerasPersistidas();
        ModelAndView mvc = whenGuardoLasCarrerasEnElModel(carreras);
        thenMeMuestreLasCarreras(mvc, carreras);
    }

    @Test
    public void QueMeMuestreTodosMisGrupos() {
        givenUnUsuarioDeLaSesion();
        List<Grupo> grupos = givenGruposPersistidos();
        ModelAndView mvc = whenGuardoLosGruposEnElModel(grupos);
        thenMeMuestreTodosMisGruposEnElModel(mvc, grupos);
    }




    private void thenMeMuestreTodosMisGruposEnElModel(ModelAndView mvc, List<Grupo> grupos) {
        assertThat(grupos).isEqualTo(mvc.getModel().get("misGrupos"));
    }

    private ModelAndView whenGuardoLosGruposEnElModel(List<Grupo> grupos) {
        Usuario usuarioLogueado= (Usuario) request.getSession().getAttribute("USUARIO");
        when(servicioGrupo.buscarTodosMisGrupos(usuarioLogueado)).thenReturn(grupos);
        return controladorMisGrupos.misGrupos(request);
    }

    private List<Grupo> givenGruposPersistidos() {
        List<Grupo> grupos = new ArrayList<>();
        grupos.add(new Grupo());
        return grupos;
    }

    void thenMeMuestreLasCarreras(ModelAndView mvc, List<Carrera> carreras) {
        assertThat(carreras).isEqualTo(mvc.getModel().get("carreras"));
    }

    private List<Carrera> givenCarrerasPersistidas() {
        Carrera carrera = new Carrera();
        List<Carrera> carreras = new ArrayList<>();
        carreras.add(carrera);
        return carreras;
    }

    private ModelAndView whenGuardoLasCarrerasEnElModel(List<Carrera> carreras) {
        when(servicioGrupo.buscarTodasLasCarreras()).thenReturn(carreras);
        return controladorMisGrupos.misGrupos(request);
    }

    void thenMeMuestreLasMaterias(ModelAndView mvc, List<Materia> materias) {
        assertThat(materias).isEqualTo(mvc.getModel().get("materias"));
    }

    private List<Materia> givenMateriasPersistidas() {
        Materia materia = new Materia();
        List<Materia> materias = new ArrayList<>();
        materias.add(materia);
        return materias;
    }

    private ModelAndView whenGuardoLasMateriasEnElModel(List<Materia> materias) {
        when(servicioGrupo.buscarTodasLasMaterias()).thenReturn(materias);
        return controladorMisGrupos.misGrupos(request);
    }

    private ModelAndView whenDoyClickAMisGrupos() {
        return controladorMisGrupos.misGrupos(request);
    }

    private void thenMeMuestraLaPaginaDeMisGrupos(ModelAndView mvc) {
        assertThat("vistaMisGrupos").isEqualTo(mvc.getViewName());
    }
    private void givenUnUsuarioDeLaSesion() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(request.getSession().getAttribute("USUARIO")).thenReturn(usuario);
    }
}
