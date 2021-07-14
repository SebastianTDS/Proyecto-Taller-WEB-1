package ar.edu.unlam.tallerweb1.modelo;

import java.time.LocalDateTime;

import javax.persistence.*;

import ar.edu.unlam.tallerweb1.util.auxClass.Algorithm;
import ar.edu.unlam.tallerweb1.util.enums.TipoSolicitud;

@Entity
public class Solicitud {

	private Long id;
	private LocalDateTime fecha;
	private TipoSolicitud tipo;
	
	private Grupo objetivo;
	private Usuario destino;
	private Usuario origen;

	public Solicitud() {
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

	@Enumerated(EnumType.STRING)
	public TipoSolicitud getTipo() {
		return tipo;
	}

	public void setTipo(TipoSolicitud tipo) {
		this.tipo = tipo;
	}

	@ManyToOne(optional = true)
	public Grupo getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Grupo objetivo) {
		this.objetivo = objetivo;
	}

	@ManyToOne(optional = false)
	public Usuario getDestino() {
		return destino;
	}
	
	public void setDestino(Usuario destino) {
		this.destino = destino;
	}
	
	@ManyToOne(optional = false,fetch = FetchType.EAGER)
	public Usuario getOrigen() {
		return origen;
	}

	public void setOrigen(Usuario origen) {
		this.origen = origen;
	}

	public String periodoTranscurrido() {
		return Algorithm.getTiempoTranscurrido(fecha);
	}
	
	public String mensaje() {
		switch(tipo) {
		case INCLUSION_GRUPO:
			return origen.getNombre() + " Ha solicitado unirse al grupo " + objetivo.getNombre();
		case INVITACION_GRUPO:
			return origen.getNombre() + " Te ha invitado a unirte a su grupo " + objetivo.getNombre();
		default:
			return null;
		}
	}

}
