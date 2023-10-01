package Capa3_AccesoDatos;

import Capa_Entidades.Detalle_Ventas;
import Config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;
import java.util.Date;

public class ADDetalle_Ventas {
    //atributos
    private Connection _cnn;
    private String _mensaje;
    
     //constructor
    public ADDetalle_Ventas() throws Exception{
        try {
            String url = Config.getConnectionString();            
            _cnn = DriverManager.getConnection(url);
            _mensaje="";
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }        
    }
    
    //getter
    public String getMensaje(){
        return _mensaje;
    }
    
    // metodos
    
    //-----------------------------------------------------------------------------------------------------------------------------------
    public int Insertar(Detalle_Ventas detalle_Ventas) throws Exception {
        int id = -1;
        String sentencia = "INSERT INTO DETALLE_VENTAS(ID_VENTA,ID_PRODUCTO,CANTIDAD,SUBTOTAL) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, detalle_Ventas.getId_Venta());
            ps.setInt(2, detalle_Ventas.getId_Producto());
            ps.setInt(3, detalle_Ventas.getCantidad());            
            ps.setFloat(4, detalle_Ventas.getSubtotal());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id = rs.getInt(1);
                _mensaje = "Ingresado satisfactoriamente";
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return id;
    }   
    
    //-----------------------------------------------------------------------------------------------------------------------------------
     public List<Detalle_Ventas> ListarRegistros(String condicion) throws SQLException {
        ResultSet rs = null;
        List<Detalle_Ventas> lista = new ArrayList();
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT ID_DETALLE,ID_VENTA,ID_PRODUCTO,CANTIDAD,SUBTOTAL FROM DETALLE_VENTAS";
            if (!condicion.equals((""))) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Detalle_Ventas(
                        rs.getInt("ID_DETALLE"),
                        rs.getInt("ID_VENTA"),
                        rs.getInt("ID_PRODUCTO"),
                        rs.getInt("CANTIDAD"),
                        rs.getFloat("SUBTOTAL")
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return lista;
    }
     
     //-----------------------------------------------------------------------------------------------------------------------------------
    public Detalle_Ventas ObtenerRegistro(String condicion) throws SQLException{
        Detalle_Ventas detalle_Ventas =new Detalle_Ventas();       
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT ID_DETALLE,ID_VENTA,ID_PRODUCTO,CANTIDAD,SUBTOTAL FROM DETALLE_VENTAS";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia,condicion);                
            }
            rs = stm.executeQuery(sentencia);
            if (rs.next()) {
                detalle_Ventas.setId(rs.getInt(1));
                detalle_Ventas.setId_Venta(rs.getInt(2));
                detalle_Ventas.setId_Producto(rs.getInt(3));
                detalle_Ventas.setCantidad(rs.getInt(4));             
                detalle_Ventas.setSubtotal(rs.getFloat(5));                
                detalle_Ventas.setExiste(true);                
            }
        } catch (Exception e) {
            throw e;
        }
        finally{
            _cnn=null;
        }
        return detalle_Ventas;
    }
    
     //-----------------------------------------------------------------------------------------------------------------------------------
    public int Modificar(Detalle_Ventas detalle_Ventas) throws Exception {
        int resultado = 0;
        String sentencia = "UPDATE DETALLE_VENTAS SET ID_VENTA=?,ID_PRODUCTO=?,CANTIDAD=?,SUBTOTAL=? WHERE ID_DETALLE=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, detalle_Ventas.getId_Venta());
            ps.setInt(2, detalle_Ventas.getId_Producto());            
            ps.setInt(3, detalle_Ventas.getCantidad());            
            ps.setFloat(4, detalle_Ventas.getSubtotal());
            ps.setInt(5, detalle_Ventas.getId());
            resultado = ps.executeUpdate();
            if (resultado > 0) {
                _mensaje = "Registro modificado satisfactoriamente";
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return resultado;
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------------
    public int Eliminar(Detalle_Ventas detalle_Ventas) throws Exception {
        int resultado = 0;
        String sentencia = "DELETE DETALLE_VENTAS WHERE ID_DETALLE=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, detalle_Ventas.getId());
            resultado = ps.executeUpdate();
            if (resultado > 0) {
                _mensaje = "Registro Eliminado satisfactoriamente";
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return resultado;
    }
    //-----------------------------------------------------------------------------------------------------------------------------------
    
}
