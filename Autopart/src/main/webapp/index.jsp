<%-- 
    Document   : index
    Created on : 13/06/2025, 8:04:36 p. m.
    Author     : danna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Informacion Página</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
        }

        .contenedor {
            padding: 60px 0;
            width: 90%;
            max-width: 1000px;
            margin: auto;
            overflow: hidden;
        }

        .titulo {
            color: #414141;
            font-size: 30px;
            text-align: center;
            margin-bottom: 60px;
        }

        header {
            width: 100%;
            height: 600px;
            background: #74ebd5;
            background: -webkit-linear-gradient(to right, hsla(169, 75%, 69%, 0.664), hsla(229, 52%, 79%, 0.664)), url(hombre.webp);
            background: linear-gradient(to right, hsla(169, 75%, 69%, 0.664), hsla(229, 52%, 79%, 0.664)), url(hombre.webp);
            background-size: cover;
            background-attachment: fixed;
            position: relative;
        }

        nav {
            text-align: right;
            padding: 30px 50px 0 0;
        }

        nav>a {
            color: #fff;
            font-weight: 300;
            text-decoration: none;
            margin-right: 10px;
        }

        nav>a:hover {
            text-decoration: underline;
        }

        header .textos-header {
            display: flex;
            height: 430px;
            width: 100%;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            text-align: center;
        }

        .textos-header h1 {
            font-size: 50px;
            color: #fff;
        }

        .textos-header h2 {
            font-size: 30px;
            font-weight: 300;
            color: #fff;
        }

        .wave {
            position: absolute;
            bottom: 0;
            width: 100%;
        }

        main .sobre-nosotros {
            padding: 30px 0 60px 0;
        }

        .contenedor-sobre-nosotros {
            display: flex;
            justify-content: space-evenly;
        }

        .img-about-us {
            width: 48%;
        }

        .contenido-textos h3 span {
            background: #4d0686;
            color: #fff;
            border-radius: 50%;
            display: inline-block;
            text-align: center;
            width: 30px;
            height: 30px;
            padding: 2px;
            box-shadow: 0 0 6px rgba(0, 0, 0, .5);
            margin-right: 5px;
        }

        .contenido-textos p {
            padding: 0px 0px 30px 15px;
            font-weight: 300;
            text-align: start;
        }

        .cards {
            display: flex;
            justify-content: space-evenly;
        }

        .card {
            background: #4d0686;
            display: flex;
            width: 100%;
            height: 200px;
            align-items: center;
            justify-content: space-evenly;
            border-radius: 5px;
            box-shadow: 0 0 6px rgba(0, 0, 0, 0.6);
        }

        .card img {
            width: 100px;
            height: 100px;
            object-fit: cover;
            border: 9px solid #fff;
            border-radius: 50%;
            display: block;
        }

        .card>.contenido-texto-card {
            width: 60%;
            color: #fff;
        }

        .card>.contenido-texto-card p {
            font-weight: 1000;
            font-size: 15px;
            padding-top: 5px;
        }

        footer {
            background: #41414141;
            padding: 60px 0 30px 0;
            margin-top: 80px;
            overflow: hidden;
        }

        .contenedor-footer {
            display: flex;
            width: 90%;
            justify-content: space-evenly;
            margin: auto;
            padding-bottom: 50px;
            border-bottom: 1px solid grey;
        }

        .content-foo {
            text-align: center;
        }

        .content-foo h4 {
            color: black;
            border-bottom: 3px solid #4d0686;
            margin-bottom: 10px;
        }
    </style>
</head>

<body>
    <header>
        <nav>
            <a href="Ingreso.html">Inicio</a>
          <a href="vistas/iniciosesion.jsp">Inicio de Sesión</a>

            <a href="error404.html">Contáctanos</a>
        </nav>

        <section class="textos-header">
            <h1>¡Bienvenido a AutoPart Manager!</h1>
        </section>

        <div class="wave" style="height: 150px; overflow: hidden;">
            <svg viewBox="0 0 500 150" preserveAspectRatio="none" style="height: 100%; width: 100%;">
                <path d="M0.00,49.98 C149.99,150.00 349.20,-49.98 500.00,49.98 L500.00,150.00 L0.00,150.00 Z"
                      style="stroke: none; fill: #fff;"></path>
            </svg>
        </div>
    </header>
</body>

</html>
