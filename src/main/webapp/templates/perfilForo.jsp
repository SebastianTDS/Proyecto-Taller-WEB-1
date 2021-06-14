<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>

<div class="window col-12 col-sm-9 text-dark">
    <h1>Foro</h1>
    <br>
    <c:forEach items="${grupo.listaDeMensajes}" var="misMensajes">
        ${misMensajes.mensaje}
        <br>
    </c:forEach>

</div>