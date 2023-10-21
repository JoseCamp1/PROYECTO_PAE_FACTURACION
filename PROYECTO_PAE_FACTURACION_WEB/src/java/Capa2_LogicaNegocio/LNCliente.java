package Capa2_LogicaNegocio;

import Capa_Entidades.Cliente;
import Capa3_AccesoDatos.ADCliente;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LNCliente {
    // ATRIBUTOS __________________________

    private String _mensaje;

    // PROPIEDADES ________________________
    public String getMensaje() {
        return _mensaje;
    }

    // CONSTRUCTOR
    public LNCliente() {
        _mensaje = "";
    }

    /*
        MÉTODO 1: Insertar un registro en la base de datos
        Recibe: un objeto tipo CLIENTE
        Retorna: un entero (la cantidad de registros afectacos)
     */
    public int Insertar(Cliente cliente) throws Exception {
        int resultado = 0;
        // Llamar a la capa de acceso a datos:
        ADCliente accesoDatos = new ADCliente();

        try {
            resultado = accesoDatos.Insertar(cliente);
            _mensaje = accesoDatos.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    /*
        MÉTODO 2: Modificar _________________________________________
        Recibe: un objeto tipo CLIENTE
        Retorna: un entero (la cantidad de registros (filas) afectados)
     */
    public int Modificar(Cliente cliente) throws Exception {
        int resultado = 0;
        ADCliente accesoDatos = new ADCliente();

        try {
            resultado = accesoDatos.Modificar(cliente);
            _mensaje = accesoDatos.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    /*
        MÉTODO 3: Eliminar _________________________________________
        Recibe: un objeto tipo CLIENTE
        Retorna: un entero (la cantidad de registros (filas) afectados)
     */
    public int Eliminar(Cliente cliente) throws Exception {
        int resultado = 0;
        ADCliente accesoDatos = new ADCliente();

        try {
            resultado = accesoDatos.Eliminar(cliente);
            _mensaje = accesoDatos.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    /*
        MÉTODO 4: Listar Registros
        Recibe: condición (String), orden (String)
        Retorna: un RESULTSET
     */
    public ResultSet ListarRegistros(String condicion, String orden) throws Exception {
        ResultSet resultado;
        ADCliente accesoDatos = new ADCliente();

        try {
            resultado = accesoDatos.ListarRegistros(condicion, orden);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    /*
        MÉTODO 5: Listar Registros
        Recibe: condición (String)
        Retorna: un LISTA de objetos cliente
     */
    public List<Cliente> ListarRegistros(String condicion) throws Exception {
        List<Cliente> resultado = new ArrayList();
        ADCliente accesoDatos = new ADCliente();

        try {
            resultado = accesoDatos.ListarRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    /*
        MÉTODO 6: Obtener registro _____________________
        Recibe: condición (String)
        Retorna: un objeto CLIENTE
     */
    public Cliente ObtenerRegistro(String condicion) throws Exception {
        Cliente cliente;
        ADCliente accesoDatos = new ADCliente();;

        try {
            cliente = accesoDatos.ObtenerRegistro(condicion);

            if (cliente.isExiste()) {
                _mensaje = "Cliente recuperado satisfactoriamente";
            } else {
                _mensaje = "El cliente no existe";
            }

        } catch (Exception e) {
            throw e;
        }

        return cliente;
    }

} // clase
