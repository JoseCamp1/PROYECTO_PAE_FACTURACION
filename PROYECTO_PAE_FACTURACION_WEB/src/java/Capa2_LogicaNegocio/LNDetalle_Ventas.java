package Capa2_LogicaNegocio;

import Capa_Entidades.Detalle_Ventas;
import Capa3_AccesoDatos.ADDetalle_Ventas;
import java.util.*;

public class LNDetalle_Ventas {
    
     private String _Mensaje;

    public String getMensaje() {
        return _Mensaje;
    }

    // llamar al método de insertar cliente de la capa de acceso de datos
//    public int Insertar(Detalle_Ventas detalle_Ventas) throws Exception {
//        int id = -1;
//        ADDetalle_Ventas addetalle_Ventas;
//        try {
//            addetalle_Ventas = new ADDetalle_Ventas();
//            id = addetalle_Ventas.Insertar(detalle_Ventas);
//            _mensaje = addetalle_Ventas.getMensaje();
//        } catch (Exception e) {
//            throw e;
//        }
//        return id;
//    }
    
     public List<Detalle_Ventas> ListarRegistros(String Condicion) throws Exception {
        List<Detalle_Ventas> Datos;
        ADDetalle_Ventas AccesoDatos;
        try {
            AccesoDatos = new ADDetalle_Ventas();

            Datos = AccesoDatos.ListarRegistros(Condicion);
        } catch (Exception ex) {
            Datos = null;
            throw ex;
        }

        return Datos;
    }

   public Detalle_Ventas ObtenerRegistro(String condicion) throws Exception {
        Detalle_Ventas Entidad = null;

        try {

            ADDetalle_Ventas DA = new ADDetalle_Ventas();

            Entidad = DA.ObtenerRegistro(condicion);

        } catch (Exception ex) {
            throw ex;
        }

        return Entidad;
    }

//    public int Modificar(Detalle_Ventas detalle_Ventas) throws Exception {
//        int resultado = -1;
//        ADDetalle_Ventas addetalle_Ventas;
//        try {
//            addetalle_Ventas = new ADDetalle_Ventas();
//            resultado = addetalle_Ventas.Modificar(detalle_Ventas);
//            _mensaje = addetalle_Ventas.getMensaje();
//        } catch (Exception e) {
//            throw e;
//        }
//        return resultado;
//    }

    public int Eliminar(Detalle_Ventas Entidad) throws Exception {
        int Resultado = 0;

        try {
            ADDetalle_Ventas DA = new ADDetalle_Ventas();

            Resultado = DA.Eliminar(Entidad);

        } catch (Exception ex) {
            Resultado = -1;
            throw ex;
        }

        return Resultado;
    }
    
}

