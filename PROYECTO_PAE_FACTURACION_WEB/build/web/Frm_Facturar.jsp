<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Capa2_LogicaNegocio.*"%>
<%@page import="Capa_Entidades.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/DataTables/datatables.min.css" rel="stylesheet" type="text/css"/>

        <title>Facturacion</title>
    </head>
    <body>
        
        <header>
            <nav class="navbar navbar-expand-sm navbar-toggleable-sm navbar-light bg-white border-bottom box-shadow mb-3">
                <div class="container">
                    <a class="navbar-brand" href="index.html">Sistema Facturación <i class="fas fa-tasks"></i></a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".navbar-collapse" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="navbar-collapse collapse d-sm-inline-flex flex-sm-row-reverse">
                        <ul class="navbar-nav flex-grow-1">
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="index.html">Inicio</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="Frm_ListarProductos.jsp">Productos</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="Frm_ListarClientes.jsp">Clientes</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="Frm_ListarFacturas.jsp">Facturación</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        
        <div class="container">
            <div class="row">
                <div class="col-10"><h1>Facturación</h1></div>

            </div>

            <%
                int numFactura = -1;
                double total = 0;
                Ventas EntidadFactura;
                LNVentas logicaFactura = new LNVentas();
                LNDetalle_Ventas logicaDetalle = new LNDetalle_Ventas();
                List<Detalle_Ventas> DatosDetalles = null;
                if (request.getParameter("txtnumFactura") != null && Integer.parseInt(request.getParameter("txtnumFactura")) != -1) {
                    numFactura = Integer.parseInt(request.getParameter("txtnumFactura"));
                    EntidadFactura = logicaFactura.ObtenerRegistro("ID_VENTA=" + numFactura);
                    DatosDetalles = logicaDetalle.ListarRegistros("ID_VENTA=" + numFactura);
                } else {
                    EntidadFactura = new Ventas();
                    EntidadFactura.setId(-1);
                    Date fecha = new Date();
                    java.sql.Date fechasql = new java.sql.Date(fecha.getTime());
                    EntidadFactura.setFecha(fechasql);
                }
                if(EntidadFactura.getId()==0){
                    EntidadFactura.setId(-1);
                    Date fecha = new Date();
                    java.sql.Date fechasql = new java.sql.Date(fecha.getTime());
                    EntidadFactura.setFecha(fechasql);
                }
            %>
            <br/>
            <form action="Facturar" method="post">
                <div class="form-group float-right">

                    <div class="input-group">
                        <label for="txtnumFactura" class="form-control">Num. Factura</label>
                        <input type="text" id="txtnumFactura" name="txtnumFactura" value="<%=EntidadFactura.getId()%>" 
                               readonly class="form-control"/>
                    </div>

                    <div class="input-group">
                        <label for="txtFechaFactura" class="form-control">Fecha</label>
                        <input type="text" id="txtFechaFactura" name="txtFechaFactura" value="<%=EntidadFactura.getFecha()%>"
                               required class="datepicker form-control"/>
                    </div>

                </div>
                               
                <br/>
                
                <div class="form-group">
                    <div class="input-group">
                        <input type="hidden" id="txtIdCliente" name="txtIdCliente" value="<%=EntidadFactura.getId_Cliente()%>"
                               readonly="" class="form-control"/>
                        
                        <input type="text" id="txtNombreCliente" name="txtNombreCliente" 
                               value="<%=EntidadFactura.getNombreCliente()%>" readonly="" class="form-control"
                               placeholder="Seleccione un cliente"/>&nbsp;&nbsp;
                        
                        <a id="btnbuscar" class="btn btn-success" data-toggle="modal"
                                       data-target="#buscarCliente"><i class="fas fa-search"></i></a>
                    </div>
                </div>
                               
                 <div class="form-group">
                    <div class="input-group">
                        <input type="hidden" id="txtIdVendedor" name="txtIdVendedor" value="<%=EntidadFactura.getId_Vendedor()%>"
                               readonly="" class="form-control"/>
                        
                        <input type="text" id="txtNombreVendedor" name="txtNombreVendedor" 
                               value="<%=EntidadFactura.getNombreVendedor()%>" readonly="" class="form-control"
                               placeholder="Seleccione un Vendedor"/>&nbsp;&nbsp;
                        
                        <a id="btnbuscar" class="btn btn-success" data-toggle="modal"
                                       data-target="#buscarVendedor"><i class="fas fa-search"></i></a>
                    </div>
                               
                </div>              
                               
                <hr/> <!-- Inicia el detalle de factura -->
                <div class="form-group">
                    <div class="input-group">
                        
                        <input type="hidden" id="txtIdProducto" name="txtIdProducto" value="" readonly="" class="form-control"/>
                        
                        <input type="text" id="txtdescripcion" name="txtdescripcion" value="" class="form-control" readonly
                               placeholder="Seleccione un producto"/> &nbsp;&nbsp;
                        
                        <a id="btnBuscarP" class="btn btn-success" data-toggle="modal" data-target="#buscarProducto">
                            <i class="fas fa-search"></i></a>&nbsp;&nbsp;
                            
                        <input type="number" id="txtcantidad" name="txtcantidad" value="" class="form-control" 
                               placeholder="Cantidad"/> &nbsp;&nbsp;
                        
                        <input type="number" id="txtprecio" readonly = "true" name="txtprecio" value="" class="form-control" 
                               placeholder="Precio"/> &nbsp;&nbsp;

                        <input type="number" id="txtexistencia"  readonly name="txtexistencia" value="" class="form-control" 
                               placeholder="Existencia"/> 
                        
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <input type="submit" name="Guardar" id="BtnGuardar" value="Agregar y Guardar" class="btn btn-primary"/>
                </div>
            </form>
            <hr/>
            <!-- Mostrar detalle de factura -->
            <h5>Detalle de Factura</h5>
            <table id="DetalleFactura" class="table">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Descripción</th>
                        <th>Cantidad</th>
                        <th>Precio</th>
                        <th>Subtotal</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (DatosDetalles != null) {

                            for (Detalle_Ventas registroDetalle : DatosDetalles) {
                    %>
                    <tr>
                        <%
                            int numfactura = registroDetalle.getId_Venta();
                            int codigop = registroDetalle.getId_Producto();

                            //String descripcion = registroDetalle.getNombreProducto(); 
                            // Se require DARLE FORMATO: (sino los caracteres especiales se visualizan mal)
                            String descripcion = new String(registroDetalle.getNombreProducto().getBytes("ISO-8859-1"), "UTF-8");

                            int cantidad = registroDetalle.getCantidad();
                            double precioV = registroDetalle.getPrecio();
                            total += (cantidad * precioV);
                        %>
                        <td><%= codigop%></td>
                        <td><%= descripcion%></td>
                        <td><%= cantidad%></td>
                        <td><%= precioV%></td>
                        <td><%= cantidad * precioV%></td>

                        <td>
                            <a href="EliminarDetalle?idproducto=<%=codigop%>&idfactura=<%=numfactura%>">
                                <i class="fas fa-trash-alt"></i></a>
                        </td>
                    </tr>
                    <%
                            }// cierre de for
                        } // cierre del if
%>
                </tbody>
            </table>
            <div class="float-right" >
                <p class="text-danger h5" >Total = <%= total%> </p>
            </div>
            <br><br>
            <%
                //mensaje generado en  en el servlets facturas
                if (request.getParameter("msgFac") != null) {
                    out.print("<p class='text-danger'>" + new String(request.getParameter("msgFac").getBytes("ISO-8859-1"), "UTF-8") + "</p>");
                }
            %>
            
            <input type="button" id="BtnCancelar" value="Realizar Facturacion"
                   onclick="location.href = 'CancelarFactura?txtnumFactura=' +<%= EntidadFactura.getId()%>"
                   class="btn btn-success"/>
            &nbsp;&nbsp;
            <a href="Frm_ListarFacturas.jsp" class="btn btn-secondary">Regresar</a>

        </div> <!-- container principal -->


        <!-- Modal de clientes -->
        <div class="modal" id="buscarCliente" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="tituloVentaja">Buscar cliente</h5>
                        <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden="true"
                                onclick="Limpiar()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <!-- tabla de clientes -->
                        <table id="tablaClientes">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nombre</th>
                                    <th>Seleccionar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    LNCliente logicaClientes = new LNCliente();
                                    List<Cliente> datosClientes;
                                    datosClientes = logicaClientes.ListarRegistros("");
                                    for (Cliente registroC : datosClientes) {
                                %>
                                <tr>
                                    <%int codigoCliente = registroC.getId();
                                        String nombreCliente = registroC.getNombre();%>
                                    <td><%= codigoCliente%></td>
                                    <td><%= nombreCliente%></td>
                                    <td>
                                        <a href="#" data-dismiss="modal"
                                           onclick="SeleccionarCliente('<%=codigoCliente%>', '<%= nombreCliente%>');">Seleccionar</a>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                            
                    </div> <!-- modal body -->
                    <div class="modal-footer">
                        <button class="btn btn-warning" type="button" data-dismiss="modal" onclick="Limpiar()">
                            Cancelar
                        </button>
                    </div>
                </div> <!-- modal content -->
            </div> <!-- mnodal dialog -->
        </div> <!-- modal -->
        
         <!-- Modal de Vendedores -->
        <div class="modal" id="buscarVendedor" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="tituloVentaja">Buscar Vendedor</h5>
                        <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden="true"
                                onclick="LimpiarVendedor()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <!-- tabla de Vendedor -->
                        <table id="tablaVendedores">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nombre</th>
                                    <th>Seleccionar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    LNVendedor logicaVendedor = new LNVendedor();
                                    List<Vendedor> datosVendedores;
                                    datosVendedores = logicaVendedor.ListarRegistros("");
                                    for (Vendedor registroV : datosVendedores) {
                                %>
                                <tr>
                                    <%int codigoVendedor = registroV.getId();
                                        String nombreVendedor = registroV.getNombre();%>
                                    <td><%= codigoVendedor%></td>
                                    <td><%= nombreVendedor%></td>
                                    <td>
                                        <a href="#" data-dismiss="modal"
                                           onclick="SeleccionarVendedor('<%=codigoVendedor%>', '<%= nombreVendedor%>');">Seleccionar</a>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                            
                    </div> <!-- modal body -->
                    <div class="modal-footer">
                        <button class="btn btn-warning" type="button" data-dismiss="modal" onclick="LimpiarVendedor()">
                            Cancelar
                        </button>
                    </div>
                </div> <!-- modal content -->
            </div> <!-- mnodal dialog -->
        </div> <!-- modal -->

        <!-- Modal de PRODUCTO -->
        <div class="modal" id="buscarProducto" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="tituloVentana">Buscar producto</h5>
                        <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden="true"
                                onclick="LimpiarProducto()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <!-- tabla de PRODUCTO -->
                        <table id="tablaProductos">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Descripción</th>
                                    <th>Precio</th>
                                    <!--<th>Existencia</th>-->
                                    <th>Seleccionar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    LNProducto logicaProductos = new LNProducto();
                                    List<Producto> datosProductos;
                                    datosProductos = logicaProductos.ListarRegistros("");
                                    for (Producto registroP : datosProductos) {
                                %>
                                <tr>
                                    <%int codigoProducto = registroP.getId();
                                        String nombreProducto = registroP.getDescripcion();
                                        //double precioDouble = registroP.getPrecio();
                                        //int precio = (int) precioDouble;
                                        double precio = registroP.getPrecio();
                                        int existencia = registroP.getStock();
                                    %>
                                    <td><%= codigoProducto%></td>
                                    <td><%= nombreProducto%></td>
                                    <td><%= precio%></td>
                                    <!-- <td><%= existencia%></td> -->
                                    <td>
                                        <a href="#" data-dismiss="modal"
                                           onclick="SeleccionarProducto('<%=codigoProducto%>',
                                                           '<%= nombreProducto%>', '<%= precio%>', '<%= existencia%>');">Seleccionar</a>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div> <!-- modal body -->
                    <div class="modal-footer">
                        <button class="btn btn-warning" type="button" data-dismiss="modal" 
                                onclick="LimpiarProducto()">
                            Cancelar
                        </button>
                    </div>
                </div> <!-- modal content -->
            </div> <!-- mnodal dialog -->
        </div> <!-- modal -->

        <!--  Scripts requeridos -->
        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js" type="text/javascript"></script>
        <script src="lib/DataTables/datatables.min.js" type="text/javascript"></script>
        <script src="lib/DataTables/DataTables-1.10.21/js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
       
        <script>

                                    //hacer que la lista de clientes se comporte como un datatable
                                    $(document).ready(function () {
                                        //mostrar calendario
                                        $('.datepicker').datepicker({
                                            format: 'yyyy-mm-dd',
                                            autoclose: true,
                                            language: 'es'
                                        });

                                        $('#tablaClientes').dataTable({
                                            "lengthMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                            "language": {
                                                "info": "Página _PAGE_ de _PAGES_",
                                                "infoEmpty": "No existen Registros disponibles",
                                                "zeroRecords": "No se encuentran registros",
                                                "search": "Buscar",
                                                "infoFiltered": "",
                                                "lengthMenu": "Mostrar _MENU_ Registros",
                                                "paginate": {
                                                    "first": "Primero",
                                                    "last": "Último",
                                                    "next": "Siguiente",
                                                    "previous": "Anterior"
                                                }

                                            }
                                        });
                                        
                                        $('#tablaVendedores').dataTable({
                                            "lengthMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                            "language": {
                                                "info": "Página _PAGE_ de _PAGES_",
                                                "infoEmpty": "No existen Registros disponibles",
                                                "zeroRecords": "No se encuentran registros",
                                                "search": "Buscar",
                                                "infoFiltered": "",
                                                "lengthMenu": "Mostrar _MENU_ Registros",
                                                "paginate": {
                                                    "first": "Primero",
                                                    "last": "Último",
                                                    "next": "Siguiente",
                                                    "previous": "Anterior"
                                                }

                                            }
                                        });
                                        
                                        //tabla de productos
                                        $('#tablaProductos').dataTable({
                                            "lengthMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                            "language": {
                                                "info": "Página _PAGE_ de _PAGES_",
                                                "infoEmpty": "No existen Registros disponibles",
                                                "zeroRecords": "No se encuentran registros",
                                                "search": "Buscar",
                                                "infoFiltered": "",
                                                "lengthMenu": "Mostrar _MENU_ Registros",
                                                "paginate": {
                                                    "first": "Primero",
                                                    "last": "Último",
                                                    "next": "Siguiente",
                                                    "previous": "Anterior"
                                                }

                                            }
                                        });

                                    });


                                    //seleccionar cliente
                                    function SeleccionarCliente(idCliente, nombreCliente) {
                                        $("#txtIdCliente").val(idCliente);
                                        $("#txtNombreCliente").val(nombreCliente);
                                    }
                                    
                                     //seleccionar vendedor
                                    function SeleccionarVendedor(idVendedor, nombreVendedor) {
                                        $("#txtIdVendedor").val(idVendedor);
                                        $("#txtNombreVendedor").val(nombreVendedor);
                                    }
                                    
                                    //seleccionar producto
                                    function SeleccionarProducto(idProducto, Descripcion, Precio,Existencia ) {
                                        $("#txtIdProducto").val(idProducto);
                                        $("#txtdescripcion").val(Descripcion);
                                        $("#txtprecio").val(Precio);
                                        $("#txtexistencia").val(Existencia);                                        
                                        $("#txtcantidad").focus();
                                    }

                                    //seleccionar cliente
                                    function Limpiar() {
                                        $("#txtIdCliente").val("");
                                        $("#txtNombreCliente").val("");
                                    }
                                    
                                    //seleccionar cliente
                                    function LimpiarVendedor() {
                                        $("#txtIdVendedor").val("");
                                        $("#txtNombreVendedor").val("");
                                    }
                                    
                                    //seleccionar producto
                                    function LimpiarProducto() {
                                        $("#txtIdProducto").val("");
                                        $("#txtdescripcion").val("");
                                        $("#txtprecio").val("");
                                        $("#txtexistencia").val("");
                                    }
        </script>
    </body>
</html>
