-- Para la primer parte las queries de la entrega anterior sirvieron para generar unas vistas con datos de la interracion del usuario con la platafroma:
-- 1. Cantidad de Documentos por Categoría
-- Esta vista se utiliza para obtener el número de documentos publicados en cada categoría.
CREATE VIEW doc_por_categoria AS
SELECT 
    (SELECT c.categoria FROM categoria c WHERE c.id_categoria = d.id_categoria) AS categoria, 
    COUNT(d.id_documento) AS cantidad_documentos 
FROM 
    documento d 
GROUP BY 
    d.id_categoria
ORDER BY 
    cantidad_documentos DESC;

-- 2. Usuarios que han visto más documentos de una categoría específica (seguridad informática)
-- Esta vista se utiliza para obtener los usuarios que han visto más documentos dentro de una categoría específica.
CREATE VIEW usr_vistos_por_categoria AS
SELECT 
    (SELECT u.nombre_usuario FROM usuario u WHERE u.id_usuario = v.id_usuario) AS nombre_usuario,
    (SELECT c.categoria FROM categoria c WHERE c.id_categoria = d.id_categoria) AS categoria, 
    COUNT(v.id_vistopor) AS total_vistos 
FROM 
    vistopor v, 
    documento d 
WHERE 
    v.id_documento = d.id_documento 
    AND d.id_categoria = (SELECT id_categoria FROM categoria WHERE categoria = 'seguridad informática') 
GROUP BY 
    v.id_usuario, d.id_categoria 
ORDER BY 
    total_vistos DESC;

-- 3. Documentos Nunca Visualizados por Usuarios
-- Esta vista muestra los documentos que no han sido visualizados por ningún usuario.
CREATE VIEW doc_nunca_vistos AS
SELECT 
    nombre_documento
FROM 
    documento 
WHERE 
    id_documento NOT IN (SELECT id_documento FROM vistopor);

-- 4. Promedio de Valoración por Documento
-- Esta vista se utiliza para obtener el promedio de valoraciones de cada documento.
CREATE VIEW promedio_valoracion_doc AS
SELECT 
    (SELECT d.nombre_documento FROM documento d WHERE d.id_documento = v.id_documento) AS nombre_documento, 
    AVG(v.estrellas) AS promedio_valoracion 
FROM 
    valoracion v 
GROUP BY 
    v.id_documento;

-- 5. Documentos Publicados en el Último Mes con sus Valoraciones
-- Esta vista se utiliza para listar los documentos publicados en el último mes junto con sus valoraciones.
CREATE VIEW doc_publicados_ultimo_mes AS
SELECT 
    nombre_documento, 
    fecha_publicacion, 
    valoracion
FROM 
    documento
WHERE 
    fecha_publicacion >= NOW() - INTERVAL 1 MONTH
ORDER BY 
    fecha_publicacion DESC;

-- 6. Usuarios con el Mayor Número de Descargas
-- Esta vista se utiliza para listar los usuarios que han realizado el mayor número de descargas.
CREATE VIEW usr_mayor_descargas AS
SELECT 
    (SELECT u.nombre_usuario FROM usuario u WHERE u.id_usuario = d.id_usuario) AS nombre_usuario,
    COUNT(d.id_descarga) AS total_descargas 
FROM 
    descarga d 
GROUP BY 
    d.id_usuario 
ORDER BY 
    total_descargas DESC;

-- 7. Documentos con la Mayor Cantidad de Comentarios
-- Esta vista se utiliza para listar los documentos con la mayor cantidad de comentarios.
CREATE VIEW doc_mayor_comentarios AS
SELECT 
    (SELECT nombre_documento FROM documento d WHERE d.id_documento = c.id_documento) AS nombre_documento,
    COUNT(c.id_comentario) AS total_comentarios
FROM 
    comentario c 
GROUP BY 
    c.id_documento
ORDER BY 
    total_comentarios DESC;

-- 8. Usuarios que han dado Valoraciones de 5 Estrellas
-- Esta vista se utiliza para listar los usuarios que han dado valoraciones de 5 estrellas.
CREATE VIEW usr_valoracion_5estrellas AS
SELECT 
    (SELECT u.nombre_usuario FROM usuario u WHERE u.id_usuario = v.id_usuario) AS nombre_usuario,
    COUNT(*) AS valoraciones_cinco_estrellas 
FROM 
    valoracion v 
WHERE 
    estrellas = 5 
GROUP BY 
    v.id_usuario 
HAVING 
    valoraciones_cinco_estrellas > 0
ORDER BY 
    valoraciones_cinco_estrellas DESC;

-- 9. Documentos que No Han Recibido Comentarios
-- Esta vista se utiliza para listar los documentos que no han recibido comentarios.
CREATE VIEW doc_sin_comentarios AS
SELECT 
    nombre_documento 
FROM 
    documento 
WHERE 
    id_documento NOT IN (SELECT id_documento FROM comentario);

-- 10. Usuarios que han Visualizado y Luego Descargado Documentos
-- Esta vista se utiliza para listar los usuarios que han visualizado y luego descargado documentos.
CREATE VIEW usr_visto_y_descargado AS
SELECT 
    (SELECT nombre_usuario FROM usuario u WHERE u.id_usuario = v.id_usuario) AS nombre_usuario
FROM 
    vistopor v
WHERE 
    id_documento IN (SELECT id_documento FROM descarga WHERE v.id_usuario = descarga.id_usuario);

-- 11 Documentos populares segun la interracion total
CREATE VIEW vista_documentos_populares AS
SELECT 
    d.id_documento,
    d.nombre_documento,
    d.descripcion,
    d.fecha_publicacion,
    d.url,
    d.valoracion,
    d.num_vistas,
    d.num_descargas,
    COUNT(c.id_comentario) AS num_comentarios
FROM 
    documento d
LEFT JOIN 
    comentario c ON d.id_documento = c.id_documento
GROUP BY 
    d.id_documento
ORDER BY 
    d.num_vistas DESC, d.num_descargas DESC, d.valoracion DESC;


--Actualizar promedio de valoracion
-- Trigger para calcular la valoracion promedio de un documento 

DELIMITER $$

CREATE TRIGGER actualizar_promedio_valoracion
AFTER INSERT ON valoracion
FOR EACH ROW
BEGIN
    DECLARE promedio DECIMAL(2, 1);

    -- Calcular el promedio de todas las valoraciones del documento
    SELECT AVG(estrellas) INTO promedio
    FROM valoracion
    WHERE id_documento = NEW.id_documento;

    -- Actualizar la valoración promedio en la tabla documento
    UPDATE documento
    SET valoracion = promedio
    WHERE id_documento = NEW.id_documento;
END$$

DELIMITER ;


DROP PROCEDURE IF EXISTS analizar_trafico;


--Procedimiento de Analizar trafico 
--Este procedimiento permite realizar análisis avanzados sobre el tráfico y las interacciones en la plataforma durante un periodo de tiempo especificado.  

DELIMITER $$

CREATE PROCEDURE analizar_trafico(IN fecha_inicio DATE, IN fecha_fin DATE)
BEGIN
    -- Crear una tabla temporal para almacenar los resultados
    CREATE TEMPORARY TABLE IF NOT EXISTS resumen_trafico (
        total_visitas INT,
        total_descargas INT,
        total_comentarios INT,
        total_valoraciones INT,
        promedio_valoracion DECIMAL(3,2),
        usuarios_unicos INT,
        documentos_publicados INT,
        comentarios_por_documento DECIMAL(5,2),
        descargas_por_usuario DECIMAL(5,2),
        visitas_por_documento DECIMAL(5,2),
        valoraciones_por_documento DECIMAL(5,2),
        comentarios_por_usuario DECIMAL(5,2),
        documentos_mas_descargados VARCHAR(255),
        documento_mas_valorado VARCHAR(255)
    );

    -- Insertar los datos analíticos
    INSERT INTO resumen_trafico (total_visitas, total_descargas, total_comentarios, total_valoraciones, promedio_valoracion, usuarios_unicos, 
                                 documentos_publicados, comentarios_por_documento, descargas_por_usuario, visitas_por_documento, 
                                 valoraciones_por_documento, comentarios_por_usuario, documentos_mas_descargados, documento_mas_valorado)
    SELECT 
        -- Contar las visualizaciones totales en el período
        (SELECT COUNT(*) FROM vistopor v WHERE v.fecha_hora BETWEEN fecha_inicio AND fecha_fin) AS total_visitas,

        -- Contar las descargas totales en el período
        (SELECT COUNT(*) FROM descarga d WHERE d.fecha_hora BETWEEN fecha_inicio AND fecha_fin) AS total_descargas,

        -- Contar los comentarios totales en el período
        (SELECT COUNT(*) FROM comentario c WHERE c.fecha_comentario BETWEEN fecha_inicio AND fecha_fin) AS total_comentarios,

        -- Contar las valoraciones totales en el período
        (SELECT COUNT(*) FROM valoracion vr WHERE vr.fecha_valoracion BETWEEN fecha_inicio AND fecha_fin) AS total_valoraciones,

        -- Calcular el promedio general de valoraciones en el período
        (SELECT AVG(vr.estrellas) FROM valoracion vr WHERE vr.fecha_valoracion BETWEEN fecha_inicio AND fecha_fin) AS promedio_valoracion,

        -- Contar la cantidad de usuarios únicos que han interactuado en el período
        (SELECT COUNT(DISTINCT v.id_usuario) FROM vistopor v WHERE v.fecha_hora BETWEEN fecha_inicio AND fecha_fin) AS usuarios_unicos,

        -- Contar el número de documentos publicados en el período
        (SELECT COUNT(*) FROM documento d WHERE d.fecha_publicacion BETWEEN fecha_inicio AND fecha_fin) AS documentos_publicados,

        -- Calcular el promedio de comentarios por documento
        (SELECT AVG(d.num_comentarios) FROM documento d WHERE d.fecha_publicacion BETWEEN fecha_inicio AND fecha_fin) AS comentarios_por_documento,

        -- Calcular el promedio de descargas por usuario
        (SELECT COUNT(*) / COUNT(DISTINCT d.id_usuario) FROM descarga d WHERE d.fecha_hora BETWEEN fecha_inicio AND fecha_fin) AS descargas_por_usuario,

        -- Calcular el promedio de visitas por documento
        (SELECT COUNT(*) / COUNT(DISTINCT v.id_documento) FROM vistopor v WHERE v.fecha_hora BETWEEN fecha_inicio AND fecha_fin) AS visitas_por_documento,

        -- Calcular el promedio de valoraciones por documento
        (SELECT COUNT(*) / COUNT(DISTINCT vr.id_documento) FROM valoracion vr WHERE vr.fecha_valoracion BETWEEN fecha_inicio AND fecha_fin) AS valoraciones_por_documento,

        -- Calcular el promedio de comentarios por usuario
        (SELECT COUNT(*) / COUNT(DISTINCT c.id_usuario) FROM comentario c WHERE c.fecha_comentario BETWEEN fecha_inicio AND fecha_fin) AS comentarios_por_usuario,

        -- Encontrar el documento más descargado
        (SELECT d.nombre_documento FROM documento d 
            JOIN descarga dg ON d.id_documento = dg.id_documento 
            WHERE dg.fecha_hora BETWEEN fecha_inicio AND fecha_fin
            GROUP BY d.nombre_documento 
            ORDER BY COUNT(dg.id_descarga) DESC 
            LIMIT 1) AS documentos_mas_descargados,

        -- Encontrar el documento mejor valorado
        (SELECT d.nombre_documento FROM documento d 
            JOIN valoracion vr ON d.id_documento = vr.id_documento 
            WHERE vr.fecha_valoracion BETWEEN fecha_inicio AND fecha_fin
            GROUP BY d.nombre_documento 
            ORDER BY AVG(vr.estrellas) DESC 
            LIMIT 1) AS documento_mas_valorado;

    -- Devolver los resultados de la tabla temporal
    SELECT * FROM resumen_trafico;

    -- Eliminar la tabla temporal después de su uso
    DROP TEMPORARY TABLE IF EXISTS resumen_trafico;
END$$

DELIMITER ;

--Ejemplo de uso 

CALL analizar_trafico('2024-09-01', '2024-10-01');

--Obtener Total descarga por documento
--Funcion para Obtener el total de descarga por documento

DELIMITER $$

CREATE FUNCTION obtener_num_descargas(documento_id INT)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE total_descargas INT;

    -- Consulta para obtener el total de descargas del documento
    SELECT COUNT(*) INTO total_descargas 
    FROM descarga 
    WHERE id_documento = documento_id;

    RETURN total_descargas;
END $$

DELIMITER ;
--Ejemplo de uso de la funcion
SELECT nombre_documento, obtener_num_descargas(id_documento) AS total_descargas
FROM documento;

