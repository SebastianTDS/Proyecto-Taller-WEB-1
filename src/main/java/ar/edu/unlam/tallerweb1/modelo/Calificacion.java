package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.util.auxClass.Algorithm;
import ar.edu.unlam.tallerweb1.util.enums.TipoSolicitud;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Calificacion {

	private Long id;
	private LocalDateTime fecha;
	private Usuario destino;
	private Usuario origen;
	private Long calificacion;

	public Calificacion() {
		this.fecha = LocalDateTime.now();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	@ManyToOne(optional = false)
	public Usuario getDestino() {
		return destino;
	}
	
	public void setDestino(Usuario destino) {
		this.destino = destino;
	}
	
	@ManyToOne(optional = false)
	public Usuario getOrigen() {
		return origen;
	}

	public void setOrigen(Usuario origen) {
		this.origen = origen;
	}


	public Long getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Long calificacion) {
		this.calificacion = calificacion;
	}

	public String periodoTranscurrido() {
		return Algorithm.getTiempoTranscurrido(fecha);
	}

}
