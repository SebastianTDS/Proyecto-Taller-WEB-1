package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;

@Service
@Transactional
public class ServicioNotificacionesImpl implements ServicioNotificaciones {

	private RepositorioNotificacion repository;
	
	@Autowired
	public ServicioNotificacionesImpl(RepositorioNotificacion repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Notificacion> obtenerNotificacionesPor(Long usuario) {
		return repository.getNotificacionesPor(usuario);
	}

}
