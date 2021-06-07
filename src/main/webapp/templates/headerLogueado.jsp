<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session = "true" %>
<header class="container-fluid bg-dark text-warning">
    <div class="row">
        <div class="col-9 col-sm-9 col-md-9 col-lg-9 col-xl-9 p-2  text-left ">
            <a href="ir-a-home"><img src="img/umatch.png" width="150px"></a>
        </div>
        <div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3 d-flex flex-wrap  justify-content-center align-items-center flex-wrap align-content-center">
            <div class=" btn-group-lg d-flex text-white ">
                <a href="modificar-datos-usuario"><img src="img/profile.png" width="40px"></a>
                <a href="modificar-datos-usuario" class="m-1 link-light"><h5>${sessionScope.USUARIO.nombre}</h5></a>
                <a href="cerrar-sesion" class="m-1 ms-2 link-light">Salir</a>
            </div>
        </div>
    </div>
</header>