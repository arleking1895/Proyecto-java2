/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author nicol
 */
public class Repuesto {
    
    public int id_repuesto;
    public String nombre;
    public int cantidad;
    public int precio;
    public Marca fk_marca;
    public Modelo fk_modelo;
    public Compra fk_compra;

    public Repuesto() {
    }

    public Repuesto(int id_repuesto, String nombre, int cantidad, int precio, Marca fk_marca, Modelo fk_modelo, Compra fk_compra) {
        this.id_repuesto = id_repuesto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fk_marca = fk_marca;
        this.fk_modelo = fk_modelo;
        this.fk_compra = fk_compra;
    }

    public int getId_repuesto() {
        return id_repuesto;
    }

    public void setId_repuesto(int id_repuesto) {
        this.id_repuesto = id_repuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Marca getFk_marca() {
        return fk_marca;
    }

    public void setFk_marca(Marca fk_marca) {
        this.fk_marca = fk_marca;
    }

    public Modelo getFk_modelo() {
        return fk_modelo;
    }

    public void setFk_modelo(Modelo fk_modelo) {
        this.fk_modelo = fk_modelo;
    }

    public Compra getFk_compra() {
        return fk_compra;
    }

    public void setFk_compra(Compra fk_compra) {
        this.fk_compra = fk_compra;
    }

    @Override
    public String toString() {
        return "Repuesto{" + "id_repuesto=" + id_repuesto + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio + ", fk_marca=" + fk_marca + ", fk_modelo=" + fk_modelo + ", fk_compra=" + fk_compra + '}';
    }
    
    
}
