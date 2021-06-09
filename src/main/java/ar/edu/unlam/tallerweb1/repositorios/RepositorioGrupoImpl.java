package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.util.enums.Disponibilidad;
import ar.edu.unlam.tallerweb1.util.enums.Privacidad;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.PropertyInferredData;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioGrupo")
public class RepositorioGrupoImpl implements RepositorioGrupo {

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioGrupoImpl(SessionFactory sessionFactory) {
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

    @Override
    public List<Grupo> buscarTodosMisGrupos(Usuario usuario) {
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Grupo.class);
        cr.createCriteria("listaDeUsuarios").add(Restrictions.eq("id", usuario.getId()));
        return cr.list();
    }


    @Override
    public void guardarGrupo(Grupo grupoNuevo) {
        sessionFactory.getCurrentSession().save(grupoNuevo);
    }

    @Override
    public List<Grupo> buscarTodos() {
        return sessionFactory.getCurrentSession().createQuery("SELECT g FROM Grupo g", Grupo.class).getResultList();
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<Grupo> buscarGrupoPorDatos(DatosDeGrupo datosDeGrupo) {
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Grupo.class);
        agregarCriteriosDeBusqueda(datosDeGrupo, cr);
        return cr.list();
    }

    private void agregarCriteriosDeBusqueda(DatosDeGrupo datosDeGrupo, Criteria cr) {
        if (datosDeGrupo.getTurno() != null) {
            cr.add(Restrictions.eq("turno", datosDeGrupo.getTurno()));
        }
        if (datosDeGrupo.getNombre() != null && !datosDeGrupo.getNombre().isBlank()) {
            cr.add(Restrictions.like("nombre", datosDeGrupo.getNombre() + "%"));
        }
        if (datosDeGrupo.getPrivacidad() != null) {
            cr.add(Restrictions.eq("cerrado", datosDeGrupo.estaCerrado()));
        }
        if (datosDeGrupo.getMateria() != null) {
            cr.createCriteria("materia").add(Restrictions.eq("id", datosDeGrupo.getMateria()));
        }
        if (datosDeGrupo.getCarrera() != null) {
            cr.createCriteria("carrera").add(Restrictions.eq("id", datosDeGrupo.getCarrera()));
        }
    }
}
