---------------------------------------------
-- PROYECTO F3                             --
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
    NOMBRE_COMPLETO VARCHAR(100) NOT NULL,
    CEDULA VARCHAR(9) UNIQUE NOT NULL   
);

-- TABLA DE VENDEDORES
CREATE TABLE VENDEDORES (
    ID_VENDEDOR INT PRIMARY KEY IDENTITY(1,1),
    NOMBRE_COMPLETO VARCHAR(100) NOT NULL,
    CEDULA VARCHAR(9) UNIQUE NOT NULL,    
    CORREO_ELECTRONICO VARCHAR(100) NOT NULL,    
);

-- TABLA DE PRODUCTOS
CREATE TABLE PRODUCTOS (
    ID_PRODUCTO INT PRIMARY KEY IDENTITY(1,1),
    NOMBRE VARCHAR(100) NOT NULL,
    DESCRIPCION VARCHAR(500) NOT NULL,
    PRECIO DECIMAL(10, 2) CHECK (PRECIO >= 0) NOT NULL,
    STOCK INT CHECK (STOCK >= 0) NOT NULL
);

-- TABLA DE VENTAS
CREATE TABLE VENTAS (
    ID_VENTA INT PRIMARY KEY IDENTITY(1,1),
    METODOPAGO VARCHAR(50) DEFAULT 'Efectivo',
    FECHA DATE DEFAULT GETDATE(),
    ID_CLIENTE INT,	
    ID_VENDEDOR INT, 
    TOTAL DECIMAL(10, 2) CHECK (TOTAL >= 0) NOT NULL,
	ESTADO VARCHAR (20),
    FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTES(ID_CLIENTE),
    FOREIGN KEY (ID_VENDEDOR) REFERENCES VENDEDORES(ID_VENDEDOR),
	CONSTRAINT CHK_METODOPAGO
        CHECK (METODOPAGO IN ('Sinpe', 'Efectivo', 'Tarjeta'))
);

-- TABLA DE DETALLE DE VENTAS

--CREATE TABLE DETALLE_VENTAS (
--    ID_DETALLE INT PRIMARY KEY IDENTITY(1,1),
--    ID_VENTA INT,
--    ID_PRODUCTO INT,
--    CANTIDAD INT CHECK (CANTIDAD >= 0) NOT NULL,
--    SUBTOTAL DECIMAL(10, 2) CHECK (SUBTOTAL >= 0) NOT NULL,
--    FOREIGN KEY (ID_VENTA) REFERENCES VENTAS(ID_VENTA),
--    FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTOS(ID_PRODUCTO)
--);


CREATE TABLE DETALLE_VENTAS (
    ID_VENTA INT,
    ID_PRODUCTO INT,
    CANTIDAD INT CHECK (CANTIDAD >= 0) NOT NULL,
    PRECIO_VENTA DECIMAL(10, 2) CHECK (PRECIO_VENTA >= 0) NOT NULL,
    PRIMARY KEY (ID_VENTA, ID_PRODUCTO), -- Definir una llave primaria compuesta
    FOREIGN KEY (ID_VENTA) REFERENCES VENTAS(ID_VENTA),
    FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTOS(ID_PRODUCTO)
);


-- TABLA DE COMPRAS
CREATE TABLE COMPRAS (
    ID_COMPRA INT PRIMARY KEY IDENTITY(1,1),
    FECHA DATE DEFAULT GETDATE(),
    PROVEEDOR VARCHAR(100) NOT NULL,
    TOTAL DECIMAL(10, 2) CHECK (TOTAL >= 0) NOT NULL
);

-- TABLA DE DETALLE DE COMPRAS
CREATE TABLE DETALLE_COMPRAS (
    ID_DETALLE INT PRIMARY KEY IDENTITY(1,1),
    ID_COMPRA INT,
    NOMBRE VARCHAR(100),
    CANTIDAD INT CHECK (CANTIDAD >= 0) NOT NULL,    
    SUBTOTAL DECIMAL(10, 2) CHECK (SUBTOTAL >= 0) NOT NULL,
    FOREIGN KEY (ID_COMPRA) REFERENCES COMPRAS(ID_COMPRA),
    
);


----------------------
-- INGRESO DE DATOS --
----------------------

-- Insertar datos en la tabla CLIENTES
INSERT INTO CLIENTES (NOMBRE_COMPLETO, CEDULA) VALUES
('Cliente Default', '999999999'),
('Juan P�rez P�rez', '111111111'),
('Ana S�nchez S�nchez', '222222222'),
('Mar�a Rodr�guez Rodr�guez', '333333333'),
('Pedro G�mez G�mez', '444444444'),
('Luisa Fern�ndez Fern�ndez', '555555555');

-- Insertar datos en la tabla VENDEDORES
INSERT INTO VENDEDORES (NOMBRE_COMPLETO, CEDULA, CORREO_ELECTRONICO) VALUES
('Carlos Gonz�lez Gonz�lez', '999999999', 'carlos@example.com'),
('Laura Mart�nez Mart�nez', '888888888', 'laura@example.com'),
('Javier Ram�rez Ram�rez', '777777777', 'javier@example.com'),
('Sof�a L�pez L�pez', '666666666', 'sofia@example.com'),
('Roberto Herrera Herrera', '555555555', 'roberto@example.com');


-- Insertar datos en la tabla PRODUCTOS
INSERT INTO PRODUCTOS (NOMBRE, DESCRIPCION, PRECIO, STOCK) VALUES
('Pan Integral', 'Pan integral de trigo', 2.99, 100),
('Pan de Avena', 'Pan de avena con pasas', 3.49, 75),
('Croissant', 'Croissant reci�n horneado', 1.99, 50),
('Rosquillas', 'Rosquillas glaseadas', 0.99, 120),
('Baguette', 'Baguette crujiente', 2.49, 80);


-- Insertar datos en la tabla VENTAS
INSERT INTO VENTAS (METODOPAGO, FECHA, ID_CLIENTE, ID_VENDEDOR, TOTAL, ESTADO) VALUES
('Tarjeta', '2023-09-01', 1, 1, 15.99,'PENDIENTE'),
('Efectivo', '2023-09-02', 2, 2, 12.49,'PENDIENTE'),
('Sinpe', '2023-09-03', 3, 3, 7.99,'PENDIENTE'),
('Tarjeta', '2023-09-04', 4, 4, 5.99,'PENDIENTE'),
('Efectivo', '2023-09-05', 5, 5, 10.49,'PENDIENTE');


-- Insertar datos en la tabla DETALLE_VENTAS
INSERT INTO DETALLE_VENTAS (ID_VENTA, ID_PRODUCTO, CANTIDAD, PRECIO_VENTA) VALUES
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
INSERT INTO DETALLE_COMPRAS (ID_COMPRA, NOMBRE, CANTIDAD, SUBTOTAL) VALUES
(1, 'Harina', 10, 25.00),
(1, 'Sal', 5, 15.00),
(2, 'Polvo de Hornear', 3, 15.75),
(3, 'Aceite', 8, 22.80),
(4, 'Huevos', 6, 27.00);


SELECT * FROM CLIENTES
SELECT * FROM VENDEDORES
SELECT * FROM VENTAS
SELECT * FROM DETALLE_COMPRAS
SELECT * FROM COMPRAS
SELECT * FROM DETALLE_COMPRAS
SELECT * FROM PRODUCTOS


--------------------
-- PROCEDIMIENTOS --
--------------------

GO
CREATE OR ALTER PROCEDURE ELIMINAR_CLIENTE(@ID_CLIENTE INT, @MSJ VARCHAR(200) OUT)
AS
BEGIN
    IF NOT EXISTS (SELECT 1 FROM CLIENTES WHERE ID_CLIENTE = @ID_CLIENTE)
    BEGIN
        SET @MSJ = 'EL CLIENTE NO EXISTE';
    END
    ELSE
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM VENTAS WHERE ID_CLIENTE = @ID_CLIENTE)
        BEGIN
            DELETE FROM CLIENTES WHERE ID_CLIENTE = @ID_CLIENTE;
            SET @MSJ = 'CLIENTE ELIMINADO';
        END
        ELSE
        BEGIN
            SET @MSJ = 'EL CLIENTE NO SE PUEDE ELIMINAR YA QUE TIENE VENTAS ASOCIADAS';
        END
    END
END
GO


CREATE OR ALTER PROCEDURE BUSCAR_CLIENTE(@ID_CLIENTE INT, @MSJ VARCHAR(200) OUT)
AS
BEGIN
    IF NOT EXISTS (SELECT 1 FROM CLIENTES WHERE ID_CLIENTE = @ID_CLIENTE)
    BEGIN
        SET @MSJ = 'EL CLIENTE NO SE ENCUENTRA';
    END
    ELSE
    BEGIN
        SELECT ID_CLIENTE, NOMBRE_COMPLETO, CEDULA FROM CLIENTES
        WHERE ID_CLIENTE = @ID_CLIENTE;
        SET @MSJ = 'CLIENTE ENCONTRADO';
    END
END
GO


--CREATE OR ALTER PROCEDURE GUARDAR_CLIENTE(
--    @IDCLIENTE INT OUT,
--    @NOMBRE_COMPLETO VARCHAR(100),
--    @CEDULA VARCHAR(9),
--    @MSJ VARCHAR(200) OUT)
--AS
--BEGIN
--    IF @IDCLIENTE IS NULL
--    BEGIN
--        -- Insertar un nuevo cliente
--        INSERT INTO CLIENTES(NOMBRE_COMPLETO, CEDULA)
--        VALUES (@NOMBRE_COMPLETO, @CEDULA);    

--        SET @MSJ = 'CLIENTE INGRESADO';

--		SET @IDCLIENTE=IDENT_CURRENT('CLIENTES')

--    END
--    ELSE
--    BEGIN
--        -- Actualizar cliente existente
--        UPDATE CLIENTES
--        SET NOMBRE_COMPLETO = @NOMBRE_COMPLETO, CEDULA = @CEDULA
--        WHERE ID_CLIENTE = @IDCLIENTE;

--        SET @MSJ = 'CLIENTE MODIFICADO';
--    END
--END
--GO


--DECLARE @IDCLIENTE INT;
--DECLARE @MSJ VARCHAR(200);

---- Para insertar un nuevo cliente
--EXEC GUARDAR_CLIENTE @IDCLIENTE OUT, 'Jose Campos', '206730045', @MSJ OUT;

GO
CREATE OR ALTER PROCEDURE ELIMINAR_PRODUCTO(@ID_PRODUCTO INT, @MSJ VARCHAR(200) OUT)
AS
BEGIN
    IF NOT EXISTS (SELECT 1 FROM PRODUCTOS WHERE ID_PRODUCTO = @ID_PRODUCTO)
    BEGIN
        SET @MSJ = 'EL PRODUCTO NO EXISTE';
    END
    ELSE
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM DETALLE_VENTAS WHERE ID_PRODUCTO = @ID_PRODUCTO)
        BEGIN
            DELETE FROM PRODUCTOS WHERE ID_PRODUCTO = @ID_PRODUCTO;
            SET @MSJ = 'PRODUCTO ELIMINADO';
        END
        ELSE
        BEGIN
            SET @MSJ = 'EL PRODUCTO NO SE PUEDE ELIMINAR YA QUE TIENE VENTAS ASOCIADAS';
        END
    END
END
GO


CREATE OR ALTER PROCEDURE ELIMINAR_VENDEDOR(@ID_VENDEDOR INT, @MSJ VARCHAR(200) OUT)
AS
BEGIN
    IF NOT EXISTS (SELECT 1 FROM VENDEDORES WHERE ID_VENDEDOR = @ID_VENDEDOR)
    BEGIN
        SET @MSJ = 'EL VENDEDOR NO EXISTE';
    END
    ELSE
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM VENTAS WHERE ID_VENDEDOR = @ID_VENDEDOR)
        BEGIN
            DELETE FROM VENDEDORES WHERE ID_VENDEDOR = @ID_VENDEDOR;
            SET @MSJ = 'VENDEDOR ELIMINADO';
        END
        ELSE
        BEGIN
            SET @MSJ = 'EL VENDEDOR NO SE PUEDE ELIMINAR YA QUE TIENE VENTAS ASOCIADAS';
        END
    END
END
GO


CREATE OR ALTER PROCEDURE GUARDAR_VENTA(
    @ID_VENTA INT OUT,
    @METODOPAGO VARCHAR(50),
    @FECHA DATE,
    @ID_CLIENTE INT,
    @ID_VENDEDOR INT,
    @TOTAL DECIMAL(10, 2),
    @ESTADO VARCHAR(20),
    @MSJ VARCHAR(200) OUT)
AS
BEGIN
    IF NOT EXISTS (SELECT 1 FROM VENTAS WHERE ID_VENTA = @ID_VENTA)
    BEGIN
        -- Insertar una nueva venta
        INSERT INTO VENTAS(METODOPAGO, FECHA, ID_CLIENTE, ID_VENDEDOR, TOTAL, ESTADO)
        VALUES (@METODOPAGO, @FECHA, @ID_CLIENTE, @ID_VENDEDOR, @TOTAL, @ESTADO);

        SET @MSJ = 'VENTA INGRESADA';

        SET @ID_VENTA = IDENT_CURRENT('VENTAS');
    END
    ELSE
    BEGIN
        -- Actualizar venta existente
        IF EXISTS (SELECT 1 FROM VENTAS WHERE ID_VENTA = @ID_VENTA AND ESTADO = 'PENDIENTE')
        BEGIN
            UPDATE VENTAS
            SET METODOPAGO = @METODOPAGO, FECHA = @FECHA, ID_CLIENTE = @ID_CLIENTE, 
                ID_VENDEDOR = @ID_VENDEDOR, TOTAL = @TOTAL, ESTADO = @ESTADO
            WHERE ID_VENTA = @ID_VENTA;

            SET @MSJ = 'VENTA MODIFICADA';
        END
        ELSE
        BEGIN
            SET @MSJ = 'NO SE PUEDE MODIFICAR LA VENTA YA QUE NO EST� PENDIENTE';
        END
    END
END
GO

CREATE OR ALTER PROCEDURE GUARDAR_DETALLE_VENTA(
    @ID_VENTA INT OUT,
    @ID_PRODUCTO INT,
    @CANTIDAD INT,
    @PRECIO_VENTA DECIMAL(10, 2),
    @MSJ VARCHAR(200) OUT)
AS
BEGIN
    DECLARE @EXISTENCIA INT
    DECLARE @DESCRIPCION VARCHAR(200)
    
    -- Verificar la existencia del producto
    SET @EXISTENCIA = (SELECT STOCK FROM PRODUCTOS WHERE ID_PRODUCTO = @ID_PRODUCTO)
    
    IF NOT EXISTS (SELECT 1 FROM VENTAS WHERE ID_VENTA = @ID_VENTA)
    BEGIN
        SET @MSJ = 'NO SE PUEDE AGREGAR EL DETALLE YA QUE LA VENTA NO EXISTE'
    END
    ELSE
    BEGIN
        -- Verificar si la venta est� en estado PENDIENTE
        IF EXISTS (SELECT 1 FROM VENTAS WHERE ID_VENTA = @ID_VENTA AND ESTADO = 'PENDIENTE')
        BEGIN
            IF (@EXISTENCIA < @CANTIDAD)
            BEGIN
                SET @MSJ = 'CANTIDAD INSUFICIENTE EN EL STOCK'
            END
            ELSE
            BEGIN
                IF NOT EXISTS (SELECT 1 FROM DETALLE_VENTAS WHERE ID_VENTA = @ID_VENTA AND ID_PRODUCTO = @ID_PRODUCTO)
                BEGIN
                    IF @CANTIDAD <= 0
                    BEGIN
                        SET @MSJ = 'LA CANTIDAD DE PRODUCTOS DEBE SER MAYOR A 0'
                    END
                    ELSE
                    BEGIN
                        -- Insertar el detalle de venta
                        INSERT INTO DETALLE_VENTAS(ID_VENTA, ID_PRODUCTO, CANTIDAD, PRECIO_VENTA)
                        VALUES (@ID_VENTA, @ID_PRODUCTO, @CANTIDAD, @PRECIO_VENTA)
                        SET @MSJ = 'DETALLE DE VENTA INGRESADO'
                    END
                END
                ELSE
                BEGIN
                    -- Actualizar el detalle de venta si ya existe
                    UPDATE DETALLE_VENTAS
                    SET CANTIDAD = @CANTIDAD, PRECIO_VENTA = @PRECIO_VENTA
                    WHERE ID_VENTA = @ID_VENTA AND ID_PRODUCTO = @ID_PRODUCTO
                    SET @DESCRIPCION = (SELECT NOMBRE FROM PRODUCTOS WHERE ID_PRODUCTO = @ID_PRODUCTO)
                    SET @MSJ = 'DETALLE DE VENTA ACTUALIZADO PARA ' + @DESCRIPCION
                END
            END
        END
        ELSE
        BEGIN
            SET @MSJ = 'NO SE PUEDE MODIFICAR LA VENTA YA QUE NO EST� EN ESTADO PENDIENTE'
        END
    END
END
GO

--SELECT ID_VENTA, METODOPAGO, FECHA, V.ID_CLIENTE, C.NOMBRE_COMPLETO, V.ID_VENDEDOR, VV.NOMBRE_COMPLETO, TOTAL, ESTADO
--FROM VENTAS V
--INNER JOIN CLIENTES C ON C.ID_CLIENTE = V.ID_CLIENTE
--INNER JOIN VENDEDORES VV ON VV.ID_VENDEDOR = V.ID_VENDEDOR;


--SELECT ID_VENTA,D.ID_PRODUCTO,NOMBRE,CANTIDAD,PRECIO_VENTA 
--FROM DETALLE_VENTAS D INNER JOIN PRODUCTOS P
--ON D.ID_PRODUCTO = P.ID_PRODUCTO