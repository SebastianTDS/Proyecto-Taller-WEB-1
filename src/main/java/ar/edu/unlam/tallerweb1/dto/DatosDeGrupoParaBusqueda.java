package ar.edu.unlam.tallerweb1.dto;

import ar.edu.unlam.tallerweb1.util.enums.Privacidad;
import ar.edu.unlam.tallerweb1.util.enums.Turno;

public class DatosDeGrupoParaBusqueda {

    private String nombre ;
    private Turno turno;
    private Long carrera;
    private Long materia;
    private Privacidad privacidad;

    public Privacidad getPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(Privacidad privacidad) {
        this.privacidad = privacidad;
    }

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

    public void setCarrera(Long carrera) {
        this.carrera=carrera;
    }

    public void setMateria(Long materia) {
        this.materia=materia;
    }

}
