/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author nicol
 */
public class ModeloDao {

    Connection autopart = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Modelo> listarTodos() {
        ArrayList<Modelo> lista = new ArrayList<>();

        try {
            autopart = Conexion.getConnection();
            String sql = "select * from modelo";
            ps = autopart.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Modelo obj = new Modelo();
                obj.setId_modelo(rs.getInt("id_modelo"));
                obj.setAnio(rs.getString("anio"));
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
    
    public int registrar(Modelo obj){
        int result = 0;
        
        try{
            
            autopart = Conexion.getConnection();
            String sql= "insert into modelo (anio) values (?)";
            
            ps = autopart.prepareStatement(sql);
            ps.setString(1, obj.getAnio());
            
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
 
    public Modelo buscarPorId(int id_modelo) {
        Modelo obj = null;

        try {
            autopart = Conexion.getConnection();
            String sql = "select id_modelo, anio from modelo where id_modelo = ?";
            ps = autopart.prepareStatement(sql);
            ps.setInt(1, id_modelo);
            rs = ps.executeQuery();

            if (rs.next()) {
                obj = new Modelo();
                obj.setId_modelo(rs.getInt("id_modelo"));
                obj.setAnio(rs.getString("anio"));
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
    
    public int editar(Modelo obj){
        int result = 0;
        
        try{
            autopart = Conexion.getConnection();
            String sql= "update modelo set anio = ? where id_modelo= ?;";
            
            ps = autopart.prepareStatement(sql);
            ps.setString(1, obj.getAnio());
            ps.setInt(2, obj.getId_modelo());
            
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
    
    public int eliminar(int id_modelo){
        int result = 0;
        
        try{
            autopart = Conexion.getConnection();
            String sql= "delete from modelo where id_modelo=?";
            ps = autopart.prepareStatement(sql);
            ps.setInt(1, id_modelo);
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
}
