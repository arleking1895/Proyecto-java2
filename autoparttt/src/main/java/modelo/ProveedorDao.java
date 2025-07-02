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
public class ProveedorDao {
    
    Connection autopart = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Proveedor> listarTodos() {
        ArrayList<Proveedor> lista = new ArrayList<>();

        try {
            autopart = Conexion.getConnection();
            String sql = "select * from proveedor";
            ps = autopart.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Proveedor obj = new Proveedor();
                obj.setId_proveedor(rs.getInt("id_proveedor"));
                obj.setNombre_proveedor(rs.getString("nombre_proveedor"));
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
    
    public int registrar(Proveedor obj){
        int result = 0;
        
        try{
            
            autopart = Conexion.getConnection();
            String sql= "insert into proveedor (id_proveedor, nombre_proveedor) values (?, ?)";
            
            ps = autopart.prepareStatement(sql);
            ps.setInt(1, obj.getId_proveedor());
            ps.setString(2, obj.getNombre_proveedor());
            
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
    
    public Proveedor buscarPorId(int id_proveedor) {
        Proveedor obj = null;

        try {
            autopart = Conexion.getConnection();
            String sql = "select id_proveedor, nombre_proveedor from proveedor where id_proveedor = ?";
            ps = autopart.prepareStatement(sql);
            ps.setInt(1, id_proveedor);
            rs = ps.executeQuery();

            if (rs.next()) {
                obj = new Proveedor();
                obj.setId_proveedor(rs.getInt("id_proveedor"));
                obj.setNombre_proveedor(rs.getString("nombre_proveedor"));
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
    
    public int editar(Proveedor obj){
        int result = 0;
        
        try{
            autopart = Conexion.getConnection();
            String sql= "update proveedor set nombre_proveedor = ? where id_proveedor= ?";
            
            ps = autopart.prepareStatement(sql);
            ps.setString(1, obj.getNombre_proveedor());
            ps.setInt(2, obj.getId_proveedor());
            
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

    public int eliminar(int id_proveedor){
        int result = 0;
        
        try{
            autopart = Conexion.getConnection();
            String sql= "delete from proveedor where id_proveedor=?";
            ps = autopart.prepareStatement(sql);
            ps.setInt(1, id_proveedor);
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
