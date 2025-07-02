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
public class RepuestoDao {

    Connection autopart = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Repuesto> listarTodos() {
        ArrayList<Repuesto> lista = new ArrayList<>();

        try {
            autopart = Conexion.getConnection();
            String sql = "SELECT r.id_repuesto, r.nombre, r.cantidad, r.precio, m.id_marca, m.nombre_marca, mo.id_modelo, mo.anio, c.id_compra FROM repuesto r "
                    + "INNER JOIN marca m ON r.fk_marca = m.id_marca INNER JOIN modelo mo ON r.fk_modelo = mo.id_modelo left JOIN compra c ON r.fk_compra = c.id_compra;";
            ps = autopart.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Repuesto obj = new Repuesto();
                obj.setId_repuesto(rs.getInt("id_repuesto"));
                obj.setNombre(rs.getString("nombre"));
                obj.setCantidad(rs.getInt("cantidad"));
                obj.setPrecio(rs.getInt("precio"));

                Marca marca = new Marca();
                marca.setId_marca(rs.getInt("id_marca"));
                marca.setNombre_marca(rs.getString("nombre_marca"));
                obj.setFk_marca(marca);

                Modelo modelo = new Modelo();
                modelo.setId_modelo(rs.getInt("id_modelo"));
                modelo.setAnio(rs.getString("anio"));
                obj.setFk_modelo(modelo);

                Compra compra = new Compra();
                compra.setId_compra(rs.getInt("id_compra"));
                obj.setFk_compra(compra);

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

    public int registrar(Repuesto obj) {
        int result = 0;

        try {

            autopart = Conexion.getConnection();
            String sql = "INSERT INTO repuesto (nombre, cantidad, precio, fk_marca, fk_modelo, fk_compra) VALUES (?,?,?,?,?,?)";

            ps = autopart.prepareStatement(sql);
            ps.setString(1, obj.getNombre());
            ps.setInt(2, obj.getCantidad());
            ps.setInt(3, obj.getPrecio());
            ps.setInt(4, obj.getFk_marca().getId_marca());
            ps.setInt(5, obj.getFk_modelo().getId_modelo());
            
            if(obj.getFk_compra() != null){
                ps.setInt(6, obj.getFk_compra().getId_compra());
            }else{
                ps.setNull(6, java.sql.Types.INTEGER);
            }
           
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

    public Repuesto buscarPorId(int id_repuesto) {
        Repuesto obj = null;

        try {
            autopart = Conexion.getConnection();
            String sql = "SELECT r.id_repuesto, r.nombre, r.cantidad, r.precio, m.id_marca, m.nombre_marca, mo.id_modelo, mo.anio FROM repuesto r "
                    + "INNER JOIN marca m ON r.fk_marca = m.id_marca INNER JOIN modelo mo ON r.fk_modelo = mo.id_modelo where id_repuesto = ?";
            ps = autopart.prepareStatement(sql);
            ps.setInt(1, id_repuesto);
            rs = ps.executeQuery();

            if (rs.next()) {
                obj = new Repuesto();
                obj.setId_repuesto(rs.getInt("id_repuesto"));
                obj.setNombre(rs.getString("nombre"));
                obj.setCantidad(rs.getInt("cantidad"));
                obj.setPrecio(rs.getInt("precio"));

                Marca marca = new Marca();
                marca.setId_marca(rs.getInt("id_marca"));
                marca.setNombre_marca(rs.getString("nombre_marca"));
                obj.setFk_marca(marca);

                Modelo modelo = new Modelo();
                modelo.setId_modelo(rs.getInt("id_modelo"));
                modelo.setAnio(rs.getString("anio"));
                obj.setFk_modelo(modelo);

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

    public int editar(Repuesto obj) {
        int result = 0;

        try {
            autopart = Conexion.getConnection();
            String sql = "update repuesto set nombre = ?, cantidad = ?, precio = ?, fk_marca = ?, fk_modelo = ?, fk_compra = ? where id_repuesto= ?";

            ps = autopart.prepareStatement(sql);
            ps.setString(1, obj.getNombre());
            ps.setInt(2, obj.getCantidad());
            ps.setInt(3, obj.getPrecio());
            ps.setInt(4, obj.getFk_marca().getId_marca());
            ps.setInt(5, obj.getFk_modelo().getId_modelo());
            ps.setInt(6, obj.getFk_compra().getId_compra());
            ps.setInt(7, obj.getId_repuesto());

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

    public int eliminar(int id_repuesto) {
        int result = 0;

        try {
            autopart = Conexion.getConnection();
            String sql = "delete from repuesto where id_repuesto=?";
            ps = autopart.prepareStatement(sql);
            ps.setInt(1, id_repuesto);
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

    public Repuesto buscarPorNombre(String nombre, int id_marca, int id_modelo) {

        Repuesto obj = null;

        try {
            autopart = Conexion.getConnection();
            String sql = "select * from repuesto where nombre = ? and fk_marca = ? and fk_modelo = ?";
            ps = autopart.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, id_marca);
            ps.setInt(3, id_modelo);
            rs = ps.executeQuery();

            if (rs.next()) {
                obj = new Repuesto();
                obj.setId_repuesto(rs.getInt("id_repuesto"));
                obj.setNombre(rs.getString("nombre"));
                obj.setCantidad(rs.getInt("cantidad"));
                obj.setPrecio(rs.getInt("precio"));
            }

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
            return obj;
        }
    }

    public int sumarCantidad(int id_repuesto, int cantidadASumar) {
        int result = 0;
        
        try {
            autopart = Conexion.getConnection();
            String sql = "update repuesto set cantidad = cantidad + ? where id_repuesto = ?";
            ps = autopart.prepareStatement(sql);
            ps.setInt(1, cantidadASumar);
            ps.setInt(2, id_repuesto);
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
            return result;
        }
    }
    
    public int actualizarStockYPrecio(int id_repuesto, int cantidadASumar, int nuevoPrecio){
        
        int result = 0;
        
        try{
            autopart = Conexion.getConnection();
            String sql = "update repuesto set cantidad = cantidad + ?, precio = ? where id_repuesto = ?";
            ps = autopart.prepareStatement(sql);
            ps.setInt(1, cantidadASumar);
            ps.setInt(2, nuevoPrecio);
            ps.setInt(3, id_repuesto);
            result = ps.executeUpdate();
        }catch (Exception ex) {
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
