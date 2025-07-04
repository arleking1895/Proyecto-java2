/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author danna
 */
public class Rol {

    public int id_rol;
    public String tipo_rol;
    
    
    

    public Rol() {

    }

    public Rol(int id_rol, String tipo_rol) {
        this.id_rol = id_rol;
        this.tipo_rol = tipo_rol;
    }
    
    
    
    

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getTipo_rol() {
        return tipo_rol;
    }

    public void setTipo_rol(String tipo_rol) {
        this.tipo_rol = tipo_rol;
    }

    @Override
    public String toString() {
        return "Rol{" + "id_rol=" + id_rol + ", tipo_rol=" + tipo_rol + '}';
    }

    
    
}
