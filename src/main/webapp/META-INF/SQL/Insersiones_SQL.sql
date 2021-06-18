USE db;

INSERT INTO carrera(nombre)
VALUES ('Desarrollo Web'),
       ('Desarrollo Moviles');

INSERT INTO materia(nombre)
VALUES ('Basica I'),
       ('Matematica'),
       ('Ingles Tecnico I'),
       ('Informatica General');

INSERT INTO grupo(cantidadMax, descripcion, nombre, cerrado, turno, carrera_id, materia_id)
VALUES (1, 'Trabajo practico basica 1', 'Equipo dinamita', 1, 'NOCHE', 1, 1),
       (4, 'TP Matematica', 'Equipo flama', 0, 'TARDE', 2, 2);

INSERT INTO usuario(email, nombre, password, rol)
VALUES ('k4tnet@outlook.com', 'Marcelo', '1234', 'Admin');

INSERT INTO usuario(email, nombre, password, rol)
VALUES ('usuario2@outlook.com', 'Andres', '1234', 'Admin');

INSERT INTO usuario(email, nombre, password, rol)
VALUES ('usuario3@outlook.com', 'Mayra', '1234', 'Admin');

INSERT INTO mensaje(fecha, mensaje, grupo_id, usuario_id)
VALUES ('2021-06-10 20:27:06', 'MENSAJE 1 GRUPO 1', 1, 1),
       ('2021-06-11 22:27:06', 'MENSAJE 2 GRUPO 1', 2, 3),
       ('2021-06-13 22:27:06', 'MENSAJE 3 GRUPO 1', 1, 2),
       ('2021-06-14 10:27:06', 'MENSAJE 4 GRUPO 1', 1, 1),
       ('2021-06-14 12:27:06', 'MENSAJE 1 GRUPO 2', 2, 3),
       ('2021-06-14 15:27:06', 'MENSAJE 2 GRUPO 2', 2, 1);




