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
                   
INSERT INTO `db`.`evento` (`id`, `fin`, `inicio`, `titulo`, `grupo_id`) VALUES ('1', '2021-07-07T10:00:00', '2021-07-07T05:00:00', 'Almuerzo', '1');
INSERT INTO `db`.`evento` (`id`, `fin`, `inicio`, `titulo`, `grupo_id`) VALUES ('2', '2021-07-08T10:00:00', '2021-07-08T05:00:00', 'Reunion', '1');
INSERT INTO `db`.`evento` (`id`, `fin`, `inicio`, `titulo`, `grupo_id`) VALUES ('3', '2021-07-15T10:00:00', '2021-07-15T05:00:00', 'Presentacion', '1');
INSERT INTO `db`.`evento` (`id`, `fin`, `inicio`, `titulo`, `grupo_id`) VALUES ('4', '2021-07-16T22:00:00', '2021-07-17T12:00:00', 'Presentacion', '1');
INSERT INTO `db`.`evento` (`id`, `fin`, `inicio`, `titulo`, `grupo_id`) VALUES ('5', '2021-07-16T22:00:00', '2021-07-17T12:00:00', 'Presentacion', '5');




