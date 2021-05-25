package ar.edu.unlam.tallerweb1.util.exceptions;

public class LimiteDeUsuariosIlegalException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private Long idGrupoError;

	public LimiteDeUsuariosIlegalException(Long idGrupoError) {
		super("El numero de integrantes debe de ser de entre 2 y 8 usuarios!");
		this.idGrupoError = idGrupoError;
	}

	public Long getGrupoError() {
		return idGrupoError;
	}
}
