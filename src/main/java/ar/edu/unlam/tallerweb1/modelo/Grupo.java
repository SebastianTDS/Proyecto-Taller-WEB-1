package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Grupo {

    private Long id;
    private String nombre;
    private String descripcion;
    private Boolean privado;
    private Integer ctdMaxima;

    private Turno turno;
    private Carrera carrera;
    private Materia materia;

    public Grupo() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
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
    public Boolean getPrivado() {
        return privado;
    }

    public void setPrivado(Boolean privado) {
        this.privado = privado;
    }

    @Column(nullable = false)
    public Integer getCtdMaxima() {
        return ctdMaxima;
    }

    public void setCtdMaxima(Integer ctdMaxima) {
        this.ctdMaxima = ctdMaxima;
    }
}