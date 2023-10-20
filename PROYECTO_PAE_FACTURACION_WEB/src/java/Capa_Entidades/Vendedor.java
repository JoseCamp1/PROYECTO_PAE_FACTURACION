package Capa_Entidades;


// Clase Vendedor que hereda de Persona
public class Vendedor extends Persona {
    
    //atributo
    private String correo;

    // Constructor
    
    public Vendedor() {
        super(0, "", ""); 
        correo = "";
    }
    public Vendedor(int id, String nombre, String cedula, String correo) {
        super(id, nombre, cedula);
        this.correo = correo;
    }

    // Getter 
    public String getCorreo() {
        return correo;
    }

    // Setter 
    public void setCorreo(String correo) {
        this.correo = correo;
    } 
    
}

