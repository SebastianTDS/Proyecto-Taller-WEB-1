package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorHome {

    private final ServicioGrupo servicioGrupo;

    @Autowired
    public ControladorHome(ServicioGrupo servicioGrupo) {
        this.servicioGrupo = servicioGrupo;
    }

    @RequestMapping(value = "/ir-a-crear-nuevo-grupo")
    public ModelAndView irAlFormulario() {
        ModelMap model = new ModelMap();
        DatosDeGrupo datos = new DatosDeGrupo();
        List<Carrera> carreras = servicioGrupo.buscarTodasLasCarreras();
        List<Materia> materias = servicioGrupo.buscarTodasLasMaterias();
        model.put("carreras", carreras);
        model.put("materias", materias);
        model.put("datos", datos);
        return new ModelAndView("vistaParaCrearGrupo", model);
    }

    @RequestMapping("/ir-a-home")
    public ModelAndView irATest() {
        ModelMap model = new ModelMap();
        List<Grupo> grupos = servicioGrupo.buscarTodos();
        DatosDeGrupo datos = new DatosDeGrupo();
        List<Carrera> carreras = servicioGrupo.buscarTodasLasCarreras();
        List<Materia> materias = servicioGrupo.buscarTodasLasMaterias();
        model.put("carreras", carreras);
        model.put("materias", materias);
        model.put("datosParaBuscarUnGrupo", datos);
        int cantidadDeResultados = grupos.size();
        if (cantidadDeResultados > 0) {
            model.put("cantidadDeResultados", cantidadDeResultados);
            model.put("grupos", grupos);
        } else
            model.put("error", "No nay grupos disponibles");
        return new ModelAndView("home", model);
    }

    @RequestMapping("/buscar-grupos")
    public ModelAndView buscarGrupos(@ModelAttribute DatosDeGrupo datosParaBuscarUnGrupo) {
        ModelMap model = new ModelMap();
        DatosDeGrupo datos = new DatosDeGrupo();
        List<Carrera> carreras = servicioGrupo.buscarTodasLasCarreras();
        List<Materia> materias = servicioGrupo.buscarTodasLasMaterias();
        model.put("carreras", carreras);
        model.put("materias", materias);
        model.put("datosParaBuscarUnGrupo", datos);
        List<Grupo> grupos = servicioGrupo.buscarGrupoPorDatos(datosParaBuscarUnGrupo);
        int cantidadDeResultados = grupos.size();
        if (cantidadDeResultados > 0) {
            model.put("cantidadDeResultados", cantidadDeResultados);
            model.put("grupos", grupos);
        } else
            model.put("error", "No se encontro tu búsqueda");
        return new ModelAndView("home", model);
    }
}
