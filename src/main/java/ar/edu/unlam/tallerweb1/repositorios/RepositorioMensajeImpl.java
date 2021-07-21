package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class RepositorioMensajeImpl implements RepositorioMensaje {
    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioMensajeImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Mensaje mensajeCreado) {
        sessionFactory.getCurrentSession().save(mensajeCreado);
    }

    @Override
    public List<Mensaje> getMensajesByIDGrupo(Long id) {
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Mensaje.class);
        cr.createCriteria("grupo").add(Restrictions.eq("id", id));
        cr.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return cr.list();
    }


}
