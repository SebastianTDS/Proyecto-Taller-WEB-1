package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dto.DatosDeMensaje;
import ar.edu.unlam.tallerweb1.modelo.Mensaje;

import java.util.TreeSet;

public interface ServicioMensajes {
    void guardarUnMensaje(Long idUsuario, DatosDeMensaje datosMensaje);
    TreeSet<Mensaje> buscarMensajesDeUnGrupo(Long id);
    }
