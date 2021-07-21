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
	            <c:if test="${error != null}">
		        	<div class="m-3 alert alert-danger" role="alert">
						${error}
					</div>
		        </c:if>
            	<form action="solicitudes/aceptar-solicitud" method="POST" id="aceptarSolicitud"></form>
				<form action="solicitudes/rechazar-solicitud" method="POST" id="rechazarSolicitud"></form>
                <h1 class="mt-2">Solicitudes</h1>
                <hr>
                <div class="list-group">
				<form action="grupos/eliminar" method="POST" id="delete"></form>                
                    <c:forEach items="${Solicitudes}" var="notificacion">
						<div class="list-group-item list-group-item-action flex-column">
	                        <div class="d-flex w-100 justify-content-between align-items-center">
	                            <small class="text-start">${notificacion.periodoTranscurrido() } > </small>
	                            <span class="vertical-middle">${notificacion.mensaje() }</span>
                               <div>
                                <c:if test="${notificacion.origen.cantidadDeEstrellas().size()==0}">
                                    <span class="vertical-middle">Sin calificaciones</span>
                                </c:if>
                                <c:forEach items="${notificacion.origen.cantidadDeEstrellas()}" var="calificacion">
                                    <img src="img/estrellaEncendida.png" class="" style="width: 30px; height: 30px">
                                </c:forEach>
                            </div>
                                <div>
		                            <div>
		                            	<button type="submit" name="idSolicitudAceptada" form="aceptarSolicitud" value="${notificacion.getId()}" class="btn btn-outline-success">Aceptar</button>
		                            	<button type="submit" name="idSolicitudRechazada" form="rechazarSolicitud" value="${notificacion.getId()}" class="btn btn-outline-danger">Rechazar</button>
		                            </div>
	                            </div>
	                        </div>
                    	</div>
					</c:forEach>
                </div>
            </div>
        </div>
    </main>
    <!-- Fin Contenido Real -->
    <jsp:include page="/templates/footer.jsp"></jsp:include>
</body>

</html>