<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="modelo.Usuario" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

    if (usuario == null) {
        response.sendRedirect("iniciosesion.jsp");
        return;
    }

    String rol = usuario.getRol().getTipo_rol().trim(); // importante usar trim
%>
<!doctype html>
<html lang="es">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Registro de Compras</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Bootstrap Icons -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

        <!-- Hoja de estilos -->
        <link rel="stylesheet" href="vistas/registrarCompraAdmin.css">
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

                <% if ("Administrador".equals(rol)) {%>
                <div class="dropdown">
                    <button class="dropbtn">Usuarios</button>
                    <div class="dropdown-content">
                        <a href="<%= request.getContextPath()%>/EmpleadoController?accion=listar">Registrar Usuarios</a>
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
                <% }%>
            </div>

            <div class="navbar-right">
                <div style="color: white">
                    <i class="fas fa-user" ></i>
                    <%= rol%> <%= usuario.getNombres_usu()%>
                </div>
                <form action="<%= request.getContextPath()%>/UsuarioController?accion=cerrar" method="POST" style="margin: 0;">
                    <button type="submit" class="logout-button">Cerrar Sesión</button>
                </form>
            </div>
        </div>        



        <!-- TABLA -->
        <section class="p-3">
            <div class="row">
                <div class="col-12">
                    <div class="tabla-contenedor">
                        <c:if test="${not empty sessionScope.success}">
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                ${sessionScope.success}
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>

                        <c:if test="${not empty sessionScope.error}">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                ${sessionScope.error}
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>

                        <table class="table table-striped table-hover mt-3 text-center table-bordered">
                            <a href="CompraControlador?accion=nuevo&abrirModal=compra"
                               class="btn btn-success me-1" title="Agregar">
                                Registrar una nueva compra
                            </a>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Fecha Compra</th>
                                    <th>Nombre Repuesto</th>
                                    <th>Cantidad Repuestos</th>
                                    <th>Precio</th>
                                    <th>Proveedor</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach items="${compras}" var="c">

                                    <tr>
                                        <td>${c.id_compra}</td>
                                        <td>${c.fecha_compra}</td>
                                        <td>${c.repuesto}</td>
                                        <td>${c.cantidad}</td>
                                        <td>${c.precio}</td>
                                        <td>${c.fk_proveedor.nombre_proveedor}</td>
                                        <td>

                                            <a href="CompraControlador?accion=editar&id_compra=${c.id_compra}"
                                               class="btn btn-sm btn-warning me-1" title="Editar">
                                                <i class="bi bi-pencil-square"></i>
                                            </a>
                                            <a href="CompraControlador?accion=eliminar&id_compra=${c.id_compra}"
                                               class="btn btn-sm btn-danger" title="Eliminar"
                                               onclick="return confirm('¿Estás seguro de eliminar el registro?')">
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

        <!-- MODAL -->
        <div class="modal fade" id="compraModal" tabindex="-1">
            <div class="modal-dialog modal-dialog-centered modal-custom">
                <div class="modal-content">

                    <div class="modal-header">
                        <h5 class="modal-title">${compra.id_compra == 0 ? "Nueva": "Editar"} Compra</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <form id="compraForm" action="CompraControlador" method="post">
                            <div class="mb-3">
                                <label for="fechaCompra" class="form-label">Fecha Compra</label>
                                <input value="${compra.fecha_compra}" type="datetime-local" class="form-control" id="fechaCompra" name="fechaCompra" required>
                            </div>

                            <div class="mb-3 d-flex align-items-end">
                                <div class="flex-grow-1 me-2">
                                    <label for="nombreRepuesto" class="form-label">Nombre Repuesto</label>
                                    <select class="form-select" id="nombreRepuesto" name="nombreRepuesto" required>
                                        <c:forEach items="${repuestos}" var="r">
                                            <option value="${r.id_repuesto}">
                                                ${r.nombre}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                            </div>

                            <div class="mb-3">
                                <label for="cantidad" class="form-label">Cantidad Repuestos</label>
                                <input value="${compra.cantidad}" type="number" class="form-control" id="cantidad" name="cantidad" min="1" required>
                            </div>

                            <div class="mb-3">
                                <label for="precio" class="form-label">Precio</label>
                                <input value="${compra.precio}" type="number" class="form-control" id="precio" name="precio" step="100" min="0"
                                       required>
                            </div>

                            <div class="mb-3 d-flex align-items-end">
                                <div class="flex-grow-1 me-2">
                                    <label for="proveedor" class="form-label">Proveedor</label>
                                    <select class="form-select" id="proveedor" name="proveedor" required>
                                        <c:forEach items="${proveedores}" var="p">
                                            <option value="${p.id_proveedor}" ${p.id_proveedor == compra.fk_proveedor.id_proveedor ? 'selected' : ''}>
                                                ${p.nombre_proveedor}
                                            </option>
                                        </c:forEach>

                                    </select>
                                </div>
                                <div>
                                    <label class="form-label invisible">Agregar</label>
                                    <a href="ProveedorControlador?accion=listar" class="btn btn-outline-primary">Agregar</a>
                                </div>
                            </div>

                    </div>
                    <div class="modal-footer">
                        <input type="hidden" name="id_compra" value="${compra.id_compra}"></input>
                        <button type="submit" class="btn btn-primary" style="background-color: #6c5ce7;" name="accion" value="guardar">
                            Guardar</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
    </body>

    <%
        String abrirModal = (String) request.getAttribute("abrirModal");
    %>


    <script>
                                                   window.onload = function () {
        <% if ("compra".equals(abrirModal)) { %>
                                                       var myModal = new bootstrap.Modal(document.getElementById('compraModal'));
                                                       myModal.show();
        <% }%>
                                                   };
    </script>

</html>

</body>

</html>