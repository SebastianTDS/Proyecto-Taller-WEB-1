<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <!-- Bootstrap core CSS -->
  <link href="../../css/bootstrap.min.css" rel="stylesheet" >
  <!-- Bootstrap theme -->
  <link href="../../css/bootstrap-theme.min.css" rel="stylesheet">
  <title>Umatch</title>
</head>
<body>

<main>

  <div class = "container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
      <h3 class="form-signin-heading">Completa los datos de tu grupo</h3>
      <hr class="colorgraph"><br>
      <form:form action="crear-grupo" method="POST" modelAttribute="datos">
        <form:input path="nombre" id="nombre" type="text" class="form-control" placeholder="Nombre"/>
        <form:input path="turno" id="turno" type="text" class="form-control" />
        <form:checkbox path="privado" id="privado"  class="form-control" />

        <form:select path="carrera" class="form-control">
          <c:forEach items="${carreras}" var ="carrera">
            <option value="${carrera.id}">${carrera.nombre}</option>
          </c:forEach>
        </form:select>
        <form:select path="materia" id="materia" class="form-control">
          <c:forEach items="${materias}" var ="materia">

            <option value="${materia.id}">${materia.nombre}</option>

          </c:forEach>
        </form:select>
        <form:input path="ctdMaxima" id="ctdMaxima" type="number" class="form-control" />
        <form:input path="descripcion" id="descripcion" type="text" class="form-control" />
        <button class="btn btn-lg btn-primary btn-block" Type="Submit">Crear el grupo</button>
      </form:form>
    </div>
  </div>


</main>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="../../js/bootstrap.min.js" type="text/javascript"></script>

</body>



</html>