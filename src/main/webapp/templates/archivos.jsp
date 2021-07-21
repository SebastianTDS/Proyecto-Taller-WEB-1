<%@ page import="ar.edu.unlam.tallerweb1.dto.DatosDeArchivo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="placeholder"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<h1 class="text-center border-bottom p-2">Archivos de
	${grupo.nombre}</h1>
<div class="d-flex justify-content-between">
	<form:form method="POST" action="grupos/${grupo.getId()}/subir-archivo"
		modelAtributte="datosDeArchivo" enctype="multipart/form-data"
		class="d-flex justify-content-between">
		<input path="archivo" id="file" type="file" name="archivo"
			class="form-control m-1" style="width: 400px" />
		<input path="nombre" type="text" name="nombre"
			class="form-control m-1" placeholder="Nombre de archivo"
			style="width: 400px" />
		<input path="idGrupo" value="${grupo.getId()}" class="d-none m-1"
			type="number" name="idGrupo" />
		<input path="idUsuario" value="${sessionScope.USUARIO.id}"
			class="d-none" type="number" name="idUsuario" />
		<button class="btn btn-lg btn btn-primary btn-sm m-1" Type="Submit">
			<i class="fas fa-cloud-upload-alt fa-lg" style="width: 50px"></i>
		</button>
	</form:form>
</div>

<table class="table">
	<thead>
		<tr>
			<th scope="col">Nombre</th>
			<th scope="col">Subido por</th>
			<th scope="col">Fecha</th>
			<th scope="col">Peso</th>
			<th scope="col">Tipo</th>
			<th scope="col">Acciones</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${archivos}" var="archivo">
			<tr>
				<td>${archivo.nombre}</td>
				<td>${archivo.usuario.nombre}</td>
				<td>${archivo.fecha}</td>
				<td>${archivo.pesoArchivo}Mb</td>
				<td>.${archivo.extension}</td>

				<td>
					<button name="id_archivo" value="${archivo.id}" form="descargar"
						class="btn btn-success" Type="Submit">
						<i class="fas fa-file-download fa-lg"></i>
					</button> <c:if test="${sessionScope.USUARIO.id==archivo.usuario.id}">
						<button name="id_archivo" value="${archivo.id}" form="borrar"
							class="btn btn-danger" Type="Submit">
							<i class="fas fa-trash fa-lg"></i>
						</button>
					</c:if>
				</td>
			</tr>
		</c:forEach>

		<form action="grupos/${grupo.getId()}/descargar-archivo"
			id="descargar" method="POST">
			<input path="idGrupo" value="${grupo.getId()}" class="d-none"
				type="number" name="idGrupo" />
		</form>
		<form action="grupos/${grupo.getId()}/borrar-archivo" id="borrar"
			method="POST">
			<input path="idGrupo" value="${grupo.getId()}" class="d-none"
				type="number" name="idGrupo" />
		</form>

	</tbody>
</table>
