package Capa3_AccesoDatos;

import Capa_Entidades.Detalle_Ventas;
import java.sql.*;
import java.util.*;

public class ADDetalle_Ventas {
    // ATRIBUTOS
    private String _Mensaje;

    // PROPIEDADES
    public String getMensaje() {
        return _Mensaje;
    }

    // CONSTRUCTOR VACÍO
    public ADDetalle_Ventas() {
        _Mensaje = "";
    }
    
    // METODOS
    
    public int Eliminar(Detalle_Ventas entidad) throws Exception{
        CallableStatement CS = null;
        int resultado = -1;
        Connection _Conexion = null;
        try{
            _Conexion = ClaseConexion.getConnection();
            CS = _Conexion.prepareCall("{call ELIMINAR_DETALLE_VENTA(?,?,?)}");
            
            CS.setInt(1, entidad.getId_Venta());
            CS.setInt(2, entidad.getId_Producto());
            CS.setString(3, _Mensaje);
            resultado = CS.executeUpdate();
        }catch (Exception ex){
            resultado = -1;
            throw ex;
        }finally{
            if (_Conexion != null ) {
                ClaseConexion.close(_Conexion);
            }
        }
        return resultado; // la cantidad de filas afectadas
    } // fin Eliminar
    
    public List<Detalle_Ventas> ListarRegistros(String Condicion) throws Exception{
        ResultSet RS = null;
                        /*
                            Porque la consulta la BD SIEMPRE va a devolver un ResultSet, no hay
                            forma que la consulta nos devuelva una LISTA. 
                            Luego convertimos el ResultSet a una Lista para retornarla 
                        */
                
        Detalle_Ventas entidad;
        List<Detalle_Ventas> lista = new ArrayList<>();
        Connection _Conexion = null;
        
        try {
            _Conexion = ClaseConexion.getConnection();
            Statement ST = _Conexion.createStatement();
            String Sentencia;
            
            Sentencia = "SELECT ID_VENTA,D.ID_PRODUCTO,NOMBRE,CANTIDAD,PRECIO_VENTA \n" +
                                "FROM DETALLE_VENTAS D INNER JOIN PRODUCTOS P\n" +
                                "ON D.ID_PRODUCTO = P.ID_PRODUCTO";
            
                        /*
                            La sentencia puede ser tan grande o compleja como se necesite, puede incluir todo lo que sea 
                            necesario para obtener la INFORMACIÓN ÚTIL que requiera el USUARIO para que su trabajo sea
                            más fácil (no para que el trabajo del programador(a) sea más fácil !)

                            Con estas instrucciones muy largas una buena estrategia es escribirlas primero en SSMS y verificar que
                            funcionan correctamente, luego copiar y pegar la consulta aquí. 
                            Existen muchas otras opciones, por ejemplo crear una función en la BD que haga esa consulta y llamar
                            a la función, y muchas otras formas más. 
                        */
            
            if(!Condicion.equals(""))  {
                Sentencia = String.format("%S WHERE %S", Sentencia, Condicion);
            }
            
            RS = ST.executeQuery(Sentencia);
            
            while(RS.next()){
                entidad = new Detalle_Ventas(RS.getInt("ID_VENTA"), 
                                                RS.getInt("ID_PRODUCTO"), 
                                                RS.getString("NOMBRE"),
                                                RS.getInt("CANTIDAD"),
                                                RS.getInt("PRECIO_VENTA"));
                
//                 los datos que se refieren al RESULTSET tienen que utilizar los mismos nombres que están dentro de la BD
//                 y en el ORDEN del constructor 
//                
//                Si se pasaron numeros seria asi, tomando en cuenta el ORDEN en que aparecen en la consulta del SELECT de arriba
//                no necesariamente van en orden numérico, dependen de la posicion en que se encuentren en el SELECT
//                
//                entidad = new DetalleFactura(RS.getInt(1), 
//                                               RS.getInt(2), 
//                                              RS.getString(3),
//                                               RS.getInt(4),
//                                              RS.getDouble(5));
                
                lista.add(entidad);
            }
        }
        catch (Exception ex) {
            throw ex;
        } finally{
            if (_Conexion != null) ClaseConexion.close(_Conexion);
        }
        return lista;
    }
    
    public Detalle_Ventas ObtenerRegistro(String condicion) throws SQLException,Exception {
        ResultSet RS = null;
        Detalle_Ventas entidad = new Detalle_Ventas();
        String Sentencia;
        Connection _Conexion = null;
        Sentencia =     "SELECT ID_VENTA,D.ID_PRODUCTO,NOMBRE,CANTIDAD,PRECIO_VENTA \n" +
                                "FROM DETALLE_VENTAS D INNER JOIN PRODUCTOS P\n" +
                                "ON D.ID_PRODUCTO = P.ID_PRODUCTO";
        
        if(!condicion.equals("")){
            Sentencia = String.format("%S WHERE %S", Sentencia, condicion);
        }
        try {
            _Conexion = ClaseConexion.getConnection();
            Statement ST = _Conexion.createStatement();
            RS = ST.executeQuery(Sentencia);
            if(RS.next()){
                entidad.setId_Venta(RS.getInt(1));
                entidad.setId_Producto(RS.getInt(2));
                entidad.setNombreProducto(RS.getString(3));
                entidad.setCantidad(RS.getInt(4));
                entidad.setPrecio(RS.getFloat(5));
                entidad.setExiste(true);
            }
            else{
                entidad.setExiste(false);
            }
            
        } catch (Exception ex) {
            throw ex;
        }finally{
            if(_Conexion != null) ClaseConexion.close(_Conexion);
        }
        return entidad;
    }
    
    
}
