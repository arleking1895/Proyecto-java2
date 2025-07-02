/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Proveedor;
import modelo.ProveedorDao;

/**
 *
 * @author nicol
 */
public class ProveedorControlador extends HttpServlet {

    ProveedorDao proveedor_dao = new ProveedorDao();
    String pagListar = "vistas/registrarProveedor.jsp";
    String pagNuevo = "vistas/registrarProveedor.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("proveedores", proveedor_dao.listarTodos());
        request.getRequestDispatcher(pagListar).forward(request, response);
    }

    protected void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String abrir = request.getParameter("abrirModal");
        if ("proveedor".equals(abrir)) {
            request.setAttribute("abrirModal", "proveedor");
        }

        if (request.getAttribute("proveedor") == null) {
            request.setAttribute("proveedor", new Proveedor());
        }

        request.setAttribute("proveedores", proveedor_dao.listarTodos());
        request.getRequestDispatcher("vistas/registrarProveedor.jsp").forward(request, response);
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Proveedor obj = new Proveedor();
        obj.setId_proveedor(Integer.parseInt(request.getParameter("id_proveedor")));
        obj.setNombre_proveedor(request.getParameter("nom_proveedor"));

        int result;

        Proveedor existente = proveedor_dao.buscarPorId(obj.getId_proveedor());

        if (existente == null) {
            result = proveedor_dao.registrar(obj);
        } else {
            result = proveedor_dao.editar(obj);
        }

        if (result > 0) {
            request.setAttribute("abrirModal", "proveedor");
            request.setAttribute("proveedor", new Proveedor());
            request.setAttribute("proveedores", proveedor_dao.listarTodos());
            request.getRequestDispatcher("vistas/registrarProveedor.jsp").forward(request, response);

        } else {
            request.setAttribute("proveedor", obj);
            request.setAttribute("abrirModal", "proveedor");
            request.setAttribute("proveedores", proveedor_dao.listarTodos());
            request.getRequestDispatcher("vistas/registrarProveedor.jsp").forward(request, response);
        }
    }

    protected void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));
        Proveedor obj = proveedor_dao.buscarPorId(id_proveedor);

        if (obj != null) {
            request.setAttribute("proveedor", obj);
            request.setAttribute("abrirModal", "proveedor");
            request.setAttribute("proveedores", proveedor_dao.listarTodos());
            request.getRequestDispatcher(pagNuevo).forward(request, response);
        } else {
            response.sendRedirect("ProveedorControlador?accion=listar");
        }
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));

        int result = proveedor_dao.eliminar(id_proveedor);

        if (result > 0) {
            request.getSession().setAttribute("sucess", "Proveedor eliminado correctamente");
        } else if (result == -1) {
            request.getSession().setAttribute("error", "No se puede eliminar el proveedor porque tiene compras asociados.");
        } else {
            request.getSession().setAttribute("error", "No se elimino el proveedor");
        }
        response.sendRedirect("ProveedorControlador?accion=listar");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
