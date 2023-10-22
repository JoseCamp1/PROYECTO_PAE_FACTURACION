package Capa3_AccesoDatos;

import Capa_Entidades.Vendedor;
import java.sql.*;
import java.util.*;

public class ADVendedor {
    //atributos

    private String _mensaje;

    //propiedades
    public String getMensaje() {
        return _mensaje;
    }

    //constructor
    public ADVendedor() {
        _mensaje = "";
    }   
    
    public int Insertar(Vendedor entidad) throws Exception {
        int resultado = -1;
        Connection _conexion = null;
        try {
            _conexion = ClaseConexion.getConnection();
           PreparedStatement ps = _conexion.prepareStatement("INSERT INTO VENDEDORES (NOMBRE_COMPLETO,CEDULA,CORREO_ELECTRONICO) VALUES (?,?,?)");
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getCedula());
            ps.setString(3, entidad.getCorreo());              
            resultado = ps.executeUpdate();
        } catch (Exception ex) {
            //resultado = -1;
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }

            /*
                NOTA: en estos casos este finally en realidad no se ocupa, porque
                la conexión es una VARIABLE LOCAL dentro del método, entonces
                al finalizar el método se destruye la variable automáticamente. 
                Es decir la conexión se cierra automáticamente al cerrarse finalizar
                el método. Pero la cerramos por buenas costumbres! 
           */
            
        }
        return resultado;        
    }//insertar
    
    
    /*
        MÉTODO 2: Modificar _________________________________________
        Recibe: un objeto tipo CLIENTE
        Retorna: un entero (la cantidad de registros (filas) afectados)
     */
    // No utiliza procedimientos almacenados:
    public int Modificar(Vendedor entidad) throws Exception {
        int resultado = -1;
        Connection _conexion = null;
        try {
            _conexion = ClaseConexion.getConnection();
            PreparedStatement ps = _conexion.prepareStatement("UPDATE VENDEDORES SET NOMBRE_COMPLETO=?, CEDULA=?, CORREO_ELECTRONICO=? WHERE ID_VENDEDOR = ?");

            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getCedula());
             ps.setString(3, entidad.getCorreo());
            ps.setInt(4, entidad.getId());
            resultado = ps.executeUpdate();
        } catch (Exception ex) {
            //resultado = -1;
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }

            /*
                NOTA: en estos casos este finally en realidad no se ocupa, porque
                la conexión es una VARIABLE LOCAL dentro del método, entonces
                al finalizar el método se destruye la variable automáticamente. 
                Es decir la conexión se cierra automáticamente al cerrarse finalizar
                el método. Pero la cerramos por buenas costumbres! 
             */
            
        }
        return resultado;
    }//Modificar
    
    
     /*
        MÉTODO 3: Eliminar _________________________________________
        Recibe: un objeto tipo CLIENTE
        Retorna: un entero (la cantidad de registros (filas) afectados)
     */
    // Utilizando un procedimiento almacenado
    /*
        IMPORTANTE: En este caso el SP se encarga de verificar por ejemplo que el entidad
        que se desea eliminar NO tenga factura asociadas. Este tipo de lógica del negocio
        deberíamos programarla aquí en código Java, en caso que no tuviéramos un SP
        que verificara todo eso. Es más FÁCIL manejarlo con SP en la BD, porque desde 
        código Java tendríamos que manejar objetos de cada tabla para hacer las 
        comprobaciones, lo cual consume más tiempo de desarrollo. Aunque cada 
        persona decide cómo le parece mejor crear sus soluciones, o bien la forma 
        en que lo maneje cada empresa de desarrollo. 
     */
    public int Eliminar(Vendedor entidad) throws Exception {
        CallableStatement cs = null;//Se usa CallableStatement porque estoy "llamando" a un procedimiento almacenado
        int resultado = -1;
        Connection _conexion = null;
        try {
            _conexion = ClaseConexion.getConnection();

            cs = _conexion.prepareCall("{call ELIMINAR_VENDEDOR(?,?)}");

            // 1) Registrar los parámetros
            cs.setInt(1, entidad.getId());
            cs.setString(2, _mensaje);

            // 2) Registrar los parámetros de SALIDA
            cs.registerOutParameter(2, Types.VARCHAR);

            // 3) Ejecutar
            resultado = cs.executeUpdate();

            // 4) Leer los parámetros de Salida
            _mensaje = cs.getString(2);

//            if(resultado>0){
//                _Mensaje="Cliente Insertado con éxito";
//            }

            /*
           En este caso el _mensaje preferimos RECUPERARLO directamente del SP de la BD
           porque podría ser que indique que no se puede eliminar el entidad porque
           tiene facturas asociadas, u otro mensaje de la BD. 
             */
            
        }//try
        catch (Exception ex) {
            //resultado = -1;
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return resultado;
    }//Eliminar
    
    
     /*
        MÉTODO 5: Listar Registros
        Recibe: condición (String)
        Retorna: un LISTA de objetos entidad
     */
    public List<Vendedor> ListarRegistros(String Condicion) throws Exception {
        ResultSet rs = null;
        Vendedor entidad;
        List<Vendedor> lista = new ArrayList<>();
        Connection _conexion = null;
        try {
            // Abrir la conexión:
            _conexion = ClaseConexion.getConnection();

            Statement st = _conexion.createStatement();
            String Sentencia;

            Sentencia = "SELECT ID_VENDEDOR,NOMBRE_COMPLETO,CEDULA,CORREO_ELECTRONICO FROM VENDEDORES";

            if (!Condicion.equals("")) {
                Sentencia = String.format("%S WHERE %S", Sentencia, Condicion);
            }

            rs = st.executeQuery(Sentencia);

            // Recorremos el ResultSet agregando cada Registro a la Lista
            while (rs.next()) {
                entidad = new Vendedor(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4));
                lista.add(entidad);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return lista;
    }
    
     /*
        MÉTODO 6: Obtener registro _____________________
        Recibe: condición (String)
        Retorna: un objeto CLIENTE
     */
    public Vendedor ObtenerRegistro(String Condicion) throws Exception {

        // La idea es que la Condición que ingresa permita conseguir un registro único y no varios!
        // Por tanto al llamar a este método debemos garantizar que SIEMPRE le enviemos en la condición
        // el ID de un entidad. 
        
        ResultSet rs = null;
        Vendedor entidad = new Vendedor();
        String sentencia;
        Connection _conexion = null;
        try {
            _conexion = ClaseConexion.getConnection();
            Statement st = _conexion.createStatement();

            sentencia = "SELECT ID_VENDEDOR,NOMBRE_COMPLETO,CEDULA,CORREO_ELECTRONICO FROM VENDEDORES";
            if (!Condicion.equals("")) {
                sentencia = String.format("%S WHERE %S", sentencia, Condicion);
            }
            rs = st.executeQuery(sentencia);

            // Con If y no While, por si esta consulta hubiera retornado varios registros, 
            // lee solamene uno (el primero).
            // En teoría eso nunca va a pasar si en la condición el método recibe el ID de un entidad
            if (rs.next()) {
                entidad.setId(rs.getInt(1));
                entidad.setNombre(rs.getString(2));
                entidad.setCedula(rs.getString(3));
                entidad.setCorreo(rs.getString(4));
                entidad.setExiste(true);
            } else {
                entidad.setExiste(false);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return entidad;
    }
    
}
