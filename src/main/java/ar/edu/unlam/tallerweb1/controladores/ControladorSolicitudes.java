package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioSolicitud;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;

@Controller
@RequestMapping("/solicitudes")
public class ControladorSolicitudes {

	private ServicioSolicitud servicioSolicitud;

	@Autowired
	public ControladorSolicitudes(ServicioSolicitud servicioSolicitud) {
		this.servicioSolicitud = servicioSolicitud;
	}

	@RequestMapping("")
	public ModelAndView verSolicitudes(HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		Usuario usuarioEnSesion = validarSesion(request);

		modelo.put("Solicitudes", servicioSolicitud.buscarSolicitudes(usuarioEnSesion.getId()));

		return new ModelAndView("vistaSolicitudes", modelo);
	}

	@RequestMapping("/solicitar-inclusion")
	public ModelAndView solicitarUnirseAGrupo(@RequestParam Long idGrupo, RedirectAttributes modelo, HttpServletRequest request) {
		Usuario usuarioEnSesion = validarSesion(request);

		servicioSolicitud.solicitarInclusionAGrupo(idGrupo, usuarioEnSesion.getId());

		modelo.addFlashAttribute("mensaje", "Solicitud Enviada");

		return new ModelAndView("redirect:/ir-a-home");
	}

	@RequestMapping("/aceptar-solicitud")
	public ModelAndView aceptarUnaSolicitud(@RequestParam Long idSolicitudAceptada, RedirectAttributes modelo, HttpServletRequest request) {
		Usuario usuarioEnSesion = validarSesion(request);

		servicioSolicitud.aprobarSolicitud(idSolicitudAceptada, usuarioEnSesion.getId());

		modelo.addFlashAttribute("mensaje", "Solicitud Aceptada");

		return new ModelAndView("redirect:/solicitudes");
	}

	@RequestMapping("/rechazar-solicitud")
	public ModelAndView rechazarUnaSolicitud(@RequestParam Long idSolicitudRechazada, RedirectAttributes modelo, HttpServletRequest request) {
		Usuario usuarioEnSesion = validarSesion(request);

		servicioSolicitud.rechazarSolicitud(idSolicitudRechazada, usuarioEnSesion.getId());

		modelo.addFlashAttribute("mensaje", "Solicitud Rechazada");

		return new ModelAndView("redirect:/solicitudes");
	}

	@RequestMapping(value = "/invitar", method = RequestMethod.POST)
	public ModelAndView invitarAGrupo(HttpServletRequest request , RedirectAttributes modelo, @RequestParam String correo,
			@RequestParam Long grupo) {
		Usuario anfitrion = (Usuario) request.getSession().getAttribute("USUARIO");

		servicioSolicitud.invitarUsuario(anfitrion.getId(), correo, grupo);
		modelo.addFlashAttribute("mensaje", "Solicitud Enviada");

		return new ModelAndView("redirect:/grupos/" + grupo);
	}

	private Usuario validarSesion(HttpServletRequest request) {
		Usuario objetivo = (Usuario) request.getSession().getAttribute("USUARIO");

		if (objetivo == null)
			throw new UsuarioNoEncontradoException("No existe un usuario logueado!");

		return objetivo;
	}

}
