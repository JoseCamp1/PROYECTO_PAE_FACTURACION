<%@page import="java.util.List"%>
<%@page import="Capa_Entidades.Ventas"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="Capa2_LogicaNegocio.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="lib/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <title>Listado de Facturas</title>
    </head>
    <body>

        <header>
            <nav class="navbar navbar-expand-lg navbar-success bg-dark navbar-light text-white">
                <div class="container-fluid">
                    <a class="navbar-brand text-white" href="index.html">La Casita del Pan</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle text-white" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Menu Principal
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item " href="Frm_ListarClientes.jsp">Clientes</a></li>
                                    <li><hr class="dropdown-divider"/></li>
                                    <li><a class="dropdown-item " href="Frm_ListarVendedores.jsp">Vendedores</a></li>
                                    <li><hr class="dropdown-divider"/></li>
                                    <li><a class="dropdown-item " href="Frm_ListarProductos.jsp">Productos</a></li>
                                    <li><hr class="dropdown-divider"/></li>                     
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle text-white" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Servicios
                                </a>
                                <ul class="dropdown-menu">                    
                                    <li><a class="dropdown-item " href="Frm_ListarFacturas.jsp">Ventas</a></li>
                                    <li><hr class="dropdown-divider"/></li>
                                    <li><a class="dropdown-item " href="En_Desarrollo.jsp">Compras</a></li>
                                    <li><hr class="dropdown-divider"/></li>
                                </ul>
                            </li>                     
                            <li class="nav-item">
                                <a class="nav-link text-white" href="En_Desarrollo.jsp">Contacto</a>
                            </li>     
                    </div>
                </div>
            </nav>
        </header> 

        <div class="container-fluid">
            <div class="card-header">
                <h1>Listado de Facturas Pendientes</h1>
            </div>
            <br/>
            <form action="Frm_ListarFacturas.jsp" method="post">
                <div class="form-group">
                    <div class="input-group">
                        <input type="text" id="txtfecha" name="txtfecha" value="" 
                               placeholder="Seleccione la fecha" class="datepicker"/> &nbsp; &nbsp;
                        <input type="submit" id="btnbuscar" name="btnBuscar" value="Buscar" 
                               class="btn-success"/> <br><br>
                    </div>
                </div>
            </form>
            </hr>
            <table class="table">
                <thead>
                    <tr>
                        <th>Num. Factura</th>
                        <th>Metodo de Pago</th>
                        <th>Fecha</th>
                        <th>Num Cliente</th>
                        <th>Cliente</th>
                        <th>Num Vendedor</th>
                        <th>Vendedor</th>
                        <th>Total</th>
                        <th>Estado</th>
                        <th>Opciones</th>
                    </tr>
                </thead>

                <tbody>
                    <%
                        String fecha = "";
                        String condicion = ""; //Estado='Pendiente'";
                        if (request.getParameter("txtfecha") != null
                                && !request.getParameter("txtfecha").equals("")) {
                            fecha = request.getParameter("txtfecha");
                            //condicion = condicion + " AND FECHA = '" + fecha + "'";
                            condicion = condicion + "FECHA = '" + fecha + "'";
                        }

                        LNVentas logica = new LNVentas();
                        List<Ventas> datos;
                        datos = logica.ListarRegistros(condicion);
                        for (Ventas registro : datos) {
                    %>
                    <tr>
                        <%int num = registro.getId();%>
                        <td><%= num%></td>
                        <td><%= registro.getMetodoPago()%></td>
                        <td><%= registro.getFecha()%></td>
                        <td><%= registro.getId_Cliente()%></td>
                        <td><%= registro.getNombreCliente()%></td>
                        <td><%= registro.getId_Vendedor()%></td>
                        <td><%= registro.getNombreVendedor()%></td>
                        <td><%= registro.getTotal()%></td>
                        <td><%= registro.getEstado()%></td>
                        <td>
                            <a href="Frm_Facturar.jsp?txtnumFactura=<%= num%>">
                                <i class="fas fa-cart-plus"></i></a>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <br/>
            <a href="Frm_Facturar.jsp?txtnumFactura=-1" class="btn btn-primary">Nueva Factura</a>
        </div>
        <footer class="bg-dark p-1 text-white fixed-bottom">        
            <p class="m-0 text-center">
                Panaderia La Casita del Pan©
            </p>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js" type="text/javascript"></script>
    </body>

    <script>
        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            language: 'es'
        });
    </script>

</html>
