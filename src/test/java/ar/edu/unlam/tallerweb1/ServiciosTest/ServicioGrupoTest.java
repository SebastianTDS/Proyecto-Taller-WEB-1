package ar.edu.unlam.tallerweb1.ServiciosTest;
import ar.edu.unlam.tallerweb1.modelo.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import static org.assertj.core.api.Assertions.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupoImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


public class ServicioGrupoTest {

    private ServicioGrupo servicio ;
    private RepositorioGrupo repositorio;
    @Before
    public void init(){
               repositorio = mock(RepositorioGrupoImpl.class);
                servicio = new ServicioGrupoImpl(repositorio);
    }


    @Test
    public void siElFormularioEstaCompletoQueSePuedaCrearElGrupo(){
        DatosDeGrupo losPicatecla= givenQueExisteDatosDeGrupo();
         Grupo grupoGeneradoAPartirDeLosDatosDeGrupo = whenCreoElGrupoConAtributosCompletos(losPicatecla);
        thenElGrupoSeCreo(grupoGeneradoAPartirDeLosDatosDeGrupo);
    }


    @Test
    public void siElFormularioEstaIncompletoQueNoSeCreeUnGrupo(){
                DatosDeGrupo losPicatecla=givenQueExisteUnGrupoIncompleto();
                Grupo grupo=whenCreoElGrupoConAtributosIncompletos(losPicatecla);
                thenElGrupoNoSeCreo(grupo);
        }

    @Test
    public void queSePuedaSolicitarTodosLosGrupos(){
        Grupo losPicatecla1= givenDadoQueExisteUnGrupo();
        Grupo losPicatecla2= givenDadoQueExisteUnGrupo();
        Grupo losPicatecla3= givenDadoQueExisteUnGrupo();
        List<Grupo> gruposPresistidos= givenQueSeGuardenTodosLosGruposExistentes(losPicatecla1,losPicatecla2,losPicatecla3);
        List<Grupo> gruposEncontrados= whenBuscoTodosLosGrupos(gruposPresistidos);
        thenVerificoQueSeMuestrenTodosLosGrupos(gruposEncontrados);
    }






    private void thenVerificoQueSeMuestrenTodosLosGrupos(List<Grupo> grupos) {
        assertThat(grupos).hasSize(3);
    }

    private List<Grupo> whenBuscoTodosLosGrupos(List<Grupo> gruposPresistidos) {
        when(repositorio.buscarTodos()).thenReturn(gruposPresistidos);
        return servicio.buscarTodos();
    }

    private List<Grupo> givenQueSeGuardenTodosLosGruposExistentes(Grupo losPicatecla1, Grupo losPicatecla2, Grupo losPicatecla3) {
        List<Grupo> grupo=new ArrayList<Grupo>();
        grupo.add(losPicatecla1);
        grupo.add(losPicatecla2);
        grupo.add(losPicatecla3);
        return grupo;
    }

    private Grupo givenDadoQueExisteUnGrupo() {
        return new Grupo();
    }


    private void thenElGrupoNoSeCreo(Grupo losPicatecla) {
        verify(repositorio,times(0)).guardarGrupo(losPicatecla);
    }

    private Grupo whenCreoElGrupoConAtributosIncompletos(DatosDeGrupo losPicatecla) {
            return servicio.crearGrupo(losPicatecla);
    }


    private DatosDeGrupo givenQueExisteUnGrupoIncompleto(){
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


    private DatosDeGrupo givenQueExisteDatosDeGrupo() {
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

    private Grupo whenCreoElGrupoConAtributosCompletos(DatosDeGrupo losPicatecla) {
        return servicio.crearGrupo(losPicatecla);
    }

    private void thenElGrupoSeCreo(Grupo grupoGenerado) {
        verify(repositorio,times(1)).guardarGrupo(grupoGenerado);
    }
}
