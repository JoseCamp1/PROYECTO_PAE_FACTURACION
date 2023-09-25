package Capa3_AccesoDatos;

import Capa_Entidades.Ventas;
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

public class ADVentas {
     //atributos
    private Connection _cnn;
    private String _mensaje;
    
     //constructor
    public ADVentas() throws Exception{
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
    public int Insertar(Ventas ventas) throws Exception {
        int id_cliente = -1;
        String sentencia = "INSERT INTO VENTAS(METODOPAGO,FECHA,ID_CLIENTE,ID_VENDEDOR,TOTAL) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ventas.getMetodoPago());
            ps.setDate(2, ventas.getFecha());
            ps.setInt(3, ventas.getId_Cliente());
            ps.setInt(4, ventas.getId_Vendedor());
            ps.setFloat(5, ventas.getTotal());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id_cliente = rs.getInt(1);
                _mensaje = "Ingresado satisfactoriamente";
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return id_cliente;
    }   
    
    //-----------------------------------------------------------------------------------------------------------------------------------
     public List<Ventas> ListarRegistros(String condicion) throws SQLException {
        ResultSet rs = null;
        List<Ventas> lista = new ArrayList();
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT ID_VENTA,METODOPAGO,FECHA,ID_CLIENTE,ID_VENDEDOR,TOTAL FROM VENTAS";
            if (!condicion.equals((""))) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Ventas(rs.getInt("ID_VENTA"), rs.getString("METODOPAGO"),rs.getDate("FECHA"),rs.getInt("ID_CLIENTE"),rs.getInt("ID_VENDEDOR"),rs.getFloat("TOTAL")));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return lista;
    }
     
     //-----------------------------------------------------------------------------------------------------------------------------------
    public Ventas ObtenerRegistro(String condicion) throws SQLException{
        Ventas ventas =new Ventas();       
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT ID_VENTA,METODOPAGO,FECHA,ID_CLIENTE,ID_VENDEDOR,TOTAL FROM VENTAS";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia,condicion);                
            }
            rs = stm.executeQuery(sentencia);
            if (rs.next()) {
                ventas.setId(rs.getInt(1));
                ventas.setMetodoPago(rs.getString(2));
                ventas.setFecha(rs.getDate(3));
                ventas.setId_Cliente(rs.getInt(4));             
                ventas.setId_Vendedor(rs.getInt(5));
                ventas.setTotal(rs.getFloat(6));
                ventas.setExiste(true);                
            }
        } catch (Exception e) {
            throw e;
        }
        finally{
            _cnn=null;
        }
        return ventas;
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------------
    public int Modificar(Ventas ventas) throws Exception {
        int resultado = 0;
        String sentencia = "UPDATE VENTAS SET METODOPAGO=?,FECHA=?,ID_CLIENTE=?,ID_VENDEDOR=?,TOTAL=? WHERE ID_VENTA=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setString(1, ventas.getMetodoPago());
            ps.setDate(2, ventas.getFecha());            
            ps.setInt(3, ventas.getId_Cliente());
            ps.setInt(4, ventas.getId_Vendedor());
            ps.setFloat(5, ventas.getTotal());
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
    public int Eliminar(Ventas ventas) throws Exception {
        int resultado = 0;
        String sentencia = "DELETE VENTAS WHERE ID_VENTA=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, ventas.getId());
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
