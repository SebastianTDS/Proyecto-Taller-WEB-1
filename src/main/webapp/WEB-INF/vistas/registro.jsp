<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
	    <jsp:include page="../../templates/head.jsp"></jsp:include>
	</head>

    <body>

    <jsp:include page="../../templates/header.jsp"></jsp:include>

    <main>
        <div class="container-fluid">


            <div class="d-flex flex-wrap justify-content-center row my-5 mx-2">

                <div class="col-12 col-md-6">
                    <img src="img/undraw_press_play_bx2d.svg" class="img-fluid ilustraciones-index" alt="imagen">
                </div>

                <div class="col-12 col-md-6">
                    <div class="containerr">
                        <div class="title">Registrarse</div>
                        <div class="content">
                            <form:form action="registrarse" method="POST" modelAttribute="datosDeRegistro">
                                <div class="my-3">
                                    <div class="input-box">
                                        <span class="details">Nombre de usuario</span>
                                        <form:input path="nombre" id="nombre" type="text" class="form-control" placeholder="nombre"/>
                                    </div>
                                </div>
                                <div class="my-3">
                                    <div class="input-box">
                                        <span class="details">Email</span>
                                        <form:input path="email" type="email" id="email" class="form-control" placeholder="email"/>
                                    </div>
                                </div>
                                <div class="my-3">
                                    <div class="input-box">
                                        <span class="details">Clave</span>
                                        <form:input path="clave" id="password" type="password" class="form-control" placeholder="clave" />
                                    </div>
                                </div>
                                <div class="my-3">
                                    <div class="input-box">
                                        <span class="details">Repetir clave</span>
                                        <form:input path="repiteClave" type="password" id="password" class="form-control" placeholder="repetir clave"/>
                                    </div>
                                </div>
                                <div class="text-end">
                                    <button class="btn btn-lg btn-primary btn-block mt-2 mb-3" Type="Submit">Registrarte</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>

            </div>

        </div>


        </main>

        <jsp:include page="../../templates/footer.jsp"></jsp:include>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
        <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>

    </body>
</html>
