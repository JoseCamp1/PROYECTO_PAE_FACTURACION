package Capa2_LogicaNegocio;

import Capa_Entidades.Compras;
import Capa3_AccesoDatos.ADCompras;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LNCompras {
    
    // atributos
    private String _mensaje;

    // método de acceso get
    public String getMensaje() {
        return _mensaje;
    }

    // llamar al método de insertar cliente de la capa de acceso de datos
    public int Insertar(Compras compras) throws Exception {
        int id = -1;
        ADCompras adcompras;
        try {
            adcompras = new ADCompras();
            id = adcompras.Insertar(compras);
            _mensaje = adcompras.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }
    
    public List<Compras> ListarRegistros(String condicion) throws Exception {
        List<Compras> resultado = new ArrayList();
        ADCompras adcompras;
        try {
            adcompras = new ADCompras();
            resultado = adcompras.ListarRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    public Compras ObtenerRegistro(String condicion) throws Exception {
        Compras resultado;
        ADCompras adcompras;
        try {
            adcompras = new ADCompras();
            resultado = adcompras.ObtenerRegistro(condicion);
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

    public int Modificar(Compras compras) throws Exception {
        int resultado = -1;
        ADCompras adcompras;
        try {
            adcompras = new ADCompras();
            resultado = adcompras.Modificar(compras);
            _mensaje = adcompras.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    public int Eliminar(Compras compras) throws Exception {
        int resultado = -1;
        ADCompras adcompras;
        try {
            adcompras = new ADCompras();
            resultado = adcompras.Eliminar(compras);
            _mensaje = adcompras.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
}

