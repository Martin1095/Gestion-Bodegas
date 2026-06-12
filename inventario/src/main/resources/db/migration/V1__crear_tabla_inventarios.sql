CREATE TABLE inventarios(
    id_inventario INT AUTO_INCREMENT PRIMARY KEY,
    stock_actual INT(3) NOT NULL,
    stock_minimo INT(3) NOT NULL,
    ubicacion VARCHAR(100) NOT NULL
);

INSERT INTO inventarios(stock_actual, stock_minimo, ubicacion) VALUES (40, 10, 'Santiago Centro');