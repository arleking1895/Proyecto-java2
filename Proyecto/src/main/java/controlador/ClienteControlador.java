package controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.ClienteDao;

/**
 *
 * @author alex
 */
@WebServlet(name = "ClienteControlador", urlPatterns = {"/ClienteControlador"})
public class ClienteControlador extends HttpServlet {
    
    private ClienteDao cliDao = new ClienteDao();
    private final String pagListar = "/vista/listar.jsp";
    private final String pagnuevo = "/vista/nuevo.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String accion = request.getParameter("accion");
        
        if (accion == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        
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
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida: " + accion);
        }
    }
    
   private void eliminar(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    
    try {
        int id = Integer.parseInt(request.getParameter("documento"));

        int result = cliDao.eliminar(id);

        if (result > 0) {
            request.getSession().setAttribute("success", "Cliente con ID " + id + " eliminado correctamente.");
        } else {
            request.getSession().setAttribute("error", "El cliente no fue eliminado.");
        }

    } catch (NumberFormatException e) {
        request.getSession().setAttribute("error", "ID inválido para eliminación.");
    }

    response.sendRedirect("ClienteControlador?accion=listar");
}
   
   /*
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
   
   
   */
   
   
   

   private void editar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
     response.setContentType("text/html;charset=UTF-8");
    String numeroDocumentoStr = request.getParameter("numero_cedula");
    

    if (numeroDocumentoStr != null && !numeroDocumentoStr.isEmpty()) {
        try {
            int numero_documento = Integer.parseInt(numeroDocumentoStr);

            Cliente c = cliDao.buscarPorid(numero_documento);

            if (c != null) {
                request.setAttribute("ncliente", c);
                request.setAttribute("abrirModal", "cliente");
            request.setAttribute("cliente", cliDao.ListarTodos());
                request.getRequestDispatcher(pagnuevo).forward(request, response);
            } else {
                response.sendRedirect("ClienteControlador?accion=listar");
            }

        } catch (NumberFormatException e) {
            // Manejar entrada no numérica
            request.setAttribute("error", "Número de documento inválido.");
            request.getRequestDispatcher(pagListar).forward(request, response);
        }
    } else {
        // Si el parámetro viene vacío o nulo
        request.setAttribute("error", "Número de documento requerido.");
        request.getRequestDispatcher(pagListar).forward(request, response);
    }
}

    
    private void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("ncliente", new Cliente());
        request.getRequestDispatcher(pagnuevo).forward(request, response);
    }
    
  private void guardar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    Cliente c = new Cliente();
    c.setNumero_cedula(Integer.parseInt(request.getParameter("documento")));
    c.setTipo_doc(request.getParameter("tipodoc"));
    c.setNombres(request.getParameter("nombres"));
    c.setApellidos(request.getParameter("apellidos"));
    c.setTelefono(request.getParameter("telefono"));

    Cliente existente = cliDao.buscarPorid(c.getNumero_cedula());
    int result = 0;

    if (existente != null) {
        // Cliente existe → ACTUALIZAR
        result = cliDao.actualizar(c);
    } else {
        // Cliente nuevo → REGISTRAR
        result = cliDao.registrar(c);
    }

    if (result > 0) {
        response.sendRedirect("ClienteControlador?accion=listar");
    } else {
        request.setAttribute("ncliente", c);
        request.getRequestDispatcher(pagnuevo).forward(request, response);
    }
}

    
    protected void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("clientes", cliDao.ListarTodos());
        request.getRequestDispatcher(pagListar).forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Controlador para acciones sobre clientes";
    }


    
}
