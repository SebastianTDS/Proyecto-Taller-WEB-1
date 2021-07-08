package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioUsuario {
	
	Usuario consultarUsuario (Usuario usuario);

	Usuario getUsuarioByID(Long id);

    void guardarUsuario(Usuario usuario);

	Usuario getUsuarioByEmail(String correo);

	void actualizarUsuario(Usuario usuario);

}
