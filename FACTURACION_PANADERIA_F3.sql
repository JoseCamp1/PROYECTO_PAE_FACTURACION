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
    FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTES(ID_CLIENTE),
    FOREIGN KEY (ID_VENDEDOR) REFERENCES VENDEDORES(ID_VENDEDOR),
	CONSTRAINT CHK_METODOPAGO
        CHECK (METODOPAGO IN ('Sinpe', 'Efectivo', 'Tarjeta'))
);

-- TABLA DE DETALLE DE VENTAS
CREATE TABLE DETALLE_VENTAS (
    ID_DETALLE INT PRIMARY KEY IDENTITY(1,1),
    ID_VENTA INT,
    ID_PRODUCTO INT,
    CANTIDAD INT CHECK (CANTIDAD >= 0) NOT NULL,
    SUBTOTAL DECIMAL(10, 2) CHECK (SUBTOTAL >= 0) NOT NULL,
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
('Juan Pérez Pérez', '111111111'),
('Ana Sánchez Sánchez', '222222222'),
('María Rodríguez Rodríguez', '333333333'),
('Pedro Gómez Gómez', '444444444'),
('Luisa Fernández Fernández', '555555555');

-- Insertar datos en la tabla VENDEDORES
INSERT INTO VENDEDORES (NOMBRE_COMPLETO, CEDULA, CORREO_ELECTRONICO) VALUES
('Carlos González González', '999999999', 'carlos@example.com'),
('Laura Martínez Martínez', '888888888', 'laura@example.com'),
('Javier Ramírez Ramírez', '777777777', 'javier@example.com'),
('Sofía López López', '666666666', 'sofia@example.com'),
('Roberto Herrera Herrera', '555555555', 'roberto@example.com');


-- Insertar datos en la tabla PRODUCTOS
INSERT INTO PRODUCTOS (NOMBRE, DESCRIPCION, PRECIO, STOCK) VALUES
('Pan Integral', 'Pan integral de trigo', 2.99, 100),
('Pan de Avena', 'Pan de avena con pasas', 3.49, 75),
('Croissant', 'Croissant recién horneado', 1.99, 50),
('Rosquillas', 'Rosquillas glaseadas', 0.99, 120),
('Baguette', 'Baguette crujiente', 2.49, 80);


-- Insertar datos en la tabla VENTAS
INSERT INTO VENTAS (METODOPAGO, FECHA, ID_CLIENTE, ID_VENDEDOR, TOTAL) VALUES
('Tarjeta', '2023-09-01', 1, 1, 15.99),
('Efectivo', '2023-09-02', 2, 2, 12.49),
('Sinpe', '2023-09-03', 3, 3, 7.99),
('Tarjeta', '2023-09-04', 4, 4, 5.99),
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


