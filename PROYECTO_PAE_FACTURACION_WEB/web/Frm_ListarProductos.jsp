<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<!-- -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Capa_Entidades.Producto"%>
<%@page import="Capa2_LogicaNegocio.LNProducto"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Lista de productos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="lib/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>

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

        <div class="container">  <!-- container y card-header son clases de BOOTSTRAP -->
            <div class="row">
                <div class="col-md-8 mx-auto">

                    <div class="card-header">
                        <h1>Listado de Productos</h1>
                    </div>
                    <br/>

                    <form action="Frm_ListarProductos.jsp" method="post">
                        <div class="form-group">
                            <div class="input-group">
                                <label for="txtnombre">Nombre de Producto</label>&nbsp; &nbsp;
                                <!-- el for de este label lo unico que hace asociar esa etiqueta con el input -->
                                <input type="text" id="txtnombre" name="txtnombre" placeholder="Nombre" class="form-control"/>&nbsp; &nbsp;
                                <input type="submit" value="Buscar" name="btnBuscar" class="btn btn-primary"/>
                            </div>
                        </div>
                    </form>
                    <br>

                    <%
                        if (request.getParameter("mensaje") != null
                                && !request.getParameter("mensaje").equals("")) {
                            out.print("<p style='color:red'>" + request.getParameter("mensaje") + "</p>");
                        }
                    %>

                    <table class="table">
                        <tr>
                            <th style="text-align: left">Código</th>
                            <th style="text-align: left">Nombre</th>
                            <th style="text-align: left">Descripcion</th>
                            <th style="text-align: left">Precio</th>
                            <th style="text-align: left">Stock</th>
                            <th style="text-align: left">Opciones</th>
                        </tr>

                        <%
                            String nombre; // la vamos a llenar con lo que traiga la solicitud
                            String condicion = "";
                            if (request.getParameter("txtnombre") != null
                                    && !request.getParameter("txtnombre").equals("")) {
                                nombre = request.getParameter("txtnombre");
                                condicion = "NOMBRE LIKE '%" + nombre + "%'";
                            }
                            LNProducto logica = new LNProducto();
                            List<Producto> datos = logica.ListarRegistros(condicion);
                            for (Producto registro : datos) {

                        %>

                        <tr>
                            <td><%=registro.getId()%></td>
                            <td><%= new String(registro.getNombre().getBytes("ISO-8859-1"), "UTF-8")%></td>
                            <td><%= new String(registro.getDescripcion().getBytes("ISO-8859-1"), "UTF-8")%></td>
                           <!--<td><%=DecimalFormat.getCurrencyInstance().format(registro.getPrecio())%></td> -->
                            <td><%=registro.getPrecio()%></td>
                            <td><%=registro.getStock()%></td>
                            <td>
                                <a href="Frm_Productos.jsp?txtIdproducto=<%= registro.getId()%>"><i class="fas fa-user-edit"></i></a> |
                                <a href="EliminarProducto?txtIdproducto=<%= registro.getId()%>"><i class="fas fa-trash-alt"></i></a>
                            </td>
                        </tr>
                        <% } // fin del For %>

                    </table>

                    <br>
                    <a href="Frm_Productos.jsp" class="btn btn-primary" >Agregar Nuevo Producto</a>

                </div> <!-- clase que crea las 6 columnas -->

            </div> <!-- class row, div de la fila -->

        </div> <!-- class container -->

        <footer class="bg-dark p-1 text-white fixed-bottom">        
            <p class="m-0 text-center">
                La Casita del Pan©
            </p>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>

    </body>
</html>

