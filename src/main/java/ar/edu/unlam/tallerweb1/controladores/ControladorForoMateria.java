package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.dto.DatosDeMensaje;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioMensajes;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorForoMateria {

    private final ServicioGrupo servicioGrupo;
    private final ServicioMensajes servicioMensajes;

    @Autowired
    public ControladorForoMateria(ServicioGrupo servicioGrupo, ServicioMensajes servicioMensajes) {
        this.servicioGrupo = servicioGrupo;
        this.servicioMensajes = servicioMensajes;
    }

    @RequestMapping(path = "/ingresar-a-foro/{id}",method = RequestMethod.GET)
    public ModelAndView ingresarAForoMateria(HttpServletRequest request, @PathVariable Long id) {
    	validarSesion(request);
    	
        ModelMap model = new ModelMap();
      
        model.put("msj", new DatosDeMensaje());
        model.put("grupo", servicioGrupo.buscarForo(id));
        model.put("mensajes",servicioMensajes.buscarMensajesDeUnGrupo(id));
      
        return new ModelAndView ("vistaForoMateria",model);
    }
    
    @RequestMapping("/ingresar-a-foro/{id}/enviar-msj")
    public ModelAndView insertarMensajeEnElForo(HttpServletRequest request, @ModelAttribute("msj") DatosDeMensaje datosDeMensaje) {
        Usuario usuarioLogueado = validarSesion(request);
        
        datosDeMensaje.setEsMateria(true);
        
        servicioMensajes.guardarUnMensaje(usuarioLogueado.getId(), datosDeMensaje);
        return new ModelAndView("redirect:/ingresar-a-foro/" + datosDeMensaje.getId());

    }
    
    private Usuario validarSesion(HttpServletRequest request) {
        Usuario objetivo = (Usuario) request.getSession().getAttribute("USUARIO");

        if (objetivo == null)
            throw new UsuarioNoEncontradoException("No existe un usuario logueado!");

        return objetivo;
    }
}
