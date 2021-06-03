<div class="window col-12 col-sm-9 text-dark text-right">
	<form action="grupos/eliminar" method="POST" id="delete"></form>
    <ul id="datos" class="mt-5 text-center">
        <li class="perfil mx-auto mb-2">
            <img class="w-100 h-100 p-1 border-secondary" src="./img/placeholder.png">
        </li>
        <li class="row justify-content-center">
            <h1>${grupo.getNombre()}</h1>
        </li>
        <li class="text-primary">
            <h3>${grupo.getMateria().getNombre()} - ${grupo.getCarrera().getNombre()} - Turno ${grupo.getTurno().name()}</h3>
        </li>
        <li class="row mt-5 justify-content-center">
            <h4>Integrantes: 1/${grupo.getCantidadMax()}</h4>
        </li>
        <li class="row justify-content-center">
            <h4>Privacidad: ${grupo.getCerrado()? "Cerrado" : "Abierto"}</h4>
        </li>
        <li class="row mt-5 justify-content-center">
            <p>
                ${grupo.getDescripcion()}
            </p>
        </li>
    </ul>
    <a href="grupos/${grupo.getId()}/edicion" class="btn btn-outline-primary mt-3">Editar Info</a>
    <button type="submit" class="btn btn-outline-danger mt-3" name="id" form="delete" value="${grupo.getId()}">Eliminar Grupo</button>
</div>