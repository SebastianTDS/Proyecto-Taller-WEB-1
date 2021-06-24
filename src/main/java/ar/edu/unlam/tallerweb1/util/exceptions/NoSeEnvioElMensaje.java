package ar.edu.unlam.tallerweb1.util.exceptions;

public class NoSeEnvioElMensaje extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoSeEnvioElMensaje(Long id) {
        super(String.valueOf(id));
    }
}

