package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.util.auxClass.Algorithm;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Mensaje implements Comparable<Mensaje> {
    private Long id;
    private String mensaje;
    private Usuario usuario;
    private LocalDateTime fecha;
    private Grupo grupo;

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


    public void setMensaje(String s) {
        this.mensaje = s;
    }

    @Type(type = "text")
    @Column(nullable = false)
    public String getMensaje() {
        return mensaje;
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

    @Override
    public int compareTo(Mensaje o) {
        if (this.id < o.getId())
            return 0;
        else
            return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mensaje mensaje = (Mensaje) o;
        return Objects.equals(id, mensaje.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
