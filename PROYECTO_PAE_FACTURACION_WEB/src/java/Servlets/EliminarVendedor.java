package Servlets;

import Capa_Entidades.Vendedor;
import Capa2_LogicaNegocio.LNVendedor;
import java.io.*; // Se necesita para crear una variable OUT y poder imprimir en pantalla
import java.net.URLEncoder; // Para hacer codificación de caracteres 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Definir la RUTA de acceso al Servlet dentro de la aplicación:
@WebServlet("/EliminarVendedor") 

public class EliminarVendedor extends HttpServlet {
    //  Sobrescribimos el método doGet, para asignarle un comportamiento
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
         response.setContentType("text/html;charset=UTF-8");  
        /* indica el tipo de salida, es importante cuando vamos a generar HTML
         dentro del Servlet*/
        
        PrintWriter out = response.getWriter(); // para poder escribir en el HTML
        
        try{
            LNVendedor logica = new LNVendedor();
            
            String id = request.getParameter("idEliminar"); 
            // obtiene el parámetro del QUERY STRING 
            // SIEMPRE retorna un String
            
            int codigo = Integer.parseInt(id);
            Vendedor entidad = new Vendedor();
            entidad.setId(codigo);
            
            int resultado = logica.Eliminar(entidad);
            /*  Si creáramos un método que elimine y sólo reciba un int, nos ahorraríamos
                crear la entidad. Pero en este ejemplo sólo tenemos un método que 
                eliminar que recibe una entidad.             
            */
            
            String mensaje = logica.getMensaje();
            // Obtenemos el mensaje que viene desde el SP
            
            mensaje = URLEncoder.encode(mensaje,"UTF-8");
            /*  Al mensaje le hacemos la codificación de caracteres porque 
                podría traer caracteres especiales que no se pueden escribir
                en el HTML
            */
            
            // Utilizamos el objeto intrínseco RESPONSE para responder:
            // Redireccionando a la página FrmListarClientes y 
            // enviando por parámetro el mensaje. 
            response.sendRedirect("Frm_ListarVendedores.jsp?mensajeServletEliminarVendedor=" + mensaje + "&resultado=" + resultado);
        }
        catch(Exception ex){
            out.print(ex.getMessage()); // imprime en el HTML
        }
    }
}