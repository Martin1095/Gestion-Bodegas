CREATE TABLE pedidos(
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    fecha_entrega DATE NOT NULL,
    direccion_entrega VARCHAR(100),
    estado_pedido VARCHAR(30)
);

INSERT INTO pedidos(fecha_entrega, direccion_entrega, estado_pedido) VALUES ('2026-06-11','Santiago Centro', 'Enviado');