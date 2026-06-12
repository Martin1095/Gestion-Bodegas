CREATE TABLE proveedores(
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL
);

INSERT INTO proveedores (nombre, correo, telefono) VALUES ('Juan', 'Juan@trabajador.com', '123456789');