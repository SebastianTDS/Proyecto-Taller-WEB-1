package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.dto.DatosDeMensaje;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioMensajes;
import ar.edu.unlam.tallerweb1.servicios.ServicioMensajesImpl;
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
import ar.edu.unlam.tallerweb1.util.enums.Permiso;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/grupos")
public class ControladorGrupos {

    private final ServicioGrupo servicioGrupo;
    private final ServicioNotificaciones servicioNotificacion;
    private final ServicioMensajes servicioMensajes;

    @Autowired
    public ControladorGrupos(ServicioGrupo servicioGrupo, ServicioNotificaciones servicioNotificacion, ServicioMensajes servicioMensajes) {
        this.servicioGrupo = servicioGrupo;
        this.servicioNotificacion = servicioNotificacion;
        this.servicioMensajes = servicioMensajes;
    }

    @RequestMapping("/{id}")
    public ModelAndView perfilDeGrupo(@PathVariable Long id, HttpServletRequest request) {
        Usuario usuarioEnSesion = validarSesion(request);
        Grupo buscado = servicioGrupo.buscarGrupoPorID(id);
        ModelMap modelo = new ModelMap();

        servicioGrupo.validarPermiso(usuarioEnSesion.getId(), buscado.getId(), Permiso.VISTA);

        modelo.put("grupo", buscado);
        return new ModelAndView("vistaGrupo", modelo);
    }

    @RequestMapping("/{id}/edicion")
    public ModelAndView perfilDeGrupoEdicion(@PathVariable Long id, HttpServletRequest request) {
        Usuario usuarioEnSesion = validarSesion(request);
        Grupo buscado = servicioGrupo.buscarGrupoPorID(id);
        ModelMap modelo = new ModelMap();

        servicioGrupo.validarPermiso(usuarioEnSesion.getId(), buscado.getId(), Permiso.MODIFICACION);

        modelo.put("grupo", buscado);
        modelo.put("formulario", new Grupo());
        return new ModelAndView("vistaGrupo", modelo);
    }

    @RequestMapping(path = "/modificar", method = RequestMethod.POST)
    public ModelAndView cambiarDatosGrupo(@ModelAttribute("formulario") DatosDeGrupo form, HttpServletRequest request) {
        Usuario usuarioEnSesion = validarSesion(request);
        ModelMap modelo = new ModelMap();

        servicioGrupo.validarPermiso(usuarioEnSesion.getId(), form.getId(), Permiso.MODIFICACION);
        servicioGrupo.modificarGrupo(form);
        modelo.put("mensaje", "Datos actualizados");

        return new ModelAndView("redirect:/grupos/" + form.getId(), modelo);
    }

    @RequestMapping(path = "/eliminar", method = RequestMethod.POST)
    public ModelAndView eliminarGrupo(@RequestParam Long id, HttpServletRequest request) {
        Usuario usuarioEnSesion = validarSesion(request);
        ModelMap modelo = new ModelMap();

        servicioGrupo.validarPermiso(usuarioEnSesion.getId(), id, Permiso.MODIFICACION);

        servicioNotificacion.notificarEliminacionDeGrupo(id);
        servicioGrupo.eliminarGrupo(id);
        modelo.put("mensaje", "Grupo eliminado con exito!");
        return new ModelAndView("redirect:/ir-a-home", modelo);
    }

    @RequestMapping("/{id}/foro")
    public ModelAndView perfilDeGrupoForo(HttpServletRequest request, @PathVariable Long id) {
    	validarSesion(request);
    	
        ModelMap modelo = new ModelMap();

        modelo.put("msj", new DatosDeMensaje());
        modelo.put("grupo", servicioGrupo.buscarGrupoPorID(id));
        modelo.put("mensajes",servicioMensajes.buscarMensajesDeUnGrupo(id));

        return new ModelAndView("vistaGrupo", modelo);
    }

    @RequestMapping("/{id}/foro/enviar-msj")
    public ModelAndView insertarMensajeEnElForo(HttpServletRequest request,@ModelAttribute("msj") DatosDeMensaje datosDeMensaje) {
        Usuario usuarioLogueado = validarSesion(request);
        servicioMensajes.guardarUnMensaje(usuarioLogueado.getId(), datosDeMensaje);
        return new ModelAndView("redirect:/grupos/" + datosDeMensaje.getId() + "/foro");

    }

    @RequestMapping("/{id}/miembros")
    public ModelAndView mostrarMiembrosDelGrupo(HttpServletRequest request, @PathVariable Long id) {
    	validarSesion(request);
    	
        ModelMap modelo = new ModelMap();

        modelo.put("grupo", servicioGrupo.buscarGrupoPorID(id));
        modelo.put("integrantes", true);
        return new ModelAndView("vistaGrupo", modelo);
    }

    private Usuario validarSesion(HttpServletRequest request) {
        Usuario objetivo = (Usuario) request.getSession().getAttribute("USUARIO");

        if (objetivo == null)
            throw new UsuarioNoEncontradoException("No existe un usuario logueado!");

        return objetivo;
    }
    @RequestMapping(path = "/salir", method = RequestMethod.POST)
    public ModelAndView salirDelGrupo(@RequestParam Long id, HttpServletRequest request) {
        Usuario usuarioEnSesion = validarSesion(request);
        ModelMap modelo = new ModelMap();

        servicioNotificacion.notificarRetiroDeGrupo(id,usuarioEnSesion);
        servicioGrupo.borrarUsuarioDelGrupo(id,usuarioEnSesion.getId());

        modelo.put("mensaje", "Se ha salido con exito!");
        return new ModelAndView("redirect:/ir-a-home", modelo);
    }
}
