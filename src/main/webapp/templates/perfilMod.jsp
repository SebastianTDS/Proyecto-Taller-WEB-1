<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<div class="window col-12 col-sm-9 text-dark">
    <form:form action="grupos/modificar" method="POST" modelAttribute="formulario" class="m-3">
		<h3 class="form-signin-heading">Actualizar Datos</h3>
		<hr class="colorgraph">
		<br>
		
		<form:hidden path="id" value="${grupo.getId()}"/>
		
		<div class="row align-items-center mb-2">
			<form:label path="nombre" class="col-sm-2">Nombre de grupo: </form:label>
			<form:input path="nombre" id="nombre" type="text" class="form-control col-sm-10" value="${grupo.getNombre() }" />
		</div>
		
		<div class="row align-items-center mb-2">
			<form:label path="nombre" class="col-sm-2">Grupo privado: </form:label>
			<c:if test="${grupo.getCerrado() }">
				<form:checkbox path="cerrado" id="cerrado" class="form-control col-sm-10" checked="checked"/>
			</c:if>
			<c:if test="${!grupo.getCerrado() }">
				<form:checkbox path="cerrado" id="cerrado" class="form-control col-sm-10"/>
			</c:if>
		</div>
		
		<div class="row align-items-center mb-2">
			<form:label path="cantidadMax" class="col-sm-2">Maximo de integrantes: </form:label>
			<form:input path="cantidadMax" id="cantidadMax" type="number" class="form-control col-sm-10" value="${grupo.getCantidadMax() }"/>
		</div>
		
		<div class="row align-top mb-2">
			<label for="descripcion" class="col-sm-2">Descripcion: </label>
			<textarea name="descripcion" id="descripcion" rows="10" class="form-control col-sm-10">${grupo.getDescripcion() }</textarea>
		</div>
		
		<button class="btn btn-outline-success mt-3" type="Submit"/>Guardar cambios</button>
		<a href="grupos/${grupo.getId()}" class="btn btn-outline-danger mt-3">Descartar cambios</a>
	</form:form>
</div>