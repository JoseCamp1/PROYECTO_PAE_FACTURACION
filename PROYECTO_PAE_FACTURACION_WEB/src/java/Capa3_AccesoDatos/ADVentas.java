package Capa3_AccesoDatos;

import Capa_Entidades.Ventas;
import Capa_Entidades.Detalle_Ventas;
import java.sql.*;
import java.util.*;

public class ADVentas {
     // ATRIBUTOS
    private String _Mensaje;

    public String getMensaje() {
        return _Mensaje;
    }
    
    // CONSTRUCTORES
    public ADVentas() {
        _Mensaje = "";
    }
    
    // METODOS
    public int Insertar(Ventas EntidadFactura,Detalle_Ventas  EntidadDetalle) throws Exception{
        //ResultSet RSDatos; // no se esta utilizando
        CallableStatement CS;
        int resultado = 0;
        int idFactura = 0;
        Connection _Conexion = null;
        try {
            _Conexion = ClaseConexion.getConnection();         
            
            /* 
                Por defecto el objeto Connection trabaja las transacciones con confirmacion automatica
                Es decir que cada vez que se ejecuta un COMANDO automáticamente realiza un COMMIT (actualizar la BD)
                Pero en este ejemplo no queremos eso, porque deseamos realizar varias transacciones y que todas se 
                manejen como si fuera una sola, de forma que si todo sale bien se haga un COMMIT de TODAS juntas, 
                y si algo sale mal NO SE HAGA COMMIT DE NINGUNA. 
                Esto es lo que se conoce como manejo de TRANSACCIONES (se hacen todas las operaciones correctamente, 
                o no se hace ninguna)
           */
            
            // Transacciones Implicitas y Explicitas:
            _Conexion.setAutoCommit(false); // en TRUE automaticamente hace COMMIT cada vez que se ejecute un COMANDO,
            // en FALSE significa que de ahora en adelante todas las operaciones QUE VAMOS a hacer en este metodo INSERTAR, 
            // se hagan en LA MISMA TRANSACCION
            
            /*
                Esta configuración se aplica al objeto Connection, en este caso definimos la conexión dentro del método,
                entonces esta característica de autoCommit en FALSE se mantendrá durante todo el método. Fuera del método no. 
            */
            
            // el COMMIT se hará únicamente cuando uno indique que haga COMMIT 
            // esto es para garantizar que se hagan todas las operaciones en UNA SOLA TRANSACCIÓN 
            
            
            CS = _Conexion.prepareCall("{call GUARDAR_VENTA(?,?,?,?,?,?,?,?)}");
            
            CS.setInt(1, EntidadFactura.getId());
            CS.setString(2, EntidadFactura.getMetodoPago());
            CS.setDate(3, EntidadFactura.getFecha());            
            CS.setInt(4, EntidadFactura.getId_Cliente());
            CS.setInt(5, EntidadFactura.getId_Vendedor());
            CS.setFloat(6, EntidadFactura.getTotal());
            CS.setString(7, EntidadFactura.getEstado());
            CS.setString(8, _Mensaje);
            CS.registerOutParameter(1, Types.INTEGER); // en la BD el parametro 1 es de salida para obtener el IDENTITY generado
            
            resultado = CS.executeUpdate();
            
            idFactura = CS.getInt(1);
            
            CS = _Conexion.prepareCall("{call GUARDAR_DETALLE_VENTA(?,?,?,?,?)}");
            CS.setInt(1, idFactura); // llama a la variable que acabamos de declarar            
            CS.setInt(2, EntidadDetalle.getId_Producto());
            CS.setInt(3, EntidadDetalle.getCantidad());
            CS.setFloat(4, EntidadDetalle.getPrecio());           
            CS.setString(5, _Mensaje);
            
            //registrar mensaje para salida
            CS.registerOutParameter(5, Types.VARCHAR);
            
            resultado = CS.executeUpdate();
            
            //SE RECIBE DEL SP
            _Mensaje = CS.getString(5);
            
            _Conexion.commit();
            
            /*
                Hacemos el COMMIT porque ya hicimos todas las operaciones que deseabamos, 
                sabemos que si llegó hasta este punto sin "caerse" significa que todo salió bien.
                Por ello hacemos el Commit para que las operaciones queden actualizadas 
                en la BD. 
            */
                   
            
        } catch (ClassNotFoundException | SQLException ex) { // tambien pudo usarse Exception que es general
            
            _Conexion.rollback(); // Si algo salió mal se DESHACEN todas las transacciones con un ROLLBACK
                                   // para que la BD quede en un estado consistente. 
            
            throw ex;
        }finally{
            if(_Conexion != null) ClaseConexion.close(_Conexion);
        }
        return idFactura;
    } // fin de insertar
    
    public int ModificarEstado(Ventas EntidadFactura)  throws Exception{
        int resultado = 0;
        Connection _Conexion = null;  
        // dentro del try no se puede declarar, porque entonces no podriamos cerrarla en el Finally
        // O, podria declararse y cerrarse dentro del Try
        try{
            _Conexion = ClaseConexion.getConnection(); 
            PreparedStatement PS = _Conexion.prepareStatement("UPDATE VENTAS SET ESTADO = ? WHERE ID_VENTA = ?");
            
            PS.setString(1, EntidadFactura.getEstado());
            PS.setInt(2, EntidadFactura.getId());
            
            resultado = PS.executeUpdate();
            
        }catch(Exception ex){
            resultado = -1;
            throw  ex;
        }finally{
            if(_Conexion != null) ClaseConexion.close(_Conexion);
            // .close y .getConnection son metodos estaticos de la clase ClaseConexion, si uno quisiera escribirlos sin 
            // ClaseConexion.  entonces solamente debe importar la clase
        }
        return resultado;
    } // fin ModificarEstado
    
    public int ModificarCliente(Ventas EntidadFactura)  throws Exception{
        int idfactura = 0;
        Connection _Conexion = null; 
        try{
            _Conexion = ClaseConexion.getConnection(); 
            PreparedStatement PS = _Conexion.prepareStatement("UPDATE VENTAS SET ID_CLIENTE = ? WHERE ID_VENTA = ?");
            
            PS.setInt(1, EntidadFactura.getId_Cliente());
            PS.setInt(2, EntidadFactura.getId());
            
            PS.executeUpdate();
            idfactura = EntidadFactura.getId(); // este devuelve el ID FACTURA
            
        }catch(Exception ex){
            throw  ex;
        }finally{
            if(_Conexion != null) ClaseConexion.close(_Conexion);
        }
        return idfactura;
    } // fin Modificar ID del cliente
    
    public int ModificarVendedor(Ventas EntidadFactura)  throws Exception{
        int idfactura = 0;
        Connection _Conexion = null; 
        try{
            _Conexion = ClaseConexion.getConnection(); 
            PreparedStatement PS = _Conexion.prepareStatement("UPDATE VENTAS SET ID_VENDEDOR = ? WHERE ID_VENTA = ?");
            
            PS.setInt(1, EntidadFactura.getId_Vendedor());
            PS.setInt(2, EntidadFactura.getId());
            
            PS.executeUpdate();
            idfactura = EntidadFactura.getId(); // este devuelve el ID FACTURA
            
        }catch(Exception ex){
            throw  ex;
        }finally{
            if(_Conexion != null) ClaseConexion.close(_Conexion);
        }
        return idfactura;
    } // fin Modificar ID del cliente
    
public List<Ventas> ListarRegistros(String Condicion) throws Exception {
    ResultSet RS = null;
    Ventas entidad;
    List<Ventas> ListaF = new ArrayList<>();
    Connection _Conexion = null;
    try {
        _Conexion = ClaseConexion.getConnection();
        Statement ST = _Conexion.createStatement();
        String Sentencia;

        Sentencia = "SELECT V.ID_VENTA, V.METODOPAGO, V.FECHA, V.ID_CLIENTE, C.NOMBRE_COMPLETO AS NOMBRE_CLIENTE, V.ID_VENDEDOR, VV.NOMBRE_COMPLETO AS NOMBRE_VENDEDOR, V.TOTAL, V.ESTADO " +
                    "FROM VENTAS V " +
                    "INNER JOIN CLIENTES C ON C.ID_CLIENTE = V.ID_CLIENTE " +
                    "INNER JOIN VENDEDORES VV ON VV.ID_VENDEDOR = V.ID_VENDEDOR";

        if (!Condicion.equals("")) {
            Sentencia = String.format("%S WHERE %S", Sentencia, Condicion);
        }
        RS = ST.executeQuery(Sentencia);
        while (RS.next()) {
            entidad = new Ventas(RS.getInt("ID_VENTA"),
                    RS.getString("METODOPAGO"),
                    RS.getDate("FECHA"),
                    RS.getInt("ID_CLIENTE"),
                    RS.getString("NOMBRE_CLIENTE"),
                    RS.getInt("ID_VENDEDOR"),
                    RS.getString("NOMBRE_VENDEDOR"),
                    RS.getInt("TOTAL"),
                    RS.getString("ESTADO"));
            ListaF.add(entidad);
        }

    } catch (Exception ex) {
        throw ex;
    } finally {
        if (_Conexion != null) ClaseConexion.close(_Conexion);
    }
    return ListaF;
} // fin Listar Registros
   
    
     // retorna una factura 
    public Ventas ObtenerRegistro(String Condicion) throws Exception{
        ResultSet RsDatos = null;
        Ventas entidad = new Ventas();
        String Sentencia;
        Connection _Conexion = null;
        Sentencia = "SELECT V.ID_VENTA, V.METODOPAGO, V.FECHA, V.ID_CLIENTE, C.NOMBRE_COMPLETO AS NOMBRE_CLIENTE, V.ID_VENDEDOR, VV.NOMBRE_COMPLETO AS NOMBRE_VENDEDOR, V.TOTAL, V.ESTADO " +
                            "FROM VENTAS V " +
                            "INNER JOIN CLIENTES C ON C.ID_CLIENTE = V.ID_CLIENTE " +
                            "INNER JOIN VENDEDORES VV ON VV.ID_VENDEDOR = V.ID_VENDEDOR";
        if(!Condicion.equals("")){
                Sentencia = String.format("%S WHERE %S", Sentencia, Condicion);
            }
        try {
            _Conexion = ClaseConexion.getConnection();
            Statement ST = _Conexion.createStatement();
            RsDatos = ST.executeQuery(Sentencia);
            if(RsDatos.next()){
                entidad.setId(RsDatos.getInt("ID_VENTA"));
                entidad.setMetodoPago(RsDatos.getString("METODOPAGO"));
                entidad.setFecha(RsDatos.getDate("FECHA"));
                entidad.setId_Cliente(RsDatos.getInt("ID_CLIENTE"));
                entidad.setNombreCliente(RsDatos.getString("NOMBRE_CLIENTE"));
                entidad.setId_Vendedor(RsDatos.getInt("ID_VENDEDOR"));
                entidad.setNombreVendedor(RsDatos.getString("NOMBRE_VENDEDOR"));
                entidad.setTotal(RsDatos.getFloat("TOTAL"));
                entidad.setEstado(RsDatos.getString("ESTADO"));
                entidad.setExiste(false);
            }else{
                entidad.setExiste(false);
            }
            } catch (Exception ex) {
                throw ex;
            }finally{
            if(_Conexion != null) ClaseConexion.close(_Conexion);
        }
        return entidad;
    } // fin Listar Registros
    
}
