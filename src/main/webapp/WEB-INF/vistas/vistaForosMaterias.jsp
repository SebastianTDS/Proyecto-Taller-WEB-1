<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
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
            <jsp:include page="/templates/nav.jsp"></jsp:include>
        </div>
        <div class="col-7 col-sm-8 col-md-9 col-lg-10 col-xl-10 border-start row container-fluid"
             style="min-height: 500px">

            <div class="col-12 col-sm-12 col-md-12 col-lg-12 clo-xl-12 container-fluid row d-flex justify-content-center " style=" overflow: scroll; max-height: 800px">
                <c:if test="${not empty grupos}">
                    <c:forEach items="${grupos}" var="grupo">
                        <div class="bg-light text-dark col-12 col-sm-6 col-md-5 col-lg-4 col-xl-3 m-3 d-flex flex-wrap align-content-between hover-shadow bg-body rounded">
                            <div class="card-body container-fluid">
                                <h5 class="card-title text-center">${grupo.materia.nombre}</h5>
                                    <div class="text-center container m-1">
                                        <img src="img/Logosolo.ico" style="width: 80px">
                                    </div>
                                <div class="d-flex justify-content-center m-3">
                                     <a href="ingresar-a-foro/${grupo.id}" class="btn btn-success mt-3">Ingresar a foro</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${empty grupos}">
                    <div>
                        No se encontraron resultados!
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</main>

<jsp:include page="/templates/footer.jsp"></jsp:include>

</body>

</html>