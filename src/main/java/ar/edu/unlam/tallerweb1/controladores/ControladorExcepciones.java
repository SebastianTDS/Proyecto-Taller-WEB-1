package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.util.exceptions.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

@ControllerAdvice
public class ControladorExcepciones {

	@ExceptionHandler(value = LimiteDeUsuariosFueraDeRango.class)
	public String errorAlModificarGrupo(LimiteDeUsuariosFueraDeRango e, HttpServletRequest request) {
		errorMsg(request, e.getMessage());
		return "redirect:/grupos/" + e.getIdGrupoError();
	}

	@ExceptionHandler(value = FormularioDeGrupoIncompleto.class)
	public String errorPorCamposIncompletos(FormularioDeGrupoIncompleto e, HttpServletRequest request) {
		errorMsg(request, e.getMessage());
		return "redirect:/ir-a-crear-nuevo-grupo";
	}

	@ExceptionHandler(value = { GrupoInexistenteException.class, NoEsMiembroException.class })
	public String errorAlBuscarGrupo(RuntimeException e, HttpServletRequest request) {
		errorMsg(request, e.getMessage());
		return "redirect:/ir-a-home";
	}

	@ExceptionHandler(value = FalloAlUnirseAlGrupo.class)
	public String errorAlUnirseAlGrupo(FalloAlUnirseAlGrupo e, HttpServletRequest request) {
		errorMsg(request, e.getMessage());
		return "redirect:/ir-a-home";
	}

	@ExceptionHandler(value = YaEstoyEnElGrupo.class)
	public String errorAlUnirseAlGrupoQueYaSoyMiembro(YaEstoyEnElGrupo e, HttpServletRequest request) {
		errorMsg(request, e.getMessage());
		return "redirect:/grupos/" + e.getIdGrupo();
	}

	@ExceptionHandler(value = NoSeEnvioElMensaje.class)
	public String errorAlEnviarUnMensaje(NoSeEnvioElMensaje e, HttpServletRequest request) {
		errorMsg(request, e.getMessage());
		return "redirect:/grupos/" + e.getIdGrupo();
	}

	@ExceptionHandler(value = UsuarioNoEncontradoException.class)
	public String errorAlLoguearUsuario(UsuarioNoEncontradoException e, HttpServletRequest request) {
		errorMsg(request, e.getMessage());
		return "redirect:/ir-a-login";
	}

	@ExceptionHandler(value = UsuarioSinPermisosException.class)
	public String errorAlVerificarPermiso(UsuarioSinPermisosException e, HttpServletRequest request) {
		errorMsg(request, e.getMessage());
		return "redirect:/grupos/" + e.getIdGrupo();
	}

	@ExceptionHandler(value = FalloAlProcesarSolicitud.class)
	public String falloAlProcesarSolicitud(FalloAlProcesarSolicitud e, HttpServletRequest request) {
		errorMsg(request, e.getMessage());
		return "redirect:/solicitudes";
	}

	@ExceptionHandler(value = FalloAlInvitarUsuario.class)
	public String falloAlInvitarUsuario(FalloAlInvitarUsuario e, HttpServletRequest request) {
		errorMsg(request, e.getMessage());
		return "redirect:/grupos/" + e.getGrupo();
	}

	@ExceptionHandler(value = ArchivoNoEncontradoException.class)
	public String errorAlBuscarArchivo(ArchivoNoEncontradoException e, HttpServletRequest request) {
		errorMsg(request, e.getMessage());
		return "redirect:/ir-a-home";
	}

	@ExceptionHandler(value = UsuarioExistenteException.class)
	public String usuarioExistenteEnRegistrar(UsuarioExistenteException e, HttpServletRequest request) {
		errorMsg(request, e.getMessage());
		return "redirect:/ir-a-registro";
	}

	@ExceptionHandler(value = NoCoincidenContraseniasException.class)
	public String noCoincidenContraseniasException(NoCoincidenContraseniasException e, HttpServletRequest request) {
		errorMsg(request, e.getMessage());
		return "redirect:/ir-a-registro";
	}

	@ExceptionHandler(value = CampoVacioException.class)
	public String campoVacioException(CampoVacioException e, HttpServletRequest request) {
		errorMsg(request, e.getMessage());
		return "redirect:/ir-a-registro";
	}

	private void errorMsg(HttpServletRequest request, String mensaje) {
		FlashMap outputFlashMap = RequestContextUtils.getOutputFlashMap(request);
		if (outputFlashMap != null) {
			outputFlashMap.put("error", mensaje);
		}
	}

}
