package ar.edu.unlam.tallerweb1.modelo;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Mensaje {
    private Long id;
    private String mensaje;
    private Usuario usuario;
    private LocalDateTime fecha;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
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
        this.mensaje=s;
    }

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
}
