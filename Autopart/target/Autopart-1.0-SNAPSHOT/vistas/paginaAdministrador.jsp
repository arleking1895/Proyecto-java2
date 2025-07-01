<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
if(session.getAttribute("Administrador") != null){
%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Pagina Administrador</title>

        <!-- Bootstrap CDN -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap Icons -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

        <!-- Estilos embebidos -->
        <style>
            * {
                font-size: medium;
                font-family: Georgia, 'Times New Roman', Times, serif;
            }

            body {
                margin: 0;
                background-image: url('vistas/fondoAuto.png');
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
                background-attachment: fixed;
                color: white;
                position: relative;
                min-height: 100vh;
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

            .navbar-left {
                display: flex;
                align-items: center;
                gap: 20px;
            }

            .logo img {
                height: 80px;
                border-radius: 40%;
            }

            .dropdown {
                position: relative;
            }

            .dropbtn {
                background-color: transparent;
                color: white;
                border: none;
                font-size: 16px;
                cursor: pointer;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f1f1f1;
                min-width: 140px;
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

            .navbar-right {
                display: flex;
                align-items: center;
                gap: 15px;
            }

            .logout-button {
                padding: 15px 15px;
                border: none;
                background-color: white;
                color: black;
                border-radius: 20px;
                cursor: pointer;
                font-size: 16px;
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
                max-width: 1200px;
                margin: 60px auto;
                padding: 20px;
            }

            .card {
                width: 300px;
                height: 180px;
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
                transition: transform 0.3s ease, box-shadow 0.3s ease;
            }

            .card i {
                font-size: 40px;
                color: #5d7c8b;
            }

            .card h4 {
                margin: 0;
                font-size: 18px;
                font-weight: bold;
                color: #444;
                text-align: center;
            }

            .card p {
                margin: 5px 0 0;
                font-size: 22px;
                font-weight: bold;
                text-align: center;
            }

            h3 {
                margin: 30px;
                color: white;
            }

            .admin {
                color: white;
            }

            .admin-only {
                display: none;
            }

            body.rol-administrador .admin-only {
                display: inline-block;
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
                        <a href="vistas/paginaAdministrador.jsp">Inicio</a>
                    </div>
                </div>

                <div class="dropdown">
                    <button class="dropbtn">Usuarios</button>
                    <div class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/EmpleadoController?accion=listar">Registrar Usuarios</a>

                    </div>
                </div>

                <div class="dropdown">
                    <button class="dropbtn">Venta</button>
                    <div class="dropdown-content">
                        <a href="registrarVentaAdmin.html">Registrar Venta</a>
                    </div>
                </div>

                <div class="dropdown">
                    <button class="dropbtn">Compra Repuestos</button>
                    <div class="dropdown-content">
                        <a href="registrarCompraAdmin.html">Registrar Compra Repuestos</a>
                    </div>
                </div>
            </div>

            <div class="navbar-right">
                <%
                 Modelo.Usuario administrador = (Modelo.Usuario) session.getAttribute("Administrador");
                %>
                <div class="admin">
                    <i class="fas fa-user"></i> 
                    Administrador <%= administrador.getNombres_usu() %>
                </div>


                <form action="${pageContext.request.contextPath}/UsuarioController?accion=cerrar" method="POST" style="margin: 0;">
                    <button type="submit" class="logout-button">Cerrar Sesión</button>
                </form>
            </div>
        </div>

        <div class="dashboard-grid">
            <div class="card">
                <i class="fas fa-users"></i>
                <div>
                    <h4>Clientes Registrados</h4>
                    <p>120</p>
                </div>
            </div>
            <div class="card">
                <i class="fas fa-hand-holding-usd"></i>
                <div>
                    <h4>Repuestos en Inventario</h4>
                    <p>2000</p>
                </div>
            </div>
            <div class="card">
                <i class="fas fa-cash-register"></i>
                <div>
                    <h4>Ventas Hoy</h4>
                    <p>$4,200,000</p>
                </div>
            </div>
            <div class="card">
                <i class="fas fa-user-cog"></i>
                <div>
                    <h4>Usuarios del Sistema</h4>
                    <p>3</p>
                </div>
            </div>
        </div>

        <p>
            Bienvenido - ${usuario.rol.tipo_rol}
            <small>Usted es, ${usuario.nombres_usu}</small>
        </p>

    </body>
</html>

<%
} else {
    response.sendRedirect("iniciosesion.jsp");
}
%>
