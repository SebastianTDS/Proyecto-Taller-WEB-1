<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<%@ page import="ar.edu.unlam.tallerweb1.util.enums.Privacidad" %>
<div class="window col-12 col-sm-9 text-dark">
    <form:form action="grupos/modificar" method="POST" modelAttribute="formulario" class="m-3">
		<h3 class="form-signin-heading">Actualizar Datos</h3>
		<hr class="colorgraph">
		<br>
		
		<form:hidden path="id" value="${grupo.getId()}"/>
		
		<div class="row align-items-center mb-2">
			<form:label path="nombre" class="col-sm-2 col-form-label">Nombre de grupo: </form:label>
			<div class="col-sm-10">
				<form:input path="nombre" id="nombre" type="text" class="form-control" value="${grupo.getNombre() }" />
			</div>
		</div>
		
		<div class="row align-items-center mb-2">
			<form:label path="nombre" class="col-sm-2 col-form-label">Privacidad: </form:label>
			<div class="col-sm-10">
				<c:if test="${grupo.getCerrado() }">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="privacidad" id="abierto" value="<%=Privacidad.ABIERTO%>"/>
						<label class="form-check-label"for="abierto">Abierto</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="privacidad" id="cerrado" value="<%=Privacidad.CERRADO%>" checked/>
						<label class="form-check-label" for="cerrado">Cerrado</label>
					</div>
				</c:if>
				<c:if test="${!grupo.getCerrado() }">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="privacidad" id="abierto" value="<%=Privacidad.ABIERTO%>" checked/>
						<label class="form-check-label" for="abierto">Abierto</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="privacidad" id="cerrado" value="<%=Privacidad.CERRADO%>"/>
						<label class="form-check-label" for="cerrado">Cerrado</label>
					</div>
				</c:if>
			</div>
		</div>
		
		<div class="row align-items-center mb-2">
			<form:label path="cantidadMax" class="col-sm-2 col-form-label">Maximo de integrantes: </form:label>
			<div class="col-sm-10">
				<form:input path="cantidadMax" id="cantidadMax" type="number" class="form-control" value="${grupo.getCantidadMax() }"/>
			</div>
		</div>
		
		<div class="row align-top mb-2">
			<label for="descripcion" class="col-sm-2 col-form-label">Descripcion: </label>
			<div class="col-sm-10">
				<textarea name="descripcion" id="descripcion" rows="10" class="form-control">${grupo.getDescripcion() }</textarea>
			</div>
		</div>
		
		<div class="text-end">
			<button class="btn btn-outline-success mt-3" type="Submit"/>Guardar cambios</button>
			<a href="grupos/${grupo.getId()}" class="btn btn-outline-danger mt-3">Descartar cambios</a>
		</div>
	</form:form>
</div>