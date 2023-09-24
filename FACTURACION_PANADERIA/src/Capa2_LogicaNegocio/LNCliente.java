package Capa2_LogicaNegocio;

import Capa_Entidades.Cliente;
import Capa3_AccesoDatos.ADCliente;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LNCliente {
    //atributos
    private String _mensaje;
    // metodo de acceso get
    public String getMensaje(){
        return _mensaje;
    }
    
     //llamar al metodo de insertar cliente de la capa de acceso de datos
    public int Insertar(Cliente cliente) throws Exception {
        int id = -1;
        ADCliente adcliente;
        try {
            adcliente = new ADCliente();
            id = adcliente.Insertar(cliente);
            _mensaje = adcliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    } 
    
    public List<Cliente> ListarRegistros(String condicion) throws Exception{
        List<Cliente> resultado =new ArrayList();
        ADCliente adcliente;
        try {
            adcliente=new ADCliente();
            resultado=adcliente.ListarRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;       
    }    
    
    public Cliente ObtenerRegistro (String condicion) throws Exception{
        Cliente resultado;
        ADCliente adcliente;
        try {
            adcliente=new ADCliente();
            resultado=adcliente.ObtenerRegistro(condicion);
            if (resultado.isExiste()) {
                _mensaje="Cliente Recuperado exitosamente";
            }else{
                _mensaje="El cliente no existe";
            }
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public int Modificar(Cliente cliente) throws Exception{
        int resultado=-1;
        ADCliente adcliente;
        try {
            adcliente=new ADCliente();
            resultado=adcliente.Modificar(cliente);
            _mensaje=adcliente.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return  resultado;
    }
    
    public int Eliminar(Cliente cliente) throws Exception{
        int resultado=-1;
        ADCliente adcliente;
        try {
            adcliente=new ADCliente();
            resultado=adcliente.Eliminar(cliente);
            _mensaje=adcliente.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return  resultado;
    }    
}
