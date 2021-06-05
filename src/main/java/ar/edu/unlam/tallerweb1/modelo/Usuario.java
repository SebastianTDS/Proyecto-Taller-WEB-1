package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

	private Long id;
	private String email;
	private String password;
	private String rol;
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
	@ManyToMany(cascade=CascadeType.ALL)
	public List<Grupo> getListaDeGrupos() {
		return  listaDeGrupos;
	}

	public void setListaDeGrupos(List<Grupo> listaDeGrupos) {
		this.listaDeGrupos = listaDeGrupos;
	}

	public void agregarGrupo(Grupo grupo) {
		listaDeGrupos.add(grupo);
	}
}
