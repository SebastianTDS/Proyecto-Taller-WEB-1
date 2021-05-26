package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.DatosDeGrupoParaBusqueda;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Privacidad;
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
            if (datosDeGrupo.getCarrera() != null) {
                hql += " gr.carrera="+datosDeGrupo.getCarrera();
                if (sentenciasUsadas < sentenciasTotales) {
                    hql += " and";
                    sentenciasUsadas++;
                }
            }
            if (datosDeGrupo.getMateria() != null){
                hql += " gr.materia="+datosDeGrupo.getMateria();
                if (sentenciasUsadas < sentenciasTotales) {
                    hql += " and";
                    sentenciasUsadas++;
                }
            }
            if (datosDeGrupo.getNombre() != null && !datosDeGrupo.getNombre().isBlank()) {
                hql += " gr.nombre LIKE"+ datosDeGrupo.getNombre() + "%";
                }
            return hql;
        }

        private int camposCompletos(DatosDeGrupoParaBusqueda datosDeGrupo) {
            int sentenciasTotales = -1;
            if (datosDeGrupo.getCarrera() != null)
                sentenciasTotales++;
            if (datosDeGrupo.getMateria() != null)
                sentenciasTotales++;
            if (datosDeGrupo.getPrivacidad() != null)
                sentenciasTotales++;
            if (datosDeGrupo.getNombre() != null && !datosDeGrupo.getNombre().isBlank())
                sentenciasTotales++;
            return sentenciasTotales;
        }
    }
