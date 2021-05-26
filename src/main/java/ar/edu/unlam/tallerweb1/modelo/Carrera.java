package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;


@Entity
public class Carrera {

    private Long id;
    private String nombre;

    public Carrera() {
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



}




