<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Cliente"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Clientes</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Listado de Clientes</h2>

        <table class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Cédula</th>
                    <th>Tipo Documento</th>
                    <th>Nombres</th>
                    <th>Apellidos</th>
                    <th>Teléfono</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Cliente> lista = (List<Cliente>) request.getAttribute("clientes");
                    if (lista != null && !lista.isEmpty()) {
                        for (Cliente c : lista) {
                %>
                    <tr>
                        <td><%= c.getNumero_cedula() %></td>
                        <td><%= c.getTipo_doc() %></td>
                        <td><%= c.getNombres() %></td>
                        <td><%= c.getApellidos() %></td>
                        <td><%= c.getTelefono() %></td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="5" class="text-center">No se encontraron clientes.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
