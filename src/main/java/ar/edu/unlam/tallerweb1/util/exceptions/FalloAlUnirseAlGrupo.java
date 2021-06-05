package ar.edu.unlam.tallerweb1.util.exceptions;

public class FalloAlUnirseAlGrupo extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FalloAlUnirseAlGrupo() {
        super("No se pudo unir al grupo");
    }
}
