
    document.getElementById("estrellaA1").onmouseover = function() {mouseOver1()};
    document.getElementById("estrellaE1").onmouseout = function() {mouseOut1()};
    document.getElementById("estrellaA2").onmouseover = function() {mouseOver2()};
    document.getElementById("estrellaE2").onmouseout = function() {mouseOut2()};
    document.getElementById("estrellaA3").onmouseover = function() {mouseOver3()};
    document.getElementById("estrellaE3").onmouseout = function() {mouseOut3()};
    document.getElementById("estrellaA4").onmouseover = function() {mouseOver4()};
    document.getElementById("estrellaE4").onmouseout = function() {mouseOut4()};
    document.getElementById("estrellaA5").onmouseover = function() {mouseOver5()};
    document.getElementById("estrellaE5").onmouseout = function() {mouseOut5()};

    function mouseOver1() {
        document.getElementById("estrellaE1").style.display="block";
        document.getElementById("estrellaA1").style.display = "none";
        document.getElementById("estrellaA2").style.display = "block";
        document.getElementById("estrellaA3").style.display = "block";
        document.getElementById("estrellaA4").style.display = "block";
        document.getElementById("estrellaA5").style.display = "block";

}
    function mouseOut1() {
        document.getElementById("estrellaA1").style.display = "block";
        document.getElementById("estrellaE1").style.display="none";
        document.getElementById("estrellaE2").style.display="none";
        document.getElementById("estrellaE3").style.display="none";
        document.getElementById("estrellaE4").style.display="none";
        document.getElementById("estrellaE5").style.display="none";
}
    function mouseOver2() {
        document.getElementById("estrellaE1").style.display="block";
        document.getElementById("estrellaA1").style.display = "none";
        document.getElementById("estrellaE2").style.display="block";
        document.getElementById("estrellaA2").style.display = "none";
        document.getElementById("estrellaA3").style.display = "block";
        document.getElementById("estrellaA4").style.display = "block";
        document.getElementById("estrellaA5").style.display = "block";
    }
    function mouseOut2() {
        document.getElementById("estrellaA1").style.display = "block";
        document.getElementById("estrellaE1").style.display="none";
        document.getElementById("estrellaA2").style.display = "block";
        document.getElementById("estrellaE2").style.display="none";
        document.getElementById("estrellaE3").style.display="none";
        document.getElementById("estrellaE4").style.display="none";
        document.getElementById("estrellaE5").style.display="none";
    }
    function mouseOver3() {
        document.getElementById("estrellaE1").style.display="block";
        document.getElementById("estrellaA1").style.display = "none";
        document.getElementById("estrellaE2").style.display="block";
        document.getElementById("estrellaA2").style.display = "none";
        document.getElementById("estrellaE3").style.display="block";
        document.getElementById("estrellaA3").style.display = "none";

    }
    function mouseOut3() {
        document.getElementById("estrellaA1").style.display = "block";
        document.getElementById("estrellaE1").style.display="none";
        document.getElementById("estrellaA2").style.display = "block";
        document.getElementById("estrellaE2").style.display="none";
        document.getElementById("estrellaA3").style.display = "block";
        document.getElementById("estrellaE3").style.display="none";

    }
    function mouseOver4() {
        document.getElementById("estrellaE1").style.display="block";
        document.getElementById("estrellaA1").style.display = "none";
        document.getElementById("estrellaE2").style.display="block";
        document.getElementById("estrellaA2").style.display = "none";
        document.getElementById("estrellaE3").style.display="block";
        document.getElementById("estrellaA3").style.display = "none";
        document.getElementById("estrellaE4").style.display="block";
        document.getElementById("estrellaA4").style.display = "none";

    }
    function mouseOut4() {
        document.getElementById("estrellaA1").style.display = "block";
        document.getElementById("estrellaE1").style.display="none";
        document.getElementById("estrellaA2").style.display = "block";
        document.getElementById("estrellaE2").style.display="none";
        document.getElementById("estrellaA3").style.display = "block";
        document.getElementById("estrellaE3").style.display="none";
        document.getElementById("estrellaA4").style.display = "block";
        document.getElementById("estrellaE4").style.display="none";
    }
    function mouseOver5() {
        document.getElementById("estrellaE1").style.display="block";
        document.getElementById("estrellaA1").style.display = "none";
        document.getElementById("estrellaE2").style.display="block";
        document.getElementById("estrellaA2").style.display = "none";
        document.getElementById("estrellaE3").style.display="block";
        document.getElementById("estrellaA3").style.display = "none";
        document.getElementById("estrellaE4").style.display="block";
        document.getElementById("estrellaA4").style.display = "none";
        document.getElementById("estrellaE5").style.display="block";
        document.getElementById("estrellaA5").style.display = "none";

    }
    function mouseOut5() {
        document.getElementById("estrellaA1").style.display = "block";
        document.getElementById("estrellaE1").style.display="none";
        document.getElementById("estrellaA2").style.display = "block";
        document.getElementById("estrellaE2").style.display="none";
        document.getElementById("estrellaA3").style.display = "block";
        document.getElementById("estrellaE3").style.display="none";
        document.getElementById("estrellaA4").style.display = "block";
        document.getElementById("estrellaE4").style.display="none";
        document.getElementById("estrellaA5").style.display = "block";
        document.getElementById("estrellaE5").style.display="none";
    }

    document.getElementById("estrellaE1").onclick = function() {enviarCalificacion(20)};
    document.getElementById("estrellaE2").onclick = function() {enviarCalificacion(40)};
    document.getElementById("estrellaE3").onclick = function() {enviarCalificacion(60)};
    document.getElementById("estrellaE4").onclick = function() {enviarCalificacion(80)};
    document.getElementById("estrellaE5").onclick = function() {enviarCalificacion(100)};

    function enviarCalificacion(calificacion) {
        document.getElementById("calificacion").value=calificacion;
        document.getElementById("enviarCalificacion").click();

    }