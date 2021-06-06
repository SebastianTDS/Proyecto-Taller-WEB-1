<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<%@ page import="ar.edu.unlam.tallerweb1.util.enums.Disponibilidad" %>
<%@ page import="ar.edu.unlam.tallerweb1.util.enums.Privacidad" %>
<%@ page import="ar.edu.unlam.tallerweb1.util.enums.Turno" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/templates/head.jsp"></jsp:include>
    <!-- MDB -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.5.0/mdb.min.css" rel="stylesheet"/>
</head>

<body>
<jsp:include page="/templates/headerLogueado.jsp"></jsp:include>

<main style="min-height: 500px">

    <div class="container-fluid row ">
        <div class="col-5 col-sm-4 col-md-3 col-lg-2 col-xl-2 container-fluid"
             style="min-height: 500px">
            <div class="m-3 d-flex justify-content-center mt-5">
                <a href="ir-a-crear-nuevo-grupo ">
                    <button class="btn btn-lg btn btn-primary btn-sm justify-content-center">Crear Grupo</button>
                </a>
            </div>
            <div class="m-1 d-flex justify-content-center mt-5">
                <a href="ir-a-home">
                    <button class="btn btn btn btn-primary btn-sm justify-content-center">Home</button>
                </a>
            </div>
        </div>
        <div class="col-7 col-sm-8 col-md-9 col-lg-10 col-xl-10 border-start row container-fluid" style="min-height: 500px">

            <div class="col-12 col-sm-12 col-md-12 col-lg-12 clo-xl-12 container-fluid row d-flex justify-content-center flex-wrap align-content-center">
                <h3 class="form-signin-heading text-center">Mis grupos</h3>
            </div>
            <div class="col-12 col-sm-12 col-md-12 col-lg-12 clo-xl-12 container-fluid row d-flex justify-content-center ">
                <c:if test="${not empty misGrupos}">
                    <c:forEach items="${misGrupos}" var="miGrupo">
                        <div class="bg-light text-dark col-12 col-sm-6 col-md-5 col-lg-4 col-xl-3 m-3 d-flex flex-wrap align-content-between hover-shadow bg-body rounded">
                            <div class="card-body container-fluid">
                                <h5 class="card-title text-center">${miGrupo.nombre}</h5>
                                <p class="card-text">${miGrupo.carrera.nombre}</p>
                                <p class="card-text">${miGrupo.materia.nombre}</p>
                                    <div class="text-center container m-1">
                                        <img src="img/Logosolo.ico" style="width: 80px">
                                    </div>

                                <div class="d-flex justify-content-center m-3">
                                    <a href="grupos/${miGrupo.id}">
                                        <button type="button" class="btn btn-success mt-3">Ver</button>
                                    </a>
                                </div>
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

<jsp:include page="/templates/footer.jsp"></jsp:include>

</body>

</html>
