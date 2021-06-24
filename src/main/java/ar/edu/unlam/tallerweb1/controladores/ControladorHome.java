package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorHome {

	private final ServicioGrupo servicioGrupo;
	private final ServicioNotificaciones servicioNotificaciones;

	@Autowired
	public ControladorHome(ServicioGrupo servicioGrupo, ServicioNotificaciones servicioNotificaciones) {
		this.servicioGrupo = servicioGrupo;
		this.servicioNotificaciones = servicioNotificaciones;
	}

	@RequestMapping(value = "/ingresar-a-grupo", method = RequestMethod.POST)
	public ModelAndView IngresarAGrupo(HttpServletRequest request, @RequestParam Long id) {
		Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("USUARIO");

		servicioGrupo.ingresarUsuarioAlGrupo(usuarioLogueado.getId(), id);
		servicioNotificaciones.notificarNuevoIngreso(id, usuarioLogueado);

		return new ModelAndView("redirect:/grupos/" + id);
	}

	@RequestMapping(value = "/ir-a-crear-nuevo-grupo")
	public ModelAndView irACrearGrupo() {
		ModelMap model = new ModelMap();

		model.put("carreras", servicioGrupo.buscarTodasLasCarreras());
		model.put("materias", servicioGrupo.buscarTodasLasMaterias());
		model.put("datos", new DatosDeGrupo());

		return new ModelAndView("vistaParaCrearGrupo", model);
	}

	@RequestMapping("/ir-a-home")
	public ModelAndView irAHome() {
		return cargarModelAndViewHome(servicioGrupo.buscarTodos());

	}

	@RequestMapping("/buscar-grupos")
	public ModelAndView buscarGrupos(@ModelAttribute DatosDeGrupo datosDeBusqueda) {
		return cargarModelAndViewHome(servicioGrupo.buscarGrupoPorDatos(datosDeBusqueda));
	}

	private ModelAndView cargarModelAndViewHome(List<Grupo> listadoDeGrupos) {
		ModelMap model = new ModelMap();

		model.put("grupos", listadoDeGrupos);
		model.put("carreras", servicioGrupo.buscarTodasLasCarreras());
		model.put("materias", servicioGrupo.buscarTodasLasMaterias());
		model.put("datosParaBuscarUnGrupo", new DatosDeGrupo());

		return new ModelAndView("home", model);
	}

	@RequestMapping("/ir-a-foros-materias")
	public ModelAndView IrAForosMaterias() {
		ModelMap model = new ModelMap();
		model.put("grupos",servicioGrupo.buscarTodos());
		return new  ModelAndView("vistaForosMaterias",model);
	}
}
