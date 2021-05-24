package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.DatosDeGrupo;
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
        this.servicioGrupo=servicioGrupo;
    }


    @RequestMapping(value = "crear-grupo", method = RequestMethod.POST)
    public ModelAndView irALaVistaDeGrupoCreado(@ModelAttribute DatosDeGrupo datos){
        ModelMap model = new ModelMap();
        Grupo grupo=servicioGrupo.crearGrupo(datos);
           if(grupo!=null)
               return CreacionExitosa(grupo);
              else
                  return creacionSinExito(model);

    }

    private ModelAndView CreacionExitosa(Grupo grupo) {
        return new ModelAndView("vistaGrupo");
    }

    private ModelAndView creacionSinExito(ModelMap model) {
       model.put("error", "Completa todos los campos del formulario");
        return new ModelAndView("redirect:/ir-a-crear-nuevo-grupo", model);
    }
}




