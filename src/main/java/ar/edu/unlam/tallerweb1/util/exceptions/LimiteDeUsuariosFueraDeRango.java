package ar.edu.unlam.tallerweb1.util.exceptions;

public class LimiteDeUsuariosFueraDeRango extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private Long idGrupoError;

	public LimiteDeUsuariosFueraDeRango(String mensaje, Long idGrupoError) {
		super(mensaje);
		this.idGrupoError = idGrupoError;
	}

	public Long getIdGrupoError() {
		return idGrupoError;
	}
}
