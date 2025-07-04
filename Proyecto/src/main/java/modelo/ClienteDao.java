/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex
 */
public class ClienteDao {

    private Connection autopart = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<Cliente> ListarTodos() {

        List<Cliente> lista = new ArrayList<>();
        

        try{
            autopart = Conexion.getConnection();
            String sql = "SELECT * FROM cliente";
            ps = autopart.prepareStatement(sql);
            rs = ps.executeQuery();
           
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setNumero_cedula(rs.getInt("numero_cedula"));
                c.setTipo_doc(rs.getString("tipo_doc"));
                c.setNombres(rs.getString("nombres"));
                c.setApellidos(rs.getString("apellidos"));
                c.setTelefono(rs.getString("telefono"));

                lista.add(c);
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

            return lista;
        }

    }

    public int registrar(Cliente c) {
        int result = 0;

        try {
            autopart = new Conexion().getConnection();
            String sql = "INSERT INTO cliente (numero_cedula, tipo_doc, nombres, apellidos, telefono) VALUES (?, ?, ?, ?, ?)";
            ps = autopart.prepareStatement(sql);
            ps.setInt(1, c.getNumero_cedula());
            ps.setString(2, c.getTipo_doc());
            ps.setString(3, c.getNombres());
            ps.setString(4, c.getApellidos());
            ps.setString(5, c.getTelefono());

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

    public int editar(Cliente c) {
        int result = 0;

        try {
          autopart = new Conexion().getConnection();
            String sql = "UPDATE cliente  SET tipo_doc=?, nombres=?, apellidos=?, telefono=?"
                    + "WHERE numero_cedula =?";
            ps = autopart.prepareStatement(sql);

            ps.setString(1, c.getTipo_doc());
            ps.setString(2, c.getNombres());
            ps.setString(3, c.getApellidos());
            ps.setString(4, c.getTelefono());
            ps.setInt(5, c.getNumero_cedula());

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

    public int actualizar(Cliente c) {
        int result = 0;
        String sql = "UPDATE cliente SET tipo_doc = ?, nombres = ?, apellidos = ?, telefono = ? WHERE numero_cedula = ?";

        try {
            autopart = new Conexion().getConnection();
            ps = autopart.prepareStatement(sql);
            ps.setString(1, c.getTipo_doc());
            ps.setString(2, c.getNombres());
            ps.setString(3, c.getApellidos());
            ps.setString(4, c.getTelefono());
            ps.setInt(5, c.getNumero_cedula());

            result = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (autopart != null) {
                    autopart.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

  public int eliminar(int numeroCedula) {
    int result = 0;
    String sql = "DELETE FROM cliente WHERE numero_cedula = ?";

    try {
      autopart = new Conexion().getConnection();
        ps = autopart.prepareStatement(sql);
        ps.setInt(1, numeroCedula);
        result = ps.executeUpdate();
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (ps != null) ps.close();
            if (autopart != null) autopart.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    return result;
}

    public Cliente buscarPorid(int numero_cedula) {
        Cliente c = null;
        String sql = "SELECT * FROM cliente WHERE numero_cedula = ?";

        try {
            autopart = new Conexion().getConnection();
            ps = autopart.prepareStatement(sql);
            ps.setInt(1, numero_cedula);
            rs = ps.executeQuery();

            if (rs.next()) {
                c = new Cliente();
                c.setNumero_cedula(rs.getInt("numero_cedula"));
                c.setTipo_doc(rs.getString("tipo_doc"));
                c.setNombres(rs.getString("nombres"));
                c.setApellidos(rs.getString("apellidos"));
                c.setTelefono(rs.getString("telefono"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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

        return c;
    }
}
