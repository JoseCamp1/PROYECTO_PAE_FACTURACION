package Capa_Entidades;

public class Compras {
    //atributos
    private int id;    
    private String fecha;
    private String proveedor;     
    private float total;
    private boolean existe;

    
    //constructores

    public Compras() {
        id=0;
        fecha="";
        proveedor="";
        total=0;
        existe=false;
    }
    
    public Compras(int id, String fecha, String proveedor, float total) {
        this.id = id;
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.total = total;
        this.existe = existe;
    }
        
    //getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
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
