CREATE TABLE recepciones(
    id_recepcion INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    cantidad INT(4) NOT NULL
);

INSERT INTO recepciones (fecha, cantidad) VALUES ('2026-05-04', 30);