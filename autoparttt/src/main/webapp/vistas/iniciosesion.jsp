<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inicio de sesión</title>

        <!-- Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">




        <style>
            body {
                margin: 0;
                padding: 0;
                font-family: Georgia, 'Times New Roman', Times, serif;
                height: 100vh;
                background-attachment: fixed;
                display: flex;
                align-items: center;
                justify-content: center;
                background: linear-gradient(135deg, #f3f4f6, #d9e3f0);
            }

            .formulario {
                width: 400px;
                background: white;
                padding: 30px 40px;
                border: 3px solid black;
                color: black;
                box-shadow:
                    10px 10px 30px rgba(0, 0, 0, 0.6),
                    -10px -10px 30px rgba(255, 255, 255, 0.3);
                border-radius: 20px;
                text-align: center;
            }

            .formulario h2 {
                margin: 0 0 20px 0;
                font-size: 28px;
                color: #333;
            }

            label {
                font-size: 18px;
                margin-bottom: 5px;
                display: block;
                text-align: left;
                color: #555;
            }

            input {
                width: 90%;
                font-size: 16px;
                padding: 10px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            input:focus {
                outline: none;
                border-color: #007BFF;
                box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
            }

            input[type="submit"] {
                width: 100%;
                font-size: 18px;
                padding: 10px;
                background-color: aquamarine;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            input[type="submit"]:hover {
                background-color: #81e6d9;
            }

            a {
                color: #007BFF;
                text-decoration: none;
                font-size: 14px;
                display: block;
                margin-top: 10px;
            }

            a:hover {
                text-decoration: underline;
            }
        </style>
    </head>

    <body>

        <form class="formulario" action="${pageContext.request.contextPath}/UsuarioController?accion=verificar" method="POST">
            <h2>Inicio de sesión</h2>

            <label for="email">Usuario</label>
            <input type="email" id="email" name="email" required placeholder=" 👦 Ingresa tu correo electrónico">

            <label for="contraseña">Contraseña</label>
            <input type="password" id="contraseña" name="pass" required
                   placeholder=" 🔑 Ingresa tu contraseña" minlength="8" maxlength="15">

            <a href="cambioContraseña.html">¿Olvidaste tu contraseña?</a>

            <input type="submit" value="Verificar" name="accion">
        </form>

        <!-- SweetAlert -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert/dist/sweetalert.min.js"></script>
        <% if (request.getAttribute("msje") != null) { %>
        <script>
        swal("Error", "<%= request.getAttribute("msje") %>", "error");
        </script>
        <% } %>


    </body>
</html>
