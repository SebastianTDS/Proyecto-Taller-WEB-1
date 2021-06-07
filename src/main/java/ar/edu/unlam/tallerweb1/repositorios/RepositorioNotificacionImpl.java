package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;

@Repository
public class RepositorioNotificacionImpl implements RepositorioNotificacion {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioNotificacionImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Notificacion> getNotificacionesPor(Long idUsuario) {
		return sessionFactory.getCurrentSession().createCriteria(Notificacion.class)
				.createAlias("usuario", "usuarioJoin")
				.add(Restrictions.eq("usuarioJoin.id", idUsuario)).list();
	}

	@Override
	public void guardarNotificacion(Notificacion mensaje) {
		sessionFactory.getCurrentSession().save(mensaje);
	}

	@Override
	public void marcarVistoDeUsuario(Long usuario) {
		String hql = "UPDATE Notificacion n SET n.visto = :vis WHERE n.usuario.id = :uid";
		sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("vis", true)
				.setParameter("uid", usuario)
				.executeUpdate();
	}

}
