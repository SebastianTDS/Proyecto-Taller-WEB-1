package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificaciones;
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
	public ModelAndView solicitarUnirseAGrupo(@RequestParam Long idGrupo, HttpServletRequest request) {
		Usuario usuarioEnSesion = validarSesion(request);
		ModelMap modelo = new ModelMap();
		
		servicioSolicitud.solicitarInclusionAGrupo(idGrupo, usuarioEnSesion.getId());
		
		modelo.put("mensaje", "Solicitud Enviada");
		
		return new ModelAndView("redirect:/ir-a-home", modelo);
	}
	
	@RequestMapping("/aceptar-solicitud")
	public ModelAndView aceptarUnaSolicitud(@RequestParam Long idSolicitudAceptada, HttpServletRequest request) {
		Usuario usuarioEnSesion = validarSesion(request);
		ModelMap modelo = new ModelMap();
		
		servicioSolicitud.aprobarSolicitud(idSolicitudAceptada, usuarioEnSesion.getId());
		
		modelo.put("mensaje", "Solicitud Aceptada");
		
		return new ModelAndView("redirect:/solicitudes", modelo);
	}
	
	@RequestMapping("/rechazar-solicitud")
	public ModelAndView rechazarUnaSolicitud(@RequestParam Long idSolicitudRechazada, HttpServletRequest request) {
		Usuario usuarioEnSesion = validarSesion(request);
		ModelMap modelo = new ModelMap();
		
		servicioSolicitud.rechazarSolicitud(idSolicitudRechazada, usuarioEnSesion.getId());
		
		modelo.put("mensaje", "Solicitud Rechazada");
		
		return new ModelAndView("redirect:/solicitudes", modelo);
	}
	
	private Usuario validarSesion(HttpServletRequest request) {
		Usuario objetivo = (Usuario) request.getSession().getAttribute("USUARIO");
		
		if(objetivo == null)
			throw new UsuarioNoEncontradoException("");
			
		return objetivo;
	}


}
