<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/templates/head.jsp"></jsp:include>
</head>

<body>
<jsp:include page="/templates/header.jsp"></jsp:include>

<main>

    <div class="container-fluid">


        <div class="d-flex flex-wrap justify-content-center row my-5 mx-2">


            <div class="col-12 col-md-6">
                <img src="img/undraw_remotely_2j6y.svg" class="img-fluid ilustraciones-index" alt="imagen">
            </div>


            <div class="col-12 col-md-6 mt-5">
                <div class="containerr">
                    <div class="title">Iniciar Sesion</div>
                    <div class="content">
                        <form:form action="validar-login" method="POST" modelAttribute="usuario">
                            <div class="my-3">
                                <div class="input-box">
                                    <span class="details">Usuario</span>
                                    <form:input path="email" id="email" type="text"
                                                class="form-control"/>
                                </div>
                            </div>
                            <div class="my-3">
                                <div class="input-box">
                                    <span class="details">Password</span>
                                    <form:input path="clave" id="clave" type="password"
                                                class="form-control"/>
                                </div>
                            </div>
                            <div class="text-end">
	                            <button class="btn btn-lg btn-primary btn-block mt-2 mb-3" Type="Submit">Iniciar</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>


        </div>

    </div>
</main>

<jsp:include page="/templates/footer.jsp"></jsp:include>

</body>
</html>

