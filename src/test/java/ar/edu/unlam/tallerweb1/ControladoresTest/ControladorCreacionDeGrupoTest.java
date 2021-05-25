package ar.edu.unlam.tallerweb1.ControladoresTest;

import ar.edu.unlam.tallerweb1.controladores.ControladorCreacionDeGrupo;
import ar.edu.unlam.tallerweb1.controladores.ControladorHome;
import ar.edu.unlam.tallerweb1.modelo.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ControladorCreacionDeGrupoTest {

    private ControladorCreacionDeGrupo controladorCreacionDeGrupo;
    private ServicioGrupo servicioGrupo;
    @Before
    public void init(){
         servicioGrupo = mock(ServicioGrupo.class);
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
        String materia = "Taller web";
        Turno turno = Turno.NOCHE;
        Boolean privado = false;
        Integer ctdMaxima = 5;
        String descripcion =  "Grupo de test para taller web";
        datosdegrupo.setNombre(nombre);
        datosdegrupo.setMateria(materia);
        datosdegrupo.setTurno(turno);
        datosdegrupo.setPrivado(privado);
        datosdegrupo.setCtdMaxima(ctdMaxima);
        datosdegrupo.setDescripcion(descripcion);
        return datosdegrupo;
    }

    private DatosDeGrupo givenDatosDeGrupo() {
        DatosDeGrupo datosdegrupo = new DatosDeGrupo();
        String nombre = "Los Picateclas";
        String carrera = "Desarrollo web";
        String materia = "Taller web";
        Turno turno = Turno.NOCHE;
        Boolean privado = false;
        Integer ctdMaxima = 5;
        String descripcion =  "Grupo de test para taller web";

        datosdegrupo.setNombre(nombre);
        datosdegrupo.setCarrera(carrera);
        datosdegrupo.setMateria(materia);
        datosdegrupo.setTurno(turno);
        datosdegrupo.setPrivado(privado);
        datosdegrupo.setCtdMaxima(ctdMaxima);
        datosdegrupo.setDescripcion(descripcion);

        return datosdegrupo;
    }

    private ModelAndView whenDoyClickACrearGrupo(DatosDeGrupo datos) {
        when(servicioGrupo.crearGrupo(datos)).thenReturn(new Grupo());
        return controladorCreacionDeGrupo.irALaVistaDeGrupoCreado(datos);
    }

    private void thenMeRedirigeALaVistaDeGrupoCreado(ModelAndView mvc) {
        assertThat("VistaGrupo").isEqualTo(mvc.getViewName());
    }

    }


