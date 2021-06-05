package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository
public class RepositorioNotificacionImpl implements RepositorioNotificacion {
	
	private SessionFactory sessionFactory;
	
	@Autowired
    public RepositorioNotificacionImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public List<Notificacion> getNotificacionesPor(Long idUsuario) {
		return sessionFactory.getCurrentSession().createCriteria(Notificacion.class)
				.createAlias("usuario", "joinUsuario")
				.add(Restrictions.eq("joinUsuario.id", idUsuario)).list();
	}

}
