package ar.edu.unlam.tallerweb1.repositorios;
import ar.edu.unlam.tallerweb1.modelo.Archivo;
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
        return new HashSet<>(cr.list());
    }

    @Override
    public Long crearRegistroDeArchivo(Archivo archivo) {
        sessionFactory.getCurrentSession().save(archivo);
        return archivo.getId();
    }

    @Override
    public Archivo buscarArchivosPorId(Long idArchivo) {
        return sessionFactory.getCurrentSession().get(Archivo.class,idArchivo);
    }

    @Override
    public void borrarArchivoPorId(Archivo archivo) {
        sessionFactory.getCurrentSession().delete(archivo);
    }


}