package modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDao extends Conexion {

    public Usuario identificar(Usuario user) throws Exception {
        Usuario usu = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String sql = "SELECT u.id_usuario, u.nombres_usu, u.email_usu, r.tipo_rol "
                   + "FROM usuario u "
                   + "INNER JOIN rol r ON u.fk_id_rol = r.id_rol "
                   + "WHERE u.email_usu = ? AND u.pass_usu = ?";

        try {
            cn = this.getConnection(); // conexión correcta
            pst = cn.prepareStatement(sql);
            pst.setString(1, user.getEmail_usu());
            pst.setString(2, user.getPass_usu()); // ya viene encriptado desde UsuarioController

            rs = pst.executeQuery();

            if (rs.next()) {
                usu = new Usuario();
                usu.setId_usuario(rs.getInt("id_usuario"));
                usu.setNombres_usu(rs.getString("nombres_usu"));
                usu.setEmail_usu(rs.getString("email_usu"));
                usu.setPass_usu(user.getPass_usu());

                Rol rol = new Rol();
                rol.setTipo_rol(rs.getString("tipo_rol"));
                usu.setRol(rol);
            }

        } catch (Exception e) {
            System.out.println("❌ Error en identificar(): " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (cn != null) cn.close();
        }

        return usu;
    }
}
