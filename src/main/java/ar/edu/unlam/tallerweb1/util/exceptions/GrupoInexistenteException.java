package ar.edu.unlam.tallerweb1.util.exceptions;

public class GrupoInexistenteException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public GrupoInexistenteException(String mensaje) {
		super(mensaje);
	}

}
