package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;

public interface ServicioGrupo {


    Grupo crearGrupo(DatosDeGrupo grupoNuevo) throws CamposDelFormularioIncompletos;
    
}
