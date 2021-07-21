USE
db;

INSERT INTO carrera(nombre)
VALUES ('Desarrollo Web'),
       ('Desarrollo Moviles');

INSERT INTO materia(nombre)
VALUES ('Basica I'),
       ('Matematica'),
       ('Ingles Tecnico I'),
       ('Taller web I'),
       ('Taller web II'),
       ('Programacion web I'),
       ('Programacion web II'),
       ('Diseño de aplicaciones web');

INSERT INTO usuario(email, cantidadDeCalificaciones, calificacion, nombre, password, rol)
VALUES ('GLOBAL', 0, 0, 'GLOBAL', 'GLOBAL', 'GLOBAL');

INSERT INTO usuario(email, cantidadDeCalificaciones, calificacion, nombre, password, rol)
VALUES ('usuario3@outlook.com', 0, 0, 'Marcelo Zelaya', '1234', 'Admin');

INSERT INTO usuario(email, cantidadDeCalificaciones, calificacion, nombre, password, rol)
VALUES ('usuario1@outlook.com', 0, 0, 'Gonzalo Fernández', '1234', 'Admin');

INSERT INTO usuario(email, cantidadDeCalificaciones, calificacion, nombre, password, rol)
VALUES ('usuario2@outlook.com', 0, 0, 'Sebastián Trillo', '1234', 'Admin');

INSERT INTO usuario(email, cantidadDeCalificaciones, calificacion, nombre, password, rol)
VALUES ('usuario4@outlook.com', 0, 0, 'Angelo García', '1234', 'Admin');

INSERT INTO grupo(cantidadMax, descripcion, nombre, cerrado, turno, carrera_id, materia_id, esMateria, administrador_id)
VALUES (1, 'Foro de la materia', 'Basica I', 1, 'NOCHE', 1, 1, 1, 4),
       (1, 'Foro de la materia', 'Matemática', 0, 'TARDE', 2, 2, 1, 4),
       (1, 'Foro de la materia', 'Ingles Técnico I', 1, 'NOCHE', 1, 3, 1, 4),
       (1, 'Foro de la materia', 'Taller Web I', 0, 'TARDE', 2, 4, 1, 4),
       (1, 'Foro de la materia', 'Taller Web II', 0, 'TARDE', 2, 5, 1, 4),
       (1, 'Foro de la materia', 'Programación Web I', 0, 'TARDE', 2, 6, 1, 4),
       (1, 'Foro de la materia', 'Programación Web II', 0, 'TARDE', 2, 7, 1, 4),
       (1, 'Foro de la materia', 'Diseño de Aplicaciones Web', 0, 'TARDE', 2, 8, 1, 4);