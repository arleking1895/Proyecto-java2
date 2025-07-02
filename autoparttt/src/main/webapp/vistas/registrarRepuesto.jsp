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
        <title>Registro Repuestos</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Bootstrap Icons -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

        <!-- Tu archivo de estilos -->
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
       

        <div class="navbar-center">

            <h4 class="m-0" style="color: white; font-weight: bold;">Respuestos</h4>

        </div>

        <!-- Sección de tabla -->
        <section class="p-3">
            <div class="row">
                <div class="col-12">
                    <div class="tabla-contenedor">
                        <a href="RepuestoControlador?accion=nuevo&abrirModal=repuesto"
                           class="btn btn-success me-1" title="Agregar">
                            Agregar un nuevo repuesto
                        </a>
                        <table class="table table-striped table-hover mt-3 text-center table-border">

                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre Respuesto</th>
                                    <th>Cantidad</th>
                                    <th>Precio</th>
                                    <th>Marca</th>
                                    <th>Modelo</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${repuestos}" var="r">
                                    <tr>
                                        <td>${r.id_repuesto}</td>
                                        <td>${r.nombre}</td>
                                        <td>${r.cantidad}</td>
                                        <td>${r.precio}</td>
                                        <td>${r.fk_marca.nombre_marca}</td>
                                        <td>${r.fk_modelo.anio}</td>
                                        <td>

                                            <a href="RepuestoControlador?accion=editar&id_repuesto=${r.id_repuesto}" 
                                               class="btn btn-sm btn-warning me-1" title="Editar">
                                                <i class="bi bi-pencil-square"></i>
                                            </a>
                                            <a href="RepuestoControlador?accion=eliminar&id_repuesto=${r.id_repuesto}" 
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

        <!-- Modal -->
        <div class="modal fade" id="repuestoModal" tabindex="-1">
            <div class="modal-dialog modal-dialog-centered modal-custom">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">${repuesto.id_repuesto == 0 ? "Nueva": "Editar"} Repuesto</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                    <div class="modal-body">
                        <form id="repuestoForm" action="RepuestoControlador" method="post">
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nombre Repuesto</label>
                                <input value="${repuesto.nombre}" type="text" class="form-control" id="nombre" name="nombre" required>
                            </div>

                            <input type="hidden" name="precio" value="0">
                            <input type="hidden" name="precio" value="0">

                            <!-- Marca con botón de agregar -->
                            <div class="mb-3 d-flex align-items-end">
                                <div class="flex-grow-1 me-2">
                                    <label for="marca" class="form-label">Marca</label>
                                    <select class="form-select" id="marca" name="marca" required>
                                        <c:forEach items="${marcas}" var="ma">
                                            <option value="${ma.id_marca}" ${ma.id_marca == repuesto.fk_marca.id_marca ? 'selected' : ''}>
                                                ${ma.nombre_marca}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <label class="form-label invisible">Agregar</label>
                                    <a href="MarcaControlador?accion=listar" class="btn btn-outline-primary">Agregar</a>
                                </div>
                            </div>

                            <!-- Modelo con botón de agregar -->
                            <div class="mb-3 d-flex align-items-end">
                                <div class="flex-grow-1 me-2">
                                    <label for="modelo" class="form-label">Modelo</label>
                                    <select class="form-select" id="modelo" name="modelo" required>
                                        <c:forEach items="${modelos}" var="mo">
                                            <option value="${mo.id_modelo}" ${mo.id_modelo == repuesto.fk_modelo.id_modelo ? 'selected' : ''}>
                                                ${mo.anio}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <label class="form-label invisible">Agregar</label>
                                    <a href="ModeloControlador?accion=listar" class="btn btn-outline-primary">Agregar</a>
                                </div>
                            </div>

                            <small class="text-muted">*La cantidad y el precio son actualizados automaticamente al registrar una compra.</small>
                            
                            <div class="modal-footer">
                                <input type="hidden" name="id_repuesto" value="${repuesto.id_repuesto}">
                                <button type="submit" class="btn btn-primary" style="background-color: #6c5ce7;" name="accion" value="guardar">
                                    Guardar</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                            </div>
                        </form>


                    </div>
                </div>
            </div>

            <!-- Scripts -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            crossorigin="anonymous"></script>

            <%
                String abrirModal = (String) request.getAttribute("abrirModal");
            %>
            <script>
                                                   window.onload = function () {
                <% if ("repuesto".equals(abrirModal)) { %>
                                                       var modal = new bootstrap.Modal(document.getElementById("repuestoModal"));
                                                       modal.show();
                <% }%>
                                                   };
            </script>


    </body>

</html>