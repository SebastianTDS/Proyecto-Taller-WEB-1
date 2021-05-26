package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.modelo.Materia;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioMateriaImpl implements  RepositorioMateria{

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioMateriaImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Materia> buscarTodasLasMaterias() {
        String s = "select m from Materia m";
        return sessionFactory.getCurrentSession().createQuery(s, Materia.class ).getResultList();
    }

    @Override
    public Materia buscarMateriaPorId(Long id) {
        return sessionFactory.getCurrentSession().get(Materia.class,id);
    }

}