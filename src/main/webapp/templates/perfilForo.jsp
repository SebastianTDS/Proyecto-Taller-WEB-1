<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>
<div class="window col-12 col-sm-9 text-dark" style="max-height: 500px; overflow: scroll;" >
    <h1 class="text-center border-bottom p-2">Foro ${grupo.nombre}</h1>
    <br>
    <c:forEach items="${grupo.ordenarMsj()}" var="misMensajes">
    <c:choose>
        <c:when test="${sessionScope.USUARIO.id==misMensajes.usuario.id}">
            <div class="d-flex justify-content-end">
                <div class="alert alert-success d-flex flex-column" style="width: 80%">
        </c:when>
        <c:otherwise>
            <div class="d-flex justify-content-start">
                <div class="alert alert-primary d-flex flex-column" style="width: 80%">
        </c:otherwise>
    </c:choose>
                    <div class="d-flex justify-content-between">
                        <div>
                            <h5>${misMensajes.usuario.nombre}</h5>
                        </div>
                        <div>
                            <strong>${misMensajes.tiempoDePublicacion()}</strong>
                        </div>
                    </div>
                    <div>
                        <p class="fs-6">${misMensajes.mensaje}</p>
                    </div>
                </div>
            </div>
            </c:forEach>

            <div class="m-3 container-fluid" style="max-width:95%">
                <jsp:include page="textEditor.jsp"></jsp:include>

                <button type="submit" onclick="fcEnviarMsj()" class="btn btn-outline-success m-3">Publicar</button>
            </div>

       </div>