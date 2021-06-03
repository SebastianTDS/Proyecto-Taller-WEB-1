package ar.edu.unlam.tallerweb1.dto;

import ar.edu.unlam.tallerweb1.util.enums.Turno;

public class DatosDeGrupo {

	private Long id;
    private String nombre;
    private Turno turno;
    private Boolean cerrado;
    private Integer cantidadMax;
    private String descripcion;
    private Long carrera;
    private Long materia;

    public Long getCarrera() {
        return carrera;
    }

    public Long getMateria() {
        return materia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Boolean getCerrado() {
        return cerrado;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
    }

    public Integer getCantidadMax() {
        return cantidadMax;
    }
    
    public void setCantidadMax(Integer cantidadMax) {
        this.cantidadMax = cantidadMax;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCarrera(Long carrera) {
        this.carrera=carrera;
    }

    public void setMateria(Long materia) {
        this.materia=materia;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
