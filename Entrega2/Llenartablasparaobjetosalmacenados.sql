INSERT INTO usuario (id_usuario, nombre_usuario, correo_electronico, ciudad, departamento, pregunta_secreta, respuesta_secreta) 
VALUES
(1, 'jdoe', 'jdoe@example.com', 'Bogotá', 'Cundinamarca', 'Nombre de tu primera mascota', 'Firulais'),
(2, 'jdoe2', 'jdoe@example2.com', 'Bogotá', 'Cundinamarca', 'Nombre de tu primera mascota', 'Firulais'),
(3, 'jdoe3', 'jdoe@example3.com', 'Bogotá', 'Cundinamarca', 'Nombre de tu primera mascota', 'Firulais'),
(4, 'pfernandez', 'pfernandez@example.com', 'Cartagena', 'Bolívar', 'Ciudad de nacimiento', 'Cartagena'),
(5, 'lrojas', 'lrojas@example.com', 'Barranquilla', 'Atlántico', 'Nombre de tu mascota', 'Sasha'),
(6, 'mmartinez', 'mmartinez@example.com', 'Bucaramanga', 'Santander', 'Nombre de tu hermano', 'Jorge'),
(7, 'vlopez', 'vlopez@example.com', 'Manizales', 'Caldas', 'Color favorito', 'Azul'),
(8, 'dmorales', 'dmorales@example.com', 'Pereira', 'Risaralda', 'Nombre de tu profesor favorito', 'Lucía'),
(9, 'aramos', 'aramos@example.com', 'Santa Marta', 'Magdalena', 'Primera película vista en cine', 'Titanic'),
(10, 'cortega', 'cortega@example.com', 'Tunja', 'Boyacá', 'Primer empleo', 'Repartidor'),
(11, 'mrodriguez', 'mrodriguez@example.com', 'Bogotá', 'Cundinamarca', 'Nombre de tu primer perro', 'Max'),
(12, 'jsmith', 'jsmith@example.com', 'Medellín', 'Antioquia', 'Nombre de tu colegio', 'San Luis'),
(13, 'ajaneth', 'ajaneth@example.com', 'Cali', 'Valle del Cauca', 'Mejor amigo de infancia', 'Carlos'),
(14, 'Bryan', 'Bryancortez@example.com', 'Bogotá', 'Cundinamarca', 'Nombre de tu primera mascota', 'Firulais'),
(15, 'Nicolas Pena', 'nicolas@example.com', 'Bogotá', 'Cundinamarca', 'Nombre de tu primera mascota', 'Firulais'),
(16, 'Nicolas', 'nicolas1@example.com', 'Bogotá', 'Cundinamarca', 'Nombre de tu primera mascota', 'Firulais'),
(17, 'Joan', 'joan@example.com', 'Bogotá', 'Cundinamarca', 'Nombre de tu primera mascota', 'Firulais'),
(18, 'Pedro infante', 'pedrito@example.com', 'Cali', 'Valle del Cauca', 'Nombre de tu primera mascota', 'Firulais');


-- Tabla: categoria
INSERT INTO categoria (id_categoria, nombre_categoria) VALUES
(1, 'Desarrollo web'),
(2, 'Bases de datos relacionales/NoSQL'),
(3, 'Programación en un lenguaje determinado'),
(4, 'Analítica de datos'),
(5, 'Visualización de datos'),
(6, 'Inteligencia artificial'),
(7, 'Machine learning'),
(8, 'Otro');

-- Tabla: documento
INSERT INTO documento (id_documento, nombre_documento, descripcion, fecha_publicacion, url, visibilidad, valoracion, id_categoria, num_descargas, num_vistas, num_comentarios, id_usuario) VALUES
(1, 'Nuevo Documento', 'Descripción del documento', '2024-09-30 22:44:08', 'http://document-url.com', 'privado', 5, 2, 2, 2, 6, 1),
(2, 'Nuevo Documento', 'Descripción del documento', '2024-09-30 22:44:36', 'http://document-url.com', 'publico', 0, 2, 4, 3, 8, 1),
(3, 'Nuevo Documento', 'Descripción del documento', '2024-09-30 23:56:15', 'http://document-url.com', 'publico', 3.7, 2, 0, 0, 0, 1),
(4, 'Nuevo Documento', 'Descripción del documento', '2024-10-01 00:03:36', 'http://document-url.com', 'publico', 4, 3, 0, 0, 0, 1),
(5, 'Visualización de datos con Tableau', 'Curso práctico de Tableau', '2024-09-05 00:00:00', 'http://example.com/tableau', 'publico', 3.3, 6, 35, 95, 12, 5),
(6, 'Modelado de datos con MongoDB', 'Bases de NoSQL y MongoDB', '2024-09-06 00:00:00', 'http://example.com/mongodb', 'privado', 3.7, 2, 25, 70, 9, 6),
(7, 'Redes neuronales con Python', 'Implementación de redes neuronales', '2024-09-07 00:00:00', 'http://example.com/neural', 'publico', 4.7, 5, 40, 105, 18, 7),
(8, 'Programación avanzada en JavaScript', 'JavaScript avanzado y frameworks', '2024-09-08 00:00:00', 'http://example.com/js', 'publico', 4.1, 1, 55, 150, 22, 8),
(9, 'Optimización de queries en SQL', 'Guía avanzada de SQL', '2024-09-09 00:00:00', 'http://example.com/sql-advanced', 'privado', 4.9, 2, 38, 80, 8, 9),
(10, 'Data Science con R', 'Análisis de datos con R', '2024-09-10 00:00:00', 'http://example.com/r', 'publico', 4.3, 4, 65, 160, 25, 10),
(11, 'Curso básico de HTML', 'Introducción al lenguaje de marcado HTML', '2024-09-01 00:00:00', 'http://example.com/html', 'publico', 4.8, 1, 50, 120, 15, 1),
(12, 'Guía de SQL para principiantes', 'SQL desde cero para bases de datos', '2024-09-02 00:00:00', 'http://example.com/sql', 'publico', 4.5, 2, 45, 110, 13, 2),
(13, 'Conceptos básicos de Python', 'Tutorial de Python desde nivel básico', '2024-09-03 00:00:00', 'http://example.com/python', 'publico', 4, 3, 60, 130, 20, 3),
(14, 'Introducción a Machine Learning', 'Machine Learning básico', '2024-09-04 00:00:00', 'http://example.com/ml', 'privado', 4.6, 7, 30, 90, 10, 4),
(15, 'Nuevo Documento', 'Descripción del documento', '2024-10-05 11:08:40', 'http://document-url.com', 'publico', 0, 3, 0, 0, 0, 2),
(16, 'Mongo db para dummies', 'Descripción del documento', '2024-10-06 19:04:42', 'http://document-url.com', 'publico', 0, 3, 0, 0, 0, 3),
(17, 'Firebase para dummies', 'Descripción del documento', '2024-10-06 19:12:26', 'http://document-url.com', 'publico', 0, 2, 0, 0, 0, 3),
(18, 'Firebase para dummies', 'Descripción del documento', '2024-10-06 19:26:29', 'http://document-url.com', 'publico', 0, 2, 0, 0, 0, 3),
(19, 'Firebase para dummies', 'Descripción del documento', '2024-10-06 19:33:52', 'http://document-url.com', 'publico', 0, 2, 0, 0, 0, 4),
(20, 'Firebase para dummies', 'Descripción del documento', '2024-10-07 10:19:39', 'http://document-url.com', 'publico', 0, 2, 0, 0, 0, 4);


-- Tabla: autor_documento
INSERT INTO autor_documento (id_autor_documento, id_documento, id_usuario, publico) VALUES
(1, 1, 1, 'Si'),
(2, 2, 1, 'Si'),
(3, 3, 1, 'Si'),
(4, 4, 1, 'Si'),
(5, 5, 5, 'Si'),
(6, 6, 6, 'No'),
(7, 7, 7, 'Si'),
(8, 8, 8, 'Si'),
(9, 9, 9, 'No'),
(10, 10, 10, 'Si'),
(11, 1, 1, 'Si'),
(12, 2, 2, 'Si'),
(13, 3, 3, 'Si'),
(14, 4, 4, 'No'),
(15, 15, 2, 'Si'),
(16, 16, 3, 'Si'),
(17, 17, 3, 'Si'),
(18, 18, 3, 'Si'),
(19, 19, 4, 'Si'),
(20, 20, 4, 'Si');



-- Tabla: comentario
INSERT INTO comentario (id_comentario, id_documento, id_usuario, comentario, fecha_comentario, id_metacomentario) VALUES
(1, 1, 1, 'Gran introducción a HTML', '2024-09-02 00:00:00', NULL),
(2, 2, 2, 'Me ayudó mucho a entender SQL', '2024-09-03 00:00:00', NULL),
(3, 3, 3, 'Excelente tutorial de Python', '2024-09-04 00:00:00', NULL),
(4, 4, 4, 'Machine learning explicado de manera sencilla', '2024-09-05 00:00:00', NULL),
(5, 5, 5, 'Aprendí a usar Tableau', '2024-09-06 00:00:00', NULL),
(6, 1, 1, 'Este es un comentario de prueba.', '2024-10-01 00:28:08', NULL),
(7, 1, 1, 'Este es un comentario de prueeba.', '2024-10-01 00:32:50', NULL),
(8, 1, 1, 'Este es un comentario de prueeba.', '2024-10-01 11:50:09', NULL),
(9, 4, 9, 'Machine learning es más fácil de lo que pensé', '2024-09-10 00:00:00', 4),
(10, 5, 10, 'Excelente curso de Tableau', '2024-09-11 00:00:00', 5),
(11, 2, 1, 'Este es un comentario de prueeba.', '2024-10-05 10:12:27', NULL),
(12, 2, 1, 'Esta es una respuesta al comentario.', '2024-10-05 10:12:50', 2),
(13, 2, 1, 'Este es un comentario de prueeba.', '2024-10-05 11:07:49', NULL),
(14, 2, 1, 'Este es un comentario de prueeba.', '2024-10-05 14:55:39', NULL),
(15, 2, 1, 'Necesito fuentes.', '2024-10-06 18:58:55', NULL),
(16, 2, 1, 'Esta es una respuesta al comentario.', '2024-10-06 18:59:08', 2),
(17, 2, 1, 'Necesito fuentes.', '2024-10-07 07:47:50', NULL);

-- Tabla: descarga
INSERT INTO descarga (id_descarga, fecha_hora, id_usuario, id_documento) VALUES
(1, '2024-10-01 11:25:36.146', 1, 1),
(2, '2024-10-01 11:26:01.933', 1, 1),
(3, '2024-09-03 09:15:00.000', 3, 3),
(4, '2024-09-03 14:00:00.000', 4, 4),
(5, '2024-09-04 11:45:00.000', 5, 5),
(6, '2024-09-04 13:20:00.000', 6, 6),
(7, '2024-09-05 08:30:00.000', 7, 7),
(8, '2024-09-05 17:00:00.000', 8, 8),
(9, '2024-09-06 09:30:00.000', 9, 9),
(10, '2024-09-06 16:00:00.000', 10, 10),
(11, '2024-09-02 10:00:00.000', 1, 1),
(12, '2024-09-02 12:30:00.000', 2, 2),
(13, '2024-10-01 19:04:25.345', 4, 2),
(14, '2024-10-01 21:08:36.347', 4, 2),
(15, '2024-10-06 19:44:44.046', 1, 2),
(16, '2024-10-06 19:45:05.640', 1, 2);


-- Tabla: historial_contrasena
INSERT INTO historial_contrasena (id_historial, id_usuario, contrasena, fecha_cambio, estado) VALUES
(1, 14, 'Password1234', '2024-10-01 14:19:39', 'activa'),
(3, 15, 'Password1234', '2024-10-01 14:53:31', 'inactiva'),
(4, 15, 'Password12345', '2024-10-01 14:56:49', 'inactiva'),
(6, 16, 'Password1234', '2024-10-01 15:20:45', 'inactiva'),
(7, 17, 'Password1234', '2024-10-01 19:01:19', 'activa'),
(8, 16, 'Password1235', '2024-10-01 19:02:39', 'activa'),
(9, 18, 'Password1234', '2024-10-06 19:15:30', 'activa');


-- Tabla: valoracion
INSERT INTO valoracion (id_valoracion, estrellas, fecha_valoracion, id_usuario, id_documento) VALUES
(1, 3, '2024-10-01 00:51:08', 1, 3),
(2, 3, '2024-10-01 12:04:22', 2, 3),
(3, 5, '2024-10-01 12:04:30', 3, 3),
(48, 4, '2024-09-12 14:15:20', 3, 4),
(49, 2, '2024-09-13 16:30:45', 1, 5),
(50, 5, '2024-09-14 18:45:10', 2, 5),
(51, 3, '2024-09-15 10:00:20', 3, 5),
(52, 4, '2024-09-16 12:15:45', 1, 6),
(53, 2, '2024-09-17 14:30:10', 2, 6),
(54, 5, '2024-09-18 16:45:35', 3, 6),
(55, 5, '2024-10-05 12:21:18', 3, 1),
(56, 4, '2024-10-05 12:21:25', 2, 1);

-- Tabla: vistopor
INSERT INTO vistopor (id_vistopor, fecha_hora, id_usuario, id_documento) VALUES
(1, '2024-10-01 11:27:07.473', 1, 1),
(2, '2024-10-01 11:28:21.366', 1, 1),
(3, '2024-09-01 10:00:00.000', 1, 1),
(4, '2024-09-01 12:15:30.000', 2, 2),
(5, '2024-09-02 14:30:45.000', 3, 3),
(6, '2024-09-03 16:45:10.000', 1, 2),
(7, '2024-09-04 18:00:25.000', 2, 1),
(8, '2024-09-05 20:15:40.000', 3, 2),
(9, '2024-09-06 09:30:55.000', 1, 3),
(10, '2024-09-07 11:45:20.000', 2, 3),
(11, '2024-09-08 13:00:35.000', 3, 1),
(12, '2024-09-09 15:15:50.000', 1, 4),
(13, '2024-09-10 17:30:05.000', 2, 4),
(14, '2024-09-11 19:45:20.000', 3, 4),
(15, '2024-09-12 09:00:35.000', 1, 5),
(16, '2024-09-13 11:15:50.000', 2, 5),
(17, '2024-09-14 13:30:05.000', 3, 5),
(18, '2024-09-15 15:45:20.000', 1, 6),
(19, '2024-09-16 17:00:35.000', 2, 6),
(20, '2024-09-17 19:15:50.000', 3, 6),
(21, '2024-09-18 09:30:05.000', 1, 1),
(22, '2024-09-19 11:45:20.000', 2, 2),
(23, '2024-09-20 13:00:35.000', 3, 3),
(24, '2024-09-21 15:15:50.000', 1, 2),
(25, '2024-09-22 17:30:05.000', 2, 1),
(26, '2024-09-23 19:45:20.000', 3, 2),
(27, '2024-09-24 09:00:35.000', 1, 3),
(28, '2024-09-25 11:15:50.000', 2, 3),
(29, '2024-09-26 13:30:05.000', 3, 1),
(30, '2024-09-27 15:45:20.000', 1, 4),
(31, '2024-09-28 17:00:35.000', 2, 4),
(32, '2024-09-29 19:15:50.000', 3, 4),
(33, '2024-09-30 09:30:05.000', 1, 5),
(34, '2024-10-05 10:03:42.343', 1, 2),
(35, '2024-10-06 19:35:11.702', 1, 2),
(36, '2024-10-06 19:35:37.971', 1, 2);


