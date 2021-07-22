package ar.edu.unlam.tallerweb1.util.exceptions;

public class FalloAlInvitarUsuario extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private Long grupo;

	public FalloAlInvitarUsuario(String mensaje, Long id) {
		super(mensaje);
		this.grupo = id;
	}

	public Long getGrupo() {
		return grupo;
	}
	
}
