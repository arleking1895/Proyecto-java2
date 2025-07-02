package modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDao {

    // Listar todos los empleados con su rol
    public ArrayList<Usuario> ListarTodos() throws ClassNotFoundException, SQLException {
        ArrayList<Usuario> lista = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM usuario u INNER JOIN rol r ON u.fk_id_rol = r.id_rol WHERE r.tipo_rol != 'Administrador'";

        try {

            cn = new Conexion().getConnection();

            st = cn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Usuario obj = new Usuario();
                obj.setId_usuario(rs.getInt("id_usuario"));
                obj.setNombres_usu(rs.getString("nombres_usu"));
                obj.setApellidos_usu(rs.getString("apellidos_usu"));
                obj.setTelefono_usu(rs.getString("telefono_usu"));
                obj.setEmail_usu(rs.getString("email_usu"));
                obj.setPass_usu(rs.getString("pass_usu"));

                Rol rol = new Rol();
                rol.setTipo_rol(rs.getString("tipo_rol"));
                obj.setRol(rol);

                lista.add(obj);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public int registrar(Usuario usuario) {
        int result = 0;
        Connection cn = null;
        PreparedStatement st = null;

        try {
            cn = new Conexion().getConnection();

            // 🚫 Validar si ya existe ese ID
            if (buscarPorId(usuario.getId_usuario()) != null) {
                return -2; // Código especial para indicar que ya existe
            }

            String sql = "INSERT INTO usuario (id_usuario, nombres_usu, apellidos_usu, telefono_usu, email_usu, pass_usu, fk_id_rol) VALUES (?, ?, ?, ?, ?, ?, ?)";

            int idRol = obtenerIdRol(usuario.getRol().getTipo_rol());

            if (idRol == -1) {
                System.out.println("Rol no válido: " + usuario.getRol().getTipo_rol());
                return 0;
            }

            st = cn.prepareStatement(sql);
            st.setInt(1, usuario.getId_usuario());
            st.setString(2, usuario.getNombres_usu());
            st.setString(3, usuario.getApellidos_usu());
            st.setString(4, usuario.getTelefono_usu());
            st.setString(5, usuario.getEmail_usu());
            st.setString(6, usuario.getPass_usu());
            st.setInt(7, idRol);

            result = st.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

    public int obtenerIdRol(String tipoRol) throws SQLException {
        int idRol = -1;

        try (
                Connection cn = new Conexion().getConnection(); PreparedStatement st = cn.prepareStatement("SELECT id_rol FROM rol WHERE tipo_rol = ?")) {
            st.setString(1, tipoRol);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                idRol = rs.getInt("id_rol");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return idRol;
    }

    public List<Rol> listarRoles() throws SQLException, ClassNotFoundException {
        List<Rol> lista = new ArrayList<>();
        String sql = "SELECT * FROM rol WHERE tipo_rol IN ('Vendedor', 'Auxiliar')";

        try (Connection cn = new Conexion().getConnection(); PreparedStatement st = cn.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Rol rol = new Rol();
                rol.setId_rol(rs.getInt("id_rol"));
                rol.setTipo_rol(rs.getString("tipo_rol"));
                lista.add(rol);
            }
        }
        return lista;
    }

    public Usuario buscarPorId(int id_usuario) throws SQLException {
        Usuario obj = null;

        String sql = "SELECT u.*, r.tipo_rol FROM usuario u JOIN rol r ON u.fk_id_rol = r.id_rol WHERE u.id_usuario = ?";

        try (
                Connection cn = new Conexion().getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id_usuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                obj = new Usuario();
                obj.setId_usuario(rs.getInt("id_usuario"));
                obj.setNombres_usu(rs.getString("nombres_usu"));
                obj.setApellidos_usu(rs.getString("apellidos_usu"));
                obj.setTelefono_usu(rs.getString("telefono_usu"));
                obj.setEmail_usu(rs.getString("email_usu"));
                obj.setPass_usu(rs.getString("pass_usu"));

                Rol rol = new Rol();
                rol.setTipo_rol(rs.getString("tipo_rol"));
                obj.setRol(rol);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return obj;
    }

    public int editar(Usuario usuario) {
        int result = 0;
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = new Conexion().getConnection();
            String sql = "UPDATE usuario SET nombres_usu = ?, apellidos_usu = ?, telefono_usu = ?, email_usu = ?, pass_usu = ?, fk_id_rol = ? WHERE id_usuario = ?";

            int idRol = obtenerIdRol(usuario.getRol().getTipo_rol());

            if (idRol == -1) {
                System.out.println("Error: El rol '" + usuario.getRol().getTipo_rol() + "' no existe.");
                return 0;
            }

            ps = cn.prepareStatement(sql);
            ps.setString(1, usuario.getNombres_usu());
            ps.setString(2, usuario.getApellidos_usu());
            ps.setString(3, usuario.getTelefono_usu());
            ps.setString(4, usuario.getEmail_usu());
            ps.setString(5, usuario.getPass_usu());
            ps.setInt(6, idRol);
            ps.setInt(7, usuario.getId_usuario());

            result = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

    public int eliminar(int id_usuario) throws ClassNotFoundException {
        int result = 0;
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = new Conexion().getConnection();
            String sql = "DELETE FROM usuario WHERE id_usuario = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id_usuario);
            result = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

            if (ex.getMessage().contains("a foreign key constraint fails")) {
                result = -1;
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

}
