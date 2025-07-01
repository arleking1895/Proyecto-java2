/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author danna
 */
public class Usuario {

    public int id_usuario;
    public String nombres_usu;
    public String apellidos_usu;
    public String telefono_usu;
    public String email_usu;
    public String pass_usu;
    public Rol rol;

    public Usuario() {

    }

    public Usuario(int id_usuario, String nombres_usu, String apellidos_usu, String telefono_usu, String email_usu, String pass_usu, Rol rol) {
        this.id_usuario = id_usuario;
        this.nombres_usu = nombres_usu;
        this.apellidos_usu = apellidos_usu;
        this.telefono_usu = telefono_usu;
        this.email_usu = email_usu;
        this.pass_usu = pass_usu;
        this.rol = rol;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombres_usu() {
        return nombres_usu;
    }

    public void setNombres_usu(String nombres_usu) {
        this.nombres_usu = nombres_usu;
    }

    public String getApellidos_usu() {
        return apellidos_usu;
    }

    public void setApellidos_usu(String apellidos_usu) {
        this.apellidos_usu = apellidos_usu;
    }

    public String getTelefono_usu() {
        return telefono_usu;
    }

    public void setTelefono_usu(String telefono_usu) {
        this.telefono_usu = telefono_usu;
    }

    public String getEmail_usu() {
        return email_usu;
    }

    public void setEmail_usu(String email_usu) {
        this.email_usu = email_usu;
    }

    public String getPass_usu() {
        return pass_usu;
    }

    public void setPass_usu(String pass_usu) {
        this.pass_usu = pass_usu;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", nombres_usu=" + nombres_usu + ", apellidos_usu=" + apellidos_usu + ", telefono_usu=" + telefono_usu + ", email_usu=" + email_usu + ", pass_usu=" + pass_usu + ", rol=" + rol + '}';
    }
    
    
    
    
    

}
