package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Mensaje;

import java.util.List;

public interface RepositorioMensaje{

    void save(Mensaje mensaje);

     List<Mensaje> getMensajesByIDGrupo(Long id);
}
