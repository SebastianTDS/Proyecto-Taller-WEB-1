package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Usuario {

	private Long id;
	private String email;
	private String password;
	private String rol;
	private String nombre;
  private List<Grupo> listaDeGrupos;

	public Usuario() {
		this.listaDeGrupos = new ArrayList<>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}

	@JoinTable(
			name = "usuario_grupo",
			joinColumns = @JoinColumn(name = "id_usuario",nullable = false),
			inverseJoinColumns = @JoinColumn(name = "id_grupo",nullable = false)
	)
	@ManyToMany
	public List<Grupo> getListaDeGrupos() {
		return  listaDeGrupos;
	}

	public void setListaDeGrupos(List<Grupo> listaDeGrupos) {
		this.listaDeGrupos = listaDeGrupos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void agregarGrupo(Grupo grupo) {
		listaDeGrupos.add(grupo);
	}
}
