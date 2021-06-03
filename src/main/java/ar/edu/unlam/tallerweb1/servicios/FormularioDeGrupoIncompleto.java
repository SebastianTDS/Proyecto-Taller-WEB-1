package ar.edu.unlam.tallerweb1.servicios;

public class FormularioDeGrupoIncompleto extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public FormularioDeGrupoIncompleto() {
        super("Completa todos los campos del formulario");
    }

}
