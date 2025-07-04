<%-- 
    Document   : nuevoc
    Created on : 30/06/2025, 9:28:07 p. m.
    Author     : alex
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Formulario Cliente</title>
    </head>
    <body>
        <div class="container mt-3">
            <div class="card">
                <div class="card-body">
                    <h3>${cliente.numero_cedula == 0 ?"Nuevo": "Editar"} Cliente</h3>
                    <hr />

                    <form action="ClienteControlador" method="post">
                        <div class="mb-3">
                            <label>Documento:</label>
                            <input value="${ncliente.numero_cedula}" name="documento" type="text" maxlength="50" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label>Tipo documento:</label>
                            <input value="${ncliente.tipo_doc}" name="tipodoc" type="text" maxlength="50" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label>Nombre:</label>
                            <input value="${ncliente.nombres}" name="nombres" type="text" maxlength="50" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label>Apellidos:</label>
                            <input value="${ncliente.apellidos}" name="apellidos" type="text" maxlength="50" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label>Teléfono:</label>
                            <input value="${ncliente.telefono}" name="telefono" type="text" maxlength="50" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <input type="hidden" name="numero_cedula" value="$Cliente.numero_cedula">

                            <input type="hidden" name="accion" value="guardar">
                            <button class="btn btn-primary btn-sm">
                                <i class="fa fa-save"></i> Guardar
                            </button>

                            <a href="ClienteControlador?accion=listar" class="btn btn-dark btn-sm">
                                <i class="fa fa-arrow-left"></i> Volver atrás
                            </a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </body>
</html>