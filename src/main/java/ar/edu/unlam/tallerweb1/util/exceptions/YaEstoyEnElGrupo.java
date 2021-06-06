package ar.edu.unlam.tallerweb1.util.exceptions;

public class YaEstoyEnElGrupo extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public YaEstoyEnElGrupo(Long id) {
        super(String.valueOf(id));
    }
}
