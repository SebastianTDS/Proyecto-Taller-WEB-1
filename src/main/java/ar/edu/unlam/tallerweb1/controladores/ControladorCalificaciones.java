package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCalificacion;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ModelAndView calificar(HttpServletRequest request, RedirectAttributes model, @RequestParam Long idCalificacion,@RequestParam Long calificaion) {
		Usuario usuarioEnSesion = validarSesion(request);

		servicioCalificacion.calificar(usuarioEnSesion.getId(),idCalificacion,calificaion);

		model.addFlashAttribute("mensaje", "Ha calificado a un usuario.");
		
		return new ModelAndView("redirect:/calificaciones");
	}
	

	
	private Usuario validarSesion(HttpServletRequest request) {
		Usuario objetivo = (Usuario) request.getSession().getAttribute("USUARIO");
		
		if(objetivo == null)
			throw new UsuarioNoEncontradoException("No existe un usuario Logueado!");
			
		return objetivo;
	}


}
