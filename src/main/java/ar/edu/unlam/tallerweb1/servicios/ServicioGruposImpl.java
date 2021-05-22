package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Grupo;

@Service
public class ServicioGruposImpl implements ServicioGrupos {

	private List<Grupo> repositorio;
	private static Long autoIncrement = 1L;

	public ServicioGruposImpl() {
		repositorio = new ArrayList<Grupo>();
		repositorio.add(cargarNuevoGrupo("A", "A", 2, true));
		repositorio.add(cargarNuevoGrupo("B", "B", 3, false));
		repositorio.add(cargarNuevoGrupo("C", "C", 4, false));
		repositorio.add(cargarNuevoGrupo("D", "D", 5, true));
	}

	@Override
	public Grupo buscarGrupoPorID(Long idBuscado) {
		Grupo encontrado = repositorio.stream().filter(p -> p.getId() == idBuscado).findFirst().orElse(null);
		
		return encontrado;
	}
	
	@Override
	public Boolean modificarGrupo(Long id, Grupo formulario) {
		// TODO Auto-generated method stub
		return null;
	}

	private Grupo cargarNuevoGrupo(String nombre, String descripcion, Integer cantidad, Boolean privado) {
		Grupo nuevo = new Grupo();

		nuevo.setId(autoIncrement);
		nuevo.setNombre(nombre);
		nuevo.setDescripcion(descripcion);
		nuevo.setCtdMaxima(cantidad);
		nuevo.setPrivado(privado);

		autoIncrement++;

		return nuevo;
	}

}
