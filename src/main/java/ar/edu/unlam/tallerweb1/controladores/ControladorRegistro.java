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
        model.put("datosDeRegistro",datosDeRegistro);
        return new ModelAndView("registro",model);
    }

    @RequestMapping(path="/registrarse", method = RequestMethod.POST)
    public ModelAndView registrarUsuario(@ModelAttribute("datosDeRegistro") DatosDeUsuario datos) {
            ModelMap model=new ModelMap();

       if(servicioUsuarios.registrar(datos).equals("Registro Existoso")){
            return new ModelAndView("redirect:/home");

        }else if (servicioUsuarios.registrar(datos).equals("Las claves no coinciden,error al registrar")){
          model.put("error","Las claves no coinciden,error al registrar");
            return new ModelAndView("registro",model);
       }else{
          model.put("error", "Usuario ya existente");
       return new ModelAndView("registro", model);

        }

    }
}
