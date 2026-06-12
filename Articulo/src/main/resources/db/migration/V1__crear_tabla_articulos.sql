CREATE TABLE articulos(
    id_articulo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    marca VARCHAR(100) NOT NULL,
    stock INT NOT NULL,
    precio INT NOT NULL
);

INSERT INTO articulos (nombre, marca, stock, precio) VALUES ('Refrigerador', 'Samsung', 10, 700000);