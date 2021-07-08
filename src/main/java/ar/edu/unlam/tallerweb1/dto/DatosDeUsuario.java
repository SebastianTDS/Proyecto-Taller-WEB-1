package ar.edu.unlam.tallerweb1.dto;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class DatosDeUsuario {

	private Long id;
    private String nombre;
    private String email;
    private String clave;
    private String repiteClave;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRepiteClave() {
        return repiteClave;
    }

    public void setRepiteClave(String repiteClave) {
        this.repiteClave = repiteClave;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario generarUsuario() {
		Usuario generado = new Usuario();
		generado.setId(id);
		generado.setEmail(email);
		generado.setNombre(nombre);
		generado.setPassword(clave);
		return generado;
	}

    public Usuario crearUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(clave);
        usuario.setCalificacion(0L);
        usuario.setCantidadDeCalificaciones(0L);
        return usuario;
    }

}
