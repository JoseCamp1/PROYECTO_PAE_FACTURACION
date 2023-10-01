package Capa_Entidades;


public class Detalle_Ventas {
    
     //atributos
    private int id;
    private int id_Venta;
    private int id_Producto;
    private int cantidad;     
    private float subtotal;
    private boolean existe;
    

    //constructores
     public Detalle_Ventas() {
         id=0;
         id_Venta=0;
         id_Producto=0;
         cantidad=0;         
         subtotal=0;
         existe=false;
    }
    public Detalle_Ventas(int id, int id_Venta, int id_Producto, int cantidad, float subtotal) {
        this.id = id;
        this.id_Venta = id_Venta;
        this.id_Producto = id_Producto;
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

    public int getId_Venta() {
        return id_Venta;
    }

    public void setId_Venta(int id_Venta) {
        this.id_Venta = id_Venta;
    }

    public int getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
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
