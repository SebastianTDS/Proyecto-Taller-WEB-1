package ar.edu.unlam.tallerweb1.modelo;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Mensaje {
    private Long id;
    private String mensaje;
    private Long idUsuario;
    private LocalDateTime fecha;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public Long getUsuario() {
        return idUsuario;
    }

    public void setUsuario(Long usuario) {
        this.idUsuario = usuario;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setMensaje(String s) {
        this.mensaje=s;
    }

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
