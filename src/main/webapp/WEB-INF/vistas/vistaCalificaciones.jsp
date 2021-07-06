<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/templates/head.jsp"></jsp:include>
    <link rel="stylesheet" href="css/main.css" type="text/CSS">
</head>

<body>
    <jsp:include page="/templates/headerLogueado.jsp"></jsp:include>
    <!-- Contenido Real -->
    <main class="container-fluid">
        <div id="main" class="row text-white">
            <div class="info col-12 col-sm-3 bg-dark p-0">
                <div class="mt-3 me-3 text-white text-end">
                    <button class="btn btn-outline-secondary rounded">></button>
                </div>

                <div class="perfil mx-auto mb-2 ">
                    <img class="w-100 h-100 p-1" src="./img/placeholder.png">
                </div>

                <div class="text-center text-white mb-5">
                    <h3 class="mb-3">${sessionScope.USUARIO.nombre}</h3>
                </div>

                <ul class="opciones">
                    <li><a class="text-white" href="perfil">Informaci�n General</a></li>
                    <li><a class="text-white" href="perfil/ir-a-mis-grupos">Mis Grupos</a></li>
                    <li><a class="text-white" href="perfil/notificaciones">Notificaciones</a></li>
                    <li><a class="text-white" href="solicitudes">Solicitudes</a></li>
                    <li><a class="text-white" href="calificaciones">Calificaciones</a></li>
                </ul>
            </div>
            <div class="window col-12 col-sm-9 text-dark">
                <h1 class="mt-2">Calificaciones</h1>
                <hr>
                <div class="list-group">
				<form action="grupos/eliminar" method="POST" id="delete"></form>                
                    <c:forEach items="${calificacionesPendientes}" var="notificacion">
						<div class="list-group-item list-group-item-action flex-column">
	                        <div class="d-flex w-100 justify-content-between align-items-center">
	                            <small class="text-start">${notificacion.periodoTranscurrido() } > </small>
	                            <span class="vertical-middle">Calificar a ${notificacion.origen.nombre} del grupo ${notificacion.nombreDeGrupo}</span>
	                            <div class="d-flex justify-content-between">
                                    <form:form action="calificaciones/calificar" method="POST">
                                    <div class="input-box d-flex justify-content-between">
                                        <img src="img/estrellaApagada.png" class="m-1" id="estrellaA1" style="width: 30px; height: 30px">
                                        <img src="img/estrellaEncendida.png" class="m-1" id="estrellaE1" style="width: 30px; height: 30px;display: none">

                                        <img src="img/estrellaApagada.png" class="m-1" id="estrellaA2" style="width: 30px; height: 30px">
                                        <img src="img/estrellaEncendida.png" class="m-1" id="estrellaE2" style="width: 30px; height: 30px;display: none">

                                        <img src="img/estrellaApagada.png" class="m-1" id="estrellaA3" style="width: 30px; height: 30px">
                                        <img src="img/estrellaEncendida.png" class="m-1" id="estrellaE3" style="width: 30px; height: 30px;display: none">

                                        <img src="img/estrellaApagada.png" class="m-1" id="estrellaA4" style="width: 30px; height: 30px">
                                        <img src="img/estrellaEncendida.png" class="m-1" id="estrellaE4" style="width: 30px; height: 30px;display: none">

                                        <img src="img/estrellaApagada.png" class="m-1" id="estrellaA5" style="width: 30px; height: 30px">
                                        <img src="img/estrellaEncendida.png" class="m-1" id="estrellaE5" style="width: 30px; height: 30px;display: none">

                                        <input id="calificacion" name="calificaion" type="number" class="form-control m-1" placeholder="Calificación" style="display: none"/>
                                        <button type="submit" id="enviarCalificacion" name="idCalificacion" value="${notificacion.id}" class="btn btn-outline-success m-1" style="display: none">Calificar</button>
		                            </div>
                                    </form:form>
	                            </div>
	                        </div>
                    	</div>
					</c:forEach>
                </div>
            </div>
        </div>
    </main>
    <!-- Fin Contenido Real -->
    <script src="js/estrellasDeCalificacion.js"></script>

    <jsp:include page="/templates/footer.jsp"></jsp:include>
</body>

</html>