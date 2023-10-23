package Capa_Entidades;


public class Detalle_Ventas {
    
     //atributos   
    private int id_Venta;
    private int id_Producto;
    private String nombreProducto; // No mapea ningún campo de la BD    
    private int cantidad;     
    private float precio;
    private boolean existe;
    
     /*
        Este último no pertenece a la tabla DetalleFactura, pero vamos a incluirlo
        para que no tengamos que estar haciendo consultas adicionales
        para saber el nombre del producto. 
        Esto demuestra que es válido que al construir nuestras entidades podemos
        agregar más datos adicionales a los campos de la BD de esa tabla, por
        ejemplo como ya lo hacemos con el atributo "Existe"
    */

    //constructores
     public Detalle_Ventas() {
         id_Venta=0;
         id_Producto=0;
         nombreProducto="";
         cantidad=0;         
         precio=0;
         existe=false;
    }
    public Detalle_Ventas( int id_Venta, int id_Producto,String nombreProducto, int cantidad, float precio) {
        this.id_Venta = id_Venta;
        this.id_Producto = id_Producto;
        this.nombreProducto=nombreProducto;
        this.cantidad = cantidad;        
        this.precio = precio;
        existe = true; // no se recibe como atributo, se configura por defecto
    }
    
    //getters y setters

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
    
    public float  getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
}
