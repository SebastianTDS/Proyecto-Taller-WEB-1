package ar.edu.unlam.tallerweb1.util.exceptions;

public class FalloAlInvitarUsuario extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private Long grupo;

	public FalloAlInvitarUsuario(Long id) {
		super("El usuario invitado no existe!");
		this.grupo = id;
	}

	public Long getGrupo() {
		return grupo;
	}
	
}
