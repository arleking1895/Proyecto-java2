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
import modelo.Modelo;
import modelo.ModeloDao;

/**
 *
 * @author nicol
 */
public class ModeloControlador extends HttpServlet {

    ModeloDao modelo_dao = new ModeloDao();
    String pagListar = "vistas/registrarModelo.jsp";
    String pagNuevo = "vistas/registrarModelo.jsp?abrirModal=modelo";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String accion = request.getParameter("accion");

        switch(accion){
            case "listar":
                listar(request, response);
                break;
            case "nuevo":
                nuevo(request, response);
                break;
            case "guardar":
                guardar (request, response);
                break;
            case "editar":
                editar (request, response);
                break;
            case "eliminar":
                eliminar (request, response);
                break;
            default:
        }
    }

    protected void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        request.setAttribute("modelos", modelo_dao.listarTodos());
        request.getRequestDispatcher(pagListar).forward(request, response);
    }
    
    protected void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String abrir = request.getParameter("abrirModal");
        if("modelo".equals(abrir)){
            request.setAttribute("abrirModal", "modelo");
        }
       
        request.setAttribute("modelo", new Modelo());
        request.setAttribute("modelos", modelo_dao.listarTodos());
        request.getRequestDispatcher("vistas/registrarModelo.jsp").forward(request, response);
    }
    
    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Modelo obj = new Modelo();
        obj.setId_modelo(Integer.parseInt(request.getParameter("id_modelo")));
        obj.setAnio(request.getParameter("modelo"));
        
        int result;
        
        if(obj.getId_modelo() == 0){
            result = modelo_dao.registrar(obj);
        }else{
            result = modelo_dao.editar(obj);
        }
        
        if(result > 0){
            response.sendRedirect("ModeloControlador?accion=listar");
        }else{
            request.setAttribute("modelo", obj);
            request.setAttribute("abrirModal", "modelo");
            request.setAttribute("modelos", modelo_dao.listarTodos());
            request.getRequestDispatcher("vistas/registrarModelo.jsp").forward(request, response);
        }
    }
    
    protected void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        int id_modelo= Integer.parseInt(request.getParameter("id_modelo"));
        Modelo obj = modelo_dao.buscarPorId(id_modelo);
         
        if (obj != null) {
            request.setAttribute("modelo", obj);
            request.setAttribute("abrirModal", "modelo");
            request.setAttribute("modelos", modelo_dao.listarTodos());
            request.getRequestDispatcher(pagNuevo).forward(request, response);
        } else {
            response.sendRedirect("ModeloControlador?accion=listar");
        }
    }
    
    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        int id_modelo = Integer.parseInt(request.getParameter("id_modelo"));
        
        int result = modelo_dao.eliminar(id_modelo);
        
        if (result > 0) {
            request.getSession().setAttribute("sucess", "Modelo eliminado correctamente");
        } else {
            request.getSession().setAttribute("error", "No se elimino el modelo");
        }
        response.sendRedirect("ModeloControlador?accion=listar");
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
