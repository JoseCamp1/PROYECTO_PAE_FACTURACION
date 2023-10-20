package Capa2_LogicaNegocio;

import Capa_Entidades.Producto;
import Capa3_AccesoDatos.ADProducto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LNProducto {
    //atributos
    private String _mensaje;
    // metodo de acceso get
    public String getMensaje(){
        return _mensaje;
    }
    
     //---------------------------------------------------------------------------------
    public int Insertar(Producto producto) throws Exception {
        int id = -1;
        ADProducto adproducto;
        try {
            adproducto = new ADProducto();
            id = adproducto.Insertar(producto);
            _mensaje = adproducto.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
    //---------------------------------------------------------------------------------
    public List<Producto> ListarRegistros(String condicion) throws Exception{
        List<Producto> resultado =new ArrayList();
        ADProducto adproducto;
        try {
            adproducto=new ADProducto();
            resultado=adproducto.ListarRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;       
    }    
    //---------------------------------------------------------------------------------
    public Producto ObtenerRegistro (String condicion) throws Exception{
        Producto resultado;
        ADProducto adproducto;
        try {
            adproducto=new ADProducto();
            resultado=adproducto.ObtenerRegistro(condicion);
            if (resultado.isExiste()) {
                _mensaje="Recuperado exitosamente";
            }else{
                _mensaje="El registro no existe";
            }
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    //---------------------------------------------------------------------------------
    public int Modificar(Producto producto) throws Exception{
        int resultado=-1;
        ADProducto adproducto;
        try {
            adproducto=new ADProducto();
            resultado=adproducto.Modificar(producto);
            _mensaje=adproducto.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return  resultado;
    }
    //---------------------------------------------------------------------------------
    public int Eliminar(Producto producto) throws Exception{
        int resultado=-1;
        ADProducto adproducto;
        try {
            adproducto=new ADProducto();
            resultado=adproducto.Eliminar(producto);
            _mensaje=adproducto.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return  resultado;
    }
//---------------------------------------------------------------------------------   
    
    
}
