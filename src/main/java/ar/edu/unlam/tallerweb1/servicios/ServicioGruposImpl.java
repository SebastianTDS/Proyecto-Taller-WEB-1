package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGrupos;

@Service
@Transactional
public class ServicioGruposImpl implements ServicioGrupos {

	private RepositorioGrupos repository;

	@Autowired
	public ServicioGruposImpl(RepositorioGrupos repositorioGrupos) {
		this.repository = repositorioGrupos;
	}

	@Override
	public Grupo buscarGrupoPorID(Long idBuscado) {
		Grupo encontrado = repository.getGrupoByID(idBuscado);

		return encontrado;
	}

	@Override
	public void modificarGrupo(Long id, Grupo formulario) {
		Grupo objetivo = repository.getGrupoByID(id);

		objetivo.actualizar(formulario);

		if (formulario.getCtdMaxima() != null && objetivo.getCtdMaxima() != formulario.getCtdMaxima())
			return;

		repository.actualizarGrupo(objetivo);
	}

	@Override
	public void eliminarGrupo(Long idBuscado) {
		Grupo objetivo = repository.getGrupoByID(idBuscado);

		repository.eliminarGrupo(objetivo);
	}

}
