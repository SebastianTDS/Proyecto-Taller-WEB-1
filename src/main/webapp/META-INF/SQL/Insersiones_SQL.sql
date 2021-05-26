USE db;

INSERT INTO carrera(nombre) VALUES ('Desarrollo Web'), ('Desarrollo Moviles');

INSERT INTO materia(nombre) VALUES ('Basica I'), ('Matematica'), ('Ingles Tecnico I'), ('Informatica General');

INSERT INTO grupo(ctdMaxima, descripcion, nombre, privado, turno, carrera_id, materia_id) 
VALUES (2, 'Trabajo practico basica 1', 'Equipo dinamita', 1, 'NOCHE', 1, 1),
	(4, 'TP Matematica', 'Equipo flama', 0, 'TARDE', 2, 2);