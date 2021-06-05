package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.dto.DatosDeUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificaciones;
import ar.edu.unlam.tallerweb1.util.auxClass.Check;

@Controller
@RequestMapping("/perfil/notificaciones")
public class ControladorNotificaciones {

	private ServicioNotificaciones service;
	
	@Autowired
	public ControladorNotificaciones(ServicioNotificaciones service) {
		this.service = service;
	}

	@RequestMapping("/simularSesion")
	public ModelAndView simularSesion(HttpServletRequest request) {
		request.getSession().setAttribute("Usuario", obtenerDto());
		return new ModelAndView("redirect:/");
	}

	@RequestMapping("/")
	public ModelAndView verNotificaciones(HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		DatosDeUsuario attrSesion = (DatosDeUsuario) request.getSession().getAttribute("Usuario");

		if (Check.isNull(attrSesion))
			return new ModelAndView("redirect:/login");
		
		modelo.put("Notificaciones", service.obtenerNotificacionesPor(attrSesion.getId()));
			
		return new ModelAndView("vistaNotificaciones", modelo);
	}

	private DatosDeUsuario obtenerDto() {
		DatosDeUsuario dto = new DatosDeUsuario();
		
		dto.setId(1L);
		dto.setNombre("Pedro");
		dto.setEmail("carlitos@unlam.edu.ar");
		
		return dto;
	}
	
}
