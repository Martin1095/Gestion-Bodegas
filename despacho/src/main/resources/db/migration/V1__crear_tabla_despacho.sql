CREATE TABLE despachos(
    id_despacho INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    estado VARCHAR(30)
);

INSERT INTO despachos (fecha, estado) VALUES('2026-05-11', 'Recibido');