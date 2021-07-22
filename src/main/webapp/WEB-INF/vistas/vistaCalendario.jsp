<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="placeholder"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<jsp:include page="/templates/head.jsp"></jsp:include>
	<link rel="stylesheet" href="css/main.css" type="text/CSS">
	<link rel="stylesheet" href="css/main.min.css" type="text/CSS">
	<script src="js/main.min.js"></script>
	<script src="js/bootstrap-native.min.js"></script>
	<script src="js/es.js"></script>
	<script src="js/configCalendar.js"></script>
</head>

<body>
	<jsp:include page="/templates/headerLogueado.jsp"></jsp:include>
	<main class="container-fluid ">
		<div id="main" class="row text-white">
			<div class="info col-12 col-sm-3 bg-dark p-0 ">
				<div class="mt-3 me-3 text-white text-end">
					<button class="btn btn-outline-secondary rounded ">></button>
				</div>

				<div class="perfil mx-auto mb-2 ">
					<img class="w-100 h-100 p-1" src="./img/placeholder.png">
				</div>
				<c:if test="${grupo.esMateria==true}">
					<h3 class="mb-3 text-center">${grupo.materia.nombre}</h3>

				</c:if>
				<c:if test="${grupo.esMateria!=true}">
					<div class="text-center text-white mb-5">
						<h3 class="mb-3">${grupo.getNombre()}</h3>
						<div class="row justify-content-center align-items-center">
							<div class="col-12 col-md-6 text-md-end p-0">
								<strong class="px-3 py-1 rounded bg-white text-dark">
									${grupo.getCerrado() ? "Cerrado" : "Abierto"} </strong>
							</div>
							<div class="col-12 col-md-4 mb-1 text-md-start">${grupo.getListaDeUsuarios().size()}
								/ ${grupo.getCantidadMax()}</div>
						</div>
					</div>

					<ul class="opciones">
						<li><a class="text-white" href="grupos/${grupo.getId()}">Información
								General</a></li>
						<li><a class="text-white" href="grupos/${grupo.getId()}/miembros">Miembros del grupo</a></li>
						<li><a class="text-white" href="grupos/${grupo.getId()}/archivos">Archivos</a></li>
						<li><a class="text-white" href="grupos/${grupo.getId()}/calendario">Calendario</a></li>
						<li><a class="text-white" href="grupos/${grupo.getId()}/foro">Foro</a></li>
					</ul>

				</c:if>

			</div>
			<div class="window col-12 col-sm-9 text-dark">
				<div id="error" style="display: none;">
					<div class="m-3 alert alert-danger" role="alert">
						<b id="errorMsg">La Api no esta corriendo!</b>
					</div>
				</div>
				<div id="calendario" class="m-4"></div>
				<div id="cargando">Cargando Eventos</div>
				<div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h3>Nuevo Evento</h3>
							</div>
							<div class="modal-body">
								<form action="#" method="post" id="nuevoEvento">
									<div class="d-flex align-items-center">
										<label for="start" class="form-label mb-0 me-2">Inicio Evento: </label>
										<input type="datetime-local" class="form-control" name="start" id="start" required>
									</div>
									<div class="d-flex align-items-center">
										<label for="end" class="form-label mb-0 me-2">Cierre Evento: </label>
										<input type="datetime-local" class="form-control" name="end" id="end" required>
									</div>
									<div class="d-flex align-items-center">
										<label for="title" class="form-label mb-0 me-2">Titulo: </label>
										<input type="text" class="form-control" name="title" id="title" required>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<div class="text-end">
									<button type="submit" class="btn btn-outline-success" form="nuevoEvento">Cargar Evento</button>
									<button id="cerrarModal" class="btn btn-outline-danger">Cerrar</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="/templates/footer.jsp"></jsp:include>
</body>

</html>