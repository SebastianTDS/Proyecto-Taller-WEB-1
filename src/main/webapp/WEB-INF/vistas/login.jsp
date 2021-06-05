<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../../templates/head.jsp"></jsp:include>
</head>

<body class="d-flex flex-wrap align-content-between">
<jsp:include page="../../templates/header.jsp"></jsp:include>

<main>

    <div class="container-fluid">


        <div class=" d-flex flex-wrap justify-content-center row m-5">


            <div class=" col-12 col-sm-12 col-md-12 col-lg-6 col-xl-6  ">
                <img src="img/undraw_remotely_2j6y.svg" class="img-fluid ilustraciones-index" alt="imagen">
            </div>


            <div class="col-12 col-sm-12 col-md-12 col-lg-6 col-xl-6 ">
                <div class="containerr">
                    <div class="title">Iniciar Sesion</div>
                    <div class="content">
                        <form:form action="validar-login" method="POST" modelAttribute="usuario">
                            <div class="user-details">
                                <div class="input-box">
                                    <span class="details">Usuario</span>
                                    <form:input path="email" id="nombre" type="text"
                                                class="form-control form-control-sm"/>
                                </div>
                            </div>
                            <div class="user-details">
                                <div class="input-box">
                                    <span class="details">Password</span>
                                    <form:input path="password" id="password" type="password"
                                                class="form-control form-control-sm"/>
                                </div>
                            </div>
                            <button class="btn btn-lg btn btn-primary btn-sm" Type="Submit">Iniciar</button>
                        </form:form>
                    </div>
                </div>
            </div>


        </div>

    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
</main>

<jsp:include page="../../templates/footer.jsp"></jsp:include>

</body>
</html>

