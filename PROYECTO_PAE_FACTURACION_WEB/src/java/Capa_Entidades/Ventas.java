package Capa_Entidades;

import java.sql.Date;

/* 
    Hay varios objetos para manejar fechas, en este caso como son fechas
    que manipulamos diretamente de la BD debemos agregar este import, para 
    que las fechas sean compatibles con el driver de la BD que estamos manejando.
    Si no hacemos ese import, al declarar el atributo fecha automáticamente
    nos va a sugerir el import java.util.Date, pero el de UTIL es un objeto
    de fecha GENÉRICO, el que nos sirve es el de SQL.
*/

public class Ventas {
    //atributos
    private int id;
    private String metodoPago;
    private Date fecha;
    private int id_Cliente;
    private String nombreCliente; // No es un campo mapeado de la BD
    private int id_Vendedor;
    private String nombreVendedor; // No es un campo mapeado de la BD    
    private float total;
    private String estado;
    private boolean existe;

    
    

   

    //constructores
    public Ventas() {
        id=0;
        metodoPago="";
        fecha=null;// Para obtener la fecha actual se usa getTime() de la clase Date
        id_Cliente=0;
        nombreCliente = "";
        id_Vendedor=0;
        nombreVendedor="";
        total=0;
        estado="";
        existe=false;
    }
    
    public Ventas(int id, String metodoPago, Date fecha, int id_Cliente,String NombreCliente, int id_Vendedor,String nombreVendedor, float total,String estado) {
        this.id = id;
        this.metodoPago = metodoPago;
        this.fecha = fecha;
        this.id_Cliente = id_Cliente;
        this.nombreCliente=NombreCliente;
        this.id_Vendedor = id_Vendedor;
        this.nombreVendedor=nombreVendedor;
        this.total = total;
        this.estado=estado;
        existe = true; // no se recibe como atributo, se configura por defecto
        
    }   
    
    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public int getId_Vendedor() {
        return id_Vendedor;
    }

    public void setId_Vendedor(int id_Vendedor) {
        this.id_Vendedor = id_Vendedor;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }   
    
    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
     public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
