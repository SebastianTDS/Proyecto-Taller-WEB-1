package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.dto.DatosDeMensaje;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.*;
import ar.edu.unlam.tallerweb1.util.auxClass.Check;
import ar.edu.unlam.tallerweb1.util.enums.Disponibilidad;
import ar.edu.unlam.tallerweb1.util.enums.Permiso;
import ar.edu.unlam.tallerweb1.util.exceptions.FalloAlUnirseAlGrupo;
import ar.edu.unlam.tallerweb1.util.exceptions.FormularioDeGrupoIncompleto;
import ar.edu.unlam.tallerweb1.util.exceptions.GrupoInexistenteException;

import ar.edu.unlam.tallerweb1.util.exceptions.NoEsMiembroException;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioSinPermisosException;
import ar.edu.unlam.tallerweb1.util.exceptions.NoSeEnvioElMensaje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("servicioGrupos")
@Transactional
public class ServicioGrupoImpl implements ServicioGrupo {

	private final RepositorioGrupo repoGrupo;
	private final RepositorioCarrera repoCarrera;
	private final RepositorioMateria repoMateria;
	private final RepositorioUsuario repoUsuario;
	private final RepositorioMensaje repoMsj;

	@Autowired
	public ServicioGrupoImpl(RepositorioGrupo repoGrupo, RepositorioCarrera repoCarrera, RepositorioMateria repoMateria,
			RepositorioUsuario repoUsuario, RepositorioMensaje repoMsj) {
		this.repoGrupo = repoGrupo;
		this.repoCarrera = repoCarrera;
		this.repoMateria = repoMateria;
		this.repoUsuario = repoUsuario;
		this.repoMsj = repoMsj;
	}

	@Override
	public Grupo buscarGrupoPorID(Long idBuscado) {
		Grupo encontrado = repoGrupo.getGrupoByID(idBuscado);

		if (Check.isNull(encontrado))
			throw new GrupoInexistenteException("Grupo buscado no encontrado");

		return encontrado;
	}

	@Override
	public void IngresarUnMensajeAlGrupo(Long idUsuario, DatosDeMensaje datosMensaje) {
		Grupo grupoAAcceder = repoGrupo.getGrupoByID(datosMensaje.getId());
		Usuario usuarioAInsertar = repoUsuario.getUsuarioByID(idUsuario);
		if (grupoAAcceder == null || usuarioAInsertar == null || verificarDatosDeMSJ(datosMensaje))
			throw new NoSeEnvioElMensaje(grupoAAcceder.getId());
		Mensaje mensajeCreado = crearMensaje(usuarioAInsertar, grupoAAcceder, datosMensaje);
		repoMsj.save(mensajeCreado);
	}

	@Override
	public void validarPermiso(Long idUsuario, Long idGrupo, Permiso permisoAValidar) {
		Grupo objetivo = repoGrupo.getGrupoByID(idGrupo);
		Usuario usuarioAValidar = repoUsuario.getUsuarioByID(idUsuario);

		if (objetivo == null)
			throw new GrupoInexistenteException("El grupo no existe");

		if (usuarioAValidar == null)
			throw new UsuarioNoEncontradoException("El usuario no existe");

		if (!objetivo.getListaDeUsuarios().contains(usuarioAValidar))
			throw new NoEsMiembroException();

		if (permisoAValidar == Permiso.MODIFICACION && !usuarioAValidar.equals(objetivo.getAdministrador()))
			throw new UsuarioSinPermisosException("No tienes permiso para realizar esta operacion", idGrupo);
	}

	@Override
	public void modificarGrupo(DatosDeGrupo formulario) {
		Grupo objetivo = repoGrupo.getGrupoByID(formulario.getId());

		if (Check.isNull(objetivo))
			throw new GrupoInexistenteException("No se puede modificar un grupo inexistente");

		objetivo.actualizar(formulario);

		repoGrupo.actualizarGrupo(objetivo);
	}

	@Override
	public void eliminarGrupo(Long idBuscado) {
		Grupo objetivo = repoGrupo.getGrupoByID(idBuscado);

		if (Check.isNull(objetivo))
			throw new GrupoInexistenteException("No se puede eliminar un grupo inexistente");

		repoGrupo.eliminarGrupo(objetivo);
	}

	@Override
	public void ingresarUsuarioAlGrupo(Long idUsuario, Long idGrupo) {
		Grupo grupoAAcceder = repoGrupo.getGrupoByID(idGrupo);
		Usuario usuarioAInsertar = repoUsuario.getUsuarioByID(idUsuario);

		if (grupoAAcceder == null || usuarioAInsertar == null || grupoAAcceder.grupoLleno()
				|| grupoAAcceder.getCerrado())
			throw new FalloAlUnirseAlGrupo();

		grupoAAcceder.agregarUsuarioAlGrupo(usuarioAInsertar);
		repoGrupo.actualizarGrupo(grupoAAcceder);
	}

	@Override
	public List<Grupo> buscarTodosMisGrupos(Usuario usuarioSesion) {
		return repoGrupo.buscarTodosMisGrupos(usuarioSesion);
	}

	@Override
	public Grupo crearGrupo(DatosDeGrupo datosDeGrupo) {
		Grupo grupoGenerado = datosDeGrupo.crearGrupoAPartirDeDatosDeGrupo();

		if (grupoGenerado == null) {
			throw new FormularioDeGrupoIncompleto();
		}

		if (grupoGenerado.getAdministrador() == null) {
			throw new UsuarioNoEncontradoException("Usuario inexistente");
		}

		Usuario administrador = repoUsuario.getUsuarioByID(grupoGenerado.getAdministrador().getId());

		materiaNoSeaNull(grupoGenerado, datosDeGrupo.getMateria());
		carreraNoSeaNull(grupoGenerado, datosDeGrupo.getCarrera());

		grupoGenerado.agregarUsuarioAlGrupo(administrador);
		repoGrupo.guardarGrupo(grupoGenerado);
		return grupoGenerado;
	}

	@Override
	public List<Grupo> buscarTodos() {
		return repoGrupo.buscarTodos();
	}

	@Override
	public List<Carrera> buscarTodasLasCarreras() {
		return repoCarrera.buscarTodasLasCarreras();
	}

	@Override
	public List<Materia> buscarTodasLasMaterias() {
		return repoMateria.buscarTodasLasMaterias();
	}

	@Override
	public List<Grupo> buscarGrupoPorDatos(DatosDeGrupo datosParaBuscarUnGrupo) {
		return filtrarPorCupo(repoGrupo.buscarGrupoPorDatos(datosParaBuscarUnGrupo),
				datosParaBuscarUnGrupo.getDisponibilidad());
	}

	private List<Grupo> filtrarPorCupo(List<Grupo> grupos, Disponibilidad disponibilidad) {
		List<Grupo> prov = new ArrayList<Grupo>();
		if (disponibilidad == Disponibilidad.LLENO) {
			for (Grupo aux : grupos) {
				if (aux.grupoLleno()) {
					prov.add(aux);
				}
			}
			return new ArrayList<Grupo>(prov);
		} else if (disponibilidad == Disponibilidad.DISPONIBLE) {
			for (Grupo aux : grupos) {
				if (!aux.grupoLleno()) {
					prov.add(aux);
				}
			}
			return new ArrayList<Grupo>(prov);
		} else {
			prov.addAll(grupos);
		}
		return prov;
	}

	private boolean verificarDatosDeMSJ(DatosDeMensaje datosMensaje) {
		return datosMensaje.getMensaje() == null || datosMensaje.getMensaje().isBlank()
				|| datosMensaje.getMensaje().equals("<p><br></p>");
	}

	private Mensaje crearMensaje(Usuario usuarioAInsertar, Grupo grupoAAcceder, DatosDeMensaje datosMensaje) {
		Mensaje mensaje = new Mensaje();
		mensaje.setUsuario(usuarioAInsertar);
		mensaje.setMensaje(datosMensaje.getMensaje());
		mensaje.setFecha(LocalDateTime.now().withNano(0));
		mensaje.setGrupo(grupoAAcceder);
		return mensaje;
	}

	private void materiaNoSeaNull(Grupo grupoGenerado, Long idMateria) {
		Materia materiaEncontrada = repoMateria.buscarMateriaPorId(idMateria);
		if (materiaEncontrada == null)
			throw new FormularioDeGrupoIncompleto();
		grupoGenerado.setMateria(materiaEncontrada);
	}

	private void carreraNoSeaNull(Grupo grupoGenerado, Long idCarrera) {
		Carrera carreraEncontrada = repoCarrera.buscarCarreraPorId(idCarrera);
		if (carreraEncontrada == null)
			throw new FormularioDeGrupoIncompleto();
		grupoGenerado.setCarrera(carreraEncontrada);
	}

}