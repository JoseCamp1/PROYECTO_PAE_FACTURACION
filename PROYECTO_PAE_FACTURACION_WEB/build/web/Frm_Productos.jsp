<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Capa_Entidades.Producto"%>
<%@page import="Capa2_LogicaNegocio.LNProducto"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">        
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <title>Agregar productos</title>
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
        
        <div class="container">
            <div class="row">
                <div class="col-md-8 mx-auto">
                    <div class="card-header">
                        <h1>Lista de Productos</h1>
                    </div>

                    <%
                        Producto entidad;
                        LNProducto logica = new LNProducto();
                        int idproducto;
                        if (request.getParameter("txtIdproducto") != null
                                && !request.getParameter("txtIdproducto").equals("")) {
                            idproducto = Integer.parseInt(request.getParameter("txtIdproducto"));
                            entidad = logica.ObtenerRegistro("Id_producto=" + idproducto);
                        } else {
                            entidad = new Producto();
                            entidad.setId(-1);
                        }
                    %>
                    
                    <br>
                    <form action="AddProducto" method="post">

                        <div class="form-group">
                            <input type="hidden" name="txtIdproducto" id="txtIdproducto" class="form-control"
                                   value="<%= entidad.getId()%>" readonly/>
                            
                            <label for="txtNombre">Nombre</label>
                            <input type="text" name="txtNombre" id="txtNombre" 
                                   value="<%= entidad.getNombre()%>" required class="form-control"/>

                            <label for="txtdescripcion">Descripción</label>
                            <input type="text" name="txtdescripcion" id="txtdescripcion" 
                                   value="<%= entidad.getDescripcion()%>" required class="form-control"/>
                        </div>  
                        <div class="form-group">
                            <label for="txtprecio">Precio</label>
                            <input type="text" name="txtprecio" id="txtprecio" 
                                   value="<%= entidad.getPrecio()%>" required class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="txtStock">Existencia</label>
                            <input type="text" name="txtStock" id="txtStock" 
                                   value="<%= entidad.getStock()%>" required class="form-control"/>

                        </div>  
                        <div class="form-group">
                            <input type="submit" value="Guardar" class="btn btn-primary">
                            <input type="button" id="btnRegresar" value="Regresar" onclick="location.href = 'Frm_ListarProductos.jsp'" class="btn btn-secondary"/>
                        </div>  
                    </form>
                </div> <!-- clase que crea las 6 columnas -->

            </div> <!-- class row, div de la fila -->

        </div> <!-- class container -->
        
        <footer class="bg-dark p-1 text-white fixed-bottom">        
            <p class="m-0 text-center">
                Panaderia La Casita del Pan©
            </p>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
    </body>
</html>
