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
import modelo.Marca;
import modelo.MarcaDao;
import modelo.Modelo;
import modelo.ModeloDao;
import modelo.Repuesto;
import modelo.RepuestoDao;

/**
 *
 * @author nicol
 */
public class RepuestoControlador extends HttpServlet {

    RepuestoDao repuesto_dao = new RepuestoDao();
    String pagListar = "vistas/registrarRepuesto.jsp";
    String pagNuevo = "vistas/registrarRepuesto.jsp";

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

        request.setAttribute("repuestos", repuesto_dao.listarTodos());
        request.setAttribute("marcas", new MarcaDao().listarTodos());
        request.setAttribute("modelos", new ModeloDao().listarTodos());
        request.setAttribute("compras", new CompraDao().listarTodos());
        request.setAttribute("repuesto", new Repuesto()); // Inicializa vacío para el formulario

        request.getRequestDispatcher(pagListar).forward(request, response);
    }

    protected void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String abrir = request.getParameter("abrirModal");
        if ("repuesto".equals(abrir)) {
            request.setAttribute("abrirModal", "repuesto");
        }

        if (request.getAttribute("repuesto") == null) {
            request.setAttribute("repuesto", new Repuesto()); // ID será 0
        }

        request.setAttribute("marcas", new MarcaDao().listarTodos());
        request.setAttribute("modelos", new ModeloDao().listarTodos());
        request.setAttribute("compras", new CompraDao().listarTodos());
        request.setAttribute("repuestos", repuesto_dao.listarTodos());

        request.getRequestDispatcher("vistas/registrarRepuesto.jsp").forward(request, response);
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Repuesto obj = new Repuesto();
        obj.setId_repuesto(Integer.parseInt(request.getParameter("id_repuesto")));
        obj.setNombre(request.getParameter("nombre"));
        
        String cantidadP = request.getParameter("cantidad");
        int cantidad = (cantidadP != null && !cantidadP.isEmpty()) ? Integer.parseInt(cantidadP) : 0;
        obj.setCantidad(cantidad);

        String precioP = request.getParameter("precio");
        int precio = (precioP != null && !precioP.isEmpty()) ? Integer.parseInt(precioP) : 0;
        obj.setPrecio(precio);
        
        Marca marca = new Marca();
        marca.setId_marca(Integer.parseInt(request.getParameter("marca")));
        obj.setFk_marca(marca);

        Modelo modelo = new Modelo();
        modelo.setId_modelo(Integer.parseInt(request.getParameter("modelo")));
        obj.setFk_modelo(modelo);

        String compraP = request.getParameter("compra");
        if (compraP != null && !compraP.isEmpty()) {
            Compra compra = new Compra();
            compra.setId_compra(Integer.parseInt(compraP));
            obj.setFk_compra(compra);
        } else {
            obj.setFk_compra(null);
        }

        int result;

        if (obj.getId_repuesto() == 0) {
            // Buscar si ya existe un repuesto con mismo nombre, marca y modelo
            Repuesto existente = repuesto_dao.buscarPorNombre(
                    obj.getNombre(),
                    obj.getFk_marca().getId_marca(),
                    obj.getFk_modelo().getId_modelo()
            );

            if (existente != null) {
                // Ya existe → sumamos la cantidad
                result = repuesto_dao.sumarCantidad(existente.getId_repuesto(), obj.getCantidad());
                request.getSession().setAttribute("success", "Cantidad actualizada del repuesto existente");
            } else {
                // No existe → insertamos
                result = repuesto_dao.registrar(obj);
                request.getSession().setAttribute("success", "Repuesto registrado correctamente");
            }
        } else {
            // Editar existente
            result = repuesto_dao.editar(obj);
            request.getSession().setAttribute("success", "Repuesto editado correctamente");
        }

        if (result > 0) {
            response.sendRedirect("RepuestoControlador?accion=listar");

        } else {
            request.setAttribute("repuesto", obj);
            request.setAttribute("abrirModal", "repuesto");
            request.setAttribute("repuestos", repuesto_dao.listarTodos());
            request.getRequestDispatcher("vistas/registrarRepuesto.jsp").forward(request, response);
        }
    }

    protected void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int id_repuesto = Integer.parseInt(request.getParameter("id_repuesto"));
        Repuesto obj = repuesto_dao.buscarPorId(id_repuesto);

        if (obj != null) {
            request.setAttribute("marcas", new MarcaDao().listarTodos());
            request.setAttribute("modelos", new ModeloDao().listarTodos());
            request.setAttribute("compras", new CompraDao().listarTodos());
            request.setAttribute("repuesto", obj);
            request.setAttribute("abrirModal", "repuesto");
            request.setAttribute("repuestos", repuesto_dao.listarTodos());
            request.getRequestDispatcher(pagNuevo).forward(request, response);
        } else {
            response.sendRedirect("RepuestoControlador?accion=listar");
        }
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int id_repuesto = Integer.parseInt(request.getParameter("id_repuesto"));

        int result = repuesto_dao.eliminar(id_repuesto);

        if (result > 0) {
            request.getSession().setAttribute("success", "Repuesto eliminado correctamente");
        } else {
            request.getSession().setAttribute("error", "No se elimino el repuesto");
        }
        response.sendRedirect("RepuestoControlador?accion=listar");
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
