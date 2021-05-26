package ar.edu.unlam.tallerweb1.ServiciosTest;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import static org.assertj.core.api.Assertions.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupoImpl;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


public class ServicioGrupoTest{

    private ServicioGrupo servicioGrupo;
    private RepositorioGrupo repositorioGrupo;
    private RepositorioMateria repositorioMateria;
    private RepositorioCarrera repositorioCarrera;

    @Before
    public void init(){
               repositorioGrupo = mock(RepositorioGrupoImpl.class);
               repositorioCarrera=mock(RepositorioCarreraImpl.class);
               repositorioMateria = mock(RepositorioMateriaImpl.class);
               servicioGrupo = new ServicioGrupoImpl(repositorioGrupo, repositorioCarrera, repositorioMateria);
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

    @Test
    public void queSeObtenganTodasLasCarreras(){

       List<Carrera> listaDeCarreras = givenQueExisteUnaListaDeCarreras();

        List<Carrera> listaDeCarrerasEncontrada= whenbuscoTodasLasCarreras(listaDeCarreras);

        thenObtengoLaListaDeCarrerasYVerificoQueTengaElTamanoCorrespondiente(listaDeCarrerasEncontrada);
    }

    @Test
    public void queSeObtenganTodasLasMaterias(){

        List<Materia> listaDeMaterias = givenQueExisteUnaListaDeMaterias();

        List<Materia> listaDeMateriasEncontrada= whenbuscoTodasLasMaterias(listaDeMaterias);

        thenObtengoLaListaDeMateriasYVerificoQueTengaElTamanoCorrespondiente(listaDeMateriasEncontrada);
    }

    @Test
    public void  queNoSePuedaCrearUnGrupoConMateriaInexistenteProvenienteDeDatosDeGrupo(){

        DatosDeGrupo intentoDeGrupo = givenQueExistenDatosDeGrupoConUnIdInvalido();
        Grupo grupoNoGenerado=whenIntentoPersistirElGrupoConDatosDeGrupoConIdInvalido(intentoDeGrupo);
        thenElGrupoNoSeCreo(grupoNoGenerado);
    }

    @Test
    public void queSeObtenganTodosLosGrupos(){

        List<Grupo> listaDeGrupos = givenQueExisteUnaListaDeGrupos();

        List<Grupo> listaDeGruposEncontrada= whenbuscoTodosLosGrupos(listaDeGrupos);

        thenObtengoLaListaDeGruposYVerificoQueTengaElTamanoCorrespondiente(listaDeGruposEncontrada);
    }

    @Test
    public void queSeObtenganLosGruposFiltrados(){
        List<Grupo> listaDeGrupos = givenQueExisteUnaListaDeGrupos();
        DatosDeGrupoParaBusqueda datosDeGrupoParaBusqueda=givenQueExisteDatosDeGrupoParaBusqueda();
        List<Grupo> listaDeGruposEncontrada= whenbuscoLosGruposFiltrados(listaDeGrupos, datosDeGrupoParaBusqueda);
        thenObtengoLaListaDeGruposYVerificoQueTengaElTamanoCorrespondiente(listaDeGruposEncontrada);
    }

    private DatosDeGrupoParaBusqueda givenQueExisteDatosDeGrupoParaBusqueda() {
        DatosDeGrupoParaBusqueda datosDeGrupoParaBusqueda=new DatosDeGrupoParaBusqueda();
        datosDeGrupoParaBusqueda.setNombre("casa");
        return datosDeGrupoParaBusqueda;
    }

    private List<Grupo> whenbuscoLosGruposFiltrados(List<Grupo> listaDeGrupos, DatosDeGrupoParaBusqueda datosDeGrupoParaBusqueda) {
        when(repositorioGrupo.buscarGrupoPorDatos(datosDeGrupoParaBusqueda)).thenReturn(listaDeGrupos);
        return servicioGrupo.buscarGrupoPorDatos(datosDeGrupoParaBusqueda);
    }

    private List<Grupo> givenQueExisteUnaListaDeGrupos() {
        return Arrays.asList(new Grupo(),new Grupo());
    }

    private List<Grupo> whenbuscoTodosLosGrupos(List<Grupo> listaDeGrupos) {
        when(repositorioGrupo.buscarTodos()).thenReturn(listaDeGrupos);
        return servicioGrupo.buscarTodos();
    }

    private void thenObtengoLaListaDeGruposYVerificoQueTengaElTamanoCorrespondiente(List<Grupo> listaDeGruposEncontrada) {
        assertThat(listaDeGruposEncontrada).hasSize(2);
    }

    private Grupo whenIntentoPersistirElGrupoConDatosDeGrupoConIdInvalido(DatosDeGrupo intentoDeGrupo) {
        when(repositorioMateria.buscarMateriaPorId(intentoDeGrupo.getMateria())).thenReturn(null);
        return servicioGrupo.crearGrupo(intentoDeGrupo);
    }

    private DatosDeGrupo givenQueExistenDatosDeGrupoConUnIdInvalido(){
        DatosDeGrupo datosdegrupo = new DatosDeGrupo();
        Long carrera = 134L;
        Long materia = 123L;
        String nombre="picatecla";
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

    private void thenObtengoLaListaDeMateriasYVerificoQueTengaElTamanoCorrespondiente(List<Materia> listaDeMateriasEncontrada) {
        assertThat(listaDeMateriasEncontrada).hasSize(2);
    }

    private List<Materia> whenbuscoTodasLasMaterias(List<Materia> listaDeMaterias) {
        when(repositorioMateria.buscarTodasLasMaterias()).thenReturn(listaDeMaterias);
        return servicioGrupo.buscarTodasLasMaterias();
    }

    private List<Materia> givenQueExisteUnaListaDeMaterias() {
        return Arrays.asList(new Materia(),new Materia());
    }

    private void thenObtengoLaListaDeCarrerasYVerificoQueTengaElTamanoCorrespondiente(List<Carrera> listaDeCarrerasEncontrada) {
        assertThat(listaDeCarrerasEncontrada).hasSize(2);
    }

    private List<Carrera> whenbuscoTodasLasCarreras(List<Carrera> listaDeCarreras) {
        when(repositorioCarrera.buscarTodasLasCarreras()).thenReturn(listaDeCarreras);
        return servicioGrupo.buscarTodasLasCarreras();
    }

    private List<Carrera> givenQueExisteUnaListaDeCarreras() {
            return Arrays.asList(new Carrera(),new Carrera());
    }

    private void thenVerificoQueSeMuestrenTodosLosGrupos(List<Grupo> grupos) {
        assertThat(grupos).hasSize(3);
    }

    private List<Grupo> whenBuscoTodosLosGrupos(List<Grupo> gruposPresistidos) {
        when(repositorioGrupo.buscarTodos()).thenReturn(gruposPresistidos);
        return servicioGrupo.buscarTodos();
    }

    private List<Grupo> givenQueSeGuardenTodosLosGruposExistentes(Grupo losPicatecla1, Grupo losPicatecla2, Grupo losPicatecla3) {
        List<Grupo> grupo=new ArrayList<>();
        grupo.add(losPicatecla1);
        grupo.add(losPicatecla2);
        grupo.add(losPicatecla3);
        return grupo;
    }

    private Grupo givenDadoQueExisteUnGrupo() {
        return new Grupo();
    }

    private void thenElGrupoNoSeCreo(Grupo losPicatecla) {
        verify(repositorioGrupo,times(0)).guardarGrupo(losPicatecla);
    }

    private Grupo whenCreoElGrupoConAtributosIncompletos(DatosDeGrupo losPicatecla) {
            return servicioGrupo.crearGrupo(losPicatecla);
    }

    private DatosDeGrupo givenQueExisteUnGrupoIncompleto(){
        DatosDeGrupo datosdegrupo = new DatosDeGrupo();
        String nombre = "Los Picateclas";
        Turno turno = Turno.NOCHE;
        Integer ctdMaxima = 5;
        String descripcion =  "Grupo de test para taller web";
        datosdegrupo.setNombre(nombre);
        datosdegrupo.setTurno(turno);
        datosdegrupo.setPrivado(false);
        datosdegrupo.setCtdMaxima(ctdMaxima);
        datosdegrupo.setDescripcion(descripcion);

        return datosdegrupo;
    }

    private DatosDeGrupo givenQueExisteDatosDeGrupo() {
        DatosDeGrupo datosdegrupo = new DatosDeGrupo();
        Long carrera = 134L;
        Long materia = 123L;
        String nombre="picatecla";
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

    private Grupo whenCreoElGrupoConAtributosCompletos(DatosDeGrupo losPicatecla) {
        Long materia1=123L;
        Long carrera2=134L;
        Carrera carrera = new Carrera();
        Materia materia = new Materia();
        when(repositorioMateria.buscarMateriaPorId(materia1)).thenReturn(materia);
        when(repositorioCarrera.buscarCarreraPorId(carrera2)).thenReturn(carrera);
        return servicioGrupo.crearGrupo(losPicatecla);
    }

    private void thenElGrupoSeCreo(Grupo grupoGenerado) {
        verify(repositorioGrupo,times(1)).guardarGrupo(grupoGenerado);
    }
}
