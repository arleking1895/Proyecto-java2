/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author nicol
 */
public class MarcaDao {

    Connection autopart = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Marca> listarTodos() {
        ArrayList<Marca> lista = new ArrayList<>();

        try {
            autopart = Conexion.getConnection();
            String sql = "select id_marca, nombre_marca, version from marca";
            ps = autopart.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Marca obj = new Marca();
                obj.setId_marca(rs.getInt("id_marca"));
                obj.setNombre_marca(rs.getString("nombre_marca"));
                obj.setVersion(rs.getString("version"));
                lista.add(obj);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (autopart != null) {
                    autopart.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {

            }
        }

        return lista;
    }

    public int registrar(Marca obj) {
        int result = 0;

        try {

            autopart = Conexion.getConnection();
            String sql = "insert into marca (nombre_marca, version) values (?,?)";

            ps = autopart.prepareStatement(sql);
            ps.setString(1, obj.getNombre_marca());
            ps.setString(2, obj.getVersion());

            result = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (autopart != null) {
                    autopart.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {

            }
        }

        return result;
    }

    public Marca buscarPorId(int id_marca) {
        Marca obj = null;

        try {
            autopart = Conexion.getConnection();
            String sql = "select id_marca, nombre_marca, version from marca where id_marca = ?";
            ps = autopart.prepareStatement(sql);
            ps.setInt(1, id_marca);
            rs = ps.executeQuery();

            if (rs.next()) {
                obj = new Marca();
                obj.setId_marca(rs.getInt("id_marca"));
                obj.setNombre_marca(rs.getString("nombre_marca"));
                obj.setVersion(rs.getString("version"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (autopart != null) {
                    autopart.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {

            }
        }

        return obj;
    }

    public int editar(Marca obj) {
        int result = 0;

        try {
            autopart = Conexion.getConnection();
            String sql = "update marca set nombre_marca = ?, version = ? where id_marca= ?;";

            ps = autopart.prepareStatement(sql);
            ps.setString(1, obj.getNombre_marca());
            ps.setString(2, obj.getVersion());
            ps.setInt(3, obj.getId_marca());

            result = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (autopart != null) {
                    autopart.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {

            }
        }

        return result;
    }

    public int eliminar(int id_marca) {
        int result = 0;

        try {
            autopart = Conexion.getConnection();
            String sql = "delete from marca where id_marca=?";
            ps = autopart.prepareStatement(sql);
            ps.setInt(1, id_marca);
            result = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            // Si falla por integridad referencial, devolvemos -1 como advertencia
            if (ex.getMessage().contains("a foreign key constraint fails")) {
                result = -1;
            }

        } finally {
            try {
                if (autopart != null) {
                    autopart.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {

            }
        }

        return result;
    }
}
