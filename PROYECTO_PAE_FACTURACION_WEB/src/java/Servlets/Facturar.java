package Servlets;

import Capa_Entidades.Detalle_Ventas;
import Capa_Entidades.Ventas;
import Capa2_LogicaNegocio.LNVentas;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

@WebServlet("/Facturar")
public class Facturar extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            LNVentas LogicaFactura = new LNVentas();
            Ventas EntidadFactura = new Ventas();
             Detalle_Ventas EntidadDetalle = new Detalle_Ventas();
            int resultado;
            String mensaje="";
            //crear entidad de factura
            if(request.getParameter("txtNombreCliente")!=null &&
                    !request.getParameter("txtNombreCliente").equals("")){
                
                EntidadFactura.setId(Integer.parseInt(request.getParameter("txtnumFactura")));
                
                EntidadFactura.setMetodoPago(request.getParameter("txtmetodoPago"));
                
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");                
                String fechaString = request.getParameter("txtFechaFactura");                
                Date fecha = formato.parse(fechaString);                
                java.sql.Date fechasql = new java.sql.Date(fecha.getTime());                
                EntidadFactura.setFecha(fechasql);
                
                EntidadFactura.setId_Cliente(Integer.parseInt(request.getParameter("txtIdCliente")));
                
                EntidadFactura.setId_Vendedor(Integer.parseInt(request.getParameter("txtIdVendedor")));
                
                EntidadFactura.setTotal(Float.parseFloat(request.getParameter("txtTotal")));         
                
                EntidadFactura.setEstado("Pendiente");
                
                if(!(request.getParameter("txtdescripcion").equals("")) &&
                        !(request.getParameter("txtcantidad").equals("")) &&
                        !(request.getParameter("txtprecio").equals(""))){
                    
                    //crear entidad de detalle
                    EntidadDetalle.setId_Venta(-1);
                    EntidadDetalle.setId_Producto(Integer.parseInt(request.getParameter("txtIdProducto")));
                    EntidadDetalle.setCantidad(Integer.parseInt(request.getParameter("txtcantidad")));
                    EntidadDetalle.setPrecio(Float.parseFloat(request.getParameter("txtprecio")));
                    resultado = LogicaFactura.Insertar(EntidadFactura, EntidadDetalle);
                    mensaje = LogicaFactura.getMensaje();
                }else{
                   mensaje = "Seleccione un producto";
                   resultado = LogicaFactura.ModificarCliente(EntidadFactura);
                }
                
                response.sendRedirect("Frm_Facturar.jsp?msgFac=" + mensaje + "&txtnumFactura=" + resultado);
                //response.sendRedirect("Frm_Facturar.jsp?txtnumFactura="+resultado);
            }else{
                response.sendRedirect("Frm_Facturar.jsp?txtnumFactura="+
                        request.getParameter("txtnumFactura"));
            }
        }catch(Exception e){
            out.print(e.getMessage());
        }
    }//fin servlets

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
