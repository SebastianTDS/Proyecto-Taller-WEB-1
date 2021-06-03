package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCreacionDeGrupo {

	private final ServicioGrupo servicioGrupo;

	@Autowired
	public ControladorCreacionDeGrupo(ServicioGrupo servicioGrupo) {
		this.servicioGrupo = servicioGrupo;
	}

	@RequestMapping(value = "crear-grupo", method = RequestMethod.POST)
	public ModelAndView irALaVistaDeGrupoCreado(@ModelAttribute DatosDeGrupo datos) {
		Grupo grupo=servicioGrupo.crearGrupo(datos);
		return new ModelAndView("redirect:/grupos/" + grupo.getId());
	}

}
