<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session = "true" %>
<header class="container-fluid bg-dark text-warning">
    <div class="row">
        <div class="col-5 p-2 text-left my-2">
            <a href="ir-a-home"><img src="img/umatch.png" class="ms-5" width="150px"></a>
        </div>
        <div class="col-7 d-flex flex-wrap justify-content-end align-items-center align-content-center">
            <div class=" btn-group-lg d-flex text-white my-2">
                
                <div class="text-end">
                	<div>
	                	<c:if test="${sessionScope.PENDIENTES}">
	                    	<a href="perfil/notificaciones" class="link-warning"><i class="fas fa-bell me-2"></i></a>
	               		</c:if>
	                	<c:if test="${!sessionScope.PENDIENTES}">
	                   		<a href="perfil/notificaciones" class="link-warning"><i class="far fa-bell me-2"></i></a>
	                	</c:if>
		                <a href="perfil/ir-a-mis-grupos" class="link-light"><h5 class="mb-0 d-inline-block">${sessionScope.USUARIO.nombre}</h5></a>
		                <div class="d-flex justify-content-between align-items-center">
		                	<div class="mb-0 me-1">
								<c:if test="${sessionScope.USUARIO.cantidadDeEstrellas().size()==0}">
									<span class="vertical-middle ">Sin calificar</span>
								</c:if>
								<c:forEach items="${sessionScope.USUARIO.cantidadDeEstrellas()}" var="calificacion">
									<img src="img/estrellaEncendida.png" class="" style="width: 20px; height: 20px">
								</c:forEach>
							</div>
							<div class="">
			                	<a href="cerrar-sesion" class="link-light">Salir <i class="fas fa-sign-out-alt"></i></a>
		                	</div>
		                </div>
						
                	</div>
                </div>
                <div class="pt-2 ms-2">
	                <a href="perfil/ir-a-mis-grupos"><img src="img/profile.png" width="40px"></a>
                </div>
            </div>
        </div>
    </div>
</header>