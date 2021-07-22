<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/templates/head.jsp"></jsp:include>
    <link rel="stylesheet" href="css/main.css" type="text/CSS">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.5.0/mdb.min.css" rel="stylesheet"/>
</head>
<body>
    <jsp:include page="/templates/headerLogueado.jsp"></jsp:include>
    <!-- Contenido Real -->
    <main class="container-fluid">
        <div id="main" class="row text-white">
            <div class="info col-12 col-sm-3 bg-dark p-0">


                <div class="perfil mx-auto mb-2 ">
                    <img class="w-100 h-100 p-1" src="./img/placeholder.png">
                </div>

                <div class="text-center text-white mb-5">
                    <h3 class="mb-3">${sessionScope.USUARIO.nombre}</h3>
                </div>

                <ul class="opciones">
                    <li><a class="text-white" href="perfil/ir-a-mis-grupos">Mis Grupos</a></li>
                    <li><a class="text-white" href="perfil/notificaciones">Notificaciones</a></li>
                    <li><a class="text-white" href="solicitudes">Solicitudes</a></li>
                    <li><a class="text-white" href="calificaciones">Calificaciones</a></li>

                </ul>
            </div>
            <div class="window col-12 col-sm-9 text-dark">
            	<h1 class="mt-2">Mis Grupos</h1>
                <hr>
	            <div class="col-12 col-sm-12 col-md-12 col-lg-12 clo-xl-12 container-fluid row d-flex justify-content-center "style=" overflow: scroll; max-height: 800px">
	                <c:if test="${not empty misGrupos}">
	                    <c:forEach items="${misGrupos}" var="miGrupo">
	                        <div class="bg-light text-dark col-12 col-sm-6 col-md-5 col-lg-4 col-xl-3 m-3 d-flex flex-wrap align-content-between hover-shadow bg-body rounded">
	                            <div class="card-body container-fluid">
	                                <h5 class="card-title text-center">${miGrupo.nombre}</h5>
                                    <p class="card-text"><i class="fas fa-university" data-bs-toggle="tooltip" data-bs-placement="top" title="Carrera"></i> ${miGrupo.carrera.nombre}</p>
                                    <p class="card-text"> <i class="fas fa-book" data-bs-toggle="tooltip" data-bs-placement="top" title="Materia"></i> ${miGrupo.materia.nombre}</p>
	                                    <div class="text-center container m-1">
	                                        <img src="img/Logosolo.ico" style="width: 100px">
	                                    </div>
									<div class="text-center">
                                        <c:if test="${miGrupo.calificacionGrupo()==0}">
                                            <span class="vertical-middle m-1">Sin Calificar</span>
                                        </c:if>
                                        <c:if test="${miGrupo.calificacionGrupo()==1}">
                                            <img src="img/estrellaEncendida.png" class=""
                                                 style="width: 20px; height: 20px">
                                        </c:if>
                                        <c:if test="${miGrupo.calificacionGrupo()==2}">
                                            <img src="img/estrellaEncendida.png" class=""
                                                 style="width: 20px; height: 20px">
                                            <img src="img/estrellaEncendida.png" class=""
                                                 style="width: 20px; height: 20px">
                                        </c:if>
                                        <c:if test="${miGrupo.calificacionGrupo()==3}">
                                            <img src="img/estrellaEncendida.png" class=""
                                                 style="width: 20px; height: 20px">
                                            <img src="img/estrellaEncendida.png" class=""
                                                 style="width: 20px; height: 20px">
                                            <img src="img/estrellaEncendida.png" class=""
                                                 style="width: 20px; height: 20px">
                                        </c:if>
                                        <c:if test="${miGrupo.calificacionGrupo()==4}">
                                            <img src="img/estrellaEncendida.png" class=""
                                                 style="width: 20px; height: 20px">
                                            <img src="img/estrellaEncendida.png" class=""
                                                 style="width: 20px; height: 20px">
                                            <img src="img/estrellaEncendida.png" class=""
                                                 style="width: 20px; height: 20px">
                                            <img src="img/estrellaEncendida.png" class=""
                                                 style="width: 20px; height: 20px">
                                        </c:if>
                                        <c:if test="${miGrupo.calificacionGrupo()==5}">
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
									</div>
	                                <div class="d-flex justify-content-center m-3">
	                                    <a href="grupos/${miGrupo.id}">
	                                        <button type="button" class="btn btn-outline-success mt-3">Ver</button>
	                                    </a>
	                                </div>
	                            </div>
	                        </div>
	                    </c:forEach>
	                </c:if>
	                <c:if test="${empty misGrupos}">
	                    <div class="alert alert-info" role="alert">
							<h3>Parece que no eres parte de ningun grupo.</h3>
							<p>Prueba sumarte a uno! <a href="ir-a-home">Ver grupos</a></p>
						</div>
	                </c:if>
	            </div>
            </div>
        </div>
    </main>
    <!-- Fin Contenido Real -->
    <jsp:include page="/templates/footer.jsp"></jsp:include>
    <script>
        var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
        var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
            return new bootstrap.Tooltip(tooltipTriggerEl)
        })
    </script>
</body>

</html>
