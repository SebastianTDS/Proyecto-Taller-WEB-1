package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.dto.DatosDeMensaje;
import ar.edu.unlam.tallerweb1.modelo.Calificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCalificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSolicitud;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/calificaciones")
public class ControladorCalificaciones {

	private ServicioCalificacion servicioCalificacion;

	@Autowired
	public ControladorCalificaciones(ServicioCalificacion calificacion) {
		this.servicioCalificacion = calificacion;
	}

	@RequestMapping("")
	public ModelAndView verCalificaciones(HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		Usuario usuarioEnSesion = validarSesion(request);
		
		modelo.put("calificacionesPendientes", servicioCalificacion.buscarCalificaciones(usuarioEnSesion.getId()));
		
		return new ModelAndView("vistaCalificaciones", modelo);
	}

	
	@RequestMapping("/calificar")
	public ModelAndView calificar(HttpServletRequest request, @RequestParam Long idCalificacion,@RequestParam Long calificaion) {
		Usuario usuarioEnSesion = validarSesion(request);
		ModelMap modelo = new ModelMap();

		servicioCalificacion.calificar(usuarioEnSesion.getId(),idCalificacion,calificaion);

		modelo.put("mensaje", "Calificacion realizada");
		
		return new ModelAndView("redirect:/calificaciones", modelo);
	}
	

	
	private Usuario validarSesion(HttpServletRequest request) {
		Usuario objetivo = (Usuario) request.getSession().getAttribute("USUARIO");
		
		if(objetivo == null)
			throw new UsuarioNoEncontradoException("");
			
		return objetivo;
	}


}
