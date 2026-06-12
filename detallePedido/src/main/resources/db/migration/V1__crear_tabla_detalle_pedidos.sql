CREATE TABLE detalle_pedidos(
    id_detalle_pedido INT AUTO_INCREMENT PRIMARY KEY,
    cantidad INT (999) NOT NULL,
    precio_unitario INT(999) NOT NULL
);

INSERT INTO detalle_pedidos(cantidad, precio_unitario) VALUES (3, 15000);