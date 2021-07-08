USE db;

INSERT INTO carrera(nombre)
VALUES ('Desarrollo Web'),
       ('Desarrollo Moviles');

INSERT INTO materia(nombre)
VALUES ('Basica I'),
       ('Matematica'),
       ('Ingles Tecnico I'),
       ('Informatica General');

INSERT INTO usuario(email, nombre, password, rol)
VALUES ('GLOBAL', 'GLOBAL', 'GLOBAL', 'GLOBAL');

INSERT INTO usuario(email, nombre, password, rol)
VALUES ('k4tnet@outlook.com', 'Marcelo', '1234', 'Admin');

INSERT INTO usuario(email, nombre, password, rol)
VALUES ('usuario2@outlook.com', 'Andres', '1234', 'Admin');

INSERT INTO usuario(email, nombre, password, rol)
VALUES ('usuario3@outlook.com', 'Mayra', '1234', 'Admin');

INSERT INTO grupo(cantidadMax, descripcion, nombre, cerrado, turno, carrera_id, materia_id, esMateria, administrador_id)
VALUES (1,'Foro de la materia', '',1, 'NOCHE', 1, 1, 1, 4 ),
                  (1,'Foro de la materia', '', 0, 'TARDE', 2, 2, 1, 4),
                   (1,'Foro de la materia', '', 1, 'NOCHE', 1, 3, 1, 4),
                   (1,'Foro de la materia', '', 0, 'TARDE', 2, 4, 1, 4);