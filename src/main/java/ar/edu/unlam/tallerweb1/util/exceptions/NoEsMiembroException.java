package ar.edu.unlam.tallerweb1.util.exceptions;

public class NoEsMiembroException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoEsMiembroException() {
		super("El Usuario no es miembro de este grupo");
	}
}
