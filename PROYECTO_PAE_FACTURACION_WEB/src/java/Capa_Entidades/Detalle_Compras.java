package Capa_Entidades;


public class Detalle_Compras {
    //atributos
    private int id;    
    private int id_Compra;    
    private String nombre;
    private int cantidad;        
    private float subtotal;
    private boolean existe;

    //constructores
    
    public Detalle_Compras() {
        id=0;
        id_Compra=0;
        nombre="";
        cantidad=0;
        subtotal=0;
        existe=false;
    }

    public Detalle_Compras(int id, int id_Compra, String nombre, int cantidad, float subtotal) {
        this.id = id;
        this.id_Compra = id_Compra;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.existe = existe;
    }
    
    //getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Compra() {
        return id_Compra;
    }

    public void setId_Compra(int id_Compra) {
        this.id_Compra = id_Compra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
}
