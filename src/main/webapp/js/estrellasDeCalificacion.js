function animarEstrellas(id) {
    document.getElementById("estrellaA1"+id).onmouseover = function () {
        mouseOver1()
    };
    document.getElementById("estrellaE1"+id).onmouseout = function () {
        mouseOut()
    };
    document.getElementById("estrellaA2"+id).onmouseover = function () {
        mouseOver2()
    };
    document.getElementById("estrellaE2"+id).onmouseout = function () {
        mouseOut()
    };
    document.getElementById("estrellaA3"+id).onmouseover = function () {
        mouseOver3()
    };
    document.getElementById("estrellaE3"+id).onmouseout = function () {
        mouseOut()
    };
    document.getElementById("estrellaA4"+id).onmouseover = function () {
        mouseOver4()
    };
    document.getElementById("estrellaE4"+id).onmouseout = function () {
        mouseOut()
    };
    document.getElementById("estrellaA5"+id).onmouseover = function () {
        mouseOver5()
    };
    document.getElementById("estrellaE5"+id).onmouseout = function () {
        mouseOut()
    };


    function mouseOut() {
        document.getElementById("estrellaA1"+id).style.display = "block";
        document.getElementById("estrellaA2"+id).style.display = "block";
        document.getElementById("estrellaA3"+id).style.display = "block";
        document.getElementById("estrellaA4"+id).style.display = "block";
        document.getElementById("estrellaA5"+id).style.display = "block";
        document.getElementById("estrellaE1"+id).style.display = "none";
        document.getElementById("estrellaE2"+id).style.display = "none";
        document.getElementById("estrellaE3"+id).style.display = "none";
        document.getElementById("estrellaE4"+id).style.display = "none";
        document.getElementById("estrellaE5"+id).style.display = "none";
    }

    function mouseOver1() {
        document.getElementById("estrellaE1"+id).style.display = "block";

        document.getElementById("estrellaA1"+id).style.display = "none";
        document.getElementById("estrellaA2"+id).style.display = "block";
        document.getElementById("estrellaA3"+id).style.display = "block";
        document.getElementById("estrellaA4"+id).style.display = "block";
        document.getElementById("estrellaA5"+id).style.display = "block";
    }

    function mouseOver2() {
        document.getElementById("estrellaE1"+id).style.display = "block";
        document.getElementById("estrellaE2"+id).style.display = "block";

        document.getElementById("estrellaA1"+id).style.display = "none";
        document.getElementById("estrellaA2"+id).style.display = "none";
        document.getElementById("estrellaA3"+id).style.display = "block";
        document.getElementById("estrellaA4"+id).style.display = "block";
        document.getElementById("estrellaA5"+id).style.display = "block";
    }

    function mouseOver3() {
        document.getElementById("estrellaE1"+id).style.display = "block";
        document.getElementById("estrellaE2"+id).style.display = "block";
        document.getElementById("estrellaE3"+id).style.display = "block";

        document.getElementById("estrellaA1"+id).style.display = "none";
        document.getElementById("estrellaA2"+id).style.display = "none";
        document.getElementById("estrellaA3"+id).style.display = "none";
        document.getElementById("estrellaA4"+id).style.display = "block";
        document.getElementById("estrellaA5"+id).style.display = "block";
    }

    function mouseOver4() {
        document.getElementById("estrellaE1"+id).style.display = "block";
        document.getElementById("estrellaE2"+id).style.display = "block";
        document.getElementById("estrellaE3"+id).style.display = "block";
        document.getElementById("estrellaE4"+id).style.display = "block";

        document.getElementById("estrellaA1"+id).style.display = "none";
        document.getElementById("estrellaA2"+id).style.display = "none";
        document.getElementById("estrellaA3"+id).style.display = "none";
        document.getElementById("estrellaA4"+id).style.display = "none";
        document.getElementById("estrellaA5"+id).style.display = "block";
    }

    function mouseOver5() {
        document.getElementById("estrellaE1"+id).style.display = "block";
        document.getElementById("estrellaE2"+id).style.display = "block";
        document.getElementById("estrellaE3"+id).style.display = "block";
        document.getElementById("estrellaE4"+id).style.display = "block";
        document.getElementById("estrellaE5"+id).style.display = "block";

        document.getElementById("estrellaA1"+id).style.display = "none";
        document.getElementById("estrellaA2"+id).style.display = "none";
        document.getElementById("estrellaA3"+id).style.display = "none";
        document.getElementById("estrellaA4"+id).style.display = "none";
        document.getElementById("estrellaA5"+id).style.display = "none";

    }


    document.getElementById("estrellaE1"+id).onclick = function () {
        enviarCalificacion(0,id)
    };
    document.getElementById("estrellaE2"+id).onclick = function () {
        enviarCalificacion(25,id)
    };
    document.getElementById("estrellaE3"+id).onclick = function () {
        enviarCalificacion(50,id)
    };
    document.getElementById("estrellaE4"+id).onclick = function () {
        enviarCalificacion(75,id)
    };
    document.getElementById("estrellaE5"+id).onclick = function () {
        enviarCalificacion(100,id)
    };

    function enviarCalificacion(calificacion,id) {
        document.getElementById("calificacion").value = calificacion;
        document.getElementById("enviarCalificacion"+id).click();
    }
}

function setearEstrellas(id) {
    document.getElementById("estrellaE1"+id).style.display = "none";
    document.getElementById("estrellaE2"+id).style.display = "none";
    document.getElementById("estrellaE3"+id).style.display = "none";
    document.getElementById("estrellaE4"+id).style.display = "none";
    document.getElementById("estrellaE5"+id).style.display = "none";

    document.getElementById("estrellaA1"+id).style.display = "block";
    document.getElementById("estrellaA2"+id).style.display = "block";
    document.getElementById("estrellaA3"+id).style.display = "block";
    document.getElementById("estrellaA4"+id).style.display = "block";
    document.getElementById("estrellaA5"+id).style.display = "block";
}
