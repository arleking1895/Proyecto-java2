/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author alex
 */
public class Conexion {
        public static final String username = "root";
    public static final String password= "";
    public static final String database = "autopart";
    public static final String url = "jdbc:mysql://localhost:3306/"+database;

    
    
    public static Connection getConnection(){
        Connection cn =null;
        
        try{
            
          Class.forName("com.mysql.cj.jdbc.Driver");

            cn = DriverManager.getConnection(url,"username","password");
            System.out.println("conexion establecida");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        
        
        return cn;
    }
}
