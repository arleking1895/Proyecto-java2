<%-- 
    Document   : index
    Created on : 30/06/2025, 1:49:33 p. m.
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
    </head>
    <body>
       
        <%
           response.sendRedirect("ClienteControlador?accion=listar");
         %>   
    </body>
</html>
