package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.dto.DatosDeMensaje;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
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

    @Autowired
    public ControladorForoMateria(ServicioGrupo servicioGrupo) {
        this.servicioGrupo = servicioGrupo;
    }

    @RequestMapping(path = "/ingresar-a-foro/{id}",method = RequestMethod.GET)
    public ModelAndView ingresarAForoMateria(@PathVariable Long id) {
        ModelMap model = new ModelMap();
        DatosDeMensaje mensaje = new DatosDeMensaje();
        model.put("msj", mensaje);
        model.put("grupo",servicioGrupo.buscarGrupoPorID(id));
        return new ModelAndView ("vistaForoMateria",model);
    }
}
