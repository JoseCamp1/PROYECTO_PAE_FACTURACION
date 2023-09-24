package Capa_Entidades;


public class Cliente extends Persona {  
    
    //construtores
    public Cliente() {
        super(0, "", ""); 
    }
    
    public Cliente(int id, String nombre, String cedula) {
        super(id, nombre, cedula);
    }   
    
}



