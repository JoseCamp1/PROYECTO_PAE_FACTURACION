//package Capa2_LogicaNegocio;
//
//import Capa_Entidades.Detalle_Ventas;
//import Capa3_AccesoDatos.ADDetalle_Ventas;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//public class LNDetalle_Ventas {
//    
//    // atributos
//    private String _mensaje;
//
//    // método de acceso get
//    public String getMensaje() {
//        return _mensaje;
//    }
//
//    // llamar al método de insertar cliente de la capa de acceso de datos
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
//    
//    public List<Detalle_Ventas> ListarRegistros(String condicion) throws Exception {
//        List<Detalle_Ventas> resultado = new ArrayList();
//        ADDetalle_Ventas addetalle_Ventas;
//        try {
//            addetalle_Ventas = new ADDetalle_Ventas();
//            resultado = addetalle_Ventas.ListarRegistros(condicion);
//        } catch (Exception e) {
//            throw e;
//        }
//        return resultado;
//    }
//
//    public Detalle_Ventas ObtenerRegistro(String condicion) throws Exception {
//        Detalle_Ventas resultado;
//        ADDetalle_Ventas addetalle_Ventas;
//        try {
//            addetalle_Ventas = new ADDetalle_Ventas();
//            resultado = addetalle_Ventas.ObtenerRegistro(condicion);
//            if (resultado.isExiste()) {
//                _mensaje = "Recuperado exitosamente";
//            } else {
//                _mensaje = "El registro no existe";
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//        return resultado;
//    }
//
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
//
//    public int Eliminar(Detalle_Ventas detalle_Ventas) throws Exception {
//        int resultado = -1;
//        ADDetalle_Ventas addetalle_Ventas;
//        try {
//            addetalle_Ventas = new ADDetalle_Ventas();
//            resultado = addetalle_Ventas.Eliminar(detalle_Ventas);
//            _mensaje = addetalle_Ventas.getMensaje();
//        } catch (Exception e) {
//            throw e;
//        }
//        return resultado;
//    }
//    
//}
//
