package ar.edu.unlam.tallerweb1.dto;

import ar.edu.unlam.tallerweb1.util.enums.Turno;

public class DatosDeGrupo {

    private String nombre ;
    private Turno turno;
    private Boolean privado;
    private Integer ctdMaxima;
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

    public Boolean getPrivado() {
        return privado;
    }

    public void setPrivado(Boolean privado) {
        this.privado = privado;
    }

    public Integer getCtdMaxima() {
        return ctdMaxima;
    }

    public void setCtdMaxima(Integer ctdMaxima) {
        this.ctdMaxima = ctdMaxima;
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
}
