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
                    <li><a class="text-white" href="grupos/${grupo.getId()}">Informacion General</a></li>
                    <li><a class="text-white" href="grupos/${grupo.getId()}/miembros">Miembros del grupo</a></li>
                    <li><a class="text-white" href="grupos/${grupo.getId()}/archivos">Archivos</a></li>
                    <li><a class="text-white" href="grupos/${grupo.getId()}/calendario">Calendario</a></li>
                    <li><a class="text-white" href="grupos/${grupo.getId()}/foro">Foro</a></li>
                </ul>

            </c:if>

        </div>
        <!--   Agregar Import  -->
        <c:if test="${not empty formulario }">
            <jsp:include page="/templates/perfilMod.jsp"></jsp:include>
        </c:if>
        <c:if test="${empty formulario and empty msj and empty integrantes and empty vistaArchivos}">
            <jsp:include page="/templates/perfilInfo.jsp"></jsp:include>
        </c:if>
        <c:if test="${not empty msj }">
        	<jsp:include page="/templates/perfilForo.jsp"></jsp:include>
        	<form:form action="grupos/${grupo.getId()}/foro/enviar-msj" method="POST" id="formulario" modelAttribute="msj">
				<input type="hidden" name="id" id="id" value="${grupo.getId()}">
			    <div class="col-sm-10">
			        <textarea name="mensaje" id="mensaje" rows="10" class="form-control container-fluid" style="display: none"></textarea>
			    </div>
			</form:form>
        </c:if>
        <c:if test="${not empty integrantes }">
            <jsp:include page="/templates/miembros.jsp"></jsp:include>
        </c:if>
        <c:if test="${not empty vistaArchivos}">
            <jsp:include page="/templates/archivos.jsp"></jsp:include>
        </c:if>
    </div>
</main>
<jsp:include page="/templates/footer.jsp"></jsp:include>
</body>

</html>