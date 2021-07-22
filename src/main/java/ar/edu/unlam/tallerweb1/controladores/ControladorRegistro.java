package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.dto.DatosDeUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ControladorRegistro {

    private final ServicioUsuario servicioUsuarios;

    @Autowired
    public ControladorRegistro(ServicioUsuario servicioUsuarios) {
        this.servicioUsuarios = servicioUsuarios;
    }

    @RequestMapping("/ir-a-registro")
    public ModelAndView irARegistro() {
        DatosDeUsuario datosDeRegistro = new DatosDeUsuario();
        ModelMap model = new ModelMap();
        model.put("datosDeRegistro", datosDeRegistro);
        return new ModelAndView("registro", model);
    }

    @RequestMapping(path = "/registrarse", method = RequestMethod.POST)
    public ModelAndView registrarUsuario(@ModelAttribute("datosDeRegistro") DatosDeUsuario datos, RedirectAttributes modelo) {
        servicioUsuarios.registrar(datos);
        modelo.addFlashAttribute("mensaje", "Usuario creado con exito!");
        return new ModelAndView("redirect:/ir-a-login");
    }

}