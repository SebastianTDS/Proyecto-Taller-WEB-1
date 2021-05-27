<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--
        Links de bootstrap 4
    -->
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet" />
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet" />
	
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <link rel="icon" type="image/png" href="img/Logosolo.ico" />

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
    <link rel="stylesheet" href="css/estilos.css" type="text/CSS">
    <link rel="stylesheet" href="css/main.css" type="text/CSS">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
    <title>Umatch</title>
</head>
<body>
<jsp:include page="../../templates/headerLogueado.jsp"></jsp:include>
<main>

  <div class="container-fluid">

    <div class=" d-flex flex-wrap justify-content-center row m-5">

      <div class=" col-12 col-sm-12 col-md-12 col-lg-6 col-xl-6  ">

        <img src="img/crear_grupo.svg" class=" img-fluid ilustraciones-index" alt="imagen">

      </div>


      <div class="col-12 col-sm-12 col-md-12 col-lg-6 col-xl-6 ">

        <div class="containerr">
          <div class="title">Crea tu equipo</div>
          <div class="content">
            <form:form action="crear-grupo" method="POST" modelAttribute="datos">
            <div class="user-details">
              <div class="input-box">
                <span class="details">Nombre del equipo</span>
                <form:input path="nombre" id="nombre" type="text"  class="form-control form-control-sm"/>
              </div>
              <div class="input-box">
                <span class="details">Descripcion del grupo</span>
                <form:input path="descripcion" id="descripcion" type="textarea"  class="form-control form-control-sm"/>
              </div>
              <div class="input-box">
                <span class="details">Cantidad De Integrantes</span>
                <form:input path="ctdMaxima" id="ctdMaxima" type="number" class="form-control form-control-sm"/>
              </div>
              <div class="input-box">
                <span class="details mb-3">Carrera</span>
              <form:select path="carrera" class="form-control form-control-sm">
                <c:forEach items="${carreras}" var="carrera">
                  <option value="${carrera.id}">${carrera.nombre}</option>
                </c:forEach>
              </form:select>
              </div>
                <div class="input-box">
                  <span class="details">Materia</span>
              <form:select path="materia" id="materia" class="form-control form-control-sm">
                <c:forEach items="${materias}" var="materia">
                  <option value="${materia.id}">${materia.nombre}</option>
                </c:forEach>
              </form:select>
                </div>
                <div class="input-box">
                  <span class="details">Turno</span>
              <form:select path="turno" id="turno" class="form-control form-control-sm">
                <option value="MANIANA">MANANA</option>
                <option value="TARDE">TARDE</option>
                <option value="NOCHE">NOCHE</option>
              </form:select>
                </div>
                <div>
                	<span class="details">Privacidad</span>
               		<form:checkbox path="privado" id="privado" class="form-control" />
                </div>
                
              <button class="btn btn-lg btn btn-primary btn-sm" Type="Submit">Crear!</button>
              </form:form>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>

</main>
<jsp:include page="../../templates/footer.jsp"></jsp:include>
</body>

</html>