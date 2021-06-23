package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Solicitud;

@Repository
public class RepositorioSolicitudImpl implements RepositorioSolicitud{

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioSolicitudImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Solicitud> buscarSolicitudesPor(Long idUsuario) {
		return sessionFactory.getCurrentSession().createCriteria(Solicitud.class)
				.createAlias("destino", "usuarioJoin")
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.add(Restrictions.eq("usuarioJoin.id", idUsuario)).list();
	}

	@Override
	public void cargarSolicitud(Solicitud nuevaSolicitud) {
		sessionFactory.getCurrentSession().save(nuevaSolicitud);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Solicitud buscarSolicitudPor(Long idSolicitud, Long idUsuario) {
		return (Solicitud) sessionFactory.getCurrentSession().createCriteria(Solicitud.class)
				.createAlias("destino", "usuarioJoin")
				.add(Restrictions.eq("usuarioJoin.id", idUsuario))
				.add(Restrictions.eq("id", idSolicitud)).uniqueResult();
	}

	@Override
	public void borrarSolicitud(Solicitud aprobada) {
		sessionFactory.getCurrentSession().remove(aprobada);
	}

}
