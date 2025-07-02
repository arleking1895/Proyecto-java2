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
public class CompraDao {

    Connection autopart = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Compra> listarTodos() {
        ArrayList<Compra> lista = new ArrayList<>();

        try {
            autopart = Conexion.getConnection();
            String sql = "SELECT c.id_compra, c.fecha_compra, c.repuesto, c.cantidad, c.precio, p.id_proveedor, p.nombre_proveedor FROM compra c INNER JOIN proveedor p ON c.fk_proveedor = p.id_proveedor";
            ps = autopart.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Compra obj = new Compra();
                obj.setId_compra(rs.getInt("id_compra"));
                obj.setFecha_compra(rs.getString("fecha_compra"));
                obj.setRepuesto(rs.getString("repuesto"));
                obj.setCantidad(rs.getInt("cantidad"));
                obj.setPrecio(rs.getInt("precio"));

                Proveedor prov = new Proveedor();
                prov.setId_proveedor(rs.getInt("id_proveedor"));
                prov.setNombre_proveedor(rs.getString("nombre_proveedor"));
                obj.setFk_proveedor(prov);

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

    public int registrar(Compra obj) {
        int result = 0;

        try {

            autopart = Conexion.getConnection();
            String sql = "INSERT INTO compra (fecha_compra, repuesto, cantidad, precio, fk_proveedor) VALUES (?,?,?,?,?)";

            ps = autopart.prepareStatement(sql);
            ps.setString(1, obj.getFecha_compra());
            ps.setString(2, obj.getRepuesto());
            ps.setInt(3, obj.getCantidad());
            ps.setInt(4, obj.getPrecio());
            ps.setInt(5, obj.getFk_proveedor().getId_proveedor());

            System.out.println("🧪 Repuesto que se va a guardar: " + obj.getRepuesto());

            
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

    public Compra buscarPorId(int id_compra) {
        Compra obj = null;

        try {
            autopart = Conexion.getConnection();
            String sql = "SELECT c.id_compra, c.fecha_compra, c.repuesto, c.cantidad, c.precio, p.id_proveedor, p.nombre_proveedor FROM compra c INNER JOIN proveedor p ON c.fk_proveedor = p.id_proveedor where id_compra = ?";
            ps = autopart.prepareStatement(sql);
            ps.setInt(1, id_compra);
            rs = ps.executeQuery();

            if (rs.next()) {
                obj = new Compra();
                obj.setId_compra(rs.getInt("id_compra"));
                obj.setFecha_compra(rs.getString("fecha_compra"));
                obj.setRepuesto(rs.getString("repuesto"));
                obj.setCantidad(rs.getInt("cantidad"));
                obj.setPrecio(rs.getInt("precio"));

                Proveedor prov = new Proveedor();
                prov.setId_proveedor(rs.getInt("id_proveedor"));
                prov.setNombre_proveedor(rs.getString("nombre_proveedor"));
                obj.setFk_proveedor(prov);
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

    public int editar(Compra obj) {
        int result = 0;

        try {
            autopart = Conexion.getConnection();
            String sql = "update compra set fecha_compra = ?, repuesto = ?, cantidad = ?, precio = ?, fk_proveedor = ? where id_compra= ?;";

            ps = autopart.prepareStatement(sql);
            ps.setString(1, obj.getFecha_compra());
            ps.setString(2, obj.getRepuesto());
            ps.setInt(3, obj.getCantidad());
            ps.setInt(4, obj.getPrecio());
            ps.setInt(5, obj.getFk_proveedor().getId_proveedor());
            ps.setInt(6, obj.getId_compra());

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

    public int eliminar(int id_compra) {
        int result = 0;

        try {
            autopart = Conexion.getConnection();
            String sql = "DELETE FROM compra WHERE id_compra = ?";
            ps = autopart.prepareStatement(sql);
            ps.setInt(1, id_compra);
            result = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            // Si falla por integridad referencial, devolvemos -1 como advertencia
            if (ex.getMessage().contains("a foreign key constraint fails")) {
                result = -1;
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (autopart != null) {
                    autopart.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

}
