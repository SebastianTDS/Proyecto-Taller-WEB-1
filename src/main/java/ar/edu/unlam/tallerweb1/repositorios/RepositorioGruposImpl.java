package ar.edu.unlam.tallerweb1.repositorios;

//import java.util.List;

import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Grupo;

@Repository
public class RepositorioGruposImpl implements RepositorioGrupos {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioGruposImpl(SessionFactory sessionFactory) {
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

//	@Override
//	public List<Grupo> buscarPorFiltros(Grupo grupo) {
//		String hql = "SELECT g FROM Grupo g JOIN g.carrera c JOIN g.materia m "
//				+ "WHERE g.nombre LIKE :nom "
//				+ "AND (c.nombre = :car OR :car = null) "
//				+ "AND (m.nombre = :mat OR :mat = null) "
//				+ "AND (g.turno = :trn OR :trn = null) "
//				+ "AND (g.privado = :prv OR :prv = null)";
//		
//		Query<Grupo> query = sessionFactory.getCurrentSession().createQuery(hql, Grupo.class);
//		
//		query.setParameter("nom", grupo.getNombre() + "%");
//		query.setParameter("car", grupo.getCarrera() == null ? null : grupo.getCarrera().getNombre());
//		query.setParameter("mat", grupo.getMateria() == null ? null : grupo.getMateria().getNombre());
//		query.setParameter("trn", grupo.getTurno());
//		query.setParameter("prv", grupo.getPrivado());
//		
//		return query.list();
//	}

}
