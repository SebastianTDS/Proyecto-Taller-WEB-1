<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session = "true" %>
<div class="window col-12 col-sm-9 text-dark">
	<form action="grupos/eliminar" method="POST" id="delete"></form>
    <ul id="datos" class="mt-5 text-center px-5 h-100">
        <li class="perfil mx-auto">
            <img class="w-100 h-100 p-1 border-secondary" src="./img/placeholder.png">
        </li>
        <li class="mt-2">
        	<h1>${grupo.getNombre()}</h1>
        </li>
        <li class="text-primary">
            <h3>${grupo.getMateria().getNombre()} - ${grupo.getCarrera().getNombre()} - Turno ${grupo.getTurno().name()}</h3>
        </li>
        <hr>
        <li><h4>Integrantes: ${grupo.getListaDeUsuarios().size()}/${grupo.getCantidadMax()}</h4></li>
        <li><h4>Privacidad: ${grupo.getCerrado()? "Cerrado" : "Abierto"}</h4></li>
        <li class="my-4">
        	<p>${grupo.getDescripcion()}</p>
        </li>
        <li class="text-end mt-auto">
	        <c:if test="${grupo.getAdministrador().equals(sessionScope.USUARIO) }">
	        	<a href="grupos/${grupo.getId()}/edicion" class="btn btn-outline-primary">Editar Info</a>
	        	<button type="submit" class="btn btn-outline-danger" name="id" form="delete" value="${grupo.getId()}">Eliminar Grupo</button>
	        </c:if>
        </li>
    </ul>
</div>