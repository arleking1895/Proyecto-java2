package Controller;

import Modelo.Usuario;
import Modelo.UsuarioDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;



@WebServlet(name = "UsuarioController", urlPatterns = {"/UsuarioController"})
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
        HttpSession sesion;
        UsuarioDao dao = new UsuarioDao();

        System.out.println("Iniciando verificación de usuario...");
        System.out.println("Correo recibido: " + request.getParameter("email"));
        System.out.println("Contraseña recibida: " + request.getParameter("pass"));

        Usuario usuario = obtenerUsuario(request);
        usuario = dao.identificar(usuario);

        if (usuario != null) {
            System.out.println("Usuario identificado: " + usuario.getEmail_usu());

            if (usuario.getRol() == null) {
                System.out.println("El rol del usuario es null");
                request.setAttribute("msje", "El usuario no tiene un rol asignado");
                request.getRequestDispatcher("/vistas/iniciosesion.jsp").forward(request, response);
                return;
            }

            String rol = usuario.getRol().getTipo_rol().trim();
            System.out.println("Rol detectado: '" + rol + "'");

            sesion = request.getSession();

            if (rol.equals("Administrador")) {
                sesion.setAttribute("Administrador", usuario);
                System.out.println("Redirigiendo a paginaAdministrador.jsp");
                request.getRequestDispatcher("vistas/paginaAdministrador.jsp").forward(request, response);
            } else if (rol.equals("Vendedor")) {
                sesion.setAttribute("Vendedor", usuario);
                System.out.println("Redirigiendo a paginaVendedor.jsp");
                request.getRequestDispatcher("vistas/paginaVendedor.jsp").forward(request, response);
            } else if (rol.equals("Auxiliar")) {
                sesion.setAttribute("Auxiliar", usuario);
                System.out.println("Redirigiendo a paginaAuxiliar.jsp");
                request.getRequestDispatcher("vistas/paginaAuxiliar.jsp").forward(request, response);
            } else {
                System.out.println("Rol no reconocido: " + rol);
                request.setAttribute("msje", "Rol no reconocido");
                request.getRequestDispatcher("vistas/iniciosesion.jsp").forward(request, response);
            }

        } else {
            System.out.println("Credenciales incorrectas");
            request.setAttribute("msje", "Credenciales incorrectas");
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
