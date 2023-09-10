--PROYECTO F1
--MODULO:PAEW
--PROFESOR:LUIS_ALONSO_BOGANTES_RODRIGUEZ
--ESTUDIANTE:JOSE_CAMPOS_CHAVES

--***BORRAR LA BASE DE DATOS***
--DROP DATABASE FACTURACION_PANADERIA


-- CREAR BASE DE DATOS 
CREATE DATABASE FACTURACION_PANADERIA;
GO

-- USAR LA BASE DE DATOS
USE FACTURACION_PANADERIA;
GO

-- TABLA DE CLIENTES
CREATE TABLE CLIENTES (
    ID_CLIENTE INT PRIMARY KEY IDENTITY(1,1),
    NOMBRE VARCHAR(50),
    APELLIDO VARCHAR(50),
    TELEFONO VARCHAR(15),
    DIRECCION VARCHAR(100),
    CORREO_ELECTRONICO VARCHAR(100)
);

-- TABLA DE PRODUCTOS
CREATE TABLE PRODUCTOS (
    ID_PRODUCTO INT PRIMARY KEY IDENTITY(1,1),
    NOMBRE VARCHAR(100),
    DESCRIPCION TEXT,
    PRECIO DECIMAL(10, 2),
    STOCK INT
);

-- TABLA DE VENTAS
CREATE TABLE VENTAS (
    ID_VENTA INT PRIMARY KEY IDENTITY(1,1),
    FECHA DATE,
    ID_CLIENTE INT,
    TOTAL DECIMAL(10, 2),
    FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTES(ID_CLIENTE)
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

-- TABLA DE VENDEDORES
CREATE TABLE VENDEDORES (
    ID_VENDEDOR INT PRIMARY KEY IDENTITY(1,1),
    NOMBRE VARCHAR(50),
    APELLIDO VARCHAR(50),
    TELEFONO VARCHAR(15),
    DIRECCION VARCHAR(100),
    CORREO_ELECTRONICO VARCHAR(100),
    ID_VENTA INT,
    FOREIGN KEY (ID_VENTA) REFERENCES VENTAS(ID_VENTA)
);
