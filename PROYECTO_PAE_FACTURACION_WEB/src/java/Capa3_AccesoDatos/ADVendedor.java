//package Capa3_AccesoDatos;
//
//
//import Capa_Entidades.Vendedor;
//import Config.Config;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class ADVendedor {
//     //atributos
//    private Connection _cnn;
//    private String _mensaje;
//    
//    //constructor
//    public ADVendedor() throws Exception{
//        try {
//            String url = Config.getConnectionString();            
//            _cnn = DriverManager.getConnection(url);
//            _mensaje="";
//        } catch (ClassNotFoundException | SQLException e) {
//            throw e;
//        }        
//    }
//    
//    //getter
//    public String getMensaje(){
//        return _mensaje;
//    }
//    
//     // metodos
//    //insertar vendedor
//    public int Insertar(Vendedor vendedor) throws Exception {
//        int id_vendedor = -1;
//        String sentencia = "INSERT INTO VENDEDORES(NOMBRE_COMPLETO,CEDULA,CORREO_ELECTRONICO) VALUES (?,?,?)";
//        try {
//            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, vendedor.getNombre());
//            ps.setString(2, vendedor.getCedula());
//            ps.setString(3, vendedor.getCorreo());
//            ps.execute();
//            ResultSet rs = ps.getGeneratedKeys();
//            if (rs != null && rs.next()) {
//                id_vendedor = rs.getInt(1);
//                _mensaje = "Ingresado satisfactoriamente.";
//
//            }
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            _cnn = null;
//        }
//        return id_vendedor;
//    }
//    
//    public List<Vendedor> ListarRegistros(String condicion) throws SQLException {
//        ResultSet rs = null;
//        List<Vendedor> lista = new ArrayList();
//        try {
//            Statement stm = _cnn.createStatement();
//            String sentencia = "SELECT ID_VENDEDOR,NOMBRE_COMPLETO,CEDULA,CORREO_ELECTRONICO from VENDEDORES";
//            if (!condicion.equals((""))) {
//                sentencia = String.format("%s WHERE %s", sentencia, condicion);
//            }
//            rs = stm.executeQuery(sentencia);
//            while (rs.next()) {
//                lista.add(new Vendedor(rs.getInt("ID_VENDEDOR"), rs.getString("NOMBRE_COMPLETO"),rs.getString("CEDULA"),rs.getString("CORREO_ELECTRONICO")));
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            _cnn = null;
//        }
//        return lista;
//    }
//    
//    public Vendedor ObtenerRegistro(String condicion) throws SQLException{
//        Vendedor vendedor =new Vendedor();       
//        ResultSet rs = null;
//        try {
//            Statement stm = _cnn.createStatement();
//            String sentencia = "SELECT ID_VENDEDOR, NOMBRE_COMPLETO,CEDULA,CORREO_ELECTRONICO FROM VENDEDORES";
//            if (!condicion.equals("")) {
//                sentencia = String.format("%s WHERE %s", sentencia,condicion);                
//            }
//            rs = stm.executeQuery(sentencia);
//            if (rs.next()) {
//                vendedor.setId(rs.getInt(1));
//                vendedor.setNombre(rs.getString(2));
//                vendedor.setCorreo(rs.getString(4));                 
//                vendedor.setCedula(rs.getString(3));
//                vendedor.setCorreo(rs.getString(4));                 
//                vendedor.setExiste(true);                
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//        finally{
//            _cnn=null;
//        }
//        return vendedor;
//    }
//    
//    public int Modificar(Vendedor vendedor) throws Exception {
//        int resultado = 0;
//        String sentencia = "UPDATE VENDEDORES SET NOMBRE_COMPLETO=?,CEDULA=?,CORREO_ELECTRONICO=? WHERE ID_VENDEDOR=?";
//        try {
//            PreparedStatement ps = _cnn.prepareStatement(sentencia);
//            ps.setString(1, vendedor.getNombre());
//            ps.setString(2, vendedor.getCedula());  
//            ps.setString(3, vendedor.getCorreo());
//            ps.setInt(4, vendedor.getId());
//            resultado = ps.executeUpdate();
//            if (resultado > 0) {
//                _mensaje = "Registro modificado satisfactoriamente.";
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            _cnn = null;
//        }
//        return resultado;
//    }
//    
//    public int Eliminar(Vendedor vendedor) throws Exception {
//        int resultado = 0;
//        String sentencia = "DELETE VENDEDORES WHERE ID_VENDEDOR=?";
//        try {
//            PreparedStatement ps = _cnn.prepareStatement(sentencia);
//            ps.setInt(1, vendedor.getId());
//            resultado = ps.executeUpdate();
//            if (resultado > 0) {
//                _mensaje = "Registro Eliminado satisfactoriamente";
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            _cnn = null;
//        }
//        return resultado;
//    }    
//    
//}
