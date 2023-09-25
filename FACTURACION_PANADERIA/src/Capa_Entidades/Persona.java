package Capa_Entidades;


// Definición de la clase abstracta Persona
public abstract class Persona {
    // Atributos
    private int id;
    private String nombre;
    private String cedula;
    private boolean existe;
    
    //Constructores

    public Persona() {
        id=0;
        nombre="";
        cedula="";
        existe=false;
    }
    public Persona(int id, String nombre, String cedula) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.existe = existe;
    }
    //Getters and Setters
    
    // Getter para el atributo id
    public int getId() {
        return id;
    }

    // Setter para el atributo id
    public void setId(int id) {
        this.id = id;
    }

    // Getter para el atributo nombre
    public String getNombre() {
        return nombre;
    }

    // Setter para el atributo nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para el atributo cedula
    public String getCedula() {
        return cedula;
    }

    // Setter para el atributo cedula
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    // Getter para el atributo existe
    public boolean isExiste() {
        return existe;
    }

    // Setter para el atributo existe
    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    
    
    
}