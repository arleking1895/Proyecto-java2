/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Modelo.EmpleadoDao;
import Modelo.Rol;
import Modelo.Usuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Util.Encriptador;

/**
 *
 * @author danna
 */
@WebServlet(name = "EmpleadoController", urlPatterns = {"/EmpleadoController"})
public class EmpleadoController extends HttpServlet {

    EmpleadoDao empDao = new EmpleadoDao();
    String registrarUsuarios = "/vistas/registrarUsuarios.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");

        switch (accion) {
            case "listar":
                listar(request, response);
                break;
            case "nuevo":
                nuevo(request, response);
                break;
            case "guardar":
                guardar(request, response);
                break;
            case "editar":
                editar(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            default:
        }

    }

    protected void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("empleados", empDao.ListarTodos());
        request.getRequestDispatcher("/vistas/registrarUsuarios.jsp").forward(request, response);

    }

    protected void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Verifica si se debe abrir el modal de usuario
        String abrir = request.getParameter("abrirModal");
        if ("usuario".equals(abrir)) {
            request.setAttribute("abrirModal", "usuario");
        }

        // 👇 Aquí creamos el usuario vacío con ID = 0 para que el formulario lo detecte como nuevo
        if (request.getAttribute("usuario") == null) {
            Usuario nuevo = new Usuario();
            nuevo.setId_usuario(0); // ← ESTA ES LA LÍNEA CLAVE
            request.setAttribute("usuario", nuevo);
        }

        // Lista de roles para el campo select
        try {
            request.setAttribute("empleados", empDao.ListarTodos());
            request.setAttribute("roles", empDao.listarRoles());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msje", "Error al cargar la lista de usuarios");
        }

        // Redirige a la JSP
        request.getRequestDispatcher("vistas/registrarUsuarios.jsp").forward(request, response);
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Usuario usuario = new Usuario();
            usuario.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
            usuario.setNombres_usu(request.getParameter("nombres"));
            usuario.setApellidos_usu(request.getParameter("apellidos"));
            usuario.setTelefono_usu(request.getParameter("telefono"));
            usuario.setEmail_usu(request.getParameter("email"));

            String modo = request.getParameter("modo");
            String passPlano = request.getParameter("password");
            String passFinal = null;

            if (passPlano != null && !passPlano.trim().isEmpty()) {
                passFinal = Encriptador.sha256(passPlano);
            } else if ("editar".equalsIgnoreCase(modo)) {
                Usuario actual = empDao.buscarPorId(usuario.getId_usuario());
                passFinal = actual.getPass_usu();
            } else {
                request.setAttribute("error", "La contraseña no puede estar vacía.");
                reenviarConErrores(request, response, usuario);
                return;
            }

            usuario.setPass_usu(passFinal);

            Rol rol = new Rol();
            rol.setTipo_rol(request.getParameter("rol"));
            usuario.setRol(rol);

            int result = 0;
            if ("nuevo".equalsIgnoreCase(modo)) {
                result = empDao.registrar(usuario);
            } else {
                result = empDao.editar(usuario);
            }

            if (result > 0) {
                request.getSession().setAttribute("success", "Usuario " + ("nuevo".equalsIgnoreCase(modo) ? "registrado" : "actualizado") + " correctamente");
                response.sendRedirect("EmpleadoController?accion=listar");
            } else {
                request.setAttribute("error", "No se pudo guardar el usuario.");
                reenviarConErrores(request, response, usuario);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("vistas/registrarUsuarios.jsp").forward(request, response);
        }
    }

    private void reenviarConErrores(HttpServletRequest request, HttpServletResponse response, Usuario usuario)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        request.setAttribute("usuario", usuario);
        request.setAttribute("abrirModal", "usuario");
        request.setAttribute("roles", empDao.listarRoles());
        request.setAttribute("empleados", empDao.ListarTodos());
        request.getRequestDispatcher("vistas/registrarUsuarios.jsp").forward(request, response);
    }

    protected void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");

        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
        Usuario usuario = new EmpleadoDao().buscarPorId(id_usuario);

        if (usuario != null) {
            request.setAttribute("usuario", usuario);
            request.setAttribute("abrirModal", "usuario");
            request.setAttribute("empleados", new EmpleadoDao().ListarTodos());
            request.setAttribute("roles", empDao.listarRoles());
            request.getRequestDispatcher("vistas/registrarUsuarios.jsp").forward(request, response);
        } else {
            response.sendRedirect("EmpleadoController?accion=listar");
        }
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");

        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));

        int result = new EmpleadoDao().eliminar(id_usuario);

        if (result > 0) {
            request.getSession().setAttribute("success", "Usuario eliminado correctamente");

        } else if (result == -1) {
            request.getSession().setAttribute("error", "No se puede eliminar el usuario porque está vinculado a otros registros.");
        } else {
            request.getSession().setAttribute("error", "Ocurrió un error al intentar eliminar el usuario.");
        }
        response.sendRedirect("EmpleadoController?accion=listar");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
