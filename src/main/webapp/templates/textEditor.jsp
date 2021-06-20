<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>

<form:form action="grupos/${grupo.getId()}/foro/enviar-msj" method="POST" id="formulario" modelAttribute="msj" class="m-3">
    <div class="col-sm-10">
        <textarea name="mensaje" id="mensaje" rows="10" class="form-control container-fluid" style="display: none"></textarea>
    </div>
    ${msj.setId(grupo.getId())}
</form:form>

<script type="text/javascript" src="js/editorQuill.js"></script>
<link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>

<div id="editor">

</div>

<script>
    var quill = new Quill('#editor', {
        theme: 'snow'
    });
</script>
</script>