package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/perfil")
public class ControladorMisGrupos {
    private final ServicioGrupo servicioGrupo;

    @Autowired
    public ControladorMisGrupos(ServicioGrupo servicioGrupo) {
        this.servicioGrupo = servicioGrupo;
    }

    @RequestMapping("/ir-a-mis-grupos")
    public ModelAndView misGrupos(HttpServletRequest request) {
        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("USUARIO");
        
        if(usuarioLogueado == null)
        	return new ModelAndView("redirect:/ir-a-login");
        
        ModelMap model = new ModelMap();

        model.put("carreras", servicioGrupo.buscarTodasLasCarreras());
        model.put("materias", servicioGrupo.buscarTodasLasMaterias());
        model.put("misGrupos", servicioGrupo.buscarTodosMisGrupos(usuarioLogueado));

        return new ModelAndView("vistaMisGrupos", model);
    }

}
