package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificaciones;

@Controller
@RequestMapping("/perfil/notificaciones")
public class ControladorNotificaciones {

	private ServicioNotificaciones service;
	
	@Autowired
	public ControladorNotificaciones(ServicioNotificaciones service) {
		this.service = service;
	}

	@RequestMapping("")
	public ModelAndView verNotificaciones(HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		Usuario usuarioEnSesion = (Usuario) request.getSession().getAttribute("USUARIO");

		if (usuarioEnSesion == null)
			return new ModelAndView("redirect:/ir-a-login");
		
		modelo.put("Notificaciones", service.obtenerNotificacionesPor(usuarioEnSesion.getId()));
		request.getSession().setAttribute("PENDIENTES", service.hayPendientes(usuarioEnSesion.getId()));
		
		return new ModelAndView("vistaNotificaciones", modelo);
	}

}
