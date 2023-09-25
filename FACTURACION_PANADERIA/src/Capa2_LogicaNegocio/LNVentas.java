package Capa2_LogicaNegocio;

import Capa_Entidades.Ventas;
import Capa3_AccesoDatos.ADVentas;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LNVentas {
    
     //atributos
    private String _mensaje;

    // metodo de acceso get
    public String getMensaje() {
        return _mensaje;
    }

    //llamar al metodo de insertar cliente de la capa de acceso de datos
    public int Insertar(Ventas ventas) throws Exception {
        int id = -1;
        ADVentas advendedor;
        try {
            advendedor = new ADVentas();
            id = advendedor.Insertar(ventas);
            _mensaje = advendedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }
    
    public List<Ventas> ListarRegistros(String condicion) throws Exception {
        List<Ventas> resultado = new ArrayList();
        ADVentas advendedor;
        try {
            advendedor = new ADVentas();
            resultado = advendedor.ListarRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    public Ventas ObtenerRegistro(String condicion) throws Exception {
        Ventas resultado;
        ADVentas advendedor;
        try {
            advendedor = new ADVentas();
            resultado = advendedor.ObtenerRegistro(condicion);
            if (resultado.isExiste()) {
                _mensaje = "Recuperado exitosamente";
            } else {
                _mensaje = "El registro no existe";
            }
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    public int Modificar(Ventas ventas) throws Exception {
        int resultado = -1;
        ADVentas advendedor;
        try {
            advendedor = new ADVentas();
            resultado = advendedor.Modificar(ventas);
            _mensaje = advendedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    public int Eliminar(Ventas ventas) throws Exception {
        int resultado = -1;
        ADVentas advendedor;
        try {
            advendedor = new ADVentas();
            resultado = advendedor.Eliminar(ventas);
            _mensaje = advendedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
}
