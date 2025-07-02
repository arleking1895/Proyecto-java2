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
import modelo.Marca;
import modelo.MarcaDao;

/**
 *
 * @author nicol
 */
public class MarcaControlador extends HttpServlet {

    MarcaDao marca_dao = new MarcaDao();
    String pagListar = "vistas/registrarMarca.jsp";
    String pagNuevo = "vistas/registrarMarca.jsp?abrirModal=marca";

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
            default:

        }
    }

    protected void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("marcas", marca_dao.listarTodos());
        request.getRequestDispatcher(pagListar).forward(request, response);
    }

    protected void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String abrir = request.getParameter("abrirModal");
        if ("marca".equals(abrir)) {
            request.setAttribute("abrirModal", "marca");
        }

        if (request.getAttribute("marca") == null) {
            request.setAttribute("marca", new Marca());
        }

        request.setAttribute("marcas", marca_dao.listarTodos());
        request.getRequestDispatcher("vistas/registrarMarca.jsp").forward(request, response);
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Marca obj = new Marca();
        obj.setId_marca(Integer.parseInt(request.getParameter("id_marca")));
        obj.setNombre_marca(request.getParameter("nombreMarca"));
        obj.setVersion(request.getParameter("versionMarca"));

        int result;

        if (obj.getId_marca() == 0) {
            result = marca_dao.registrar(obj);
        } else {
            result = marca_dao.editar(obj);
        }

        if (result > 0) {
            response.sendRedirect("MarcaControlador?accion=listar");

        } else {
            request.setAttribute("marca", obj);
            request.setAttribute("abrirModal", "marca");
            request.setAttribute("marcas", marca_dao.listarTodos());
            request.getRequestDispatcher("vistas/registrarMarca.jsp").forward(request, response);
        }
    }

    protected void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int id_marca = Integer.parseInt(request.getParameter("id_marca"));
        Marca obj = marca_dao.buscarPorId(id_marca);

        if (obj != null) {
            request.setAttribute("marca", obj);
            request.setAttribute("abrirModal", "marca");
            request.setAttribute("marcas", marca_dao.listarTodos());
            request.getRequestDispatcher(pagNuevo).forward(request, response);
        } else {
            response.sendRedirect("MarcaControlador?accion=listar");
        }
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int id_marca = Integer.parseInt(request.getParameter("id_marca"));

        int result = marca_dao.eliminar(id_marca);

        if (result > 0) {
            request.getSession().setAttribute("sucess", "Marca eliminada correctamente");
        } else if (result == -1) {
            request.getSession().setAttribute("error", "No se puede eliminar la marca porque tiene repuestos asociados.");
        } else {
            request.getSession().setAttribute("error", "No se elimino la marca");
        }
        response.sendRedirect("MarcaControlador?accion=listar");
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
