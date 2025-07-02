<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Registro Proveedor</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Bootstrap Icons -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

        <!-- Tu archivo de estilos -->
        <link rel="stylesheet" href="vistas/registrarProveedor.css">
    </head>

    <body>

        <!-- Encabezado -->

        <div class="navbar-center">

            <h4 class="m-0" style="color: white; font-weight: bold;">Registrar Proveedor</h4>

        </div>

        <!-- Sección de tabla -->
        <section class="p-3">
            <div class="row">
                <div class="col-12">
                    <div class="tabla-contenedor">

                        <!-- Mensajes de alerta -->
                        <c:if test="${not empty sessionScope.success}">
                            <div class="alert alert-success alert-dismissible fade show m-3" role="alert">
                                ${sessionScope.success}
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
                            </div>
                            <c:remove var="success" scope="session" />
                        </c:if>

                        <c:if test="${not empty sessionScope.error}">
                            <div class="alert alert-danger alert-dismissible fade show m-3" role="alert">
                                ${sessionScope.error}
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
                            </div>
                            <c:remove var="error" scope="session" />
                        </c:if>

                        <table class="table table-striped table-hover mt-3 text-center table-border">
                            <a href="ProveedorControlador?accion=nuevo&abrirModal=proveedor"
                               class="btn btn-success me-1" title="Agregar">
                                Registrar un nuevo proveedor
                            </a>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre Proveedor</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${proveedores}" var="r">
                                    <tr>
                                        <td>${r.id_proveedor}</td>
                                        <td>${r.nombre_proveedor}</td>

                                        <td>
                                            <a href="ProveedorControlador?accion=editar&id_proveedor=${r.id_proveedor}"
                                               class="btn btn-sm btn-warning me-1" title="Editar">
                                                <i class="bi bi-pencil-square"></i>
                                            </a>
                                            <a href="ProveedorControlador?accion=eliminar&id_proveedor=${r.id_proveedor}"
                                               class="btn btn-sm btn-danger" title="Eliminar"
                                               onclick="return confirm('¿Estás seguro de eliminar el registro?')">
                                                <i class="bi bi-trash-fill"></i>
                                            </a>
                                        </td>
                                    </tr>

                                </c:forEach>
                            </tbody>
                        </table>

                        <!-- Botón Regresar -->
                        <div class="text-center mt-4">
                            <a href="CompraControlador?accion=listar" class="btn btn-secondary">
                                <i class="bi bi-arrow-left-circle"></i> Regresar
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Modal -->
        <div class="modal fade" id="proveedorModal" tabindex="-1">
            <div class="modal-dialog modal-dialog-centered modal-custom">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">${proveedor.id_proveedor == 0 ? "Nuevo": "Editar"} Proveedor</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                    <div class="modal-body">
                        <form id="modeloForm" action="ProveedorControlador" method="post">

                            <div class="mb-3">
                                <label for="idproveedor" class="form-label">Nit proveedor</label>
                                <input value="${proveedor.id_proveedor}" type="number" class="form-control" id="idproveedor" name="id_proveedor" required>
                            </div>

                            <div class="mb-3">
                                <label for="proveedor" class="form-label">Proveedor</label>
                                <input value="${proveedor.nombre_proveedor}" type="text" class="form-control" id="proveedor" name="nom_proveedor" required>
                            </div>

                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary" style="background-color: #6c5ce7;" name="accion" value="guardar">
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
            <% if ("proveedor".equals(abrirModal)) { %>
                                                       var myModal = new bootstrap.Modal(document.getElementById('proveedorModal'));
                                                       myModal.show();
            <% }%>
                                                   };
        </script>

    </body>

</html>