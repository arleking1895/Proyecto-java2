/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author nicol
 */
public class Compra {
    
    public int id_compra;
    public String fecha_compra;
    public String repuesto;
    public int cantidad;
    public int precio;
    public Proveedor fk_proveedor;

    public Compra() {
    }

    public Compra(int id_compra, String fecha_compra, String repuesto, int cantidad, int precio, Proveedor fk_proveedor) {
        this.id_compra = id_compra;
        this.fecha_compra = fecha_compra;
        this.repuesto = repuesto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fk_proveedor = fk_proveedor;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public String getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public String getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(String repuesto) {
        this.repuesto = repuesto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Proveedor getFk_proveedor() {
        return fk_proveedor;
    }

    public void setFk_proveedor(Proveedor fk_proveedor) {
        this.fk_proveedor = fk_proveedor;
    }

    @Override
    public String toString() {
        return "Compra{" + "id_compra=" + id_compra + ", fecha_compra=" + fecha_compra + ", repuesto=" + repuesto + ", cantidad=" + cantidad + ", precio=" + precio + ", fk_proveedor=" + fk_proveedor + '}';
    }
    
    
}
