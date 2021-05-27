<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
     <jsp:include page="../../templates/head.jsp"></jsp:include>
                
<body>
  	<jsp:include page="../../templates/headerLogueado.jsp"></jsp:include>

<main style="min-height: 500px">

	<div class="container-fluid row ">
		<div class="col-5 col-sm-4 col-md-3 col-lg-2 col-xl-2 border-end container-fluid text-center "style="min-height: 200px">
			<h3 class="form-signin-heading mt-3">Mis grupos</h3>
			<div class="m-3 d-flex justify-content-center mt-5">
				<a href="ir-a-crear-nuevo-grupo ">
					<button class="btn btn-lg btn btn-primary btn-sm justify-content-center">Crear Grupo</button>
				</a>
			</div>
			<c:if test="${not empty grupos}">
				<c:forEach items="${grupos}" var="grupo">
					<a href="grupos/${grupo.id}">
						<div class="alert alert-light container-fluid d-flex flex-wrap align-content-between justify-content-center hover-shadow p-3 mb-5 bg-body rounded" >
							<img src="img/Logosolo.ico" style="width: 50px">
							<h5>${grupo.nombre}</h5>
						</div>
					</a>
				</c:forEach>
			</c:if>
		</div>
		<div class="col-7 col-sm-8 col-md-9 col-lg-10 col-xl-10  row container-fluid">

			<div class="col-12 col-sm-12 col-md-12 col-lg-12 clo-xl-12 container-fluid row d-flex justify-content-center flex-wrap align-content-center">
				<form:form action="buscar-grupos" method="POST" modelAttribute="datosParaBuscarUnGrupo" class="row text-center">
					<h3 class="form-signin-heading">Busqueda de grupos</h3>
					<div class="col-5 col-sm-4 col-md-3 col-lg-2 p-2">
						<form:select path="carrera" id="carrera" class="form-control form-control-sm">
							<option value="999999">TODO</option>
							<c:forEach items="${carreras}" var ="carrera">
								<option value="${carrera.id}">${carrera.nombre}</option>
							</c:forEach>
						</form:select>
					</div>
					<div class="col-5 col-sm-4 col-md-3 col-lg-2 p-2">
						<form:select path="materia" id="materia" class="form-control form-control-sm">
							<option value="999999">TODO</option>
							<c:forEach items="${materias}" var ="materia">
								<option value="${materia.id}">${materia.nombre}</option>
							</c:forEach>
						</form:select>
					</div>
					<div class="col-5 col-sm-4 col-md-3 col-lg-2 p-2">
						<form:select path="turno" id="turno" class="form-control form-control-sm">
							<option value="">TODO</option>
							<option value="MANIANA">MANIANA</option>
							<option value="TARDE">TARDE</option>
							<option value="NOCHE">NOCHE</option>
						</form:select>
					</div>
					<div class="col-5 col-sm-4 col-md-3 col-lg-2 p-2">
						<form:select path="privacidad" id="privacidad" class="form-control form-control-sm">
								<option value="TODO">TODO</option>
								<option value="PUBLICO">PUBLICO</option>
								<option value="PRIVADO">PRIVADO</option>
						</form:select>
					</div>
					<div class="col-5 col-sm-4 col-md-3 col-lg-2 p-2">
						<form:input path="nombre" id="nombre" type="text" class="form-control form-control-sm" placeholder="Nombre"/>
					</div>
					<div class="col-5 col-sm-4 col-md-3 col-lg-2 p-2">
						<button class="btn btn-lg btn btn-primary btn-sm" Type="Submit">Buscar</button>
					</div>
				</form:form>
			</div>

			<div class="col-12 col-sm-12 col-md-12 col-lg-12 clo-xl-12 container-fluid row d-flex justify-content-center ">
				<c:if test="${not empty grupos}">
					<c:forEach items="${grupos}" var="grupo">
						<div class="bg-light text-dark col-12 col-sm-6 col-md-5 col-lg-4 col-xl-3 m-3 d-flex flex-wrap align-content-between hover-shadow bg-body rounded" >
							<div class="card-body container-fluid">
								<h5 class="card-title text-center">${grupo.nombre}</h5>
								<p class="card-text">${grupo.carrera.nombre}</p>
								<p class="card-text">${grupo.materia.nombre}</p>
								<div>
									<div>
										<p class="card-text">${grupo.turno}</p>
										<c:if test="${grupo.privado==true}">
											<p class="card-text">Privado</p>
										</c:if>
										<c:if test="${grupo.privado==false}">
											<p class="card-text">Publico</p>
										</c:if>
									</div>
									<div class="text-center container m-1">
										<img src="img/Logosolo.ico" style="width: 80px">
									</div>
								</div>
								<div class="d-flex justify-content-center m-3">
									<a href="grupos/${grupo.id}" class="btn btn-primary m-2">Ingresar</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${not empty error}">
					${error}
				</c:if>
			</div>
		</div>
	</div>
</main>

  <jsp:include page="../../templates/footer.jsp"></jsp:include>

</body>

</html>