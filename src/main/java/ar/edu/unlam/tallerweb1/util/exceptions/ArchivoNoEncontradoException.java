package ar.edu.unlam.tallerweb1.util.exceptions;

public class ArchivoNoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ArchivoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
