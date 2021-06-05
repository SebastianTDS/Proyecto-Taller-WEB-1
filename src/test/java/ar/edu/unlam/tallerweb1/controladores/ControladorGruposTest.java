package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.*;
import ar.edu.unlam.tallerweb1.util.exceptions.FalloAlUnirseAlGrupo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.util.exceptions.GrupoInexistenteException;
import ar.edu.unlam.tallerweb1.util.exceptions.LimiteDeUsuariosFueraDeRango;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ControladorGruposTest {

    private static ControladorGrupos controller;
    private static ServicioGrupo service;
    private static HttpServletRequest request;
    private static ServicioLogin servicioLogin;
    private static HttpSession session;

    @Before
    public void init() {
        session=mock(HttpSession.class);
        service = mock(ServicioGrupoImpl.class);
        request = mock(HttpServletRequest.class);
        servicioLogin=mock(ServicioLoginImpl.class);
        controller = new ControladorGrupos(service,servicioLogin);
        when(request.getSession()).thenReturn(session);
    }

@Test
public void  testQueAlIngresarUnUsuarioAUnGrupoMeTraigaLaVistaDelGrupo(){
         givenUnUsuarioDeLaSesion();
        Long idGrupo=1L;
      ModelAndView mvc =  whenElUsuarioIngresaAlGrupo(idGrupo);
      thenVerificoLaVista(mvc);
}

/*@Test(expected = FalloAlUnirseAlGrupo.class)
public void testQueAlIngresarAUnGrupoInexistenteLanzeException(){
    givenUnUsuarioDeLaSesion();
    Long idGrupo=1L;
    whenElUsuarioIngresaAlGrupoConNull(idGrupo);
}*/

    @Test
    public void testQueAlPegarleALaURLTraigaLaVistaDelGrupo() {
        Long idGrupoBuscado = 1L;
        ModelAndView vistaObtenida = whenBuscoPorLaURLConElIDCorrecto(idGrupoBuscado);
        thenObtengoLaVistaYLosDatosDelGrupo(vistaObtenida);
    }

    @Test(expected = GrupoInexistenteException.class)
    public void testQueAlBuscarUnGrupoInexistenteVolvamosAlIndex() {
        Long idGrupoInexistente = 2L;
        whenBuscoPorLaURLConElIDIncorrectoLanzaExcepcion(idGrupoInexistente);
    }

    @Test
    public void testQuePodamosEditarDatosDelGrupo() {
        Long idGrupoBuscado = 1L;
        DatosDeGrupo formulario = givenCompletamosFormulario(idGrupoBuscado);

        ModelAndView cambiosRealizados = whenCargoLaModificacionDeLosDatos(idGrupoBuscado, formulario);

        thenSusDatosSeCambian(cambiosRealizados);
    }

    @Test
    public void testQuePodamosEliminarUnGrupo() {
        Long idGrupoBuscado = 1L;

        ModelAndView postGrupoEliminado = whenEliminoElGrupo(idGrupoBuscado);

        thenElGrupoYaNoExiste(postGrupoEliminado);
    }

    @Test(expected = LimiteDeUsuariosFueraDeRango.class)
    public void testQueArrojeExcepcionAlModificarErroneamente() {
        Long idGrupoBuscado = 1L;
        DatosDeGrupo formulario = givenCompletamosFormularioErroneamente(idGrupoBuscado);

        whenIntentamosModificarGrupoLanzaException(idGrupoBuscado, formulario);

    }

    @Test
    public void testQuePodamosAccederAEdicionDeGrupo() {
        Long idGrupoBuscado = 1L;
        ModelAndView vistaObtenida = whenBuscoPorLaURLConElIDCorrectoAEditar(idGrupoBuscado);

        thenObtengoLaVistaYElModeloDelFormulario(vistaObtenida);
    }

    @Test(expected = GrupoInexistenteException.class)
    public void testQueNoPodamosAccederAEdicionDeGrupoInexistente() {
        Long idGrupoBuscado = 1L;
        whenBuscoPorLaURLDeEdicionConElIDIncorrectoLanzaExcepcion(idGrupoBuscado);

    }

    /* Metodos Auxiliares */

    private void givenUnUsuarioDeLaSesion() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(request.getSession().getAttribute("USUARIO")).thenReturn(usuario);
    }
    private void thenVerificoLaVista(ModelAndView mvc) {
        assertThat(mvc.getViewName()).isEqualTo("redirect:/1");
    }

    private void thenVerificoLaVistaLuegoDeLaExcepcion(ModelAndView mvc) {
        assertThat(mvc.getViewName()).isEqualTo("redirect:/home");
    }

    private ModelAndView whenElUsuarioIngresaAlGrupo(Long idGrupo) {
        return controller.IngresarAGrupo(request,idGrupo) ;
    }

    private void whenElUsuarioIngresaAlGrupoConNull(Long id) {
        Usuario usuario = new Usuario();
        doThrow(FalloAlUnirseAlGrupo.class).when(service).IngresarUsuarioAlGrupo(usuario.getId(),id);
    }

    private ModelAndView whenBuscoPorLaURLDeEdicionConElIDIncorrectoLanzaExcepcion(Long idGrupoBuscado) {
        doThrow(GrupoInexistenteException.class).when(service).buscarGrupoPorID(idGrupoBuscado);

        return controller.perfilDeGrupoEdicion(idGrupoBuscado);
    }

    private void thenObtengoLaVistaYElModeloDelFormulario(ModelAndView vistaObtenida) {
        assertThat(vistaObtenida.getViewName()).isEqualTo("vistaGrupo");
        assertThat(vistaObtenida.getModel().get("formulario")).isNotNull();
    }

    private ModelAndView whenBuscoPorLaURLConElIDCorrectoAEditar(Long idGrupoBuscado) {
        when(service.buscarGrupoPorID(idGrupoBuscado)).thenReturn(new Grupo());

        return controller.perfilDeGrupoEdicion(idGrupoBuscado);
    }


    private void whenIntentamosModificarGrupoLanzaException(Long idGrupoBuscado, DatosDeGrupo formulario) {
        doThrow(LimiteDeUsuariosFueraDeRango.class).when(service).modificarGrupo(idGrupoBuscado, formulario);
        controller.cambiarDatosGrupo(formulario);
    }

    private ModelAndView whenEliminoElGrupo(Long idGrupoBuscado) {
        return controller.eliminarGrupo(idGrupoBuscado);
    }

    private void thenElGrupoYaNoExiste(ModelAndView postGrupoEliminado) {
        assertThat(postGrupoEliminado.getViewName()).isEqualTo("redirect:/ir-a-home");
        assertThat(postGrupoEliminado.getModel().get("mensaje")).isEqualTo("Grupo eliminado con exito!");
    }

    private void thenSusDatosSeCambian(ModelAndView cambiosRealizados) {
        assertThat(cambiosRealizados.getModel().get("mensaje")).isEqualTo("Datos actualizados");
    }

    private ModelAndView whenCargoLaModificacionDeLosDatos(Long idGrupoBuscado, DatosDeGrupo formulario) {
        return controller.cambiarDatosGrupo(formulario);
    }

    private DatosDeGrupo givenCompletamosFormulario(Long idGrupoBuscado) {
        DatosDeGrupo nuevoGrupo = new DatosDeGrupo();

        nuevoGrupo.setId(idGrupoBuscado);
        nuevoGrupo.setNombre("Grupo de pepe");
        nuevoGrupo.setCantidadMax(2);

        return nuevoGrupo;
    }

    private DatosDeGrupo givenCompletamosFormularioErroneamente(Long idGrupoBuscado) {
        DatosDeGrupo nuevoGrupo = new DatosDeGrupo();

        nuevoGrupo.setId(idGrupoBuscado);
        nuevoGrupo.setCantidadMax(8);

        return nuevoGrupo;
    }

    private ModelAndView whenBuscoPorLaURLConElIDCorrecto(Long idGrupoBuscado) {
        when(service.buscarGrupoPorID(idGrupoBuscado)).thenReturn(new Grupo());

        return controller.perfilDeGrupo(idGrupoBuscado);
    }

    private ModelAndView whenBuscoPorLaURLConElIDIncorrectoLanzaExcepcion(Long idGrupoInexistente) {
        doThrow(GrupoInexistenteException.class).when(service).buscarGrupoPorID(idGrupoInexistente);
        return controller.perfilDeGrupo(idGrupoInexistente);
    }

    private void thenObtengoLaVistaYLosDatosDelGrupo(ModelAndView vistaObtenida) {
        assertThat(vistaObtenida.getViewName()).isEqualTo("vistaGrupo");
        assertThat(vistaObtenida.getModel()).isNotNull();
    }

}
