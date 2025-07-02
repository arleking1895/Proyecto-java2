<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Usuario" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

    if (usuario == null) {
        response.sendRedirect("iniciosesion.jsp");
        return;
    }

    String rol = usuario.getRol().getTipo_rol().trim(); // importante usar trim
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Página Principal</title>

        <!-- Bootstrap CDN -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- FontAwesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

        <style>
            body {
                margin: 0;
                font-family: Georgia, serif;
                background-image: url('vistas/fondoAuto.png');
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
                background-attachment: fixed;
                color: white;
                min-height: 100vh;
                position: relative;
            }

            body::before {
                content: "";
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0, 0, 0, 0.6);
                z-index: -1;
            }

            .navbar {
                display: flex;
                justify-content: space-between;
                align-items: center;
                background-color: #607E8B;
                padding: 10px 20px;
            }

            .navbar-left, .navbar-right {
                display: flex;
                align-items: center;
                gap: 20px;
            }

            .logo img {
                height: 70px;
                border-radius: 50%;
            }

            .dropdown {
                position: relative;
            }

            .dropbtn {
                background: transparent;
                border: none;
                color: white;
                font-size: 16px;
                cursor: pointer;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f1f1f1;
                min-width: 160px;
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 10px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .logout-button {
                padding: 10px 15px;
                background-color: white;
                color: black;
                border: none;
                border-radius: 20px;
                cursor: pointer;
            }

            .logout-button:hover {
                background-color: #7A6BAF;
                color: white;
            }

            .dashboard-grid {
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                gap: 40px;
                margin: 60px auto;
                padding: 20px;
                max-width: 1200px;
            }

            .card {
                width: 280px;
                height: 160px;
                background-color: white;
                color: #333;
                border-radius: 20px;
                padding: 25px;
                box-shadow: 0 6px 14px rgba(0, 0, 0, 0.2);
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                gap: 10px;
            }

            .card i {
                font-size: 36px;
                color: #5d7c8b;
            }

            .card h4, .card p {
                margin: 0;
                text-align: center;
            }

            h3 {
                margin: 30px;
                color: white;
                text-align: center;
            }
        </style>
    </head>
    <body>

        <div class="navbar">
            <div class="navbar-left">
                <div class="logo">
                    <img src="vistas/logo.jpg" alt="Logo">
                </div>

                <div class="dropdown">
                    <button class="dropbtn">Inicio</button>
                    <div class="dropdown-content">
                        <a href="paginaPrincipal.jsp">Inicio</a>
                    </div>
                </div>

                <% if ("Administrador".equals(rol)) { %>
                <div class="dropdown">
                    <button class="dropbtn">Usuarios</button>
                    <div class="dropdown-content">
                        <a href="<%= request.getContextPath() %>/EmpleadoController?accion=listar">Registrar Usuarios</a>
                    </div>
                </div>

                <div class="dropdown">
                    <button class="dropbtn">Venta</button>
                    <div class="dropdown-content">
                        <a href="registrarVentaAdmin.html">Registrar Venta</a>
                    </div>
                </div>

                <div class="dropdown">
                    <button class="dropbtn">Repuestos</button>
                    <div class="dropdown-content">
                        <a href="CompraControlador?accion=listar">Registrar Compra Repuestos</a>
                        <a href="RepuestoControlador?accion=listar">Repuestos</a>
                    </div>
                </div>
                <% } else if ("Vendedor".equals(rol)) { %>
                <div class="dropdown">
                    <button class="dropbtn">Venta</button>
                    <div class="dropdown-content">
                        <a href="registrarVentaAdmin.html">Registrar Venta</a>
                    </div>
                </div>
                <% } else if ("Auxiliar".equals(rol)) { %>
                <div class="dropdown">
                    <button class="dropbtn">Repuestos</button>
                    <div class="dropdown-content">
                        <a href="CompraControlador?accion=listar">Registrar Compra Repuestos</a>
                        <a href="RepuestoControlador?accion=listar">Repuestos</a>
                    </div>
                </div>
                <% } %>
            </div>

            <div class="navbar-right">
                <div>
                    <i class="fas fa-user"></i>
                    <%= rol %> <%= usuario.getNombres_usu() %>
                </div>
                <form action="<%= request.getContextPath() %>/UsuarioController?accion=cerrar" method="POST" style="margin: 0;">
                    <button type="submit" class="logout-button">Cerrar Sesión</button>
                </form>
            </div>
        </div>

        <div class="dashboard-grid">
            <div class="card">
                <i class="fas fa-users"></i>
                <h4>Clientes Registrados</h4>
                <p>120</p>
            </div>
            <div class="card">
                <i class="fas fa-hand-holding-usd"></i>
                <h4>Repuestos en Inventario</h4>
                <p>2000</p>
            </div>
            <div class="card">
                <i class="fas fa-cash-register"></i>
                <h4>Ventas Hoy</h4>
                <p>$4,200,000</p>
            </div>
            <div class="card">
                <i class="fas fa-user-cog"></i>
                <h4>Usuarios del Sistema</h4>
                <p>3</p>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/sweetalert/dist/sweetalert.min.js"></script>
        <% if (request.getAttribute("bienvenida") != null) { %>
        <script>
        swal("¡Éxito!", "<%= request.getAttribute("bienvenida") %>", "success");
        </script>
        <% } %>


    </body>
</html>
