/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author alex
 */
public class Pago {
    
   private int id_pago; 
private String metodo_pago; 


 
public Pago(){
    
} 

    public Pago(int id_pago, String metodo_pago) {
        this.id_pago = id_pago;
        this.metodo_pago = metodo_pago;
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    @Override
    public String toString() {
        return "Pago{" + "id_pago=" + id_pago + ", metodo_pago=" + metodo_pago + '}';
    }
 

    
}
