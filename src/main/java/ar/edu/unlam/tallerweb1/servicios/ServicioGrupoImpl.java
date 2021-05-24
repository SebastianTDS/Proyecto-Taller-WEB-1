package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("servicioGrupos")
@Transactional
public class ServicioGrupoImpl implements ServicioGrupo{

    private final RepositorioGrupo repositorioParaElServicio;

    @Autowired
    public ServicioGrupoImpl(RepositorioGrupo repositorioParaElServicio){
        this.repositorioParaElServicio=repositorioParaElServicio;
    }


    @Override
    public Grupo crearGrupo(DatosDeGrupo datosDeGrupo) {

        Grupo grupoAPartirDeDatosDeGrupo = crearGrupoAPartirDeDatosDeGrupo(datosDeGrupo);

        if (grupoAPartirDeDatosDeGrupo != null) {
            repositorioParaElServicio.guardarGrupo(grupoAPartirDeDatosDeGrupo);
        }
        return grupoAPartirDeDatosDeGrupo;
    }





        private Grupo crearGrupoAPartirDeDatosDeGrupo(DatosDeGrupo datosDeGrupo){
        if (verificarDatosDeGrupoNoEstenVacios(datosDeGrupo)){
            return getGrupo(datosDeGrupo);
            }
            return null;
        }

        private Grupo getGrupo(DatosDeGrupo datosDeGrupo) {
                Grupo grupo = new Grupo();
                grupo.setNombre(datosDeGrupo.getNombre());
                grupo.setCarrera(datosDeGrupo.getCarrera());
                grupo.setMateria(datosDeGrupo.getMateria());
                grupo.setTurno(datosDeGrupo.getTurno());
                grupo.setPrivado(datosDeGrupo.getPrivado());
                grupo.setCtdMaxima(datosDeGrupo.getCtdMaxima());
                grupo.setDescripcion(datosDeGrupo.getDescripcion());
                return grupo;
            }

        private boolean verificarDatosDeGrupoNoEstenVacios(DatosDeGrupo datosDeGrupo) {
            final Integer CTD_USUARIO_MINIMO = 2;
            final Integer CTD_USUARIO_MAXIMO = 8;

                                if (verificarDatosDeGruposNoVacios(datosDeGrupo)) {
                                                    return datosDeGrupo.getCtdMaxima() >= CTD_USUARIO_MINIMO &&
                                                                 datosDeGrupo.getCtdMaxima() <= CTD_USUARIO_MAXIMO;

                                }
                                                     return false;

        }

        private boolean verificarDatosDeGruposNoVacios(DatosDeGrupo datosDeGrupo){

                                if(verificarDatosDeGruposNoSeanNulos(datosDeGrupo)) {

                                    return
                                                   !datosDeGrupo.getNombre().isBlank() &&
                                                    !datosDeGrupo.getCarrera().isBlank() &&
                                                    !datosDeGrupo.getMateria().isBlank() &&
                                                    !datosDeGrupo.getDescripcion().isBlank();

                                }
                                return false ;
        }

                 private boolean verificarDatosDeGruposNoSeanNulos(DatosDeGrupo datosDeGrupo){

        return         datosDeGrupo.getNombre() != null &&
                             datosDeGrupo.getCarrera() != null &&
                             datosDeGrupo.getMateria() != null &&
                             datosDeGrupo.getTurno() != null &&
                             datosDeGrupo.getPrivado() != null &&
                             datosDeGrupo.getCtdMaxima() != null &&
                             datosDeGrupo.getDescripcion() != null;
                 }

}
