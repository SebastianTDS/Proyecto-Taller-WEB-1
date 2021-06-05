<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/templates/head.jsp"></jsp:include>
    <link rel="stylesheet" href="css/main.css" type="text/CSS">
</head>

<body>
    <jsp:include page="/templates/header.jsp"></jsp:include>
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
                    <h3 class="mb-3">Juanito</h3>
                </div>

                <ul class="opciones">
                    <li><a class="text-white" href="#">Información General</a></li>
                    <li><a class="text-white" href="#">Mis Grupos</a></li>
                    <li><a class="text-white" href="#">Notificaciones</a></li>
                </ul>
            </div>
            <div class="window col-12 col-sm-9 text-dark">
                <h1 class="mt-2">Notificaciones</h1>
                <hr>
                <div class="list-group">
                    <c:forEach items="${Notificaciones}" var="notificacion">
						<a href="#" class="list-group-item list-group-item-action flex-column align-items-start active">
	                        <div class="d-flex w-100 justify-content-between">
	                            <strong>Nuevo miembro en el grupo "Equipo Dinamita"</strong>
	                            <small>3 days ago</small>
	                        </div>
	                        <small>${notificacion.getTitulo() }</small>
                    	</a>
					</c:forEach>
                </div>
            </div>
        </div>
    </main>
    <!-- Fin Contenido Real -->
    <jsp:include page="/templates/footer.jsp"></jsp:include>
</body>

</html>