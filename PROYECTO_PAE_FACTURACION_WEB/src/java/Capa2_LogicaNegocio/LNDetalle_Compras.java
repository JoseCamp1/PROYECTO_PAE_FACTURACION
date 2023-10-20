//package Capa2_LogicaNegocio;
//
//import Capa_Entidades.Detalle_Compras;
//import Capa3_AccesoDatos.ADDetalle_Compras;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//public class LNDetalle_Compras {
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
//    public int Insertar(Detalle_Compras detalle_Compras) throws Exception {
//        int id = -1;
//        ADDetalle_Compras addetalle_Compras;
//        try {
//            addetalle_Compras = new ADDetalle_Compras();
//            id = addetalle_Compras.Insertar(detalle_Compras);
//            _mensaje = addetalle_Compras.getMensaje();
//        } catch (Exception e) {
//            throw e;
//        }
//        return id;
//    }
//    
//    public List<Detalle_Compras> ListarRegistros(String condicion) throws Exception {
//        List<Detalle_Compras> resultado = new ArrayList();
//        ADDetalle_Compras addetalle_Compras;
//        try {
//            addetalle_Compras = new ADDetalle_Compras();
//            resultado = addetalle_Compras.ListarRegistros(condicion);
//        } catch (Exception e) {
//            throw e;
//        }
//        return resultado;
//    }
//
//    public Detalle_Compras ObtenerRegistro(String condicion) throws Exception {
//        Detalle_Compras resultado;
//        ADDetalle_Compras addetalle_Compras;
//        try {
//            addetalle_Compras = new ADDetalle_Compras();
//            resultado = addetalle_Compras.ObtenerRegistro(condicion);
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
//    public int Modificar(Detalle_Compras detalle_Compras) throws Exception {
//        int resultado = -1;
//        ADDetalle_Compras addetalle_Compras;
//        try {
//            addetalle_Compras = new ADDetalle_Compras();
//            resultado = addetalle_Compras.Modificar(detalle_Compras);
//            _mensaje = addetalle_Compras.getMensaje();
//        } catch (Exception e) {
//            throw e;
//        }
//        return resultado;
//    }
//
//    public int Eliminar(Detalle_Compras detalle_Compras) throws Exception {
//        int resultado = -1;
//        ADDetalle_Compras addetalle_Compras;
//        try {
//            addetalle_Compras = new ADDetalle_Compras();
//            resultado = addetalle_Compras.Eliminar(detalle_Compras);
//            _mensaje = addetalle_Compras.getMensaje();
//        } catch (Exception e) {
//            throw e;
//        }
//        return resultado;
//    }
//    
//}
//
