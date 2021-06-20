package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Carrera;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Materia;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioGrupo {

    Grupo crearGrupo(DatosDeGrupo grupoNuevo, Long owner);

    List<Grupo> buscarTodos();

    List<Carrera> buscarTodasLasCarreras();

    List<Materia> buscarTodasLasMaterias();

    List<Grupo> buscarGrupoPorDatos(DatosDeGrupo datosParaBuscarUnGrupo);
    
    Grupo buscarGrupoPorID(Long idBuscado);

	void modificarGrupo(Long id, DatosDeGrupo formulario);

	void eliminarGrupo(Long idBuscado);

    void IngresarUsuarioAlGrupo(Long idUsuario, Long idGrupo);

    List<Grupo> buscarTodosMisGrupos(Usuario usuarioSesion);
}
