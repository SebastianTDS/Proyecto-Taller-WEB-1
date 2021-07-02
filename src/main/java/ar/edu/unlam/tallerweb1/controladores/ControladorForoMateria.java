package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.dto.DatosDeMensaje;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioMensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public ModelAndView ingresarAForoMateria(@PathVariable Long id) {
        ModelMap model = new ModelMap();
      
        model.put("msj", new DatosDeMensaje());
        model.put("grupo",servicioGrupo.buscarForo(id));
      
        return new ModelAndView ("vistaForoMateria",model);
    }
}
