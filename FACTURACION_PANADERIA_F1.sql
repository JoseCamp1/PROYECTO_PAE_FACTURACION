---------------------------------------------
-- PROYECTO F1                             --
-- MODULO:PAE                              --
-- PROFESOR:LUIS ALONSO BOGANTES RODRIGUEZ --
-- ESTUDIANTE:JOSE JOAQUIN CAMPOS CHAVES   --
---------------------------------------------

-----------------------------------------
-- *** BORRAR LA BASE DE DATOS ***     --
-- DROP DATABASE FACTURACION_PANADERIA --
-----------------------------------------

-------------------------
-- CREAR BASE DE DATOS --
-------------------------

CREATE DATABASE FACTURACION_PANADERIA;
GO

---------------------------
-- USAR LA BASE DE DATOS --
---------------------------

USE FACTURACION_PANADERIA;
GO

------------------------
-- CREACION DE TABLAS --
------------------------

-- TABLA DE CLIENTES
CREATE TABLE CLIENTES (
    ID_CLIENTE INT PRIMARY KEY IDENTITY(1,1),
    NOMBRE_COMPLETO VARCHAR(100),
    CEDULA VARCHAR(9)    
);

-- TABLA DE VENDEDORES
CREATE TABLE VENDEDORES (
    ID_VENDEDOR INT PRIMARY KEY IDENTITY(1,1),
    NOMBRE_COMPLETO VARCHAR(100),
    CEDULA VARCHAR(9),    
    CORREO_ELECTRONICO VARCHAR(100),    
);

-- TABLA DE PRODUCTOS
CREATE TABLE PRODUCTOS (
    ID_PRODUCTO INT PRIMARY KEY IDENTITY(1,1),
    NOMBRE VARCHAR(100),
    DESCRIPCION VARCHAR(500),
    PRECIO DECIMAL(10, 2),
    STOCK INT
);

-- TABLA DE VENTAS
CREATE TABLE VENTAS (
    ID_VENTA INT PRIMARY KEY IDENTITY(1,1),
    METODOPAGO VARCHAR(50),
    FECHA DATE,
    ID_CLIENTE INT,	
    ID_VENDEDOR INT, 
    TOTAL DECIMAL(10, 2),
    FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTES(ID_CLIENTE),
    FOREIGN KEY (ID_VENDEDOR) REFERENCES VENDEDORES(ID_VENDEDOR)
);

-- TABLA DE DETALLE DE VENTAS
CREATE TABLE DETALLE_VENTAS (
    ID_DETALLE INT PRIMARY KEY IDENTITY(1,1),
    ID_VENTA INT,
    ID_PRODUCTO INT,
    CANTIDAD INT,
    SUBTOTAL DECIMAL(10, 2),
    FOREIGN KEY (ID_VENTA) REFERENCES VENTAS(ID_VENTA),
    FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTOS(ID_PRODUCTO)
);

-- TABLA DE COMPRAS
CREATE TABLE COMPRAS (
    ID_COMPRA INT PRIMARY KEY IDENTITY(1,1),
    FECHA DATE,
    PROVEEDOR VARCHAR(100),
    TOTAL DECIMAL(10, 2)
);

-- TABLA DE DETALLE DE COMPRAS
CREATE TABLE DETALLE_COMPRAS (
    ID_DETALLE INT PRIMARY KEY IDENTITY(1,1),
    ID_COMPRA INT,
    ID_PRODUCTO INT,
    CANTIDAD INT,
    PRECIO_UNITARIO DECIMAL(10, 2),
    SUBTOTAL DECIMAL(10, 2),
    FOREIGN KEY (ID_COMPRA) REFERENCES COMPRAS(ID_COMPRA),
    FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTOS(ID_PRODUCTO)
);

--------------------------------
-- PROCEDIMIENTOS ALMACENADOS --
--------------------------------

GO
-- Insertar Cliente
CREATE OR ALTER PROCEDURE INSERTAR_CLIENTE
    @NOMBRE_COMPLETO VARCHAR(100),
    @CEDULA VARCHAR(9),
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    IF (EXISTS (SELECT * FROM CLIENTES WHERE CEDULA = @CEDULA))
    BEGIN
        SET @MENSAJE = 'El cliente ya existe';
        RETURN 0;
    END
    ELSE
    BEGIN
        INSERT INTO CLIENTES (NOMBRE_COMPLETO, CEDULA)
        VALUES (@NOMBRE_COMPLETO, @CEDULA);
        SET @MENSAJE = 'Cliente insertado';
        RETURN 1;
    END
END;
GO


-- Modificar Cliente
CREATE OR ALTER PROCEDURE MODIFICAR_CLIENTE
    @ID_CLIENTE INT,
    @NOMBRE_COMPLETO VARCHAR(100),
    @CEDULA VARCHAR(9),
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    IF (NOT EXISTS (SELECT * FROM CLIENTES WHERE ID_CLIENTE = @ID_CLIENTE))
    BEGIN
        SET @MENSAJE = 'El cliente no existe';
        RETURN 0;
    END
    ELSE
    BEGIN
        UPDATE CLIENTES
        SET NOMBRE_COMPLETO = @NOMBRE_COMPLETO, CEDULA = @CEDULA
        WHERE ID_CLIENTE = @ID_CLIENTE;
        SET @MENSAJE = 'Cliente modificado';
        RETURN 1;
    END
END;
GO


-- Eliminar Cliente
CREATE OR ALTER PROCEDURE ELIMINAR_CLIENTE
    @ID_CLIENTE INT,
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    IF (NOT EXISTS (SELECT * FROM CLIENTES WHERE ID_CLIENTE = @ID_CLIENTE))
    BEGIN
        SET @MENSAJE = 'El cliente no existe';
        RETURN 0;
    END
    ELSE
    BEGIN
        DELETE FROM CLIENTES WHERE ID_CLIENTE = @ID_CLIENTE;
        SET @MENSAJE = 'Cliente eliminado';
        RETURN 1;
    END
END;
GO


-- Mostrar Clientes
CREATE OR ALTER PROCEDURE MOSTRAR_CLIENTES
AS
BEGIN
    SELECT * FROM CLIENTES
END;
GO

-- Insertar Vendedor
CREATE OR ALTER PROCEDURE INSERTAR_VENDEDOR
    @NOMBRE_COMPLETO VARCHAR(100),
    @CEDULA VARCHAR(9),
    @CORREO_ELECTRONICO VARCHAR(100),
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    IF (EXISTS (SELECT * FROM VENDEDORES WHERE CEDULA = @CEDULA))
    BEGIN
        SET @MENSAJE = 'El vendedor ya existe';
        RETURN 0;
    END
    ELSE
    BEGIN
        INSERT INTO VENDEDORES (NOMBRE_COMPLETO, CEDULA, CORREO_ELECTRONICO)
        VALUES (@NOMBRE_COMPLETO, @CEDULA, @CORREO_ELECTRONICO);
        SET @MENSAJE = 'Vendedor insertado';
        RETURN 1;
    END
END;
GO

-- Modificar Vendedor
CREATE OR ALTER PROCEDURE MODIFICAR_VENDEDOR
    @ID_VENDEDOR INT,
    @NOMBRE_COMPLETO VARCHAR(100),
    @CEDULA VARCHAR(9),
    @CORREO_ELECTRONICO VARCHAR(100),
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    IF (NOT EXISTS (SELECT * FROM VENDEDORES WHERE ID_VENDEDOR = @ID_VENDEDOR))
    BEGIN
        SET @MENSAJE = 'El vendedor no existe';
        RETURN 0;
    END
    ELSE
    BEGIN
        UPDATE VENDEDORES
        SET NOMBRE_COMPLETO = @NOMBRE_COMPLETO, CEDULA = @CEDULA, CORREO_ELECTRONICO = @CORREO_ELECTRONICO
        WHERE ID_VENDEDOR = @ID_VENDEDOR;
        SET @MENSAJE = 'Vendedor modificado';
        RETURN 1;
    END
END;
GO

-- Eliminar Vendedor
CREATE OR ALTER PROCEDURE ELIMINAR_VENDEDOR
    @ID_VENDEDOR INT,
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    IF (NOT EXISTS (SELECT * FROM VENDEDORES WHERE ID_VENDEDOR = @ID_VENDEDOR))
    BEGIN
        SET @MENSAJE = 'El vendedor no existe';
        RETURN 0;
    END
    ELSE
    BEGIN
        DELETE FROM VENDEDORES WHERE ID_VENDEDOR = @ID_VENDEDOR;
        SET @MENSAJE = 'Vendedor eliminado';
        RETURN 1;
    END
END;
GO


-- Mostrar Vendedores
CREATE OR ALTER PROCEDURE MOSTRAR_VENDEDORES
AS
BEGIN
    SELECT * FROM VENDEDORES
END;
GO



-- Insertar Producto
CREATE OR ALTER PROCEDURE INSERTAR_PRODUCTO
    @NOMBRE VARCHAR(100),
    @DESCRIPCION VARCHAR(600),
    @PRECIO DECIMAL(10, 2),
    @STOCK INT,
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    IF (EXISTS (SELECT * FROM PRODUCTOS WHERE NOMBRE = @NOMBRE))
    BEGIN
        SET @MENSAJE = 'El producto ya existe';
        RETURN 0;
    END
    ELSE
    BEGIN
        INSERT INTO PRODUCTOS (NOMBRE, DESCRIPCION, PRECIO, STOCK)
        VALUES (@NOMBRE, @DESCRIPCION, @PRECIO, @STOCK);
        SET @MENSAJE = 'Producto insertado';
        RETURN 1;
    END
END;
GO

-- Modificar Producto
CREATE OR ALTER PROCEDURE MODIFICAR_PRODUCTO
    @ID_PRODUCTO INT,
    @NOMBRE VARCHAR(100),
    @DESCRIPCION VARCHAR(600),
    @PRECIO DECIMAL(10, 2),
    @STOCK INT,
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    IF (NOT EXISTS (SELECT * FROM PRODUCTOS WHERE ID_PRODUCTO = @ID_PRODUCTO))
    BEGIN
        SET @MENSAJE = 'El producto no existe';
        RETURN 0;
    END
    ELSE
    BEGIN
        UPDATE PRODUCTOS
        SET NOMBRE = @NOMBRE, DESCRIPCION = @DESCRIPCION, PRECIO = @PRECIO, STOCK = @STOCK
        WHERE ID_PRODUCTO = @ID_PRODUCTO;
        SET @MENSAJE = 'Producto modificado';
        RETURN 1;
    END
END;
GO

-- Eliminar Producto
CREATE OR ALTER PROCEDURE ELIMINAR_PRODUCTO
    @ID_PRODUCTO INT,
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    IF (NOT EXISTS (SELECT * FROM PRODUCTOS WHERE ID_PRODUCTO = @ID_PRODUCTO))
    BEGIN
        SET @MENSAJE = 'El producto no existe';
        RETURN 0;
    END
    ELSE
    BEGIN
        DELETE FROM PRODUCTOS WHERE ID_PRODUCTO = @ID_PRODUCTO;
        SET @MENSAJE = 'Producto eliminado';
        RETURN 1;
    END
END;
GO

-- Mostrar Productos
CREATE OR ALTER PROCEDURE MOSTRAR_PRODUCTOS
AS
BEGIN
    SELECT * FROM PRODUCTOS
END;
GO




-- Insertar Venta
CREATE OR ALTER PROCEDURE INSERTAR_VENTA
    @METODOPAGO VARCHAR(50),
    @FECHA DATE,
    @ID_CLIENTE INT,
    @ID_VENDEDOR INT,
    @TOTAL DECIMAL(10, 2),
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    INSERT INTO VENTAS (METODOPAGO, FECHA, ID_CLIENTE, ID_VENDEDOR, TOTAL)
    VALUES (@METODOPAGO, @FECHA, @ID_CLIENTE, @ID_VENDEDOR, @TOTAL);
    SET @MENSAJE = 'Venta insertada';
    RETURN 1;
END;
GO

-- Modificar Venta
CREATE OR ALTER PROCEDURE MODIFICAR_VENTA
    @ID_VENTA INT,
    @METODOPAGO VARCHAR(50),
    @FECHA DATE,
    @ID_CLIENTE INT,
    @ID_VENDEDOR INT,
    @TOTAL DECIMAL(10, 2),
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    IF (NOT EXISTS (SELECT * FROM VENTAS WHERE ID_VENTA = @ID_VENTA))
    BEGIN
        SET @MENSAJE = 'La venta no existe';
        RETURN 0;
    END
    ELSE
    BEGIN
        UPDATE VENTAS
        SET METODOPAGO = @METODOPAGO, FECHA = @FECHA, ID_CLIENTE = @ID_CLIENTE, ID_VENDEDOR = @ID_VENDEDOR, TOTAL = @TOTAL
        WHERE ID_VENTA = @ID_VENTA;
        SET @MENSAJE = 'Venta modificada';
        RETURN 1;
    END
END;
GO

-- Eliminar Venta
CREATE OR ALTER PROCEDURE ELIMINAR_VENTA
    @ID_VENTA INT,
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    IF (NOT EXISTS (SELECT * FROM VENTAS WHERE ID_VENTA = @ID_VENTA))
    BEGIN
        SET @MENSAJE = 'La venta no existe';
        RETURN 0;
    END
    ELSE
    BEGIN
        DELETE FROM VENTAS WHERE ID_VENTA = @ID_VENTA;
        SET @MENSAJE = 'Venta eliminada';
        RETURN 1;
    END
END;
GO


-- Mostrar Ventas
CREATE OR ALTER PROCEDURE MOSTRAR_VENTAS
AS
BEGIN
    SELECT * FROM VENTAS
END;
GO




-- Insertar Detalle de Venta
CREATE OR ALTER PROCEDURE INSERTAR_DETALLE_VENTA
    @ID_VENTA INT,
    @ID_PRODUCTO INT,
    @CANTIDAD INT,
    @SUBTOTAL DECIMAL(10, 2),
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    INSERT INTO DETALLE_VENTAS (ID_VENTA, ID_PRODUCTO, CANTIDAD, SUBTOTAL)
    VALUES (@ID_VENTA, @ID_PRODUCTO, @CANTIDAD, @SUBTOTAL);
    SET @MENSAJE = 'Detalle de venta insertado';
    RETURN 1;
END;
GO

-- Modificar Detalle de Venta
CREATE OR ALTER PROCEDURE MODIFICAR_DETALLE_VENTA
    @ID_DETALLE INT,
    @ID_VENTA INT,
    @ID_PRODUCTO INT,
    @CANTIDAD INT,
    @SUBTOTAL DECIMAL(10, 2),
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    IF (NOT EXISTS (SELECT * FROM DETALLE_VENTAS WHERE ID_DETALLE = @ID_DETALLE))
    BEGIN
        SET @MENSAJE = 'El detalle de venta no existe';
        RETURN 0;
    END
    ELSE
    BEGIN
        UPDATE DETALLE_VENTAS
        SET ID_VENTA = @ID_VENTA, ID_PRODUCTO = @ID_PRODUCTO, CANTIDAD = @CANTIDAD, SUBTOTAL = @SUBTOTAL
        WHERE ID_DETALLE = @ID_DETALLE;
        SET @MENSAJE = 'Detalle de venta modificado';
        RETURN 1;
    END
END;
GO

-- Eliminar Detalle de Venta
CREATE OR ALTER PROCEDURE ELIMINAR_DETALLE_VENTA
    @ID_DETALLE INT,
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    IF (NOT EXISTS (SELECT * FROM DETALLE_VENTAS WHERE ID_DETALLE = @ID_DETALLE))
    BEGIN
        SET @MENSAJE = 'El detalle de venta no existe';
        RETURN 0;
    END
    ELSE
    BEGIN
        DELETE FROM DETALLE_VENTAS WHERE ID_DETALLE = @ID_DETALLE;
        SET @MENSAJE = 'Detalle de venta eliminado';
        RETURN 1;
    END
END;
GO

-- Mostrar Detalles de Venta
CREATE OR ALTER PROCEDURE MOSTRAR_DETALLES_VENTA
AS
BEGIN
    SELECT * FROM DETALLE_VENTAS
END;
GO



-- Insertar Compra
CREATE OR ALTER PROCEDURE INSERTAR_COMPRA
    @FECHA DATE,
    @PROVEEDOR VARCHAR(100),
    @TOTAL DECIMAL(10, 2),
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    INSERT INTO COMPRAS (FECHA, PROVEEDOR, TOTAL)
    VALUES (@FECHA, @PROVEEDOR, @TOTAL);
    SET @MENSAJE = 'Compra insertada';
    RETURN 1;
END;
GO

-- Modificar Compra
CREATE OR ALTER PROCEDURE MODIFICAR_COMPRA
    @ID_COMPRA INT,
    @FECHA DATE,
    @PROVEEDOR VARCHAR(100),
    @TOTAL DECIMAL(10, 2),
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    IF (NOT EXISTS (SELECT * FROM COMPRAS WHERE ID_COMPRA = @ID_COMPRA))
    BEGIN
        SET @MENSAJE = 'La compra no existe';
        RETURN 0;
    END
    ELSE
    BEGIN
        UPDATE COMPRAS
        SET FECHA = @FECHA, PROVEEDOR = @PROVEEDOR, TOTAL = @TOTAL
        WHERE ID_COMPRA = @ID_COMPRA;
        SET @MENSAJE = 'Compra modificada';
        RETURN 1;
    END
END;
GO

-- Eliminar Compra
CREATE OR ALTER PROCEDURE ELIMINAR_COMPRA
    @ID_COMPRA INT,
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    IF (NOT EXISTS (SELECT * FROM COMPRAS WHERE ID_COMPRA = @ID_COMPRA))
    BEGIN
        SET @MENSAJE = 'La compra no existe';
        RETURN 0;
    END
    ELSE
    BEGIN
        DELETE FROM COMPRAS WHERE ID_COMPRA = @ID_COMPRA;
        SET @MENSAJE = 'Compra eliminada';
        RETURN 1;
    END
END;
GO

-- Mostrar Compras
CREATE OR ALTER PROCEDURE MOSTRAR_COMPRAS
AS
BEGIN
    SELECT * FROM COMPRAS
END;
GO


-- Insertar Detalle de Compra
CREATE OR ALTER PROCEDURE INSERTAR_DETALLE_COMPRA
    @ID_COMPRA INT,
    @ID_PRODUCTO INT,
    @CANTIDAD INT,
    @PRECIO_UNITARIO DECIMAL(10, 2),
    @SUBTOTAL DECIMAL(10, 2),
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    INSERT INTO DETALLE_COMPRAS (ID_COMPRA, ID_PRODUCTO, CANTIDAD, PRECIO_UNITARIO, SUBTOTAL)
    VALUES (@ID_COMPRA, @ID_PRODUCTO, @CANTIDAD, @PRECIO_UNITARIO, @SUBTOTAL);
    SET @MENSAJE = 'Detalle de compra insertado';
    RETURN 1;
END;
GO

-- Modificar Detalle de Compra
CREATE OR ALTER PROCEDURE MODIFICAR_DETALLE_COMPRA
    @ID_DETALLE INT,
    @ID_COMPRA INT,
    @ID_PRODUCTO INT,
    @CANTIDAD INT,
    @PRECIO_UNITARIO DECIMAL(10, 2),
    @SUBTOTAL DECIMAL(10, 2),
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    IF (NOT EXISTS (SELECT * FROM DETALLE_COMPRAS WHERE ID_DETALLE = @ID_DETALLE))
    BEGIN
        SET @MENSAJE = 'El detalle de compra no existe';
        RETURN 0;
    END
    ELSE
    BEGIN
        UPDATE DETALLE_COMPRAS
        SET ID_COMPRA = @ID_COMPRA, ID_PRODUCTO = @ID_PRODUCTO, CANTIDAD = @CANTIDAD, PRECIO_UNITARIO = @PRECIO_UNITARIO, SUBTOTAL = @SUBTOTAL
        WHERE ID_DETALLE = @ID_DETALLE;
        SET @MENSAJE = 'Detalle de compra modificado';
        RETURN 1;
    END
END;
GO

-- Eliminar Detalle de Compra
CREATE OR ALTER PROCEDURE ELIMINAR_DETALLE_COMPRA
    @ID_DETALLE INT,
    @MENSAJE VARCHAR(50) OUTPUT
AS
BEGIN
    IF (NOT EXISTS (SELECT * FROM DETALLE_COMPRAS WHERE ID_DETALLE = @ID_DETALLE))
    BEGIN
        SET @MENSAJE = 'El detalle de compra no existe';
        RETURN 0;
    END
    ELSE
    BEGIN
        DELETE FROM DETALLE_COMPRAS WHERE ID_DETALLE = @ID_DETALLE;
        SET @MENSAJE = 'Detalle de compra eliminado';
        RETURN 1;
    END
END;
GO

-- Mostrar Detalles de Compra
CREATE OR ALTER PROCEDURE MOSTRAR_DETALLES_COMPRA
AS
BEGIN
    SELECT * FROM DETALLE_COMPRAS
END;
GO


-- función COALESCE o ISNULL, para reemplazar valores NULL por 0 
-- obtener un resumen completo de ventas por mes y/o por vendedor, y los valores NULL se reemplazan por 0 en el resultado.
CREATE OR ALTER PROCEDURE RESUMIR_VENTAS
    @Mes INT = NULL,
    @ID_VENDEDOR INT = NULL
AS
BEGIN
    SELECT
        V.ID_VENDEDOR,
        V.NOMBRE_COMPLETO AS VENDEDOR,
        COALESCE(MONTH(VT.FECHA), 0) AS MES,
        COALESCE(SUM(VT.TOTAL), 0) AS VENTA_TOTAL
    FROM
        VENTAS VT
    RIGHT JOIN  
        VENDEDORES V ON VT.ID_VENDEDOR = V.ID_VENDEDOR
    WHERE
        (@Mes IS NULL OR MONTH(VT.FECHA) = @Mes)
        AND (@ID_VENDEDOR IS NULL OR V.ID_VENDEDOR = @ID_VENDEDOR)
    GROUP BY
        V.ID_VENDEDOR, V.NOMBRE_COMPLETO, COALESCE(MONTH(VT.FECHA), 0)
    ORDER BY
        V.ID_VENDEDOR, COALESCE(MONTH(VT.FECHA), 0);
END;
GO

--------------
-- TRIGGERS --
--------------

-- PD: AUN NO ESTOY SEGURO SI LOS USARE!!!

-- Este trigger se ejecutará después de insertar un nuevo registro en DETALLE_VENTAS y reducirá la cantidad de stock del producto correspondiente en la tabla PRODUCTOS.
CREATE OR ALTER TRIGGER TRIGGER_ACTUALIZAR_STOCK_VENTAS
ON DETALLE_VENTAS
AFTER INSERT
AS
BEGIN
    -- Obtener el ID del producto y la cantidad vendida del registro insertado
    DECLARE @ID_PRODUCTO INT, @CANTIDAD INT;
    SELECT @ID_PRODUCTO = ID_PRODUCTO, @CANTIDAD = CANTIDAD
    FROM inserted;

    -- Actualizar el stock del producto en la tabla PRODUCTOS
    UPDATE PRODUCTOS
    SET STOCK = STOCK - @CANTIDAD
    WHERE ID_PRODUCTO = @ID_PRODUCTO;
END;
GO


-- Este trigger se ejecutará después de insertar un nuevo registro en DETALLE_COMPRAS y aumentará la cantidad de stock del producto en la tabla PRODUCTOS.
CREATE OR ALTER TRIGGER TRIGGER_ACTUALIZAR_STOCK_COMPRAS
ON DETALLE_COMPRAS
AFTER INSERT
AS
BEGIN
    -- Obtener el ID del producto y la cantidad comprada del registro insertado
    DECLARE @ID_PRODUCTO INT, @CANTIDAD INT;
    SELECT @ID_PRODUCTO = ID_PRODUCTO, @CANTIDAD = CANTIDAD
    FROM inserted;

    -- Actualizar el stock del producto en la tabla PRODUCTOS
    UPDATE PRODUCTOS
    SET STOCK = STOCK + @CANTIDAD
    WHERE ID_PRODUCTO = @ID_PRODUCTO;
END;
GO

-- Este trigger se ejecutará después de insertar o actualizar un registro en la tabla VENTAS y 
-- recalculará automáticamente el campo TOTAL basado en los SUBTOTAL de los registros en DETALLE_VENTAS asociados a esa venta.
CREATE OR ALTER TRIGGER TRIGGER_CALCULAR_TOTAL_VENTA
ON VENTAS
AFTER INSERT, UPDATE
AS
BEGIN
    -- Obtener el ID de la venta del registro insertado o actualizado
    DECLARE @ID_VENTA INT;
    SELECT @ID_VENTA = ID_VENTA
    FROM inserted;

    -- Calcular el nuevo total de la venta
    UPDATE VENTAS
    SET TOTAL = (SELECT SUM(SUBTOTAL) FROM DETALLE_VENTAS WHERE ID_VENTA = @ID_VENTA)
    WHERE ID_VENTA = @ID_VENTA;
END;
GO

----------------------
-- INGRESO DE DATOS --
----------------------

-- Insertar datos en la tabla CLIENTES
INSERT INTO CLIENTES (NOMBRE_COMPLETO, CEDULA) VALUES
('Juan Pérez', '123456789'),
('Ana Sánchez', '987654321'),
('María Rodríguez', '555555555'),
('Pedro Gómez', '111111111'),
('Luisa Fernández', '222222222');

-- Insertar datos en la tabla VENDEDORES
INSERT INTO VENDEDORES (NOMBRE_COMPLETO, CEDULA, CORREO_ELECTRONICO) VALUES
('Carlos González', '999999999', 'carlos@example.com'),
('Laura Martínez', '888888888', 'laura@example.com'),
('Javier Ramírez', '777777777', 'javier@example.com'),
('Sofía López', '666666666', 'sofia@example.com'),
('Roberto Herrera', '555555555', 'roberto@example.com');


-- Insertar datos en la tabla PRODUCTOS
INSERT INTO PRODUCTOS (NOMBRE, DESCRIPCION, PRECIO, STOCK) VALUES
('Pan Integral', 'Pan integral de trigo', 2.99, 100),
('Pan de Avena', 'Pan de avena con pasas', 3.49, 75),
('Croissant', 'Croissant recién horneado', 1.99, 50),
('Rosquillas', 'Rosquillas glaseadas', 0.99, 120),
('Baguette', 'Baguette crujiente', 2.49, 80);


-- Insertar datos en la tabla VENTAS
INSERT INTO VENTAS (METODOPAGO, FECHA, ID_CLIENTE, ID_VENDEDOR, TOTAL) VALUES
('Tarjeta de Crédito', '2023-09-01', 1, 1, 15.99),
('Efectivo', '2023-09-02', 2, 2, 12.49),
('Efectivo', '2023-09-03', 3, 3, 7.99),
('Tarjeta de Débito', '2023-09-04', 4, 4, 5.99),
('Efectivo', '2023-09-05', 5, 5, 10.49);


-- Insertar datos en la tabla DETALLE_VENTAS
INSERT INTO DETALLE_VENTAS (ID_VENTA, ID_PRODUCTO, CANTIDAD, SUBTOTAL) VALUES
(1, 1, 3, 8.97),
(1, 3, 2, 3.98),
(2, 2, 1, 3.49),
(3, 4, 4, 23.96),
(4, 5, 2, 4.98);


-- Insertar datos en la tabla COMPRAS
INSERT INTO COMPRAS (FECHA, PROVEEDOR, TOTAL) VALUES
('2023-09-01', 'Proveedor A', 50.25),
('2023-09-02', 'Proveedor B', 35.50),
('2023-09-03', 'Proveedor C', 75.30),
('2023-09-04', 'Proveedor D', 42.75),
('2023-09-05', 'Proveedor E', 28.90);


-- Insertar datos en la tabla DETALLE_COMPRAS
INSERT INTO DETALLE_COMPRAS (ID_COMPRA, ID_PRODUCTO, CANTIDAD, PRECIO_UNITARIO, SUBTOTAL) VALUES
(1, 1, 10, 2.50, 25.00),
(1, 2, 5, 3.00, 15.00),
(2, 3, 3, 5.25, 15.75),
(3, 4, 8, 2.85, 22.80),
(4, 5, 6, 4.50, 27.00);

----------------------
-- HACIENDO PRUEBAS --
----------------------

GO

DECLARE @Mensaje VARCHAR(50);

EXEC INSERTAR_VENDEDOR
    @NOMBRE_COMPLETO = 'Juan Pérez',
    @CEDULA = '123456789',
    @CORREO_ELECTRONICO = 'juan@example.com',
    @MENSAJE = @Mensaje OUTPUT;

PRINT @Mensaje; 
GO

DECLARE @Mensaje VARCHAR(50);

EXEC MODIFICAR_VENDEDOR
    @ID_VENDEDOR = 6, 
    @NOMBRE_COMPLETO = 'Nuevo Nombre',
    @CEDULA = '987654321',
    @CORREO_ELECTRONICO = 'nuevo@example.com',
    @MENSAJE = @Mensaje OUTPUT;

PRINT @Mensaje; 
GO

DECLARE @Mensaje VARCHAR(50);

EXEC ELIMINAR_VENDEDOR
    @ID_VENDEDOR = 6, 
    @MENSAJE = @Mensaje OUTPUT;

PRINT @Mensaje; 
GO

EXEC MOSTRAR_VENDEDORES; 
GO


-- Ejemplo 1: Obtener resumen de ventas por mes
EXEC RESUMIR_VENTAS @Mes = 9;

-- Ejemplo 2: Obtener resumen de ventas por vendedor
EXEC RESUMIR_VENTAS @ID_VENDEDOR = 1;

-- Ejemplo 3: Obtener resumen de ventas por mes y vendedor
EXEC RESUMIR_VENTAS @Mes = 9, @ID_VENDEDOR = 1;

-- Ejemplo 4: Obtener resumen de ventas sin filtrar (todos los meses y vendedores)
EXEC RESUMIR_VENTAS;

GO

SELECT * FROM CLIENTES
SELECT * FROM VENDEDORES
SELECT * FROM VENTAS
SELECT * FROM DETALLE_COMPRAS
SELECT * FROM COMPRAS
SELECT * FROM DETALLE_COMPRAS
SELECT * FROM PRODUCTOS