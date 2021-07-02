package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificaciones;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;

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
		Usuario usuarioLogueado = validarSesion(request);

		servicioGrupo.ingresarUsuarioAlGrupo(usuarioLogueado.getId(), id);
		servicioNotificaciones.notificarNuevoIngreso(id, usuarioLogueado);

		return new ModelAndView("redirect:/grupos/" + id);
	}

	@RequestMapping(value = "/ir-a-crear-nuevo-grupo")
	public ModelAndView irACrearGrupo(HttpServletRequest request) {
		validarSesion(request);
		ModelMap model = new ModelMap();

		model.put("carreras", servicioGrupo.buscarTodasLasCarreras());
		model.put("materias", servicioGrupo.buscarTodasLasMaterias());
		model.put("datos", new DatosDeGrupo());

		return new ModelAndView("vistaParaCrearGrupo", model);
	}

	@RequestMapping("/ir-a-home")
	public ModelAndView irAHome(HttpServletRequest request) {
		Usuario usuarioLogueado = validarSesion(request);
		return cargarModelAndViewHome(servicioGrupo.buscarTodos(usuarioLogueado));

	}

	@RequestMapping("/buscar-grupos")
	public ModelAndView buscarGrupos(HttpServletRequest request, @ModelAttribute DatosDeGrupo datosDeBusqueda) {
		validarSesion(request);
		return cargarModelAndViewHome(servicioGrupo.buscarGrupoPorDatos(datosDeBusqueda));
	}
	
	@RequestMapping("/ir-a-foros-materias")
	public ModelAndView IrAForosMaterias(HttpServletRequest request) {
		validarSesion(request);
		ModelMap model = new ModelMap();
		model.put("grupos",servicioGrupo.buscarForosMateria());
		return new  ModelAndView("vistaForosMaterias",model);
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
		model.put("grupos",servicioGrupo.buscarForos());
		return new  ModelAndView("vistaForosMaterias",model);
	}

	private Usuario validarSesion(HttpServletRequest request) {
		Usuario objetivo = (Usuario) request.getSession().getAttribute("USUARIO");
		
		if(objetivo == null)
			throw new UsuarioNoEncontradoException("");
			
		return objetivo;
  }
}
