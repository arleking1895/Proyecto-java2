<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Registro Marca</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Bootstrap Icons -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

        <!-- Tu archivo de estilos -->
        <link rel="stylesheet" href="vistas/registrarMarca.css">
    </head>

    <body>

        <!-- Encabezado -->

        <div class="navbar-center">

            <h4 class="m-0" style="color: white; font-weight: bold;">Registrar Marca</h4>

        </div>
        <!-- Sección de tabla -->
        <section class="p-3">
            <div class="row">
                <div class="col-12">
                    <div class="tabla-contenedor">

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
                            <a href="MarcaControlador?accion=nuevo&abrirModal=marca" 
                               class="btn btn-success me-1" 
                               title="Agregar">
                                Agregar una nueva marca
                            </a>

                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre Marca</th>
                                    <th>Versión</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach items="${marcas}" var="m">

                                    <tr>
                                        <td>${m.id_marca}</td>
                                        <td>${m.nombre_marca}</td>
                                        <td>${m.version}</td>

                                        <td>
                                            <a href="MarcaControlador?accion=editar&id_marca=${m.id_marca}" 
                                               class="btn btn-sm btn-warning me-1" title="Editar">
                                                <i class="bi bi-pencil-square"></i>
                                            </a>

                                            <a class="btn btn-sm btn-danger" 
                                               title="Eliminar" 
                                               href="MarcaControlador?accion=eliminar&id_marca=${m.id_marca}" 
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
                            <a href="RepuestoControlador?accion=listar" class="btn btn-secondary">
                                <i class="bi bi-arrow-left-circle"></i> Regresar
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Modal -->
        <div class="modal fade" id="marcaModal" tabindex="-1">
            <div class="modal-dialog modal-dialog-centered modal-custom">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">${marca.id_marca == 0 ? "Nueva": "Editar"} Marca</h5>

                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                    <div class="modal-body">
                        <form id="marcaForm" action="MarcaControlador" method="post">
                            <div class="mb-3">
                                <label for="nombreMarca" class="form-label">Nombre Marca</label>
                                <input value="${marca.nombre_marca}" type="text" class="form-control" id="nombreMarca" name="nombreMarca" required>
                            </div>

                            <div class="mb-3">
                                <label for="versionMarca" class="form-label">Versión</label>
                                <input value="${marca.version}" type="text" class="form-control" id="versionMarca" name="versionMarca" required>
                            </div>

                            <div class="modal-footer">
                                <input type="hidden" name="id_marca" value="${marca.id_marca}"></input>
                                <button type="submit" class="btn btn-primary" style="background-color: #6c5ce7;" name="accion" value="guardar">
                                    Guardar
                                </button>
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
            <% if ("marca".equals(abrirModal)) { %>
                                                       var myModal = new bootstrap.Modal(document.getElementById('marcaModal'));
                                                       myModal.show();
            <% }%>
                                                   };
        </script>

    </body>

</html>
