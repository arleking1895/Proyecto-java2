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
        <link rel="stylesheet" href="vistas/registrarModelo.css">
    </head>

    <body>

        <!-- Encabezado -->

        <div class="navbar-center">

            <h4 class="m-0" style="color: white; font-weight: bold;">Registrar Modelo</h4>

        </div>

        <!-- Sección de tabla -->
        <section class="p-3">
            <div class="row">
                <div class="col-12">
                    <div class="tabla-contenedor">
                        <table class="table table-striped table-hover mt-3 text-center table-border">
                            <a class="btn btn-success me-1" title="Agregar"
                               href="ModeloControlador?accion=nuevo&abrirModal=modelo">
                                Agregar un nuevo modelo
                            </a>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Año</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach items="${modelos}" var="m">

                                    <tr>
                                        <td>${m.id_modelo}</td>
                                        <td>${m.anio}</td>

                                        <td>

                                            <a href="ModeloControlador?accion=editar&id_modelo=${m.id_modelo}"
                                                class="btn btn-sm btn-warning me-1" title="Editar">
                                                <i class="bi bi-pencil-square"></i>
                                            </a>
                                            <a class="btn btn-sm btn-danger" title="Eliminar"
                                               href="ModeloControlador?accion=eliminar&id_modelo=${m.id_modelo}"
                                               onclick="return confirm('¿Estás seguro de eliminar el registro')">
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
        <div class="modal fade" id="modeloModal" tabindex="-1">
            <div class="modal-dialog modal-dialog-centered modal-custom">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">${modelo.id_modelo == 0 ? "Nuevo": "Editar"} Modelo</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                    <div class="modal-body">
                        <form id="modeloForm" action="ModeloControlador" method="post">
                            <div class="mb-3">
                                <label for="modelo" class="form-label">Modelo</label>
                                <input value="${modelo.anio}" type="number" class="form-control" id="modelo" name="modelo" required>
                            </div>

                            <div class="modal-footer">
                                <input type="hidden" name="id_modelo" value="${modelo.id_modelo}"></input>
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
            <% if ("modelo".equals(abrirModal)) { %>
                var myModal = new bootstrap.Modal(document.getElementById('modeloModal'));
                myModal.show();
            <% }%>
            };
        </script>

    </body>

</html>