package Capa_Entidades;

import java.sql.Date;

public class Ventas {
    //atrubutos
    private int id;
    private String metodoPago;
    private Date fecha;
    private int id_Cliente;
    private int id_Vendedor;
    private float total;
    private boolean existe;

    //constructores
    public Ventas() {
        id=0;
        metodoPago="";
        fecha=Date.valueOf("");
        id_Cliente=0;
        id_Vendedor=0;
        total=0;
        existe=false;
    }
    
    public Ventas(int id, String metodoPago, Date fecha, int id_Cliente, int id_Vendedor, float total) {
        this.id = id;
        this.metodoPago = metodoPago;
        this.fecha = fecha;
        this.id_Cliente = id_Cliente;
        this.id_Vendedor = id_Vendedor;
        this.total = total;
        this.existe = existe;
        
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
    
}
