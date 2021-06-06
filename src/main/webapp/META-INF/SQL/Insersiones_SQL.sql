USE db;

INSERT INTO carrera(nombre) VALUES ('Desarrollo Web'), ('Desarrollo Moviles');

INSERT INTO materia(nombre) VALUES ('Basica I'), ('Matematica'), ('Ingles Tecnico I'), ('Informatica General');

INSERT INTO grupo(cantidadMax, descripcion, nombre, cerrado, turno, carrera_id, materia_id) 
VALUES (2, 'Trabajo practico basica 1', 'Equipo dinamita', 1, 'NOCHE', 1, 1),
	(4, 'TP Matematica', 'Equipo flama', 0, 'TARDE', 2, 2);
    
INSERT INTO usuario(id, email, password) VALUES (1,'pepe@hotmail.com','1234');
INSERT INTO usuario(id, email, password) VALUES (2,'tata@hotmail.com','1234');
INSERT INTO usuario(id, email, password) VALUES (3,'juja@hotmail.com','1234');


INSERT INTO usuario_grupo(id_usuario, id_grupo) VALUES (2, 1), (1, 1);

/*INSERT INTO notificacion(titulo,usuario_id) VALUES ("Uno",1), ("dos",1), ("tres",1);*/