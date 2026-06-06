-- Módulos
INSERT INTO modulos (titulo, descripcion, orden) VALUES ('Logica y Conjuntos', 'Logica proposicional, predicados e induccion matematica.', 1);
INSERT INTO modulos (titulo, descripcion, orden) VALUES ('Sucesiones y Sumatorias', 'Patrones, formulas cerradas y propiedades.', 2);
INSERT INTO modulos (titulo, descripcion, orden) VALUES ('Teoria de Numeros y Conjuntos', 'Divisibilidad, congruencias y algoritmos.', 3);

-- Temas Módulo 1
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Logica proposicional', 'Proposiciones, conectores y equivalencias logicas.', 1, 1);
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Predicados', 'Cuantificadores y evaluacion en dominios finitos.', 2, 1);
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Induccion matematica', 'Base, hipotesis y paso inductivo.', 3, 1);

-- Temas Módulo 2
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Induccion matematica', 'Base, hipotesis y paso inductivo.', 1, 2);
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Sucesiones y sumatorias', 'Patrones, formulas cerradas y propiedades.', 2, 2);
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Teoria de operaciones de conjuntos', 'Divisibilidad, congruencias y algoritmos.', 3, 2);
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Residuo chino', 'Sistema de congruencias con modulos coprimos.', 4, 2);

-- Temas Módulo 3
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Logica proposicional', 'Proposiciones, conectores y equivalencias logicas.', 1, 3);
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Predicados', 'Cuantificadores y evaluacion en dominios finitos.', 2, 3);
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Induccion matematica', 'Base, hipotesis y paso inductivo.', 3, 3);
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Sucesiones y sumatorias', 'Patrones, formulas cerradas y propiedades.', 4, 3);
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Teoria de operaciones de conjuntos', 'Divisibilidad, congruencias y algoritmos.', 5, 3);
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Residuo chino', 'Sistema de congruencias con modulos coprimos.', 6, 3);
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Combinatoria', 'Permutaciones, combinaciones y principios de conteo.', 7, 3);
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Teoria de grafos', 'Vertices, aristas, caminos y conectividad.', 8, 3);
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Algebra booleana', 'Operaciones, leyes y simplificacion de expresiones.', 9, 3);
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Funciones de crecimiento', 'Comportamiento asintotico y comparacion de algoritmos.', 10, 3);
INSERT INTO temas (titulo, texto, orden, modulo_id) VALUES ('Cifrado Cesar', 'Cifrado de sustitucion sencillo.', 11, 3);