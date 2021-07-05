package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Calificacion;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioCalificacionImpl implements RepositorioCalificacion{

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioCalificacionImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Calificacion> buscarCalificacionesPor(Long idUsuario) {
		return sessionFactory.getCurrentSession().createCriteria(Calificacion.class)
				.createAlias("destino", "usuarioJoin")
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.add(Restrictions.eq("usuarioJoin.id", idUsuario)).list();
	}

	@Override
	public void cargarCalificacion(Calificacion calificacion) {
		sessionFactory.getCurrentSession().save(calificacion);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Calificacion buscarCalificacionPor(Long idSolicitud, Long idUsuario) {
		return (Calificacion) sessionFactory.getCurrentSession().createCriteria(Calificacion.class)
				.createAlias("destino", "usuarioJoin")
				.add(Restrictions.eq("usuarioJoin.id", idUsuario))
				.add(Restrictions.eq("id", idSolicitud)).uniqueResult();
	}

	@Override
	public void borrarCalificacion(Calificacion enviada) {
		sessionFactory.getCurrentSession().remove(enviada);
	}

	@Override
	public Calificacion getExistePendiente(Long idUsuario) {
		return (Calificacion) sessionFactory.getCurrentSession().createCriteria(Calificacion.class)
				.add(Restrictions.eq("destino.id", idUsuario))
				.setMaxResults(1).uniqueResult();
	}

}
