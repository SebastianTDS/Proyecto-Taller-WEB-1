package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.util.exceptions.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControladorExcepciones {

    @ExceptionHandler(value = LimiteDeUsuariosFueraDeRango.class)
    public ModelAndView errorAlModificarGrupo(LimiteDeUsuariosFueraDeRango e) {
        ModelMap model = new ModelMap();

        model.put("error", e.getMessage());

        return new ModelAndView("redirect:/grupos/" + e.getIdGrupoError(), model);
    }

    @ExceptionHandler(value = FormularioDeGrupoIncompleto.class)
    public ModelAndView errorPorCamposIncompletos(FormularioDeGrupoIncompleto e) {
        ModelMap model = new ModelMap();
        model.put("error", e.getMessage());
        return new ModelAndView("redirect:/ir-a-crear-nuevo-grupo", model);
    }

    @ExceptionHandler(value = {GrupoInexistenteException.class, NoEsMiembroException.class})
    public ModelAndView errorAlBuscarGrupo(RuntimeException e) {
        ModelMap model = new ModelMap();

        model.put("error", e.getMessage());

        return new ModelAndView("redirect:/ir-a-home", model);
    }

    @ExceptionHandler(value = FalloAlUnirseAlGrupo.class)
    public ModelAndView errorAlUnirseAlGrupo(FalloAlUnirseAlGrupo e) {
        ModelMap model = new ModelMap();
        model.put("error", e.getMessage());

        return new ModelAndView("redirect:/ir-a-home", model);
    }

    @ExceptionHandler(value = YaEstoyEnElGrupo.class)
    public ModelAndView errorAlUnirseAlGrupoQueYaSoyMiembro(YaEstoyEnElGrupo e) {
        return new ModelAndView("redirect:/grupos/" + e.getMessage());
    }
    @ExceptionHandler(value = NoSeEnvioElMensaje.class)
    public ModelAndView errorAlEnviarUnMensaje(NoSeEnvioElMensaje e) {
        ModelMap model = new ModelMap();
        model.put("error", "No se envio el mensaje");
        return new ModelAndView("redirect:/grupos/" + e.getMessage()+"/foro",model);
    }
    @ExceptionHandler(value = UsuarioNoEncontradoException.class)
    public ModelAndView errorAlLoguearUsuario(UsuarioNoEncontradoException e){
    	ModelMap model = new ModelMap();
        model.put("error", e.getMessage());

        return new ModelAndView("redirect:/ir-a-login", model);
    }
    
    @ExceptionHandler(value = UsuarioSinPermisosException.class)
    public ModelAndView errorAlVerificarPermiso(UsuarioSinPermisosException e) {
    	ModelMap model = new ModelMap();
        model.put("error", e.getMessage());

        return new ModelAndView("redirect:/grupos/" + e.getIdGrupo(), model);
    }
    
    @ExceptionHandler(value = FalloAlProcesarSolicitud.class)
    public ModelAndView falloAlProcesarSolicitud(FalloAlProcesarSolicitud e) {
    	ModelMap model = new ModelMap();
        model.put("error", e.getMessage());

        return new ModelAndView("redirect:/solicitudes" , model);
    }

    @ExceptionHandler(value = UsuarioExistenteException.class)
    public ModelAndView usuarioExistenteEnRegistrar(UsuarioExistenteException e) {
        ModelMap model = new ModelMap();
        model.put("error", e.getMessage());

        return new ModelAndView("redirect:/ir-a-registro" , model);
    }

    @ExceptionHandler(value = NoCoincidenContraseniasException.class)
    public ModelAndView noCoincidenContraseniasException(NoCoincidenContraseniasException e) {
        ModelMap model = new ModelMap();
        model.put("error", e.getMessage());

        return new ModelAndView("redirect:/ir-a-registro" , model);
    }

    @ExceptionHandler(value = CampoVacioException.class)
    public ModelAndView campoVacioException(CampoVacioException e) {
        ModelMap model = new ModelMap();
        model.put("error", e.getMessage());

        return new ModelAndView("redirect:/ir-a-registro" , model);
    }

}
