package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.HttpSessionTest;
import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacionesImpl;

import ar.edu.unlam.tallerweb1.util.exceptions.FalloAlUnirseAlGrupo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorHomeTest extends HttpSessionTest{
    private ControladorHome controladorHome;
    private ServicioGrupo servicioGrupo;
    
    @Before
    public void init(){
        servicioGrupo = mock(ServicioGrupo.class);
        controladorHome = new ControladorHome(servicioGrupo, mock(ServicioNotificacionesImpl.class));
    }
    
    @Test
	public void testQueAlIngresarUnUsuarioAUnGrupoMeTraigaLaVistaDelGrupo() {
		givenUnUsuarioDeLaSesion();
		Long idGrupo = 1L;
		ModelAndView mvc = whenElUsuarioIngresaAlGrupo(idGrupo);
		thenVerificoLaVista(mvc);
	}

	@Test (expected = FalloAlUnirseAlGrupo.class)
    public void testQueAlIngresarAUnGrupoSinUsuarioLanzeException(){
        Usuario usuario=givenUnUsuarioNoLogueado();
        Grupo grupo = givenUnGrupo();
        whenIntentoIngresarAlGrupo(usuario.getId(),grupo.getId());
    }



    @Test
    public void QueMeRedirigaALaVistaCrearGrupo(){

       ModelAndView mvc= whenDoyClickACrearGrupo();
        thenMeMuestraLaPaginaDeCreacionDeGrupo(mvc);
    }

    @Test
    public void QueMeRedirigaALaVistaHomeAlFiltarGrupos(){
    	givenUnUsuarioDeLaSesion();
        ModelAndView mvc= whenDoyClickAFiltar();
        thenMeMuestraLaPaginaDeGruposFiltrados(mvc);
    }

    @Test
    public void QueMeMuestreTodosLosGrupos(){
        List<Grupo>grupos=givenGruposPersistidos();
        givenUnUsuarioDeLaSesion();
        ModelAndView mvc= whenGuardoLosGruposEnElModel(grupos);
        thenMeMuestreLosGrupos(mvc,grupos);
    }

    @Test
    public void QueMeMuestreLosGruposFiltrados(){
        List<Grupo>grupos=givenGruposPersistidos();
        ModelAndView mvc= whenGuardoLosGruposEnElModel(grupos);
        thenMeMuestreLosGrupos(mvc,grupos);
    }

    @Test
    public void QueMeMuestreTodasLasMaterias(){
        List<Materia>materias=givenMateriasPersistidas();
        ModelAndView mvc= whenGuardoLasMateriasEnElModel(materias);
        thenMeMuestreLasMaterias(mvc,materias);
    }
    @Test
    public void QueMeMuestreTodasLasCarreras(){
        List<Carrera>carreras=givenCarrerasPersistidas();
        ModelAndView mvc= whenGuardoLasCarrerasEnElModel(carreras);
        thenMeMuestreLasCarreras(mvc,carreras);
    }

    @Test
    public void queMeRedirigaALaVistaForoMaterias(){
    	givenUnUsuarioDeLaSesion();
        ModelAndView mvc=whenDoyClickAForoMaterias();
        thenMeMuestraLaVistaForoMaterias(mvc);
    }

    private void thenMeMuestraLaVistaForoMaterias(ModelAndView mvc) {
        assertThat(mvc.getViewName()).isEqualTo("vistaForosMaterias");
    }

    private ModelAndView whenDoyClickAForoMaterias() {
        return controladorHome.IrAForosMaterias(request());
    }


    private void givenUnUsuarioDeLaSesion() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		when(request().getSession().getAttribute("USUARIO")).thenReturn(usuario);
	}
    
    private ModelAndView whenElUsuarioIngresaAlGrupo(Long idGrupo) {
		return controladorHome.IngresarAGrupo(request(), idGrupo);
	}
    
    private void thenVerificoLaVista(ModelAndView mvc) {
		assertThat(mvc.getViewName()).isEqualTo("redirect:/grupos/1");
	}


    void thenMeMuestreLasCarreras(ModelAndView mvc, List<Carrera>carreras){
        assertThat(carreras).isEqualTo(mvc.getModel().get("carreras"));
    }


    private Usuario givenUnUsuarioNoLogueado() {
        return new Usuario();
    }
    private List<Carrera> givenCarrerasPersistidas() {
        Carrera carrera=new Carrera();
        List<Carrera>carreras=new ArrayList<>();
        carreras.add(carrera);
        return carreras;
    }

    private ModelAndView whenGuardoLasCarrerasEnElModel(List<Carrera> carreras){
        DatosDeGrupo datosDeGrupo=new DatosDeGrupo();
        when(servicioGrupo.buscarTodasLasCarreras()).thenReturn(carreras);
        return controladorHome.buscarGrupos(request(),datosDeGrupo);
    }

    void thenMeMuestreLasMaterias(ModelAndView mvc, List<Materia>materias){
        assertThat(materias).isEqualTo(mvc.getModel().get("materias"));
    }

    private List<Materia> givenMateriasPersistidas() {
        Materia materia=new Materia();
        List<Materia>materias=new ArrayList<>();
        materias.add(materia);
        return materias;
    }

    private ModelAndView whenGuardoLasMateriasEnElModel(List<Materia> materias){
    	DatosDeGrupo datosDeGrupo=new DatosDeGrupo();
        when(servicioGrupo.buscarTodasLasMaterias()).thenReturn(materias);
        return controladorHome.buscarGrupos(request(),datosDeGrupo);
    }

    private List<Grupo> givenGruposPersistidos() {
        List<Grupo>gruposPersistidos=new ArrayList<>();
        gruposPersistidos.add(new Grupo());
        return gruposPersistidos;
    }


    private ModelAndView whenGuardoLosGruposEnElModel(List<Grupo> grupos) {
    	DatosDeGrupo datosDeGrupo=new DatosDeGrupo();
    	
        when(servicioGrupo.buscarGrupoPorDatos(datosDeGrupo)).thenReturn(grupos);
        return controladorHome.buscarGrupos(request(),datosDeGrupo);
    }

    private void thenMeMuestreLosGrupos(ModelAndView mvc, List<Grupo>grupos) {
        assertThat(grupos).isEqualTo(mvc.getModel().get("grupos"));
    }
    
    private void thenMeMuestraLaPaginaDeCreacionDeGrupo(ModelAndView mvc ) {
        assertThat("vistaParaCrearGrupo").isEqualTo(mvc.getViewName());
    }

    private ModelAndView whenDoyClickACrearGrupo() {
      return controladorHome.irACrearGrupo(request());
    }

    private ModelAndView whenDoyClickAFiltar() {
        return controladorHome.buscarGrupos(request(),new DatosDeGrupo());
    }

    private void thenMeMuestraLaPaginaDeGruposFiltrados(ModelAndView mvc ) {
        assertThat("home").isEqualTo(mvc.getViewName());
    }

    private Grupo givenUnGrupo() {
        return new Grupo();
    }

    private void whenIntentoIngresarAlGrupo(Long idUser, Long idGrupo) {
        doThrow(FalloAlUnirseAlGrupo.class).when(servicioGrupo).ingresarUsuarioAlGrupo(idUser,idGrupo);
        servicioGrupo.ingresarUsuarioAlGrupo(idUser,idGrupo);
    }

}
