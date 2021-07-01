package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.dto.DatosDeMensaje;
import ar.edu.unlam.tallerweb1.modelo.Carrera;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Materia;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.util.enums.Permiso;

public interface ServicioGrupo {

    Grupo crearGrupo(DatosDeGrupo grupoNuevo);

    List<Grupo> buscarTodos(Usuario logueado);
    
    List<Grupo> buscarForosMateria();

    List<Carrera> buscarTodasLasCarreras();

    List<Materia> buscarTodasLasMaterias();

    List<Grupo> buscarGrupoPorDatos(DatosDeGrupo filtros);
    
    Grupo buscarGrupoPorID(Long idBuscado);

	void modificarGrupo(DatosDeGrupo formulario);

	void eliminarGrupo(Long idBuscado);

    void ingresarUsuarioAlGrupo(Long idUsuario, Long idGrupo);

    List<Grupo> buscarTodosMisGrupos(Usuario usuarioSesion);

	void validarPermiso(Long idUsuario, Long idGrupo, Permiso permisoAValidar);

    void IngresarUnMensajeAlGrupo(Long idUsuario, DatosDeMensaje mensaje);

}
