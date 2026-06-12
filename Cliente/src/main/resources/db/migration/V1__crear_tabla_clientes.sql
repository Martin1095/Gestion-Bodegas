CREATE TABLE clientes(
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    rut VARCHAR(20) NOT NULL UNIQUE,
    correo VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL
);

INSERT INTO clientes (nombre, apellido, rut, correo, telefono) VALUES ('Juan', 'Pérez', '12345678-9', 'juan.perez@example.com', '123456789');
