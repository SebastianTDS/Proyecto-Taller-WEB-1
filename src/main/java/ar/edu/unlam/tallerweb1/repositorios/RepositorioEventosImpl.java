package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Evento;

@Repository
public class RepositorioEventosImpl implements RepositorioEventos {

	private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioEventosImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Evento> buscarEventosPor(Long idGrupo) {
		return sessionFactory.getCurrentSession().createCriteria(Evento.class)
				.add(Restrictions.eq("grupo.id", idGrupo))
				.list();
	}

	@Override
	public void guardarEvento(Evento nuevoEvento) {
		sessionFactory.getCurrentSession().save(nuevoEvento);
	}

}
