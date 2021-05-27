package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.util.exceptions.LimiteDeUsuariosIlegalException;

@ControllerAdvice
public class ControladorExcepciones {

	@ExceptionHandler(value = LimiteDeUsuariosIlegalException.class)
	public ModelAndView errorAlModificarGrupo(LimiteDeUsuariosIlegalException e) {
		ModelMap model= new ModelMap();
		
		model.put("error", e.getMessage());
		
		return new ModelAndView("redirect:/grupos/" + e.getGrupoError(), model);
	}
	
//	TODO: Metodo encargado de redirigir a las paginas de error 4xx personalizadas.
//	
//	@ExceptionHandler(value = HttpClientErrorException.class)
//	public void ErroresHttpDelCliente(HttpClientErrorException e) {
//	}
}
