<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<jsp:include page="/templates/head.jsp"></jsp:include>
	<link rel="stylesheet" href="css/main.css" type="text/CSS">
</head>

<body>
	<jsp:include page="/templates/headerLogueado.jsp"></jsp:include>
	<main class="container-fluid">
		<div id="main" class="row text-white">
			<div class="info col-12 col-sm-3 bg-dark p-0">
				<div class="mt-3 mr-3 text-white text-right">
					<button class="btn btn-outline-secondary rounded ">></button>
				</div>

				<div class="perfil mx-auto mb-2 ">
					<img class="w-100 h-100 p-1" src="./img/placeholder.png">
				</div>

				<div class="text-center text-white mb-5">
					<h3 class="mb-3">${grupo.getNombre()}</h3>
					<div class="row justify-content-center align-items-center">
						<div class="col-12 col-md-6 text-md-end p-0">
							<strong class="px-3 py-1 rounded bg-white text-dark">
								${grupo.getCerrado() ? "Cerrado" : "Abierto"} </strong>
						</div>
						<div class="col-12 col-md-4 mb-1 text-md-start">1 / ${grupo.getCantidadMax()}</div>
					</div>
				</div>

				<ul class="opciones">
					<li><a class="text-white" href="#">Informaci�n General</a></li>
					<li><a class="text-white" href="#">Miembros del grupo</a></li>
					<li><a class="text-white" href="#">Archivos</a></li>
					<li><a class="text-white" href="#">Calendario</a></li>
					<li><a class="text-white" href="#">Foro</a></li>
				</ul>
			</div>
			<!--   Agregar Import  -->
			<c:if test="${not empty formulario }">
				<jsp:include page="/templates/perfilMod.jsp"></jsp:include>
			</c:if>
			<c:if test="${empty formulario }">
				<jsp:include page="/templates/perfilInfo.jsp"></jsp:include>
			</c:if>
		</div>
	</main>
	<jsp:include page="/templates/footer.jsp"></jsp:include>
</body>

</html>