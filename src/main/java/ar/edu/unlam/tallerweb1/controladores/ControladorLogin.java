package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.dto.DatosDeUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
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

	private ServicioLogin servicioLogin;

	@Autowired
	public ControladorLogin(ServicioLogin servicioLogin) {
		this.servicioLogin = servicioLogin;
	}

	@RequestMapping("/ir-a-login")
	public ModelAndView irALogin() {
		ModelMap modelo = new ModelMap();
		DatosDeUsuario usuario = new DatosDeUsuario();
		modelo.put("usuario", usuario);
		return new ModelAndView("login", modelo);
	}

	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") DatosDeUsuario usuario, HttpServletRequest request) {
		Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);
		request.getSession().setAttribute("USUARIO", usuarioBuscado);
		return new ModelAndView("redirect:/ir-a-home");
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("index");
	}

	@RequestMapping(path = "/ir-a-inicio", method = RequestMethod.GET)
	public ModelAndView inicio1() {
		return new ModelAndView("index");
	}

	@RequestMapping(path = "/cerrar-sesion", method = RequestMethod.GET)
	public ModelAndView cerrarSession(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("index");
	}

}