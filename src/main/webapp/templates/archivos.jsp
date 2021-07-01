<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>
<div class="window col-12 col-sm-9 text-dark" style="max-height: 500px; overflow: scroll;">
    <h1 class="text-center border-bottom p-2">Archivos de ${grupo.nombre}</h1>
    <div>
        <form:form method="POST" action="grupos/${grupo.getId()}/subir-archivo" enctype="multipart/form-data">
            <table>
                <tr>
                    <td><input type="file" name="file"/></td>
                </tr>
                <tr>
                    <td><input type="button" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Nombre</th>
            <th scope="col">Subido por</th>
            <th scope="col">Fecha</th>
            <th scope="col">Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${archivos}" var="archivo">
            <tr>
                <td>${archivo.nombre}</td>
                <td>${archivo.usuario.nombre}</td>
                <td>${archivo.fecha}</td>
                <td>
                    <button type="button" name="id_achivo" value="${archivo.id}" form="descargar"
                            class="btn btn-outline-primary">Descargar
                    </button>
                    <c:if test="${sessionScope.USUARIO.id==archivo.usuario.id}">
                        <button type="button" name="id_achivo" value="${archivo.id}" form="borrar"
                                class="btn btn-outline-primary">Borrar
                        </button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>

        <form action="grupos/${grupo.getId()}/descargar-archivo" id="descargar" method="POST"></form>
        <form action="grupos/${grupo.getId()}/borrar-archivo" id="borrar" method="POST"></form>

        </tbody>
    </table>
</div>