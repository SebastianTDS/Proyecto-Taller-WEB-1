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
                <c:forEach items="${calificacionesPendientes}" var="notificacion">
                    <div class="list-group-item list-group-item-action flex-column">
                        <div class="d-flex w-100 justify-content-between align-items-center">
                            <small class="text-start">${notificacion.periodoTranscurrido() } > </small>
                            <span class="vertical-middle">Calificar a ${notificacion.origen.nombre} del grupo ${notificacion.nombreDeGrupo}</span>
                            <div class="d-flex justify-content-between">
                                <div class="input-box d-flex justify-content-between"
                                     onmouseover="animarEstrellas(${notificacion.id})">

                                    <img src="img/estrellaApagada.png" class="p-2" id="estrellaA1${notificacion.id}"
                                         style="width: 40px; height: 40px">
                                    <img src="img/estrellaEncendida.png" class="p-2" id="estrellaE1${notificacion.id}"
                                         style="width: 40px; height: 40px;display: none" onmouseout="setearEstrellas(${notificacion.id})">

                                    <img src="img/estrellaApagada.png" class="p-2" id="estrellaA2${notificacion.id}"
                                         style="width: 40px; height: 40px">
                                    <img src="img/estrellaEncendida.png" class="p-2" id="estrellaE2${notificacion.id}"
                                         style="width: 40px; height: 40px;display: none" onmouseout="setearEstrellas(${notificacion.id})">

                                    <img src="img/estrellaApagada.png" class="p-2" id="estrellaA3${notificacion.id}"
                                         style="width: 40px; height: 40px">
                                    <img src="img/estrellaEncendida.png" class="p-2" id="estrellaE3${notificacion.id}"
                                         style="width: 40px; height: 40px;display: none" onmouseout="setearEstrellas(${notificacion.id})">

                                    <img src="img/estrellaApagada.png" class="p-2" id="estrellaA4${notificacion.id}"
                                         style="width: 40px; height: 40px">
                                    <img src="img/estrellaEncendida.png" class="p-2" id="estrellaE4${notificacion.id}"
                                         style="width: 40px; height: 40px;display: none" onmouseout="setearEstrellas(${notificacion.id})">

                                    <img src="img/estrellaApagada.png" class="p-2" id="estrellaA5${notificacion.id}"
                                         style="width: 40px; height: 40px">
                                    <img src="img/estrellaEncendida.png" class="p-2" id="estrellaE5${notificacion.id}"
                                         style="width: 40px; height: 40px;display: none" onmouseout="setearEstrellas(${notificacion.id})">

                                    <button type="submit" form="formCalificacion"
                                            id="enviarCalificacion${notificacion.id}" name="idCalificacion"
                                            value="${notificacion.id}" class="btn btn-outline-success m-1"
                                            style="display: none">Calificar
                                    </button>
                                </div>

                            </div>
                        </div>
                    </div>
                </c:forEach>
                <form:form action="calificaciones/calificar" id="formCalificacion" method="POST">
                    <input id="calificacion" name="calificaion" type="number" class="form-control m-1"
                           placeholder="Calificación" style="display: none"/>
                </form:form>
            </div>
        </div>
    </div>
</main>
<!-- Fin Contenido Real -->
<script src="js/estrellasDeCalificacion.js"></script>

<jsp:include page="/templates/footer.jsp"></jsp:include>
</body>

</html>