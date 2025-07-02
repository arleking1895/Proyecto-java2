/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

/**
 *
 * @author danna
 */
public class Comprobar {
    public static void main(String[] args) throws ClassNotFoundException{
        Conexion c = new Conexion();
        
        if(c.Connection()!=null){
            System.out.println("Conexion es correcta");
        }else{
            System.out.println("Conexion erronea");
            
        }
    
}
}

