package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.dto.DatosDeUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorLogin {

	private final ServicioLogin servicioLogin;
	private final ServicioNotificaciones servicioNotificacion;

	@Autowired
	public ControladorLogin(ServicioLogin servicioLogin, ServicioNotificaciones servicioNotificacion) {
		this.servicioLogin = servicioLogin;
		this.servicioNotificacion = servicioNotificacion;
	}

	@RequestMapping("/ir-a-login")
	public ModelAndView irALogin() {
		ModelMap modelo = new ModelMap();
		modelo.put("usuario", new DatosDeUsuario());
		return new ModelAndView("login", modelo);
	}

	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") DatosDeUsuario usuario, HttpServletRequest request) {
		Usuario buscado = servicioLogin.consultarUsuario(usuario);
		
		request.getSession().setAttribute("USUARIO", buscado);
		request.getSession().setAttribute("PENDIENTES", servicioNotificacion.hayPendientes(buscado.getId()));
		
		return new ModelAndView("redirect:/ir-a-home");
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("index");
	}

	@RequestMapping(path = "/cerrar-sesion", method = RequestMethod.GET)
	public ModelAndView cerrarSession(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:/");
	}

}