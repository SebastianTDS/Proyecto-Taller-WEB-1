package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.DatosDeGrupo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorHome {

    @RequestMapping("/ir-a-crear-nuevo-grupo")
    public ModelAndView irAlFormulario() {
        ModelMap model = new ModelMap();
        DatosDeGrupo datos= new DatosDeGrupo();
        model.put("datos",datos);
        return new ModelAndView("vistaParaCrearGrupo",model);
    }
}
