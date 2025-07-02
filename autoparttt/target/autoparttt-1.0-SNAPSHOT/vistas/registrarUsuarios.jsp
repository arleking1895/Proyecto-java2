<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="modelo.Usuario" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    if (usuario == null) {
        response.sendRedirect("iniciosesion.jsp");
        return;
    }
%>


<!doctype html>
<html lang="es">


    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Registro Usuarios</title>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>




        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Bootstrap Icons -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

        <!-- CSS embebido -->
        <style>
            * {
                font-size: medium;
                font-family: Georgia, 'Times New Roman', Times, serif;
            }

            body {
                margin: 0;
                padding: 0;
            }

            .tabla-contenedor {
                background-color: rgba(185, 195, 206, 0.85);
                padding: 20px;
                margin: 20px;
                border-radius: 12px;
                max-width: 98%;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            }

            table {
                width: 100%;
                border-collapse: collapse;
                background-color: transparent;
            }

            th,
            td {
                border: 1px solid #ccc;
                padding: 10px;
                text-align: center;
                color: #fff;
            }

            thead {
                background-color: #6c5ce7;
                color: white;
            }

            table tr td {
                vertical-align: middle;
                color: #000;
            }

            td button {
                margin: 5px;
            }

            td button i {
                font-size: 20px;
            }

            .modal-header {
                background: #6c5ce7;
                color: #fff;
            }

            .modal-custom {
                max-width: 500px;
            }

            #readData form .inputField>div input {
                color: #000;
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
                font-size: 15px;
            }

            .logout-button:hover {
                background-color: #7A6BAF;
                color: white;
            }

            h3 {
                margin: 30px;
                color: white;
            }

            .admin {
                color: white;
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
                        <a href="/vistas/paginaAdministrador.jsp">Inicio</a>
                    </div>
                </div>

                <div class="dropdown">
                    <button class="dropbtn">Usuarios</button>
                    <div class="dropdown-content">
                        <a href="EmpleadoController?accion=listar">Registrar Usuarios</a>
                    </div>
                </div>

                <div class="dropdown">
                    <button class="dropbtn">Venta</button>
                    <div class="dropdown-content">
                        <a href="/vistas/registrarVentaAdmin.html">Registrar Venta</a>
                    </div>
                </div>

                <div class="dropdown">
                    <button class="dropbtn">Repuestos</button>
                    <div class="dropdown-content">
                        <a href="CompraControlador?accion=listar">Registrar Compra Repuestos</a>
                        <a href="RepuestoControlador?accion=listar">Repuestos</a>
                    </div>
                </div>
            </div>

            <div class="navbar-right">
                <div class="admin">
                    <i class="fas fa-user"></i>
                    <%= usuario.getRol().getTipo_rol() %> <%= usuario.getNombres_usu() %>
                </div>

                <form action="<%= request.getContextPath() %>/UsuarioController?accion=cerrar" method="POST" style="margin: 0;">
                    <button type="submit" class="logout-button">Cerrar Sesión</button>
                </form>
            </div>

        </div>

        <!-- Seccion principal -->
        <section class="p-3">
            <div class="row">
                <div class="col-12">
                    <div class="tabla-contenedor">




                        <table class="table table-striped table-hover mt-3 text-center table-border">
                            <a href="EmpleadoController?accion=nuevo&abrirModal=usuario"
                               class="btn btn-success me-1" title="Agregar">
                                Registrar un Nuevo Usuario
                            </a>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nombres</th>
                                    <th>Apellidos</th>
                                    <th>Telefono</th>
                                    <th>Email</th>

                                    <th>Tipo Rol</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach items="${empleados}" var="usu">
                                    <tr>
                                        <td>${usu.id_usuario}</td>
                                        <td>${usu.nombres_usu}</td>
                                        <td>${usu.apellidos_usu}</td>
                                        <td>${usu.telefono_usu}</td>
                                        <td>${usu.email_usu}</td>

                                        <td>${usu.rol.tipo_rol}</td>
                                        <td>

                                            <a href="EmpleadoController?accion=editar&id_usuario=${usu.id_usuario}"
                                               class="btn btn-sm btn-warning me-1" title="Editar">
                                                <i class="bi bi-pencil-square"></i>
                                            </a>
                                            <a href="#" class="btn btn-sm btn-danger" title="Eliminar"
                                               onclick="confirmarEliminacion(${usu.id_usuario});">
                                                <i class="bi bi-trash-fill"></i>
                                            </a>


                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>

                        </table>
                    </div>
                </div>
            </div>
        </section>
        <c:if test="${not empty msje}">
            <div class="alert alert-danger">${msje}</div>
        </c:if>


        <!-- Modal Único para agregar/editar -->
        <div class="modal fade" id="usuarioModal" tabindex="-1">
            <div class="modal-dialog modal-dialog-centered modal-custom">
                <div class="modal-content">

                    <div class="modal-header">
                        <h5 class="modal-title">${usuario.id_usuario == 0 ? "Nueva": "Editar"} Usuario</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                    <div class="modal-body">
                        <form id="usuarioForm" action="EmpleadoController?accion=guardar" method="post">




                            <div class="mb-3">
                                <label for="cedula" class="form-label">Cédula</label>
                                <input  value="${usuario.id_usuario}"  type="text" class="form-control" id="id_usuario" name="id_usuario" placeholder="Ingrese su cédula"  required pattern="^[0-9]{6,10}$" minlength="10" maxlength="10"
                                        title="Ingrese una cédula válida de solo 10 dígitos">
                            </div>

                            <div class="mb-3">
                                <label for="nombres" class="form-label">Nombres</label>
                                <input value="${usuario.nombres_usu}" type="text" class="form-control" id="nombres" name="nombres" placeholder="Nombres del cliente" required pattern="^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+$"
                                       title="Solo se permiten letras y espacios">
                            </div>       



                            <div class="mb-3">
                                <label for="apellidos" class="form-label">Apellidos</label>
                                <input  value="${usuario.apellidos_usu}" type="text" class="form-control" id="apellidos" name="apellidos" placeholder="Apellidos del cliente" equired pattern="^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+$">
                            </div>        



                            <div class="mb-3">
                                <label for="telefono" class="form-label">Número de Teléfono</label>
                                <input value="${usuario.telefono_usu}" type="tel" class="form-control" id="telefono" name="telefono"  placeholder="Ingrese su número de teléfono" required pattern="^\d{10}$"
                                       maxlength="10" minlength="10"
                                       title="Debe ingresar exactamente 10 números">
                            </div>         



                            <div class="mb-3">
                                <label for="email" class="form-label">Correo Electrónico</label>
                                <input value="${usuario.email_usu}" type="email" class="form-control" id="email" name="email"   placeholder="Ingrese su correo electrónico"  required maxlength="100"
                                       title="Ingrese un correo válido">
                            </div>        



                            <div class="mb-3">
                                <label for="rol" class="form-label">Tipo de Rol</label>
                                <select class="form-select" id="rol" name="rol" required>
                                    <option ${usuario == null ? "selected" : ""}>Seleccione un rol</option>

                                    <c:forEach var="r" items="${roles}">
                                        <option value="${r.tipo_rol}" ${usuario != null && usuario.rol.tipo_rol == r.tipo_rol ? "selected" : ""}>
                                            ${r.tipo_rol}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label for="password" class="form-label">Contraseña</label>
                                <input value="${usuario.pass_usu}" type="password" class="form-control"
                                       id="password" name="password" placeholder="Ingrese su contraseña" required minlength="8"
                                       title="La contraseña debe tener al menos 8 caracteres">


                            </div>

                            <div class="modal-footer">
                                <input type="hidden" name="modo" value="${usuario.id_usuario != 0 ? 'editar' : 'nuevo'}">


                                <button type="submit" class="btn btn-primary" style="background-color: #6c5ce7;" >
                                    Guardar</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
        <%
        String abrirModal = (String) request.getAttribute("abrirModal");
        %>


        <script>
                                                   window.onload = function () {
            <% if ("usuario".equals(abrirModal)) { %>
                                                       var myModal = new bootstrap.Modal(document.getElementById('usuarioModal'));
                                                       myModal.show();
            <% }%>
                                                   };
        </script> 


        <c:if test="${not empty sessionScope.success}">
            <script>
                swal("¡Éxito!", "${sessionScope.success}", "success");
            </script>
            <% session.removeAttribute("success"); %>
        </c:if>

        <c:if test="${not empty sessionScope.error}">
            <script>
                swal("Error", "${sessionScope.error}", "error");
            </script>
            <% session.removeAttribute("error"); %>
        </c:if>


        <script>
            function confirmarEliminacion(id) {
                swal({
                    title: "¿Estás segura?",
                    text: "¡No podrás recuperar este usuario!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Sí, eliminar",
                    cancelButtonText: "No, cancelar",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {
                        window.location.href = 'EmpleadoController?accion=eliminar&id_usuario=' + id;
                    } else {
                        swal("Cancelado", "El usuario está a salvo :)", "error");
                    }
                });
            }
        </script>





    </body>

</html>
