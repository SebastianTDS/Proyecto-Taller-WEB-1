package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupoImpl;
import ar.edu.unlam.tallerweb1.util.enums.Turno;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorCreacionDeGrupoTest {

    private ControladorCreacionDeGrupo controladorCreacionDeGrupo;
    private ServicioGrupo servicioGrupo;
    
    @Before
    public void init(){
        servicioGrupo = mock(ServicioGrupoImpl.class);
        controladorCreacionDeGrupo = new ControladorCreacionDeGrupo(servicioGrupo);
    }
    
    @Test
    public void queAlCrearElGrupoMedianteDatosCorrectosMeRedirigaALaVistaDelGrupoCreado(){
        DatosDeGrupo grupo = givenDatosDeGrupo();
        ModelAndView mvc =  whenDoyClickACrearGrupo(grupo);
        thenMeRedirigeALaVistaDeGrupoCreado(mvc);
    }

    @Test
    public void queAlCrearElGrupoConCamposNulosMeRedirigaALaVistaParaVolverACrearlo(){
        DatosDeGrupo grupo =givenDatosDeGrupoIncompletos();
        ModelAndView mvc = whenDoyClickACrearGrupoIncompleto(grupo);
        thenMeRedirigeALaVistaParaVolverALlenarElFormulario(mvc);
    }

    private ModelAndView whenDoyClickACrearGrupoIncompleto(DatosDeGrupo grupo) {
        when(servicioGrupo.crearGrupo(grupo)).thenReturn(null);
        return controladorCreacionDeGrupo.irALaVistaDeGrupoCreado(grupo);
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
        datosdegrupo.setPrivado(false);
        datosdegrupo.setCtdMaxima(ctdMaxima);
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
        datosdegrupo.setPrivado(false);
        datosdegrupo.setCtdMaxima(ctdMaxima);
        datosdegrupo.setDescripcion(descripcion);

        return datosdegrupo;
    }

    private ModelAndView whenDoyClickACrearGrupo(DatosDeGrupo datos) {
        when(servicioGrupo.crearGrupo(datos)).thenReturn(new Grupo());
        return controladorCreacionDeGrupo.irALaVistaDeGrupoCreado(datos);
    }

    private void thenMeRedirigeALaVistaDeGrupoCreado(ModelAndView mvc) {
        assertThat("vistaGrupo").isEqualTo(mvc.getViewName());
    }

}


