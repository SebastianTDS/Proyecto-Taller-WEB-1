package ar.edu.unlam.tallerweb1.util.exceptions;

public class UsuarioSinPermisosException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final Long idGrupo;
	
	public UsuarioSinPermisosException(String mensaje, Long idGrupo) {
		super(mensaje);
		this.idGrupo = idGrupo;
	}
	
	public Long getIdGrupo() {
		return idGrupo;
	}
}
