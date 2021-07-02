package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Archivo;
import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
@Repository
public class RepositorioArchivosImpl implements RepositorioArchivos{
    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioArchivosImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public HashSet<Archivo> buscarArchivosPorGrupo(Long id) {
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Archivo.class);
        cr.createCriteria("grupo").add(Restrictions.eq("id", id));
        HashSet<Archivo>archivos=new HashSet<>(cr.list());
        return archivos;
    }



}
