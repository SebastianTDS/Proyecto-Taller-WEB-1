<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap core CSS -->
<link href="../css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="../css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		 
		<h1>Perfil del grupo #${grupo.getId()}</h1>
		<hr>
		<h2>Nombre: ${grupo.getNombre()}</h2>
		<h2>Descripcion: ${grupo.getDescripcion()}</h2>
		<h2>Cantidad de integrantes Maxima: ${grupo.getCtdMaxima()}</h2>
		<h2>${grupo.getPrivado() ? "Grupo privado" : "Grupo publico"}</h2>
		<h3>${grupo.getTurno().name()} - ${grupo.getMateria().getNombre()} - ${grupo.getCarrera().getNombre()}</h3>

		<form:form action="${grupo.getId()}/modificarGrupo" method="POST" modelAttribute="formulario">
			<h3 class="form-signin-heading">Actualizar Datos</h3>
			<hr class="colorgraph">
			<br>

			<form:input path="nombre" id="nombre" type="text" class="form-control" />
			<form:input path="descripcion" id="descripcion" type="text" class="form-control" />
			<c:if test="${grupo.getPrivado() }">
				<form:checkbox path="privado" id="privado" class="form-control" checked="checked"/>
			</c:if>
			<c:if test="${!grupo.getPrivado() }">
				<form:checkbox path="privado" id="privado" class="form-control"/>
			</c:if>
			
			<form:input path="ctdMaxima" id="ctdMaxima" type="number" class="form-control"/>

			<button class="btn btn-lg btn-primary btn-block" type="Submit" />Modificar</button>
		</form:form>
		
		<form:form action="eliminarGrupo" method="GET">
			<button class="btn btn-lg btn-primary btn-block" type="Submit" name="id" value="${grupo.getId()}"/>Eliminar</button>
		</form:form>
	</div>
</body>
</html>