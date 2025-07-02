/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author nicol
 */
public class Conexion {
    
   public static String user = "root";
   public static String password = "";
   public static String database = "prueba";
   public static String url = "jdbc:mysql://localhost:3306/"+database;
   
   public static Connection getConnection(){
       Connection autopart = null;
       
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           autopart = DriverManager.getConnection(url, user, password);
           System.out.println("Conexion bien");
       }catch(Exception ex){
           ex.printStackTrace();
       }
       
       return autopart;
   }
}
