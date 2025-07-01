package modelo;

public class Cliente {

    private int numero_cedula;
    private String tipo_doc;
    private String nombres;
    private String apellidos;
    private String telefono;

    public Cliente() {
        // Constructor por defecto
    }

    // Getters y Setters

    public int getNumero_cedula() {
        return numero_cedula;
    }

    public void setNumero_cedula(int numero_cedula) {
        this.numero_cedula = numero_cedula;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
