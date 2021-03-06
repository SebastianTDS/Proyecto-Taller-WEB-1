<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="placeholder"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<h1 class="text-center border-bottom p-2">Miembros del grupo:
	${grupo.nombre}</h1>
<c:forEach items="${grupo.listaDeUsuarios}" var="usuario">
	<div class="alert alert-primary d-flex flex-column" style="width: 80%">
		<h5>${usuario.nombre}</h5>
		<h6>Email: ${usuario.email}</h6>


			<div>
				<c:if test="${usuario.cantidadDeEstrellas().size()==0}">
					<span class="vertical-middle">Sin calificaciones</span>
				</c:if>
				<c:forEach items="${usuario.cantidadDeEstrellas()}" var="calificacion">
					<img src="img/estrellaEncendida.png" class="" style="width: 30px; height: 30px">
				</c:forEach>
			</div>

		</div>
	</c:forEach>
<c:if test="${grupo.getAdministrador().equals(sessionScope.USUARIO) }">
	<div class="alert alert-success">
		<form action="solicitudes/invitar" method="post">
			<input type="hidden" name="grupo" id="grupo" value="${grupo.getId()}">
			<h3>Invite un usuario al grupo!</h3>
			<hr>
			<div class="d-flex align-items-center">
				<label for="correo" class="form-label mb-0 me-2">Correo: </label> <input
					type="email" class="form-control me-2" name="correo" id="correo"
					placeholder="name@example.com">
				<button type="submit" class="btn btn-outline-success">Invitar</button>
			</div>
		</form>
	</div>
</c:if>
