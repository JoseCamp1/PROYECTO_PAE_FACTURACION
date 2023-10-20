//package Capa3_AccesoDatos;
//
//import Capa_Entidades.Detalle_Compras;
//import Config.Config;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.time.Instant;
//import java.util.Date;
//
//public class ADDetalle_Compras {
//    //atributos
//    private Connection _cnn;
//    private String _mensaje;
//    
//     //constructor
//    public ADDetalle_Compras() throws Exception{
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
//    // metodos
//    
//    //-----------------------------------------------------------------------------------------------------------------------------------
//    public int Insertar(Detalle_Compras detalle_Compras) throws Exception {
//        int id = -1;
//        String sentencia = "INSERT INTO DETALLE_COMPRAS(ID_COMPRA,NOMBRE,CANTIDAD,SUBTOTAL) VALUES (?,?,?,?)";
//        try {
//            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, detalle_Compras.getId_Compra());
//            ps.setString(2, detalle_Compras.getNombre());
//            ps.setInt(3, detalle_Compras.getCantidad());            
//            ps.setFloat(4, detalle_Compras.getSubtotal());
//            ps.execute();
//            ResultSet rs = ps.getGeneratedKeys();
//            if (rs != null && rs.next()) {
//                id = rs.getInt(1);
//                _mensaje = "Ingresado satisfactoriamente";
//            }
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            _cnn = null;
//        }
//        return id;
//    }   
//    
//    
//    //-----------------------------------------------------------------------------------------------------------------------------------
//     public List<Detalle_Compras> ListarRegistros(String condicion) throws SQLException {
//        ResultSet rs = null;
//        List<Detalle_Compras> lista = new ArrayList();
//        try {
//            Statement stm = _cnn.createStatement();
//            String sentencia = "SELECT ID_DETALLE,ID_COMPRA,NOMBRE,CANTIDAD,SUBTOTAL FROM DETALLE_COMPRAS";
//            if (!condicion.equals((""))) {
//                sentencia = String.format("%s WHERE %s", sentencia, condicion);
//            }
//            rs = stm.executeQuery(sentencia);
//            while (rs.next()) {
//                lista.add(new Detalle_Compras(
//                        rs.getInt("ID_DETALLE"),
//                        rs.getInt("ID_COMPRA"),
//                        rs.getString("NOMBRE"),
//                        rs.getInt("CANTIDAD"),
//                        rs.getFloat("SUBTOTAL")
//                ));
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            _cnn = null;
//        }
//        return lista;
//    }
//    
//     //-----------------------------------------------------------------------------------------------------------------------------------
//    public Detalle_Compras ObtenerRegistro(String condicion) throws SQLException{
//        Detalle_Compras detalle_Compras =new Detalle_Compras();       
//        ResultSet rs = null;
//        try {
//            Statement stm = _cnn.createStatement();
//            String sentencia = "SELECT ID_DETALLE,ID_COMPRA,NOMBRE,CANTIDAD,SUBTOTAL FROM DETALLE_COMPRAS";
//            if (!condicion.equals("")) {
//                sentencia = String.format("%s WHERE %s", sentencia,condicion);                
//            }
//            rs = stm.executeQuery(sentencia);
//            if (rs.next()) {
//                detalle_Compras.setId(rs.getInt(1));
//                detalle_Compras.setId_Compra(rs.getInt(2));
//                detalle_Compras.setNombre(rs.getString(3));
//                detalle_Compras.setCantidad(rs.getInt(4));             
//                detalle_Compras.setSubtotal(rs.getFloat(5));                
//                detalle_Compras.setExiste(true);                
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//        finally{
//            _cnn=null;
//        }
//        return detalle_Compras;
//    }
//     //-----------------------------------------------------------------------------------------------------------------------------------
//    public int Modificar(Detalle_Compras detalle_Compras) throws Exception {
//        int resultado = 0;
//        String sentencia = "UPDATE DETALLE_COMPRAS SET ID_COMPRA=?,NOMBRE=?,CANTIDAD=?,SUBTOTAL=? WHERE ID_DETALLE=?";
//        try {
//            PreparedStatement ps = _cnn.prepareStatement(sentencia);
//            ps.setInt(1, detalle_Compras.getId_Compra());
//            ps.setString(2, detalle_Compras.getNombre());            
//            ps.setInt(3, detalle_Compras.getCantidad());            
//            ps.setFloat(4, detalle_Compras.getSubtotal());
//            ps.setInt(5, detalle_Compras.getId());
//            resultado = ps.executeUpdate();
//            if (resultado > 0) {
//                _mensaje = "Registro modificado satisfactoriamente";
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            _cnn = null;
//        }
//        return resultado;
//    }
//    //-----------------------------------------------------------------------------------------------------------------------------------
//    public int Eliminar(Detalle_Compras detalle_Compras) throws Exception {
//        int resultado = 0;
//        String sentencia = "DELETE DETALLE_COMPRAS WHERE ID_DETALLE=?";
//        try {
//            PreparedStatement ps = _cnn.prepareStatement(sentencia);
//            ps.setInt(1, detalle_Compras.getId());
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
//    //-----------------------------------------------------------------------------------------------------------------------------------
//    
//}
