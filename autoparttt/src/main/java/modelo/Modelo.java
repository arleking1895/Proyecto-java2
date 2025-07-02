/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author nicol
 */
public class Modelo {
    public int id_modelo;
    public String anio;

    public Modelo() {
    }

    public Modelo(int id_modelo, String anio) {
        this.id_modelo = id_modelo;
        this.anio = anio;
    }

    public int getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(int id_modelo) {
        this.id_modelo = id_modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "Modelo{" + "id_modelo=" + id_modelo + ", anio=" + anio + '}';
    }
    
    
}
