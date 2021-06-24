package ar.edu.unlam.tallerweb1.util.exceptions;

public class UsuarioNoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UsuarioNoEncontradoException(String mensaje) {
		super(mensaje);
	}

}
