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
import modelo.Compra;
import modelo.CompraDao;
import modelo.Proveedor;
import modelo.ProveedorDao;
import modelo.Repuesto;
import modelo.RepuestoDao;

/**
 *
 * @author nicol
 */
public class CompraControlador extends HttpServlet {

    CompraDao compra_dao = new CompraDao();
    String pagListar = "vistas/registrarCompraAdmin.jsp";
    String pagNuevo = "vistas/registrarCompraAdmin.jsp?abrirModal=compra";

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

        request.setAttribute("compras", compra_dao.listarTodos());
        request.getRequestDispatcher(pagListar).forward(request, response);
    }

    protected void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String abrir = request.getParameter("abrirModal");
        if ("compra".equals(abrir)) {
            request.setAttribute("abrirModal", "compra");
        }

        if (request.getAttribute("compra") == null) {
            request.setAttribute("compra", new Compra());
        }
        request.setAttribute("repuestos", new RepuestoDao().listarTodos());
        request.setAttribute("proveedores", new ProveedorDao().listarTodos());
        request.setAttribute("compras", compra_dao.listarTodos());
        request.getRequestDispatcher("vistas/registrarCompraAdmin.jsp").forward(request, response);
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Compra obj = new Compra();
        obj.setId_compra(Integer.parseInt(request.getParameter("id_compra")));
        obj.setFecha_compra(request.getParameter("fechaCompra"));
        obj.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        obj.setPrecio(Integer.parseInt(request.getParameter("precio")));

        Proveedor prov = new Proveedor();
        prov.setId_proveedor(Integer.parseInt(request.getParameter("proveedor")));
        obj.setFk_proveedor(prov);

        int idRepuesto = Integer.parseInt(request.getParameter("nombreRepuesto"));

        RepuestoDao repuestoDao = new RepuestoDao();
        Repuesto rep = repuestoDao.buscarPorId(idRepuesto);
        if (rep != null) {
            obj.setRepuesto(rep.getNombre()); // para mostrarlo en la tabla de compras
        } else {
            System.out.println("❌ El repuesto con ID " + idRepuesto + " no fue encontrado");
            response.sendRedirect("CompraControlador?accion=listar");
            return;
        }

        int result;

        if (obj.getId_compra() == 0) {
            result = compra_dao.registrar(obj);

            if (result > 0 && rep != null) {
                int cantidadCompra = obj.getCantidad();
                int nuevoPrecio = (int) Math.round(obj.getPrecio() * 1.15);

                repuestoDao.actualizarStockYPrecio(idRepuesto, cantidadCompra, nuevoPrecio);
            }

        } else {
            result = compra_dao.editar(obj);
        }

        if (result > 0) {
            response.sendRedirect("CompraControlador?accion=listar");

        } else {
            request.setAttribute("compra", obj);
            request.setAttribute("abrirModal", "compra");
            request.setAttribute("compras", compra_dao.listarTodos());
            request.getRequestDispatcher("vistas/registrarCompraAdmin.jsp").forward(request, response);
        }
    }

    protected void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int id_compra = Integer.parseInt(request.getParameter("id_compra"));
        Compra obj = compra_dao.buscarPorId(id_compra);

        if (obj != null) {
            request.setAttribute("repuestos", new RepuestoDao().listarTodos());
            request.setAttribute("proveedores", new ProveedorDao().listarTodos());
            request.setAttribute("compra", obj);
            request.setAttribute("abrirModal", "compra");
            request.setAttribute("compras", compra_dao.listarTodos());
            request.getRequestDispatcher(pagNuevo).forward(request, response);
        } else {
            response.sendRedirect("CompraControlador?accion=listar");
        }
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int id_compra = Integer.parseInt(request.getParameter("id_compra"));

        int result = compra_dao.eliminar(id_compra);

        if (result > 0) {
            request.getSession().setAttribute("success", "Compra eliminada correctamente");
        } else if (result == -1) {
            request.getSession().setAttribute("error", "No se puede eliminar la compra porque tiene repuestos asociados.");
        } else {
            request.getSession().setAttribute("error", "Ocurrió un error al intentar eliminar la compra.");
        }
        response.sendRedirect("CompraControlador?accion=listar");

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
