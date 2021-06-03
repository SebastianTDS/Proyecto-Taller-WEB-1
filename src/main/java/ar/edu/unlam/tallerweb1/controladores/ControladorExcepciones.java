package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.servicios.FormularioDeGrupoIncompleto;
import ar.edu.unlam.tallerweb1.util.exceptions.GrupoInexistenteException;
import ar.edu.unlam.tallerweb1.util.exceptions.LimiteDeUsuariosFueraDeRango;

@ControllerAdvice
public class ControladorExcepciones {

	@ExceptionHandler(value = LimiteDeUsuariosFueraDeRango.class)
	public ModelAndView errorAlModificarGrupo(LimiteDeUsuariosFueraDeRango e) {
		ModelMap model= new ModelMap();
		
		model.put("error", e.getMessage());
		
		return new ModelAndView("redirect:/grupos/" + e.getIdGrupoError(), model);
	}
	
	@ExceptionHandler(value = FormularioDeGrupoIncompleto.class)
	public ModelAndView errorPorCamposIncompletos(FormularioDeGrupoIncompleto e) {
		ModelMap model= new ModelMap();
		model.put("error",e.getMessage());
		return new ModelAndView("redirect:/ir-a-crear-nuevo-grupo", model);
	}
	
	@ExceptionHandler(value = GrupoInexistenteException.class)
	public ModelAndView errorAlBuscarGrupo(GrupoInexistenteException e) {
		ModelMap model= new ModelMap();
		
		model.put("error", e.getMessage());
		
		return new ModelAndView("redirect:/ir-a-home", model);
	}
	
//	TODO: Metodo encargado de redirigir a las paginas de error 4xx personalizadas.
//	
//	@ExceptionHandler(value = HttpClientErrorException.class)
//	public void ErroresHttpDelCliente(HttpClientErrorException e) {
//	}
}
