package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.dto.DatosDeMensaje;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.*;
import static org.assertj.core.api.Assertions.*;
import ar.edu.unlam.tallerweb1.util.enums.Privacidad;
import ar.edu.unlam.tallerweb1.util.enums.Turno;
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
    private static final Usuario usuario = new Usuario();

    @Before
    public void init() {
        repositorioUsuario = mock(RepositorioUsuarioImpl.class);
        repositorioGrupo = mock(RepositorioGrupoImpl.class);
        repositorioCarrera = mock(RepositorioCarreraImpl.class);
        repositorioMateria = mock(RepositorioMateriaImpl.class);
        servicioGrupo = new ServicioGrupoImpl(repositorioGrupo, repositorioCarrera, repositorioMateria, repositorioUsuario);
    }

    @Test
    public void queSePuedaEnviarUnMensajeAlGrupo() {
        Grupo buscado = givenQueExisteUnGrupo();
        Usuario usuario = givenQueExisteUnUsuario();
        DatosDeMensaje datosMensaje = givenQueExisteUnDatosDeMensaje();
        whenAsignoElMensajeAlGrupo(buscado, usuario, datosMensaje);
        thenVerificoQueElMensajeFueAgregado(buscado);
    }

    private void whenAsignoElMensajeAlGrupo(Grupo buscado, Usuario usuario, DatosDeMensaje mensaje) {
        when(repositorioGrupo.getGrupoByID(buscado.getId())).thenReturn(buscado);
        when(repositorioUsuario.getUsuarioByID(usuario.getId())).thenReturn(usuario);
        servicioGrupo.IngresarUnMensajeAlGrupo(buscado.getId(), usuario.getId(),mensaje);
    }

    private void thenVerificoQueElMensajeFueAgregado(Grupo buscado) {
        verify(repositorioGrupo, times(1)).actualizarGrupo(buscado);
    }

    private DatosDeMensaje givenQueExisteUnDatosDeMensaje() {
        DatosDeMensaje mensaje = new DatosDeMensaje();
        mensaje.setMensaje("MSJ DE PRUEBA");
        return mensaje;
    }

    @Test
    public void queSePuedaUnirUnUsuarioAlGrupo() {
        Grupo buscado = givenQueExisteUnGrupo();
        Usuario usuario = givenQueExisteUnUsuario();
        whenAsignoElUsuarioAlGrupo(buscado, usuario);
        thenVerificoQueElUsuarioFueAgregado(buscado);
    }

    @Test
    public void siElFormularioEstaCompletoQueSePuedaCrearElGrupo() {
        DatosDeGrupo losPicatecla = givenQueExisteDatosDeGrupo();
        Grupo grupoGeneradoAPartirDeLosDatosDeGrupo = whenCreoElGrupoConAtributosCompletos(losPicatecla);
        thenElGrupoSeCreo(grupoGeneradoAPartirDeLosDatosDeGrupo);
    }

    @Test(expected = FormularioDeGrupoIncompleto.class)
    public void siElFormularioEstaIncompletoQueSeLanzeUnaExcepcion() {
        DatosDeGrupo losPicatecla = givenQueExisteUnGrupoIncompleto();
        whenCreoElGrupoConAtributosIncompletos(losPicatecla);
    }

    @Test
    public void queSePuedaSolicitarTodosLosGrupos() {
        Grupo losPicatecla1 = givenDadoQueExisteUnGrupo();
        List<Grupo> gruposPresistidos = givenQueSeGuardenTodosLosGruposExistentes(losPicatecla1);
        List<Grupo> gruposEncontrados = whenBuscoTodosLosGrupos(gruposPresistidos);
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

        List<Grupo> listaDeGrupos = givenQueExisteUnaListaDeGrupos();

        List<Grupo> listaDeGruposEncontrada = whenbuscoTodosLosGrupos(listaDeGrupos);

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
        return Arrays.asList(new Grupo(), new Grupo());
    }

    private List<Grupo> whenbuscoTodosLosGrupos(List<Grupo> listaDeGrupos) {
        when(repositorioGrupo.buscarTodos()).thenReturn(listaDeGrupos);
        return servicioGrupo.buscarTodos();
    }

    private void thenObtengoLaListaDeGruposYVerificoQueTengaElTamanoCorrespondiente(List<Grupo> listaDeGruposEncontrada) {
        assertThat(listaDeGruposEncontrada).hasSize(2);
    }

    private void thenObtengoLaListaDeMateriasYVerificoQueTengaElTamanoCorrespondiente(List<Materia> listaDeMateriasEncontrada) {
        assertThat(listaDeMateriasEncontrada).hasSize(2);
    }

    private List<Materia> whenbuscoTodasLasMaterias(List<Materia> listaDeMaterias) {
        when(repositorioMateria.buscarTodasLasMaterias()).thenReturn(listaDeMaterias);
        return servicioGrupo.buscarTodasLasMaterias();
    }

    private List<Materia> givenQueExisteUnaListaDeMaterias() {
        return Arrays.asList(new Materia(), new Materia());
    }

    private void thenObtengoLaListaDeCarrerasYVerificoQueTengaElTamanoCorrespondiente(List<Carrera> listaDeCarrerasEncontrada) {
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

    private List<Grupo> whenBuscoTodosLosGrupos(List<Grupo> gruposPresistidos) {
        when(repositorioGrupo.buscarTodos()).thenReturn(gruposPresistidos);
        return servicioGrupo.buscarTodos();
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
        return datosdegrupo;
    }

    private Grupo whenCreoElGrupoConAtributosCompletos(DatosDeGrupo losPicatecla) {
        when(repositorioMateria.buscarMateriaPorId(losPicatecla.getMateria())).thenReturn(new Materia());
        when(repositorioCarrera.buscarCarreraPorId(losPicatecla.getCarrera())).thenReturn(new Carrera());
        return servicioGrupo.crearGrupo(losPicatecla);
    }

    private void thenElGrupoSeCreo(Grupo grupoGenerado) {
        verify(repositorioGrupo, times(1)).guardarGrupo(grupoGenerado);
    }

    private void thenVerificoQueElUsuarioFueAgregado(Grupo buscado) {
        verify(repositorioGrupo, times(1)).actualizarGrupo(buscado);
    }

    private void whenAsignoElUsuarioAlGrupo(Grupo buscado, Usuario usuario) {
        when(repositorioGrupo.getGrupoByID(buscado.getId())).thenReturn(buscado);
        when(repositorioUsuario.getUsuarioByID(usuario.getId())).thenReturn(usuario);
        servicioGrupo.IngresarUsuarioAlGrupo(buscado.getId(), usuario.getId());
    }

    private Usuario givenQueExisteUnUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        return usuario;
    }

    private Grupo givenQueExisteUnGrupo() {
        Grupo grupo = new Grupo();
        grupo.setId(1L);
        grupo.setCantidadMax(2);
        return grupo;
    }

}