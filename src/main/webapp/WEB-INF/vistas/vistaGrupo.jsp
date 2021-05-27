<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet" />
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet" />

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/main.css" type="text/CSS">
    <link rel="stylesheet" href="../css/estilos.css" type="text/CSS">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
    <title>Umatch</title>
</head>

<body>
	<header class="container-fluid bg-dark text-warning">
		<div class="row">
			<div class="col col-sm-12 col-xl-9 p-2 text-left ">
				<a href="../"><img src="../img/umatch.png" width="150px"></a>
			</div>
			<div
				class="col  col-xl-3 d-flex flex-wrap  justify-content-center align-items-center ">
				<div class=" btn-group-lg ">
					<a href="ir-a-login"><button
							class="btn btn-outline-warning m-1">Iniciar Sesion</button></a> <a
						href="ir-a-registro">
						<button class="btn btn-outline-warning m-1">Registrar</button>
					</a>
				</div>
			</div>
		</div>
	</header>
	<main class="container-fluid">
		<div id="main" class="row text-white">
			<div class="info col-12 col-sm-3 bg-dark p-0">
				<div class="mt-3 mr-3 text-white text-right">
					<button class="btn btn-outline-secondary rounded ">></button>
				</div>

				<div class="perfil mx-auto mb-2 ">
					<img class="w-100 h-100 p-1" src="../img/profile.jpg">
				</div>

				<div class="text-center text-white mb-5">
					<h3 class="mb-3">${grupo.getNombre()}</h3>
					<div class="row justify-content-center align-items-center">
						<div class="col-12 col-md-6 text-md-end p-0">
							<strong class="px-3 py-1 rounded bg-white text-dark">
								${grupo.getPrivado() ? "Privado" : "Publico"} </strong>
						</div>
						<div class="col-12 col-md-4 mb-1 text-md-start">1 / ${grupo.getCtdMaxima()}</div>
					</div>
				</div>

				<ul class="opciones">
					<li><a class="text-white" href="#">Información General</a></li>
					<li><a class="text-white" href="#">Miembros del grupo</a></li>
					<li><a class="text-white" href="#">Archivos</a></li>
					<li><a class="text-white" href="#">Calendario</a></li>
					<li><a class="text-white" href="#">Foro</a></li>
				</ul>
			</div>
			<!--   Agregar Import  -->
			<c:if test="${not empty formulario }">
				<jsp:include page="../../templates/perfilMod.jsp"></jsp:include>
			</c:if>
			<c:if test="${empty formulario }">
				<jsp:include page="../../templates/perfilInfo.jsp"></jsp:include>
			</c:if>
		</div>
	</main>
	<footer class="footer bg-dark ">
    <div class="container bottom_border ">
        <div class="row ">
            <div class=" col-sm-4 col-md col-sm-4 col-12 col ">
                <h5 class="headin5_amrc col_white_amrc pt2 ">Acerca de</h5>

                <p class="mb10 ">Proyecto desarrollado en Java bajo el framework Spring, para la materia Taller Web I de la Universidad Nacional de La Matanza</p>
                <p><i class="fa fa-location-arrow "></i>Trabajo Remoto</p>
                <p><i class="fa fa-phone "></i> +91-9999878398 </p>
                <p><i class="fa fa fa-envelope "></i>Umatch@outlook.com</p>


            </div>


            <div class=" col-sm-4 col-md col-6 col ">
                <h5 class="headin5_amrc col_white_amrc pt2 ">Integrantes</h5>

                <ul class="footer_ul_amrc ">
                    <li>
                        <p>Gonzalo Elias Fernandez</p>
                    </li>
                    <li>
                        <p>Marcelo Andres Zelaya</p>
                    </li>
                    <li>
                        <p>Angelo</p>
                    </li>
                    <li>
                        <p>Sebastian Trillo Da Silva</p>
                    </li>
                </ul>

            </div>


            <div class=" col-sm-4 col-md col-6 col ">
                <h5 class="headin5_amrc col_white_amrc pt2 ">Docentes</h5>

                <ul class="footer_ul_amrc ">
                    <li>
                        <p>Sebastian</p>
                    </li>
                    <li>
                        <p>Ruben</p>
                    </li>
                    <li>
                        <p></p>
                    </li>

                </ul>

            </div>


            <div class=" col-sm-4 col-md col-12 col ">
                <h5 class="headin5_amrc col_white_amrc pt2 ">Repositorios</h5>


                <ul class="footer_ul2_amrc ">
                    <li><i class="github fab fa-github fa-5x fleft padding-right "></i>
                        <a href="# ">https://www.lipsum.com/</a>
                    </li>

                </ul>

            </div>
        </div>
    </div>


    <div class="container ">
        <ul class="foote_bottom_ul_amrc ">
            <li><a href="http://webenlance.com ">Home</a></li>
            <li><a href="http://webenlance.com ">About</a></li>
            <li><a href="http://webenlance.com ">Services</a></li>
            <li><a href="http://webenlance.com ">Pricing</a></li>
            <li><a href="http://webenlance.com ">Blog</a></li>
            <li><a href="http://webenlance.com ">Contact</a></li>
        </ul>

        <p class="text-center ">Copyright @2021 | Designed With by <a href="# ">Umatch</a></p>

        <ul class="social_footer_ul ">
            <li><a href="http://webenlance.com "><i class="fab fa-facebook-f "></i></a></li>
            <li><a href="http://webenlance.com "><i class="fab fa-twitter "></i></a></li>
            <li><a href="http://webenlance.com "><i class="fab fa-linkedin "></i></a></li>
            <li><a href="http://webenlance.com "><i class="fab fa-instagram "></i></a></li>
        </ul>

    </div>

</footer>

</body>

</html>