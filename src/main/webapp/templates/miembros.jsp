<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>
<div class="window col-12 col-sm-9 text-dark" style="max-height: 500px; overflow: scroll;" >
    <h1 class="text-center border-bottom p-2">Miembros del ${grupo.nombre}</h1>
    <c:forEach items="${grupo.listaDeUsuarios}" var="usuario">
        <div class="alert alert-primary d-flex flex-column" style="width: 80%">
            <h5>${usuario.nombre}</h5>
            <h6>Email: ${usuario.email}</h6>
        </div>
    </c:forEach>
</div>