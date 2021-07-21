<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<form action="grupos/eliminar" method="POST" id="delete"></form>
<ul id="datos" class="mt-5 text-center px-5 h-100">
	<li class="perfil mx-auto"><img
		class="w-100 h-100 p-1 border-secondary" src="./img/placeholder.png">
	</li>
	<li class="mt-2">
		<h1>${grupo.getNombre()}</h1>
	</li>
	<li class="text-primary">
		<h3>${grupo.getMateria().getNombre()}-
			${grupo.getCarrera().getNombre()} - Turno ${grupo.getTurno().name()}</h3>
	</li>
	<li>
        <c:if test="${grupo.calificacionGrupo()==0}">
            <span class="vertical-middle m-1">Grupo sin calificaciones</span>
        </c:if>
        <c:if test="${grupo.calificacionGrupo()==1}">
            <img src="img/estrellaEncendida.png" class=""
                 style="width: 20px; height: 20px">
        </c:if>
        <c:if test="${grupo.calificacionGrupo()==2}">
            <img src="img/estrellaEncendida.png" class=""
                 style="width: 20px; height: 20px">
            <img src="img/estrellaEncendida.png" class=""
                 style="width: 20px; height: 20px">
        </c:if>
        <c:if test="${grupo.calificacionGrupo()==3}">
            <img src="img/estrellaEncendida.png" class=""
                 style="width: 20px; height: 20px">
            <img src="img/estrellaEncendida.png" class=""
                 style="width: 20px; height: 20px">
            <img src="img/estrellaEncendida.png" class=""
                 style="width: 20px; height: 20px">
        </c:if>
        <c:if test="${grupo.calificacionGrupo()==4}">
            <img src="img/estrellaEncendida.png" class=""
                 style="width: 20px; height: 20px">
            <img src="img/estrellaEncendida.png" class=""
                 style="width: 20px; height: 20px">
            <img src="img/estrellaEncendida.png" class=""
                 style="width: 20px; height: 20px">
            <img src="img/estrellaEncendida.png" class=""
                 style="width: 20px; height: 20px">
        </c:if>
        <c:if test="${grupo.calificacionGrupo()==5}">
            <img src="img/estrellaEncendida.png" class=""
                 style="width: 20px; height: 20px">
            <img src="img/estrellaEncendida.png" class=""
                 style="width: 20px; height: 20px">
            <img src="img/estrellaEncendida.png" class=""
                 style="width: 20px; height: 20px">
            <img src="img/estrellaEncendida.png" class=""
                 style="width: 20px; height: 20px">
            <img src="img/estrellaEncendida.png" class=""
                 style="width: 20px; height: 20px">
        </c:if>
	</li>
	<hr>
	<li><h4>Integrantes:
			${grupo.getListaDeUsuarios().size()}/${grupo.getCantidadMax()}</h4></li>
	<li><h4>Privacidad: ${grupo.getCerrado()? "Cerrado" : "Abierto"}</h4></li>
	<li class="my-4">
		<p>${grupo.getDescripcion()}</p>
	</li>
	<li class="text-end mt-auto"><c:if
			test="${!grupo.getAdministrador().equals(sessionScope.USUARIO) }">
			<form:form action="grupos/salir" method="POST" id="salir"></form:form>
			<button type="submit" class="btn btn-outline-danger" name="id"
				form="salir" value="${grupo.getId()}">Salir del Grupo</button>
		</c:if> <c:if
			test="${grupo.getAdministrador().equals(sessionScope.USUARIO) }">
			<a href="grupos/${grupo.getId()}/edicion"
				class="btn btn-outline-primary">Editar Info</a>
			<button type="submit" class="btn btn-outline-danger" name="id"
				form="delete" value="${grupo.getId()}">Eliminar Grupo</button>
		</c:if></li>
</ul>
