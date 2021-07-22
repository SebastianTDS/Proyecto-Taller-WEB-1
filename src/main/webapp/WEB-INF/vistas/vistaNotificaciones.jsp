<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <h1 class="mt-2">Notificaciones</h1>
                <hr>
                <div class="list-group">
                    <c:forEach items="${Notificaciones}" var="notificacion">
                    	<c:if test="${notificacion.getVisto()}">
							<div class="list-group-item list-group-item-action flex-column">
                    	</c:if>
                    	<c:if test="${!notificacion.getVisto()}">
                    		<div class="list-group-item list-group-item-action flex-column active">
                    	</c:if>
	                        <div class="d-flex w-100 justify-content-between align-items-center">
	                            <strong class="vertical-middle">${notificacion.getTitulo() }</strong>
	                            <small class="text-end">${notificacion.periodoTranscurrido() }</small>
	                        </div>
                    	</div>
					</c:forEach>
				<c:if test="${empty Notificaciones}">
                	<div class="m-3 alert alert-info" role="alert">
						<h5>No tienes ninguna Notificación pendiente!</h5>
					</div>
                </c:if>
                </div>
            </div>
        </div>
    </main>
    <!-- Fin Contenido Real -->
    <jsp:include page="/templates/footer.jsp"></jsp:include>
</body>

</html>