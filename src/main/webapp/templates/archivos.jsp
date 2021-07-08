<%@ page import="ar.edu.unlam.tallerweb1.dto.DatosDeArchivo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>
<div class="window col-12 col-sm-9 text-dark" style="max-height: 500px; overflow: scroll;">
    <h1 class="text-center border-bottom p-2">Archivos de ${grupo.nombre}</h1>
    <div>
        <form:form method="POST" action="grupos/${grupo.getId()}/subir-archivo" modelAtributte="datosDeArchivo" enctype="multipart/form-data">
            <input path="archivo"  type="file" name="archivo"/>
                <input path="nombre" type="text" name="nombre"/>
            <input path="idGrupo" value="${grupo.getId()}" class="d-none" type="number" name="idGrupo"/>
            <input path="idUsuario" value="${sessionScope.USUARIO.id}" class="d-none" type="number" name="idUsuario"/>
            <button class="btn btn-lg btn btn-primary btn-sm" Type="Submit">Subir archivo</button>
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
                    <button  name="id_archivo" value="${archivo.id}" form="descargar"
                            class="btn btn-outline-primary"  Type="Submit" >Descargar
                    </button>
                    <c:if test="${sessionScope.USUARIO.id==archivo.usuario.id}">
                        <button  name="id_archivo" value="${archivo.id}" form="borrar"
                                class="btn btn-outline-primary"  Type="Submit">Borrar
                        </button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>

        <form action="grupos/${grupo.getId()}/descargar-archivo" id="descargar" method="POST">
            <input path="idGrupo" value="${grupo.getId()}" class="d-none" type="number" name="idGrupo"/>
        </form>
        <form action="grupos/${grupo.getId()}/borrar-archivo" id="borrar" method="POST">
            <input path="idGrupo" value="${grupo.getId()}" class="d-none" type="number" name="idGrupo"/>
        </form>

        </tbody>
    </table>
</div>