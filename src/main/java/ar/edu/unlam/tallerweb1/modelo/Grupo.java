package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.util.auxClass.Check;
import ar.edu.unlam.tallerweb1.util.enums.Turno;
import ar.edu.unlam.tallerweb1.util.exceptions.LimiteDeUsuariosFueraDeRango;
import org.springframework.beans.MutablePropertyValues;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Grupo  {
	
	private Long id;
	private String nombre;
	private String descripcion;
	private Boolean cerrado;
	private Integer cantidadMax;
	
	private Turno turno;
	private Carrera carrera;
	private Materia materia;

	private  List<Usuario> listaDeUsuarios;

	public Grupo() {
		this.listaDeUsuarios = new ArrayList<>();
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
	
	public void actualizar(DatosDeGrupo formulario) {
		nombre 		= Check.empty(formulario.getNombre())		? nombre		: formulario.getNombre();
		descripcion = Check.empty(formulario.getDescripcion())  ? descripcion	: formulario.getDescripcion();
		cerrado 	= Check.isNull(formulario.getCerrado()) 	? cerrado		: formulario.getCerrado();
		cantidadMax = formulario.getCantidadMax();
		
		if(!Check.isInRange(cantidadMax, 2, 7) && !Check.isNull(cantidadMax))
			throw new LimiteDeUsuariosFueraDeRango(id);
	}

	public void agregarUsuarioAlGrupo(Usuario usuarioAInsertar) {
		listaDeUsuarios.add(usuarioAInsertar);
		usuarioAInsertar.agregarGrupo(this);
	}

	@ManyToMany(mappedBy="listaDeGrupos")
	public List<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public void setListaDeUsuarios(List<Usuario> listaDeUsuarios) {
		this.listaDeUsuarios = listaDeUsuarios;
	}

	@PreRemove
	public void removerGruposDeUsuario(){
		for(Usuario usuario:listaDeUsuarios){
			usuario.getListaDeGrupos().remove(this);
		}
	}


}