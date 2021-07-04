package ar.edu.unlam.tallerweb1.modelo;

import java.util.HashSet;
import java.util.Set;

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
	private Set<Grupo> listaDeGrupos;
	private Set<Grupo> listaDeGruposHistorica;
	private int calificacion;

	public Usuario() {
		this.listaDeGrupos = new HashSet<Grupo>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "id_usuario", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_grupo", nullable = false))
	@ManyToMany
	public Set<Grupo> getListaDeGrupos() {
		return listaDeGrupos;
	}

	public void setListaDeGrupos(Set<Grupo> listaDeGrupos) {
		this.listaDeGrupos = listaDeGrupos;
	}

	@JoinTable(name = "usuario_grupo_historico", joinColumns = @JoinColumn(name = "id_usuario", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_grupo", nullable = false))
	@ManyToMany
	public Set<Grupo> getListaDeGruposHistorica() {
		return listaDeGruposHistorica;
	}

	public void setListaDeGruposHistorica(Set<Grupo> listaDeGruposHistorica) {
		this.listaDeGruposHistorica = listaDeGruposHistorica;
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

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
}
