<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <jsp:include page="../../templates/head.jsp"></jsp:include>



    <body>

    <jsp:include page="../../templates/header.jsp"></jsp:include>

    <main>
            <div class = "container">
                <div id="registroContainer" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

                    <form:form action="registrarse" method="POST" modelAttribute="datosDeRegistro">
                        <h3 class="form-signin-heading">Registro</h3>
                        <hr class="colorgraph"><br>

                        <%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
                        <form:input path="nombre" id="nombre" type="text" class="form-control" placeholder="nombre"/>
                        <form:input path="email" type="email" id="email" class="form-control" placeholder="email"/>
                        <form:input path="clave" id="password" type="password" class="form-control" placeholder="clave" />
                        <form:input path="repiteClave" type="password" id="password" class="form-control" placeholder="confirme clave"/>

                        <button class="btn btn-lg btn-primary btn-block" Type="Submit">
                            Registrarte</button>
                    </form:form>

                    <%--Bloque que es visible si el elemento error no está vacío	--%>
                    <c:if test="${not empty error}">
                        <h4><span>${error}</span></h4>
                        <br>
                    </c:if>
                </div>
            </div>
        </main>

        <jsp:include page="../../templates/footer.jsp"></jsp:include>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
        <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>

    </body>
</html>
