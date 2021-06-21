package ar.edu.unlam.tallerweb1.dto;

import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.util.auxClass.Check;
import ar.edu.unlam.tallerweb1.util.enums.Disponibilidad;
import ar.edu.unlam.tallerweb1.util.enums.Privacidad;
import ar.edu.unlam.tallerweb1.util.enums.Turno;
import ar.edu.unlam.tallerweb1.util.exceptions.LimiteDeUsuariosFueraDeRango;

public class DatosDeGrupo {

	private Long id;
	private String nombre;
	private String descripcion;
	private Integer cantidadMax;
	
	private Long carrera;
	private Long materia;
	private Usuario administrador;
	
	private Turno turno;
	private Privacidad privacidad;
	private Disponibilidad disponibilidad;

	public Long getCarrera() {
		return carrera;
	}

	public Usuario getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Usuario administrador) {
		this.administrador = administrador;
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

	public Integer getCantidadMax() {
		return cantidadMax;
	}

	public void setCantidadMax(Integer cantidadMax) {
		this.cantidadMax = cantidadMax;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCarrera(Long carrera) {
		this.carrera = carrera;
	}

	public void setMateria(Long materia) {
		this.materia = materia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Privacidad getPrivacidad() {
		return privacidad;
	}

	public void setPrivacidad(Privacidad privacidad) {
		this.privacidad = privacidad;
	}

	public Disponibilidad getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Disponibilidad disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public Boolean estaCerrado() {
		return privacidad == Privacidad.CERRADO;
	}

	public String tryGetNombre(String valorInicial) {
		if (Check.empty(nombre))
			return valorInicial;

		return nombre;
	}

	public String tryGetDescripcion(String valorInicial) {
		if (Check.empty(descripcion) || Check.outOfLength(descripcion, 255))
			return valorInicial;

		return descripcion;
	}

	public Integer tryGetCantidadMax(Integer valorInicial) {
		if (!Check.isNull(cantidadMax) && !Check.isInRange(cantidadMax, 2, 7))
			throw new LimiteDeUsuariosFueraDeRango(id);

		return Check.isNull(cantidadMax) ? valorInicial : cantidadMax;
	}

	public Boolean tryGetEstaCerrado(Boolean valorInicial) {
		if (Check.isNull(privacidad))
			return valorInicial;

		return estaCerrado();
	}

   public Grupo crearGrupoAPartirDeDatosDeGrupo() {
        if (verificarQueCtdEsteEnElRango()) {
            return getGrupo();
        }
        return null;
    }

    private Grupo getGrupo() {
        Grupo grupo = new Grupo();
        grupo.setNombre(nombre);
        grupo.setTurno(turno);
        grupo.setCerrado(estaCerrado());
        grupo.setCantidadMax(cantidadMax);
        grupo.setDescripcion(descripcion);
        grupo.setAdministrador(administrador);
        return grupo;
    }

    private boolean verificarQueCtdEsteEnElRango() {
        final Integer CTD_USUARIO_MINIMO = 2;
        final Integer CTD_USUARIO_MAXIMO = 7;

        if (verificarDatosDeGruposNoVacios()) return Check.isInRange(cantidadMax, CTD_USUARIO_MINIMO, CTD_USUARIO_MAXIMO);
        return false;
    }

    private boolean verificarDatosDeGruposNoVacios() {
        if (verificarDatosDeGruposNoSeanNulos()) return  !Check.empty(nombre) && !Check.empty(descripcion);
        return false;
    }

    private boolean verificarDatosDeGruposNoSeanNulos() {
        return materia != null & carrera!= null && nombre != null && turno!= null && privacidad != null && cantidadMax != null && descripcion != null;
    }

}
