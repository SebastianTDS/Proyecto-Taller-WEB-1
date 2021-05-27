package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupoParaBusqueda;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.util.enums.Privacidad;

import org.hibernate.SessionFactory;
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

    @Override
    public void guardarGrupo(Grupo grupoNuevo) {
        sessionFactory.getCurrentSession().save(grupoNuevo);
    }

    @Override
    public Grupo buscarPorId(Long idDelGrupoABuscar) {
        return sessionFactory.getCurrentSession().get(Grupo.class, idDelGrupoABuscar);
    }

    @Override
    public List<Grupo> buscarTodos() {
        String s = "select g from Grupo g";
        return sessionFactory.getCurrentSession().createQuery(s, Grupo.class).getResultList();
    }

        @Override
        public List<Grupo> buscarGrupoPorDatos(DatosDeGrupoParaBusqueda  datosDeGrupo) {

            int sentenciasUsadas = 0;
            int sentenciasTotales = camposCompletos(datosDeGrupo);
            String hql = generadorDeQueryBusqueda(datosDeGrupo, sentenciasUsadas, sentenciasTotales);
            System.out.println(hql);
            return sessionFactory.getCurrentSession().createQuery(hql,Grupo.class).getResultList();
        }

        private String generadorDeQueryBusqueda(DatosDeGrupoParaBusqueda datosDeGrupo, int sentenciasUsadas, int sentenciasTotales) {
            String hql = "from Grupo gr where";
            if (datosDeGrupo.getPrivacidad() != null && datosDeGrupo.getPrivacidad()!=Privacidad.TODO) {
                byte estado;
                if (datosDeGrupo.getPrivacidad()== Privacidad.PRIVADO)
                    estado = 1;
                else
                    estado = 0;
                hql += " gr.privado="+estado;
                if (sentenciasUsadas < sentenciasTotales) {
                    hql += " and";
                    sentenciasUsadas++;
                }
            }
            if (datosDeGrupo.getTurno() != null) {
                hql += " gr.turno='"+datosDeGrupo.getTurno()+"'";
                if (sentenciasUsadas < sentenciasTotales) {
                    hql += " and";
                    sentenciasUsadas++;
                }
            }
            if (datosDeGrupo.getCarrera() != null && datosDeGrupo.getCarrera()!=999999) {
                hql += " gr.carrera="+datosDeGrupo.getCarrera();
                if (sentenciasUsadas < sentenciasTotales) {
                    hql += " and";
                    sentenciasUsadas++;
                }
            }
            if (datosDeGrupo.getMateria() != null && datosDeGrupo.getMateria()!=999999){
                hql += " gr.materia="+datosDeGrupo.getMateria();
                if (sentenciasUsadas < sentenciasTotales) {
                    hql += " and";
                    sentenciasUsadas++;
                }
            }
            if (datosDeGrupo.getNombre() != null && !datosDeGrupo.getNombre().isBlank()) {
                hql += " gr.nombre LIKE '"+ datosDeGrupo.getNombre() + "%'";
                }
            return hql;
        }

        private int camposCompletos(DatosDeGrupoParaBusqueda datosDeGrupo) {
            int sentenciasTotales = -1;
            if (datosDeGrupo.getTurno() != null)
                sentenciasTotales++;
            if (datosDeGrupo.getCarrera() != null && datosDeGrupo.getCarrera()!=999999)
                sentenciasTotales++;
            if (datosDeGrupo.getMateria() != null && datosDeGrupo.getMateria()!=999999)
                sentenciasTotales++;
            if (datosDeGrupo.getPrivacidad() != null && datosDeGrupo.getPrivacidad()!=Privacidad.TODO)
                sentenciasTotales++;
            if (datosDeGrupo.getNombre() != null && !datosDeGrupo.getNombre().isBlank())
                sentenciasTotales++;
            return sentenciasTotales;
        }
    }
