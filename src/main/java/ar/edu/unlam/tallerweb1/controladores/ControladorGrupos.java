package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupos;

@Controller
@RequestMapping("/grupos")
public class ControladorGrupos {

	private ServicioGrupos service;

	@Autowired
	public ControladorGrupos(ServicioGrupos service) {
		this.service = service;
	}

	@RequestMapping("/{id}")
	public ModelAndView buscarGrupo(@PathVariable Long id) {
		ModelMap modelo = new ModelMap();
		Grupo buscado = service.buscarGrupoPorID(id);

		if (buscado != null) {
			modelo.put("grupo", buscado);
			return new ModelAndView("vistaGrupoEspecifico", modelo);
		} else
			return new ModelAndView("redirect:/");
	}

	@RequestMapping("/{id}/modificarGrupo")
	public ModelAndView cambiarDatosGrupo(Long id, Grupo formulario) {
		ModelMap modelo = new ModelMap();
		if(service.modificarGrupo(id, formulario))
			modelo.put("mensaje", "Datos actualizados");
		else
			modelo.put("mensaje", "Error al actualizar los datos");
		
		return new ModelAndView("redirect:/grupos/" + id, modelo);
	}

	@RequestMapping("/eliminarGrupo")
	public ModelAndView eliminarGrupo(Long id) {
		ModelMap modelo = new ModelMap();

		if (eliminarGrupoPorID(id)) {
			modelo.put("mensaje", "Grupo eliminado con exito!");
			return new ModelAndView("redirect:/", modelo);
		} else
			return new ModelAndView("redirect:/grupos/" + id);
	}

	private Boolean eliminarGrupoPorID(Long id) {
		return true;
	}

}
