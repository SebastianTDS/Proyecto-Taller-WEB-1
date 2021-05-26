package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Carrera;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioCarreraImpl implements RepositorioCarrera{


    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioCarreraImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Carrera> buscarTodasLasCarreras() {
        String s = "select c from Carrera c";
        return sessionFactory.getCurrentSession().createQuery(s,Carrera.class ).getResultList();
    }

    @Override
    public Carrera buscarCarreraPorId(Long id) {
        return sessionFactory.getCurrentSession().get(Carrera.class,id);
    }


}
