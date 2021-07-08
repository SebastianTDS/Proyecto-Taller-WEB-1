package ar.edu.unlam.tallerweb1.util.exceptions;

public class NoCoincidenContraseniasException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoCoincidenContraseniasException(String mensaje) {
        super(mensaje);
    }
}
