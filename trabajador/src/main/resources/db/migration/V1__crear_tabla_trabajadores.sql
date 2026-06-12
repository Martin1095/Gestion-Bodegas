CREATE TABLE trabajadores(
    id_trabajador INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    cargo VARCHAR(40) NOT NULL,
    edad INT(2) NOT NULL
);

INSERT INTO trabajadores (nombre, cargo, edad) VALUES ('Juan','Supervisor',20);