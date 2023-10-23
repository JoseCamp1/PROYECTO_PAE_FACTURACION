package Capa2_LogicaNegocio;

import java.util.*;
import Capa3_AccesoDatos.ADVentas;
import Capa_Entidades.Detalle_Ventas;
import Capa_Entidades.Ventas;

public class LNVentas {
    
    private String _Mensaje;

    public String getMensaje() {
        return _Mensaje;
    }

    //llamar al metodo de insertar cliente de la capa de acceso de datos
    public int Insertar(Ventas Entidad, Detalle_Ventas EntidadDetalle) throws Exception{
            int Resultado= 0;
            
            try{
                
                ADVentas DA = new ADVentas();
                Resultado=DA.Insertar(Entidad, EntidadDetalle);
                _Mensaje=DA.getMensaje();
                
            }catch(Exception ex){
                Resultado=-1;
                throw ex;
            }

            return Resultado;
    }
    
    public List<Ventas> ListarRegistros(String condicion) throws Exception{
        List<Ventas> Datos;
        
        try{
            
            ADVentas DA = new ADVentas();

            Datos = DA.ListarRegistros(condicion);
                    
            

        }catch (Exception ex){
            Datos=null;
            throw ex;
        }

        return Datos;
    }
    

    public Ventas ObtenerRegistro(String condicion) throws Exception{
        Ventas Entidad=null;
        
        try{
            
            ADVentas DA = new ADVentas();

            Entidad = DA.ObtenerRegistro(condicion);
            

        }catch (Exception ex){
            throw ex;
        }

        return Entidad;
    }

    public int ModificarEstado(Ventas Entidad) throws Exception {
        int Resultado= 0;
        
        try{

            ADVentas DA = new ADVentas();

            Resultado = DA.ModificarEstado(Entidad);
          

        }catch(Exception ex){
            throw ex;
        }

        return Resultado;
    }
    
    public int ModificarCliente(Ventas Entidad) throws Exception {
        int idfactura= 0;
        
        try{

            ADVentas DA = new ADVentas();

            idfactura = DA.ModificarCliente(Entidad);
          

        }catch(Exception ex){
            throw ex;
        }

        return idfactura;
    }
    
    public int ModificarVendedor(Ventas Entidad) throws Exception {
        int idfactura= 0;
        
        try{

            ADVentas DA = new ADVentas();

            idfactura = DA.ModificarVendedor(Entidad);
          

        }catch(Exception ex){
            throw ex;
        }

        return idfactura;
    }

//    public int Eliminar(Ventas ventas) throws Exception {
//        int resultado = -1;
//        ADVentas advendedor;
//        try {
//            advendedor = new ADVentas();
//            resultado = advendedor.Eliminar(ventas);
//            _mensaje = advendedor.getMensaje();
//        } catch (Exception e) {
//            throw e;
//        }
//        return resultado;
//    }
    
}
