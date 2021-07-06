package ar.edu.unlam.tallerweb1.util.exceptions;

public class CampoVacioException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CampoVacioException(String mensaje) {
        super(mensaje);
    }
}
