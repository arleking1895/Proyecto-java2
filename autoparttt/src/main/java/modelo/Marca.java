/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author nicol
 */
public class Marca {
 
    public int id_marca;
    public String nombre_marca;
    public String version;

    public Marca() {
    }

    public Marca(int id_marca, String nombre_marca, String version) {
        this.id_marca = id_marca;
        this.nombre_marca = nombre_marca;
        this.version = version;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getNombre_marca() {
        return nombre_marca;
    }

    public void setNombre_marca(String nombre_marca) {
        this.nombre_marca = nombre_marca;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Marca{" + "id_marca=" + id_marca + ", nombre_marca=" + nombre_marca + ", version=" + version + '}';
    }
    
    
}
