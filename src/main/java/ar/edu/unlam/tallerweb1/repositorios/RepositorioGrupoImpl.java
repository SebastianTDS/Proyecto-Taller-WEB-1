package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Grupo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("repositorioGrupo")
public class RepositorioGrupoImpl implements RepositorioGrupo{


private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioGrupoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void guardarGrupo(Grupo grupoNuevo) {
        sessionFactory.getCurrentSession().save(grupoNuevo);
    }
    @Override
    public Grupo buscarPorId(Long idDelGrupoABuscar) {
        return sessionFactory.getCurrentSession().get(Grupo.class, idDelGrupoABuscar);
    }

    @Override
    public List buscarTodos() {
        return sessionFactory.getCurrentSession().createQuery("from Grupo").getResultList();
    }


}
