<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>


<body>


<main>

	<div class = "container">
		<h1>Bienvenidos a Taller Web 1</h1>
	</div>
	<div>
		<c:if test="${not empty grupos}">
			<h4><span>${grupos}</span></h4>
		</c:if>
	</div>
	<div>

	</div>

	<div>

		<a href="ir-a-crear-nuevo-grupo">
			<button>Crear Grupo</button>
		</a>
	</div>


</main>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>

</body>



</html>