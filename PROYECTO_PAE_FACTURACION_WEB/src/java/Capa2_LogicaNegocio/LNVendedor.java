package Capa2_LogicaNegocio;

import Capa_Entidades.Vendedor;
import Capa3_AccesoDatos.ADVendedor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LNVendedor {
    
    //atributos
    private String _mensaje;

    // metodo de acceso get
    public String getMensaje() {
        return _mensaje;
    }

    //llamar al metodo de insertar cliente de la capa de acceso de datos
    public int Insertar(Vendedor vendedor) throws Exception {
        int id = -1;
        ADVendedor advendedor;
        try {
            advendedor = new ADVendedor();
            id = advendedor.Insertar(vendedor);
            _mensaje = advendedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }

    public List<Vendedor> ListarRegistros(String condicion) throws Exception {
        List<Vendedor> resultado = new ArrayList();
        ADVendedor advendedor;
        try {
            advendedor = new ADVendedor();
            resultado = advendedor.ListarRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    public Vendedor ObtenerRegistro(String condicion) throws Exception {
        Vendedor resultado;
        ADVendedor advendedor;
        try {
            advendedor = new ADVendedor();
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

    public int Modificar(Vendedor vendedor) throws Exception {
        int resultado = -1;
        ADVendedor advendedor;
        try {
            advendedor = new ADVendedor();
            resultado = advendedor.Modificar(vendedor);
            _mensaje = advendedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    public int Eliminar(Vendedor vendedor) throws Exception {
        int resultado = -1;
        ADVendedor advendedor;
        try {
            advendedor = new ADVendedor();
            resultado = advendedor.Eliminar(vendedor);
            _mensaje = advendedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

}
