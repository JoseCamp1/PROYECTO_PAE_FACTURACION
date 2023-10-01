package Capa3_AccesoDatos;

import Capa_Entidades.Compras;
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

public class ADCompras {
    //atributos
    private Connection _cnn;
    private String _mensaje;
    
     //constructor
    public ADCompras() throws Exception{
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
    public int Insertar(Compras compras) throws Exception {
        int id = -1;
        String sentencia = "INSERT INTO COMPRAS(FECHA,PROVEEDOR,TOTAL) VALUES (?,?,?)";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, compras.getFecha());
            ps.setString(2, compras.getProveedor());                     
            ps.setFloat(3, compras.getTotal());
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
    public List<Compras> ListarRegistros(String condicion) throws SQLException {
        ResultSet rs = null;
        List<Compras> lista = new ArrayList();
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT ID_COMPRA,FECHA,PROVEEDOR,TOTAL FROM COMPRAS";
            if (!condicion.equals((""))) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Compras(
                        rs.getInt("ID_COMPRA"),
                        rs.getString("FECHA"),
                        rs.getString("PROVEEDOR"),
                        rs.getFloat("TOTAL")
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
    public Compras ObtenerRegistro(String condicion) throws SQLException{
        Compras compras =new Compras();       
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT ID_COMPRA,FECHA,PROVEEDOR,TOTAL FROM COMPRAS";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia,condicion);                
            }
            rs = stm.executeQuery(sentencia);
            if (rs.next()) {
                compras.setId(rs.getInt(1));
                compras.setFecha(rs.getString(2));
                compras.setProveedor(rs.getString(3));                           
                compras.setTotal(rs.getFloat(4));                
                compras.setExiste(true);                
            }
        } catch (Exception e) {
            throw e;
        }
        finally{
            _cnn=null;
        }
        return compras;
    }
    
         //-----------------------------------------------------------------------------------------------------------------------------------
    public int Modificar(Compras compras) throws Exception {
        int resultado = 0;
        String sentencia = "UPDATE COMPRAS SET FECHA=?,PROVEEDOR=?,TOTAL=? WHERE ID_COMPRA=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setString(1, compras.getFecha());
            ps.setString(2, compras.getProveedor());                     
            ps.setFloat(3, compras.getTotal());
            ps.setInt(4, compras.getId());
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
    public int Eliminar(Compras compras) throws Exception {
        int resultado = 0;
        String sentencia = "DELETE COMPRAS WHERE ID_COMPRA=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, compras.getId());
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
