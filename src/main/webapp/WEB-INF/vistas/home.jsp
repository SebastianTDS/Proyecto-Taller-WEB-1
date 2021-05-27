<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
     <jsp:include page="../../templates/head.jsp"></jsp:include>
                
<body>
  	<jsp:include page="../../templates/header.jsp"></jsp:include>

<main >

	<div class="container-fluid row ">
		<div class="col-5 col-sm-4 col-md-3 col-lg-2 col-xl-2 border border-danger container-fluid">
			<div class="m-3 d-flex justify-content-center">
				<a href="ir-a-crear-nuevo-grupo">
					<button class="btn btn-lg btn btn-primary btn-sm justify-content-center">Crear Grupo</button>
				</a>
			</div>
		</div>
		<div class="col-7 col-sm-8 col-md-9 col-lg-10 col-xl-10 border border border-black row container-fluid">
			<div class="col-12 col-sm-12 col-md-12 col-lg-12 clo-xl-12 container-fluid border border-warning row d-flex justify-content-center flex-wrap align-content-center">
				<form:form action="buscar-grupos" method="POST" modelAttribute="datosParaBuscarUnGrupo" class="row text-center">
					<h4 class="form-signin-heading">Busqueda de grupos</h4>
					<div class="col-5 col-sm-4 col-md-3 col-lg-2 p-2 border-warning">
						<form:select path="carrera" id="carrera" class="form-control form-control-sm">
							<option value="">Todas las Carreras</option>
							<c:forEach items="${carreras}" var ="carrera">
								<option value="${carrera.id}">${carrera.nombre}</option>
							</c:forEach>
						</form:select>
					</div>
					<div class="col-5 col-sm-4 col-md-3 col-lg-2 p-2 border-warning">
						<form:select path="materia" id="materia" class="form-control form-control-sm">
							<option value="">Todas las Materias</option>
							<c:forEach items="${materias}" var ="materia">
								<option value="${materia.id}">${materia.nombre}</option>
							</c:forEach>
						</form:select>
					</div>
					<div class="col-5 col-sm-4 col-md-3 col-lg-2 p-2 border-warning">
						<form:select path="turno" id="turno" class="form-control form-control-sm">
							<option value="MANIANA">Mañana</option>
							<option value="TARDE">Tarde</option>
							<option value="NOCHE">Noche</option>
						</form:select>
					</div>
					<div class="col-5 col-sm-4 col-md-3 col-lg-2 p-2 border-warning">
						<form:select path="privacidad" id="privacidad" class="form-control form-control-sm">
								<option value="TODO">TODO</option>
								<option value="PUBLICO">Publico</option>
								<option value="PRIVADO">Privado</option>
						</form:select>
					</div>
					<div class="col-5 col-sm-4 col-md-3 col-lg-2 p-2 border-warning">
						<form:input path="nombre" id="nombre" type="text" class="form-control form-control-sm" placeholder="Nombre"/>
					</div>
					<div class="col-5 col-sm-4 col-md-3 col-lg-2 p-2 border-warning">
						<button class="btn btn-lg btn btn-primary btn-sm" Type="Submit">Buscar grupo</button>
					</div>
				</form:form>
			</div>
			<div class="col-12 col-sm-12 col-md-12 col-lg-12 clo-xl-12 container-fluid border border-warning row d-flex justify-content-center">
				<c:if test="${not empty grupos}">
					<c:forEach items="${grupos}" var="grupo">
						<div class="bg-light text-dark col-6 col-sm-4 col-md-2 col-lg-1 col-xl-1 m-3 d-flex flex-wrap align-content-between" style="width:200px">
							<div class="card-body">
								<h5 class="card-title">${grupo.nombre}</h5>
								<p class="card-text text-center">${grupo.carrera.nombre}</p>
								<p class="card-text">${grupo.materia.nombre}</p>
								<p class="card-text">${grupo.turno}</p>
								<c:if test="${grupo.privado==true}">
									<p class="card-text">Privado</p>
								</c:if>
								<c:if test="${grupo.privado==false}">
									<p class="card-text">Publico</p>
								</c:if>
							</div>
							<div>
								<a href="grupos/${grupo.id}" class="btn btn-primary m-2">Ingresar</a>
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