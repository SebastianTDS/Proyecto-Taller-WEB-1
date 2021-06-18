package ar.edu.unlam.tallerweb1.modelo;

import java.util.*;

import javax.persistence.*;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.util.enums.Turno;
import ar.edu.unlam.tallerweb1.util.exceptions.YaEstoyEnElGrupo;

@Entity
public class Grupo {

	private Long id;
	private String nombre;
	private String descripcion;
	private Boolean cerrado;
	private Integer cantidadMax;

	private Turno turno;
	private Carrera carrera;
	private Materia materia;

	private Set<Usuario> listaDeUsuarios;
	private Set<Mensaje> listaDeMensajes;


	public Grupo() {
		this.listaDeUsuarios = new HashSet<Usuario>();
		this.listaDeMensajes = new HashSet<Mensaje>();
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
		if (!(obj instanceof Grupo))
			return false;
		Grupo other = (Grupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@OneToMany(mappedBy = "grupo", fetch = FetchType.EAGER)
	public Set<Mensaje> getListaDeMensajes() { return listaDeMensajes;}

	public void setListaDeMensajes(Set<Mensaje> listaDeMensajes) {
		this.listaDeMensajes = listaDeMensajes;
	}

	@ManyToMany(mappedBy = "listaDeGrupos", fetch = FetchType.EAGER)
	public Set<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public void setListaDeUsuarios(Set<Usuario> listaDeUsuarios) {
		this.listaDeUsuarios = listaDeUsuarios;
	}

	@ManyToOne(optional = false, targetEntity = Materia.class)
	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	@ManyToOne(optional = false, targetEntity = Carrera.class)
	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(nullable = false)
	public Boolean getCerrado() {
		return cerrado;
	}

	public void setCerrado(Boolean privado) {
		this.cerrado = privado;
	}

	@Column(nullable = false)
	public Integer getCantidadMax() {
		return cantidadMax;
	}

	public void setCantidadMax(Integer ctdMaxima) {
		this.cantidadMax = ctdMaxima;
	}

	@PreRemove
	public void removerGruposDeUsuario() {
		for (Usuario usuario : listaDeUsuarios) {
			usuario.getListaDeGrupos().remove(this);
		}
	}

	public void actualizar(DatosDeGrupo formulario) {
		nombre = formulario.tryGetNombre(nombre);
		descripcion = formulario.tryGetDescripcion(descripcion);
		cerrado = formulario.tryGetEstaCerrado(cerrado);
		cantidadMax = formulario.tryGetCantidadMax(cantidadMax);
	}

	public void agregarUsuarioAlGrupo(Usuario usuarioAInsertar) {
		if(!listaDeUsuarios.contains(usuarioAInsertar)){
			listaDeUsuarios.add(usuarioAInsertar);
			usuarioAInsertar.agregarGrupo(this);
		}
	}
	
	public Boolean grupoLleno() {
		return listaDeUsuarios.size() >= cantidadMax;
	}

  public Integer cantidadDeIntegrantes(){
      return listaDeUsuarios.size();
  }


	public TreeSet<Mensaje>  ordenarMsj(){
		TreeSet<Mensaje> mensajes=new TreeSet();
		mensajes.addAll(listaDeMensajes);
		return mensajes;
	}
}