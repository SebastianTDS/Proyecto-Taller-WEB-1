package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

	private final SessionFactory sessionFactory;

    @Autowired
	public RepositorioUsuarioImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Usuario consultarUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getPassword()))
				.uniqueResult();
	}

	@Override
	public Usuario getUsuarioByID(Long id) {
		return sessionFactory.getCurrentSession().get(Usuario.class, id);
	}

	@Override
	public void guardarUsuario(Usuario usuario) {
		sessionFactory.getCurrentSession().save(usuario);
	}

	@Override
	public Usuario getUsuarioByEmail(String correo) {
		return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
				.add(Restrictions.eq("email", correo))
				.uniqueResult();
	}

	public void actualizarUsuario(Usuario usuario) {
		sessionFactory.getCurrentSession().update(usuario);
	}

}
