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
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;
import modelo.UsuarioDao;

/**
 *
 * @author nicol
 */
public class UsuarioController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");

        try {
            if (accion != null) {
                switch (accion) {
                    case "verificar":
                        verificar(request, response);
                        break;
                    case "cerrar":
                        cerrarsesion(request, response);
                        break;
                    default:
                        response.sendRedirect(request.getContextPath() + "/vistas/iniciosesion.jsp");
                        break;
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/vistas/iniciosesion.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msje", "Error interno: " + e.getMessage());
            request.getRequestDispatcher("/vistas/iniciosesion.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.getWriter().write("Servlet funcionando correctamente");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entró al doPost del servlet.");
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controlador para login y logout de usuarios";
    }

 private void verificar(HttpServletRequest request, HttpServletResponse response) throws Exception {

    HttpSession sesion = request.getSession();
    UsuarioDao dao = new UsuarioDao();
    Usuario usuario = obtenerUsuario(request);
    usuario = dao.identificar(usuario);

    if (usuario != null && usuario.getRol() != null) {
        sesion.setAttribute("usuarioLogueado", usuario);

        // ✅ Obtener el nombre del rol
        String rol = usuario.getRol().getTipo_rol();

        // ✅ Enviar el mensaje de bienvenida al JSP
        request.setAttribute("bienvenida", "Bienvenido " + rol);

        // ✅ Redirigir con forward para que el mensaje llegue
        request.getRequestDispatcher("vistas/paginaPrincipal.jsp").forward(request, response);
    } else {
        request.setAttribute("msje", "Credenciales incorrectas. Vuelve a intentarlo");
        request.getRequestDispatcher("vistas/iniciosesion.jsp").forward(request, response);
    }
}


private void cerrarsesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            System.out.println("Sesión cerrada correctamente");
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    private Usuario obtenerUsuario(HttpServletRequest request) {
        Usuario u = new Usuario();
        u.setEmail_usu(request.getParameter("email"));

        // Encriptamos la contraseña antes de enviarla al DAO
        String passPlano = request.getParameter("pass");
        String passEncriptado = Util.Encriptador.sha256(passPlano); // usa tu clase Encriptar
        u.setPass_usu(passEncriptado);

        return u;
    }

}
