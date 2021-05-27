<div class="window col-12 col-sm-9 text-dark text-right">
    <ul id="datos" class="mt-5 text-center">
        <li class="perfil mx-auto mb-2">
            <img class="w-100 h-100 p-1 border-secondary" src="../img/profile.jpg">
        </li>
        <li class="row justify-content-center">
            <label for="nombre" class="col-6" hidden>Nombre:</label>
            <h1>${grupo.getNombre()}</h1>
        </li>
        <li class="text-primary">
            <h3>${grupo.getMateria().getNombre()} - ${grupo.getCarrera().getNombre()} - Turno ${grupo.getTurno().name()}</h3>
        </li>
        <li class="row mt-5 justify-content-center">
            <label for="ctdMaxima" class="col-6" hidden>Numero de Integrantes</label>
            <h4>Integrantes: 1/${grupo.getCtdMaxima()}</h4>
        </li>
        <li class="row justify-content-center">
            <label for="privado" class="col-6" hidden>Privacidad: </label>
            <h4>Privacidad: ${grupo.getPrivado()? "Privado" : "Publico"}</h4>
        </li>
        <li class="row mt-5 justify-content-center">
            <label for="descripcion" class="col-6" hidden></label>
            <p>
                ${grupo.getDescripcion()}
            </p>
        </li>
    </ul>
    <a href="${grupo.getId()}?edit=1" class="btn btn-outline-primary mt-3">Editar Info</a>
    <a href="eliminarGrupo?id=${grupo.getId()}" class="btn btn-outline-danger mt-3">Eliminar Grupo</a>
</div>