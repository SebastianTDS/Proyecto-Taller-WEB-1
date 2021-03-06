package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.*;

import static org.assertj.core.api.Assertions.*;

import ar.edu.unlam.tallerweb1.util.enums.Privacidad;
import ar.edu.unlam.tallerweb1.util.enums.Turno;
import ar.edu.unlam.tallerweb1.util.exceptions.FalloAlUnirseAlGrupo;
import ar.edu.unlam.tallerweb1.util.exceptions.FormularioDeGrupoIncompleto;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.mockito.Mockito.*;

public class ServicioGrupoTest {

    private ServicioGrupo servicioGrupo;
    private RepositorioGrupo repositorioGrupo;
    private RepositorioMateria repositorioMateria;
    private RepositorioCarrera repositorioCarrera;
    private RepositorioUsuario repositorioUsuario;
    private final Usuario usuario = new Usuario();

    @Before
    public void init() {
        repositorioUsuario = mock(RepositorioUsuarioImpl.class);
        repositorioGrupo = mock(RepositorioGrupoImpl.class);
        repositorioCarrera = mock(RepositorioCarreraImpl.class);
        repositorioMateria = mock(RepositorioMateriaImpl.class);
        servicioGrupo = new ServicioGrupoImpl(repositorioGrupo, repositorioCarrera, repositorioMateria, repositorioUsuario);
    }



    @Test
    public void queSePuedaBorrarUnUsuarioDeUnGrupo() {
        Grupo buscado = givenQueExisteUnGrupo();
        Usuario usuario = givenQueExisteUnUsuario();
        Usuario usuario1 = givenQueExisteUnUsuario1();
        whenAsignoElUsuarioAlGrupo(buscado, usuario);
        whenAsignoElUsuarioAlGrupo(buscado, usuario1);

        whenBorroElUsuarioDelGrupo(buscado, usuario);
        thenVerificoQueElUsuarioFueBorradoDelGrupo(buscado, usuario);
    }



    private void whenBorroElUsuarioDelGrupo(Grupo buscado, Usuario usuario) {
        when(repositorioGrupo.getGrupoByID(buscado.getId())).thenReturn(buscado);
        when(repositorioUsuario.getUsuarioByID(usuario.getId())).thenReturn(usuario);

        servicioGrupo.borrarUsuarioDelGrupo(buscado.getId(),usuario.getId());
    }

    private void thenVerificoQueElUsuarioFueBorradoDelGrupo(Grupo buscado, Usuario usuario) {
        assertThat(buscado.getListaDeUsuarios().contains(usuario)).isFalse();
        verify(repositorioGrupo, times(3)).actualizarGrupo(buscado);
    }

    @Test
    public void queSePuedaUnirUnUsuarioAlGrupo() {
        Grupo buscado = givenQueExisteUnGrupo();
        Usuario usuario = givenQueExisteUnUsuario();
        whenAsignoElUsuarioAlGrupo(buscado, usuario);
        thenVerificoQueElUsuarioFueAgregado(buscado);
    }


    @Test(expected = FalloAlUnirseAlGrupo.class)
    public void testQueNoSePuedaUnirAGrupoCerrado() {
        Grupo buscado = givenQueExisteUnGrupoCerrado();
        Usuario usuario = givenQueExisteUnUsuario();
        whenAsignoElUsuarioAlGrupo(buscado, usuario);
    }

    private Grupo givenQueExisteUnGrupoCerrado() {
        Grupo grupo = new Grupo();
        grupo.setId(1L);
        grupo.setCantidadMax(2);
        grupo.setCerrado(true);
        return grupo;
    }

    @Test
    public void siElFormularioEstaCompletoQueSePuedaCrearElGrupo() {
        DatosDeGrupo losPicatecla = givenQueExisteDatosDeGrupo();
        Grupo grupoGeneradoAPartirDeLosDatosDeGrupo = whenCreoElGrupoConAtributosCompletos(losPicatecla);
        thenElGrupoSeCreo(grupoGeneradoAPartirDeLosDatosDeGrupo);
    }

    /*
     * @Test public void siElFormularioEstaCompletoQueSePuedaCrearElGrupo() {
     * DatosDeGrupo losPicatecla = givenQueExisteDatosDeGrupo(); Grupo
     * grupoGeneradoAPartirDeLosDatosDeGrupo =
     * whenCreoElGrupoConAtributosCompletos(losPicatecla);
     * thenElGrupoSeCreo(grupoGeneradoAPartirDeLosDatosDeGrupo); }
     */
    @Test(expected = FormularioDeGrupoIncompleto.class)
    public void siElFormularioEstaIncompletoQueSeLanzeUnaExcepcion() {
        DatosDeGrupo losPicatecla = givenQueExisteUnGrupoIncompleto();
        whenCreoElGrupoConAtributosIncompletos(losPicatecla);
    }

    @Test
    public void queSePuedaSolicitarTodosLosGrupos() {
        Grupo losPicatecla1 = givenDadoQueExisteUnGrupo();
        Usuario visitante = givenQueExisteUnUsuario();

        List<Grupo> gruposPresistidos = givenQueSeGuardenTodosLosGruposExistentes(losPicatecla1);
        List<Grupo> gruposEncontrados = whenBuscoTodosLosGrupos(gruposPresistidos, visitante);

        thenVerificoQueSeMuestrenTodosLosGrupos(gruposEncontrados);
    }

    @Test
    public void queSeObtenganTodasLasCarreras() {

        List<Carrera> listaDeCarreras = givenQueExisteUnaListaDeCarreras();

        List<Carrera> listaDeCarrerasEncontrada = whenbuscoTodasLasCarreras(listaDeCarreras);

        thenObtengoLaListaDeCarrerasYVerificoQueTengaElTamanoCorrespondiente(listaDeCarrerasEncontrada);
    }

    @Test
    public void queSeObtenganTodasLasMaterias() {

        List<Materia> listaDeMaterias = givenQueExisteUnaListaDeMaterias();

        List<Materia> listaDeMateriasEncontrada = whenbuscoTodasLasMaterias(listaDeMaterias);

        thenObtengoLaListaDeMateriasYVerificoQueTengaElTamanoCorrespondiente(listaDeMateriasEncontrada);
    }

    @Test
    public void queSeObtenganTodosLosGrupos() {
        Usuario visitante = givenQueExisteUnUsuario();
        List<Grupo> listaDeGrupos = givenQueExisteUnaListaDeGrupos();

        List<Grupo> listaDeGruposEncontrada = whenbuscoTodosLosGrupos(listaDeGrupos, visitante);

        thenObtengoLaListaDeGruposYVerificoQueTengaElTamanoCorrespondiente(listaDeGruposEncontrada);
    }

    @Test
    public void queSeObtenganLosGruposFiltrados() {
        List<Grupo> listaDeGrupos = givenQueExisteUnaListaDeGrupos();
        DatosDeGrupo datosDeGrupoParaBusqueda = givenQueExisteDatosDeGrupoParaBusqueda();
        List<Grupo> listaDeGruposEncontrada = whenbuscoLosGruposFiltrados(listaDeGrupos, datosDeGrupoParaBusqueda);
        thenObtengoLaListaDeGruposYVerificoQueTengaElTamanoCorrespondiente(listaDeGruposEncontrada);
    }

    @Test
    public void queSePuedaSolicitarTodosMisGrupos() {
        Grupo losPicatecla1 = givenDadoQueExisteUnGrupo();

        List<Grupo> gruposPresistidos = givenQueSeGuardenTodosLosGruposExistentes(losPicatecla1);
        List<Grupo> gruposEncontrados = whenBuscoTodosMisGrupos(gruposPresistidos);
        thenVerificoQueSeMuestrenTodosMisGrupos(gruposEncontrados);
    }

    private DatosDeGrupo givenQueExisteDatosDeGrupoParaBusqueda() {
        DatosDeGrupo datosDeGrupoParaBusqueda = new DatosDeGrupo();
        datosDeGrupoParaBusqueda.setNombre("casa");
        return datosDeGrupoParaBusqueda;
    }

    private List<Grupo> whenbuscoLosGruposFiltrados(List<Grupo> listaDeGrupos, DatosDeGrupo datosDeGrupoParaBusqueda) {
        when(repositorioGrupo.buscarGrupoPorDatos(datosDeGrupoParaBusqueda)).thenReturn(listaDeGrupos);
        return servicioGrupo.buscarGrupoPorDatos(datosDeGrupoParaBusqueda);
    }

    private List<Grupo> givenQueExisteUnaListaDeGrupos() {

        Grupo a = new Grupo();
        Grupo b = new Grupo();

        a.setId(1L);
        b.setId(2L);

        return Arrays.asList(a, b);
    }

    private List<Grupo> whenbuscoTodosLosGrupos(List<Grupo> listaDeGrupos, Usuario visitante) {
        when(repositorioGrupo.buscarTodos(visitante)).thenReturn(listaDeGrupos);
        return servicioGrupo.buscarTodos(visitante);
    }

    private void thenObtengoLaListaDeGruposYVerificoQueTengaElTamanoCorrespondiente(
            List<Grupo> listaDeGruposEncontrada) {
        assertThat(listaDeGruposEncontrada).hasSize(2);
    }

    private void thenObtengoLaListaDeMateriasYVerificoQueTengaElTamanoCorrespondiente(
            List<Materia> listaDeMateriasEncontrada) {
        assertThat(listaDeMateriasEncontrada).hasSize(2);
    }

    private List<Materia> whenbuscoTodasLasMaterias(List<Materia> listaDeMaterias) {
        when(repositorioMateria.buscarTodasLasMaterias()).thenReturn(listaDeMaterias);
        return servicioGrupo.buscarTodasLasMaterias();
    }

    private List<Materia> givenQueExisteUnaListaDeMaterias() {
        return Arrays.asList(new Materia(), new Materia());
    }

    private void thenObtengoLaListaDeCarrerasYVerificoQueTengaElTamanoCorrespondiente(
            List<Carrera> listaDeCarrerasEncontrada) {
        assertThat(listaDeCarrerasEncontrada).hasSize(2);
    }

    private List<Carrera> whenbuscoTodasLasCarreras(List<Carrera> listaDeCarreras) {
        when(repositorioCarrera.buscarTodasLasCarreras()).thenReturn(listaDeCarreras);
        return servicioGrupo.buscarTodasLasCarreras();
    }

    private List<Carrera> givenQueExisteUnaListaDeCarreras() {
        return Arrays.asList(new Carrera(), new Carrera());
    }

    private void thenVerificoQueSeMuestrenTodosLosGrupos(List<Grupo> grupos) {
        assertThat(grupos).hasSize(1);
    }

    private void thenVerificoQueSeMuestrenTodosMisGrupos(List<Grupo> grupos) {
        assertThat(grupos).hasSize(1);
    }

    private List<Grupo> whenBuscoTodosMisGrupos(List<Grupo> gruposPresistidos) {
        when(repositorioGrupo.buscarTodosMisGrupos(usuario)).thenReturn(gruposPresistidos);
        return servicioGrupo.buscarTodosMisGrupos(usuario);
    }

    private List<Grupo> whenBuscoTodosLosGrupos(List<Grupo> gruposPresistidos, Usuario visitante) {
        when(repositorioGrupo.buscarTodos(visitante)).thenReturn(gruposPresistidos);
        return servicioGrupo.buscarTodos(visitante);
    }

    private List<Grupo> givenQueSeGuardenTodosLosGruposExistentes(Grupo losPicatecla1) {
        List<Grupo> grupo = new ArrayList<>();
        grupo.add(losPicatecla1);
        return grupo;
    }

    private Grupo givenDadoQueExisteUnGrupo() {
        return new Grupo();
    }

    private void whenCreoElGrupoConAtributosIncompletos(DatosDeGrupo losPicatecla) {
        servicioGrupo.crearGrupo(losPicatecla);
    }

    private DatosDeGrupo givenQueExisteUnGrupoIncompleto() {
        DatosDeGrupo datosdegrupo = new DatosDeGrupo();
        String nombre = "Los Picateclas";
        Turno turno = Turno.NOCHE;
        Integer ctdMaxima = 5;
        String descripcion = "Grupo de test para taller web";
        datosdegrupo.setNombre(nombre);
        datosdegrupo.setTurno(turno);
        datosdegrupo.setPrivacidad(Privacidad.ABIERTO);
        datosdegrupo.setCantidadMax(ctdMaxima);
        datosdegrupo.setDescripcion(descripcion);

        return datosdegrupo;
    }

    private DatosDeGrupo givenQueExisteDatosDeGrupo() {
        DatosDeGrupo datosdegrupo = new DatosDeGrupo();
        String nombre = "picatecla";
        Turno turno = Turno.NOCHE;
        Integer ctdMaxima = 5;
        String descripcion = "Grupo de test para taller web";
        Long materia1 = 123L;
        Long carrera2 = 134L;
        datosdegrupo.setCarrera(carrera2);
        datosdegrupo.setMateria(materia1);
        datosdegrupo.setNombre(nombre);
        datosdegrupo.setTurno(turno);
        datosdegrupo.setPrivacidad(Privacidad.ABIERTO);
        datosdegrupo.setCantidadMax(ctdMaxima);
        datosdegrupo.setDescripcion(descripcion);
        datosdegrupo.setAdministrador(new Usuario());
        return datosdegrupo;
    }

    private Grupo whenCreoElGrupoConAtributosCompletos(DatosDeGrupo losPicatecla) {
        when(repositorioMateria.buscarMateriaPorId(losPicatecla.getMateria())).thenReturn(new Materia());
        when(repositorioCarrera.buscarCarreraPorId(losPicatecla.getCarrera())).thenReturn(new Carrera());

        when(repositorioGrupo.getGrupoByID(anyLong())).thenReturn(losPicatecla.crearGrupoAPartirDeDatosDeGrupo());
        when(repositorioUsuario.getUsuarioByID(anyLong())).thenReturn(losPicatecla.getAdministrador());

        return servicioGrupo.crearGrupo(losPicatecla);
    }

    private void thenElGrupoSeCreo(Grupo grupoGenerado) {
        verify(repositorioGrupo, times(1)).guardarGrupo(grupoGenerado);
        assertThat(grupoGenerado.getAdministrador()).isNotNull();
    }

    private void thenVerificoQueElUsuarioFueAgregado(Grupo buscado) {
        verify(repositorioGrupo, times(1)).actualizarGrupo(buscado);
    }

    private void whenAsignoElUsuarioAlGrupo(Grupo buscado, Usuario usuario) {
        when(repositorioGrupo.getGrupoByID(anyObject())).thenReturn(buscado);
        when(repositorioUsuario.getUsuarioByID(anyObject())).thenReturn(usuario);

        servicioGrupo.ingresarUsuarioAlGrupo(buscado.getId(), usuario.getId());
    }

    private Usuario givenQueExisteUnUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        return usuario;
    }
    private Usuario givenQueExisteUnUsuario1() {
        Usuario usuario = new Usuario();
        return usuario;
    }
    private Grupo givenQueExisteUnGrupo() {
        Grupo grupo = new Grupo();
        grupo.setId(1L);
        grupo.setCantidadMax(2);
        grupo.setCerrado(false);
        return grupo;
    }

}