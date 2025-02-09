CREATE DATABASE gestionEventos;
USE gestionEventos;

-- Tabla de Eventos
CREATE TABLE Eventos (
    id_evento INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    fecha_evento DATE NOT NULL,
    entradas_disponibles INT NOT NULL,
    precio DOUBLE NOT NULL
);

-- Tabla de Compras
CREATE TABLE Compras (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    fecha_compra DATE NOT NULL,
    numero_tarjeta VARCHAR(16) NOT NULL,
    id_evento INT NOT NULL,
    FOREIGN KEY (id_evento) REFERENCES Eventos(id_evento) ON DELETE CASCADE
);

-- Insertar eventos
INSERT INTO Eventos (nombre, fecha_evento, entradas_disponibles, precio) VALUES
('Concierto Rock', '2025-06-15', 500, 50.00),
('Festival Jazz', '2025-07-20', 300, 35.00),
('Teatro Clásico', '2025-08-10', 200, 25.00),
('Resurrection Fest', '2025-06-22', 0, 100.00),
('Cocacola Music Experience', '2025-01-01', 10, 50.00);

-- Insertar compras
INSERT INTO Compras (dni, nombre, apellidos, fecha_compra, numero_tarjeta, id_evento) VALUES
('12345678A', 'Juan', 'Pérez', '2025-06-01', '1111222233334444', 1),
('87654321B', 'Ana', 'Gómez', '2025-06-02', '5555666677778888', 2),
('11223344C', 'Carlos', 'López', '2025-06-03', '9999000011112222', 3);

--ENUNCIADO
--Comprobar que la fecha del evento no se haya pasado antes de hacer la compra
--Comporbar que hay stock suficiente antes de hacer la compra
--Comprobar que el stock del evento baja al hacer una compra
--Que salga un mensaje de sold out si el stock del evento es 0 y se intenta hacer una compra
--Que se persista en base de datos
--Enseñar al cliente su entrada al realizar una compra
--Validar DNI
--Validar que Nombre y Apellido Empiecen por mayuscula
--Validar que el número de tarjeta tenga 16 dígitos
--Validar el formato de las fechas