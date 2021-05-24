package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Grupo;

@Repository
public class RepositorioGruposImpl implements RepositorioGrupos {

	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioGruposImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Grupo getGrupoByID(Long id) {
		return sessionFactory.getCurrentSession().get(Grupo.class, id);
	}

	@Override
	public void actualizarGrupo(Grupo objetivo) {
		sessionFactory.getCurrentSession().update(objetivo);
	}

	@Override
	public void eliminarGrupo(Grupo objetivo) {
		sessionFactory.getCurrentSession().remove(objetivo);
	}

}
