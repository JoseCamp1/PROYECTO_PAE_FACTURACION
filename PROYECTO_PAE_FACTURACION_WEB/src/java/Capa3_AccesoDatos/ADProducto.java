//package Capa3_AccesoDatos;
//
//import Config.Config;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import Capa_Entidades.Producto;
//        
//
//public class ADProducto {
//        //atributos
//    private Connection _cnn;
//    private String _mensaje;
//    
//    //constructor
//    public ADProducto() throws Exception{
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
//    public int Insertar(Producto producto) throws Exception {
//        int id_cliente = -1;
//        String sentencia = "INSERT INTO PRODUCTOS(NOMBRE,DESCRIPCION,PRECIO,STOCK) VALUES (?,?,?,?)";
//        try {
//            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, producto.getNombre());
//            ps.setString(2, producto.getDescripcion());
//            ps.setFloat(3, producto.getPrecio());
//            ps.setInt(4, producto.getStock());
//            ps.execute();
//            ResultSet rs = ps.getGeneratedKeys();
//            if (rs != null && rs.next()) {
//                id_cliente = rs.getInt(1);
//                _mensaje = "Ingresado satisfactoriamente";
//
//            }
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            _cnn = null;
//        }
//        return id_cliente;
//    }   
//    //-----------------------------------------------------------------------------------------------------------------------------------
//    public List<Producto> ListarRegistros(String condicion) throws SQLException {
//        ResultSet rs = null;
//        List<Producto> lista = new ArrayList();
//        try {
//            Statement stm = _cnn.createStatement();
//            String sentencia = "SELECT ID_PRODUCTO,NOMBRE,DESCRIPCION,PRECIO,STOCK FROM PRODUCTOS";
//            if (!condicion.equals((""))) {
//                sentencia = String.format("%s WHERE %s", sentencia, condicion);
//            }
//            rs = stm.executeQuery(sentencia);
//            while (rs.next()) {
//                lista.add(new Producto(rs.getInt("ID_PRODUCTO"), rs.getString("NOMBRE"),rs.getString("DESCRIPCION"),rs.getFloat("PRECIO"),rs.getInt("STOCK")));
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            _cnn = null;
//        }
//        return lista;
//    }
//    
//    //-----------------------------------------------------------------------------------------------------------------------------------
//    public Producto ObtenerRegistro(String condicion) throws SQLException{
//        Producto producto =new Producto();       
//        ResultSet rs = null;
//        try {
//            Statement stm = _cnn.createStatement();
//            String sentencia = "SELECT ID_PRODUCTO,NOMBRE,DESCRIPCION,PRECIO,STOCK FROM PRODUCTOS";
//            if (!condicion.equals("")) {
//                sentencia = String.format("%s WHERE %s", sentencia,condicion);                
//            }
//            rs = stm.executeQuery(sentencia);
//            if (rs.next()) {
//                producto.setId(rs.getInt(1));
//                producto.setNombre(rs.getString(2));
//                producto.setDescripcion(rs.getString(3));   
//                producto.setPrecio(rs.getFloat(4));             
//                producto.setStock(rs.getInt(5));                
//                producto.setExiste(true);                
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//        finally{
//            _cnn=null;
//        }
//        return producto;
//    }
//    
//     //-----------------------------------------------------------------------------------------------------------------------------------
//    public int Modificar(Producto producto) throws Exception {
//        int resultado = 0;
//        String sentencia = "UPDATE PRODUCTOS SET NOMBRE=?,DESCRIPCION=?,PRECIO=?,STOCK=? WHERE ID_PRODUCTO=?";
//        try {
//            PreparedStatement ps = _cnn.prepareStatement(sentencia);
//            ps.setString(1, producto.getNombre());
//            ps.setString(2, producto.getDescripcion());            
//            ps.setFloat(3, producto.getPrecio());
//            ps.setInt(4, producto.getStock());    
//            ps.setInt(5, producto.getId());
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
//    
//    
//    //-----------------------------------------------------------------------------------------------------------------------------------
//    public int Eliminar(Producto producto) throws Exception {
//        int resultado = 0;
//        String sentencia = "DELETE PRODUCTOS WHERE ID_PRODUCTO=?";
//        try {
//            PreparedStatement ps = _cnn.prepareStatement(sentencia);
//            ps.setInt(1, producto.getId());
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
//    
//}
//
//
//
//
//
