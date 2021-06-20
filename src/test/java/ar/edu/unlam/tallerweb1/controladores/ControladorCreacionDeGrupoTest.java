package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.HttpSessionTest;
import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupoImpl;
import ar.edu.unlam.tallerweb1.util.enums.Privacidad;
import ar.edu.unlam.tallerweb1.util.enums.Turno;
import ar.edu.unlam.tallerweb1.util.exceptions.FormularioDeGrupoIncompleto;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorCreacionDeGrupoTest extends HttpSessionTest{

    private ControladorCreacionDeGrupo controladorCreacionDeGrupo;
    private ServicioGrupo servicioGrupo;
    
    @Before
    public void init(){
        servicioGrupo = mock(ServicioGrupoImpl.class);
        controladorCreacionDeGrupo = new ControladorCreacionDeGrupo(servicioGrupo);
    }
    
    @Test
    public void queAlCrearElGrupoMedianteDatosCorrectosYUsuarioMeRedirigaALaVistaDelGrupoCreado(){
        DatosDeGrupo grupo = givenDatosDeGrupo();
        Usuario usuario = givenUsuarioDeLaSesion();
        ModelAndView mvc =  whenDoyClickACrearGrupo(grupo, usuario);
        thenMeRedirigeALaVistaDeGrupoCreado(mvc);
    }

    private Usuario givenUsuarioDeLaSesion() {
    	when(request().getSession().getAttribute("USUARIO")).thenReturn(new Usuario());
    	return new Usuario();
	}

	@Test(expected = FormularioDeGrupoIncompleto.class)
    public void queAlCrearElGrupoConCamposNulosMeRedirigaALaVistaParaVolverACrearlo(){
        DatosDeGrupo grupo =givenDatosDeGrupoIncompletos();
        ModelAndView mvc = whenDoyClickACrearGrupoIncompleto(grupo);
        thenMeRedirigeALaVistaParaVolverALlenarElFormulario(mvc);
    }

    private ModelAndView whenDoyClickACrearGrupoIncompleto(DatosDeGrupo grupo) {
    	when(request().getSession().getAttribute("USUARIO")).thenReturn(new Usuario());
    	doThrow(FormularioDeGrupoIncompleto.class).when(servicioGrupo).crearGrupo(grupo, new Usuario().getId());
        return controladorCreacionDeGrupo.irALaVistaDeGrupoCreado(request(), grupo);
    }

    private void thenMeRedirigeALaVistaParaVolverALlenarElFormulario(ModelAndView mvc) {
        assertThat("redirect:/ir-a-crear-nuevo-grupo" ).isEqualTo(mvc.getViewName());
    }

    private DatosDeGrupo givenDatosDeGrupoIncompletos() {
        DatosDeGrupo datosdegrupo = new DatosDeGrupo();
        String nombre = "Los Picateclas";
        Integer ctdMaxima = 5;
        String descripcion =  "Grupo de test para taller web";
        datosdegrupo.setNombre(nombre);
        datosdegrupo.setMateria(123L);
        datosdegrupo.setPrivacidad(Privacidad.ABIERTO);
        datosdegrupo.setCantidadMax(ctdMaxima);
        datosdegrupo.setDescripcion(descripcion);
        return datosdegrupo;
    }

    private DatosDeGrupo givenDatosDeGrupo() {
        DatosDeGrupo datosdegrupo = new DatosDeGrupo();
        Long materia = 1334L;
        Long carrera = 1234L;
        String nombre = "Los Picateclas";
        Turno turno = Turno.NOCHE;
        Integer ctdMaxima = 5;
        String descripcion =  "Grupo de test para taller web";

        datosdegrupo.setNombre(nombre);
        datosdegrupo.setCarrera(carrera);
        datosdegrupo.setMateria(materia);
        datosdegrupo.setTurno(turno);
        datosdegrupo.setPrivacidad(Privacidad.ABIERTO);
        datosdegrupo.setCantidadMax(ctdMaxima);
        datosdegrupo.setDescripcion(descripcion);

        return datosdegrupo;
    }

    private ModelAndView whenDoyClickACrearGrupo(DatosDeGrupo datos, Usuario usuario) {
    	when(request().getSession().getAttribute("USUARIO")).thenReturn(new Usuario());
        when(servicioGrupo.crearGrupo(datos, new Usuario().getId())).thenReturn(new Grupo());
        return controladorCreacionDeGrupo.irALaVistaDeGrupoCreado(request(),datos);
    }

    private void thenMeRedirigeALaVistaDeGrupoCreado(ModelAndView mvc) {
        assertThat("redirect:/grupos/null").isEqualTo(mvc.getViewName());
    }

}


