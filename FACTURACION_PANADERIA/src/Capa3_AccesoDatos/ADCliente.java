package Capa3_AccesoDatos;

import Capa_Entidades.Cliente;
import Config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ADCliente {
    //atributos
    private Connection _cnn;
    private String _mensaje;
    
    //constructor
    public ADCliente() throws Exception{
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
    //insertar cliente
public int Insertar(Cliente cliente) throws  Exception {
        int id_cliente = -1;
        String sentencia = "INSERT INTO CLIENTES(NOMBRE_COMPLETO,CEDULA) VALUES (?,?)";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCedula());            
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id_cliente = rs.getInt(1);
                _mensaje = "Cliente ingresado satisfactoriamente";

            }
        } catch (SQLException e) {
            throw e;
        } finally {
            _cnn=null;
        }
        return id_cliente;
    }

    private Connection obtenerConexion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     public List<Cliente> ListarRegistros(String condicion) throws SQLException {
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList();
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "select ID_CLIENTE,NOMBRE_COMPLETO,CEDULA from clientes";
            if (!condicion.equals((""))) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Cliente(rs.getInt("ID_CLIENTE"), rs.getString("NOMBRE_COMPLETO"),rs.getString("CEDULA")));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return lista;
    }
    
    public Cliente ObtenerRegistro(String condicion) throws SQLException{
        Cliente cliente =new Cliente();       
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT ID_CLIENTE, NOMBRE_COMPLETO,CEDULA FROM CLIENTES";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia,condicion);                
            }
            rs = stm.executeQuery(sentencia);
            if (rs.next()) {
                cliente.setId(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setCedula(rs.getString(3));                
                cliente.setExiste(true);                
            }
        } catch (Exception e) {
            throw e;
        }
        finally{
            _cnn=null;
        }
        return cliente;
    }
    
    public int Modificar(Cliente cliente) throws Exception {
        int resultado = 0;
        String sentencia = "UPDATE CLIENTES SET NOMBRE_COMPLETO=?,CEDULA=? WHERE ID_CLIENTE=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCedula());            
            ps.setInt(3, cliente.getId());
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
    
    public int Eliminar(Cliente cliente) throws Exception {
        int resultado = 0;
        String sentencia = "DELETE CLIENTES WHERE ID_CLIENTE=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, cliente.getId());
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
    
    
}
