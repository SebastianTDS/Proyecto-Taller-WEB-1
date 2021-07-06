package ar.edu.unlam.tallerweb1.util.exceptions;

public class UsuarioExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioExistenteException(String mensaje) {
		super(mensaje);
	}

}
