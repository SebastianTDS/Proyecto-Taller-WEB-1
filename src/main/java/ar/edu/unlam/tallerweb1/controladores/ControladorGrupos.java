package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.dto.DatosDeMensaje;
import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificaciones;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

@Controller
@RequestMapping("/grupos")
public class ControladorGrupos {

    private final ServicioGrupo servicioGrupo;
    private final ServicioNotificaciones servicioNotificacion;

    @Autowired
    public ControladorGrupos(ServicioGrupo servicioGrupo, ServicioNotificaciones servicioNotificacion) {
        this.servicioGrupo = servicioGrupo;
        this.servicioNotificacion = servicioNotificacion;
    }

    @RequestMapping("/{id}")
    public ModelAndView perfilDeGrupo(@PathVariable Long id) {
        ModelMap modelo = new ModelMap();
        Grupo buscado = servicioGrupo.buscarGrupoPorID(id);
        modelo.put("grupo", buscado);
        return new ModelAndView("vistaGrupo", modelo);
    }

    @RequestMapping("/{id}/edicion")
    public ModelAndView perfilDeGrupoEdicion(@PathVariable Long id) {
        ModelAndView vistaModificada = perfilDeGrupo(id);
        vistaModificada.addObject("formulario", new Grupo());
        return vistaModificada;
    }

    @RequestMapping(path = "/modificar", method = RequestMethod.POST)
    public ModelAndView cambiarDatosGrupo(@ModelAttribute("formulario") DatosDeGrupo form) {
        ModelMap modelo = new ModelMap();
        servicioGrupo.modificarGrupo(form.getId(), form);
        modelo.put("mensaje", "Datos actualizados");

        return new ModelAndView("redirect:/grupos/" + form.getId(), modelo);
    }

    @RequestMapping(path = "/eliminar", method = RequestMethod.POST)
    public ModelAndView eliminarGrupo(@RequestParam Long id) {
        ModelMap modelo = new ModelMap();

        servicioNotificacion.notificarEliminacionDeGrupo(id);
        servicioGrupo.eliminarGrupo(id);
        modelo.put("mensaje", "Grupo eliminado con exito!");
        return new ModelAndView("redirect:/ir-a-home", modelo);
    }

    @RequestMapping("/{id}/foro")
    public ModelAndView perfilDeGrupoForo(@PathVariable Long id) {
        ModelAndView vistaModificada = perfilDeGrupo(id);
        DatosDeMensaje mensaje = new DatosDeMensaje();
        vistaModificada.addObject("msj", mensaje);
        return vistaModificada;
    }
    @RequestMapping("/{id}/foro/enviar-msj")
    public ModelAndView insertarMensajeEnElForo(HttpServletRequest request, @ModelAttribute("msj") DatosDeMensaje datosDeMensaje) {
        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("USUARIO");
        servicioGrupo.IngresarUnMensajeAlGrupo(usuarioLogueado.getId(),datosDeMensaje);
        return new ModelAndView("redirect:/grupos/"+datosDeMensaje.getId()+"/foro");

    }
}