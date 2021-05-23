package ar.edu.unlam.tallerweb1.ServiciosTest;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.servicios.CamposDelFormularioIncompletos;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;


public class ServicioGrupoTest extends SpringTest{

    @Autowired
    private ServicioGrupo servicio ;



    @Test @Transactional @Rollback
    public void siElFormularioEstaCompletoQueSePuedaCrearElGrupo(){
        DatosDeGrupo losPicatecla=givenQueExisteUnGrupo();
         Grupo grupoGeneradoAPartirDeLosDatosDeGrupo = whenCreoElGrupoConAtributosCompletos(losPicatecla);
        thenElGrupoSeCreo(grupoGeneradoAPartirDeLosDatosDeGrupo);
    }


    @Test (expected = NullPointerException.class)
    @Rollback @Transactional
    public void siElFormularioEstaIncompletoQueNoSeCreeUnGrupo(){

                DatosDeGrupo losPicatecla=givenQueExisteUnGrupoIncompleto();
                Grupo grupo=whenCreoElGrupoConAtributosIncompletos(losPicatecla);
                thenElGrupoNoSeCreo(grupo);
        }


    private void thenElGrupoNoSeCreo(Grupo losPicatecla) {
        assertThat(losPicatecla).isNull();
    }

    private Grupo whenCreoElGrupoConAtributosIncompletos(DatosDeGrupo losPicatecla) {
          return servicio.crearGrupo(losPicatecla);
    }


    private DatosDeGrupo givenQueExisteUnGrupoIncompleto(){
        DatosDeGrupo datosdegrupo = new DatosDeGrupo();
        String nombre = "Los Picateclas";

        String materia = "Taller web";
        Turno turno = Turno.NOCHE;
        Boolean privado = false;
        Integer ctdMaxima = 5;
        String descripcion =  "Grupo de test para taller web";

        datosdegrupo.setNombre(nombre);

        datosdegrupo.setMateria(materia);
        datosdegrupo.setTurno(turno);
        datosdegrupo.setPrivado(privado);
        datosdegrupo.setCtdMaxima(ctdMaxima);
        datosdegrupo.setDescripcion(descripcion);

        return datosdegrupo;
    }


    private DatosDeGrupo givenQueExisteUnGrupo() {
        DatosDeGrupo datosdegrupo = new DatosDeGrupo();
        String nombre = "Los Picateclas";
        String carrera = "Desarrollo web";
        String materia = "Taller web";
        Turno turno = Turno.NOCHE;
        Boolean privado = false;
        Integer ctdMaxima = 5;
        String descripcion =  "Grupo de test para taller web";

        datosdegrupo.setNombre(nombre);
        datosdegrupo.setCarrera(carrera);
        datosdegrupo.setMateria(materia);
        datosdegrupo.setTurno(turno);
        datosdegrupo.setPrivado(privado);
        datosdegrupo.setCtdMaxima(ctdMaxima);
        datosdegrupo.setDescripcion(descripcion);

        return datosdegrupo;
    }

    private Grupo whenCreoElGrupoConAtributosCompletos(DatosDeGrupo losPicatecla) {
        return servicio.crearGrupo(losPicatecla);
    }

    private void thenElGrupoSeCreo(Grupo grupoGenerado) {
        assertThat(grupoGenerado).isNotNull();
    }


}
