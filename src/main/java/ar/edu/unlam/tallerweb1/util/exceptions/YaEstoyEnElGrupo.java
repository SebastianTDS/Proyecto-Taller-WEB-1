package ar.edu.unlam.tallerweb1.util.exceptions;

public class YaEstoyEnElGrupo extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Long idGrupo;

    public YaEstoyEnElGrupo(String mensaje, Long id) {
        super(mensaje);
        this.idGrupo = id;
    }
    
    public Long getIdGrupo() {
    	return idGrupo;
    }
}
