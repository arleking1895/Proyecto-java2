<!doctype html>
<html lang="es">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Registro Usuarios</title>

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

  <!-- Bootstrap Icons -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

  <!-- Tu archivo de estilos -->
  <link rel="stylesheet" href="registrarCliente.css">
</head>

<body>

  <!-- Encabezado -->
 <div class="navbar-center">

    <h4 class="m-0" style="color: white; font-weight: bold;">Registrar Cliente</h4>

</div>

  <!-- Sección de tabla -->
  <section class="p-3">
    <div class="row">
      <div class="col-12">
        <div class="tabla-contenedor">
          <table class="table table-striped table-hover mt-3 text-center table-border">
            <thead>
              <tr>
                <th>Número de Documento</th>
                <th>Tipo de Documento</th>
                <th>Nombres</th>
                <th>Apellidos</th>
                <th>Teléfono</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody id="data">
              <tr>
                <td>1010963534</td>
                <td>CC</td>
                <td>Danna</td>
                <td>Jaimes</td>
                <td>3132391778</td>
                <td>
                  <button class="btn btn-sm btn-success me-1" title="Agregar" data-bs-toggle="modal"
                    data-bs-target="#pagoForm">
                    <i class="bi bi-plus-circle"></i>
                  </button>
                  <button class="btn btn-sm btn-warning me-1" title="Editar">
                    <i class="bi bi-pencil-square"></i>
                  </button>
                  <button class="btn btn-sm btn-danger" title="Eliminar">
                    <i class="bi bi-trash-fill"></i>
                  </button>
                </td>
              </tr>
            </tbody>
          </table>

          <!-- Botón Regresar -->
          <div class="text-center mt-4">
            <a href="registrarVentaAdmin.html" class="btn btn-secondary">
              <i class="bi bi-arrow-left-circle"></i> Regresar
            </a>
          </div>
        </div>
      </div>
    </div>
  </section>

  <!-- Modal -->
  <div class="modal fade" id="pagoForm" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered modal-custom">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Registro Clientes</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>

        <div class="modal-body">
          <form id="clienteForm">
            <div class="mb-3">
              <label for="cedula" class="form-label">Número de Documento</label>
              <input type="text" class="form-control" id="cedula" name="cedula" pattern="[0-9]{6,10}" required>
            </div>

            <div class="mb-3">
              <label for="tipoDocumento" class="form-label">Tipo de Documento</label>
              <select class="form-select" id="tipoDocumento" name="tipoDocumento" required>
                <option selected disabled value="">Seleccione tipo</option>
                <option value="CC">Cédula de Ciudadanía</option>
                <option value="TI">Tarjeta de Identidad</option>
                <option value="CE">Cédula de Extranjería</option>
              </select>
            </div>

            <div class="mb-3">
              <label for="nombres" class="form-label">Nombres</label>
              <input type="text" class="form-control" id="nombres" name="nombres" pattern="[A-Za-z ]+" required>
            </div>

            <div class="mb-3">
              <label for="apellidos" class="form-label">Apellidos</label>
              <input type="text" class="form-control" id="apellidos" name="apellidos" pattern="[A-Za-z ]+" required>
            </div>

            <div class="mb-3">
              <label for="telefono" class="form-label">Teléfono</label>
              <input type="tel" class="form-control" id="telefono" name="telefono" pattern="[0-9]{10}" required>
            </div>

            <div class="modal-footer">
             <button type="submit" class="btn btn-primary"
                            style="background-color: #6c5ce7;">Guardar</button>
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
            </div>
          </form>
        </div>

      </div>
    </div>
  </div>

  <!-- Scripts -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    crossorigin="anonymous"></script>

</body>

</html>
