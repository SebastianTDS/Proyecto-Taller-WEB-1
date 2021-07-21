package ar.edu.unlam.tallerweb1.util.exceptions;

public class NoSeEnvioElMensaje extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Long idGrupo;
    
    public NoSeEnvioElMensaje(Long id) {
        super("No fue posible enviar el mensaje, intente nuevamente");
        this.idGrupo = id;
    }
    
    public Long getIdGrupo() {
    	return this.idGrupo;
    }
}

