package ar.edu.unlam.tallerweb1.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Notificacion {
	
	private Long id;
	private String titulo;
	private LocalDateTime fecha;
	private Usuario usuario;
	private Boolean visto;
	
	public Notificacion() {
		this.fecha = LocalDateTime.now().withNano(0);
		this.visto = false;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	@ManyToOne(optional = false)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Boolean getVisto() {
		return visto;
	}

	public void setVisto(Boolean visto) {
		this.visto = visto;
	}
}
