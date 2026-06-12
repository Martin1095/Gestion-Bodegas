CREATE TABLE bodegas(
    id_bodega INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    direccion VARCHAR(100) NOT NULL
);

INSERT INTO bodegas (nombre, direccion) VALUES ('Bodega de Juan', 'Santiago Centro');