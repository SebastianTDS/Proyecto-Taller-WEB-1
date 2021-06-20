function fcEnviarMsj() {
    let contenido = quill.container.firstChild.innerHTML;
    form = document.getElementById("formulario");
    form.mensaje.value = contenido;
    form.submit();
}
