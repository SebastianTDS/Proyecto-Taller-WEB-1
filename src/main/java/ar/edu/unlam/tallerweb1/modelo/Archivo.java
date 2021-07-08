package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.util.auxClass.Algorithm;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Archivo implements Comparable<Archivo> {
    private Long id;
    private String nombre;
    private String nombreOriginal;
    private Usuario usuario;
    private LocalDateTime fecha;
    private Grupo grupo;

    public Archivo() {
        this.fecha = LocalDateTime.now().withNano(0);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @ManyToOne(optional = false, targetEntity = Grupo.class)
    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @ManyToOne(optional = false, targetEntity = Usuario.class)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }
    @Column(nullable = false)
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String tiempoDePublicacion(){
        return Algorithm.getTiempoTranscurrido(fecha);
    }

    public String getNombreOriginal() {
        return nombreOriginal;
    }

    public void setNombreOriginal(String nombreOriginal) {
        this.nombreOriginal = nombreOriginal;
    }

    @Override
    public int compareTo(Archivo o) {
        if (this.id < o.getId())
            return 0;
        else
            return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Archivo mensaje = (Archivo) o;
        return id.equals(mensaje.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
