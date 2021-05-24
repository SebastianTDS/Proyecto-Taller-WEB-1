package ar.edu.unlam.tallerweb1.modelo;

import javax.management.ConstructorParameters;
import javax.persistence.*;
@Entity
public class Grupo {

    public Grupo() {
    }

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

   private String nombre ;
    private String carrera;
    private String materia;
    @Enumerated(EnumType.STRING)
    private Turno turno;

    private Boolean privado;
    private Integer ctdMaxima;
    private String descripcion;

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

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
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
}
