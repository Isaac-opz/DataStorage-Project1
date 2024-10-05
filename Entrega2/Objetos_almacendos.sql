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

CALL analizar_trafico('2024-09-01', '2024-10-01');


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


DELIMITER $$

CREATE TRIGGER auditar_cambios_documentos
AFTER UPDATE ON documento
FOR EACH ROW
BEGIN
    IF OLD.nombre_documento <> NEW.nombre_documento 
        OR OLD.visibilidad <> NEW.visibilidad 
        OR OLD.id_categoria <> NEW.id_categoria THEN
        INSERT INTO auditoria_documentos (id_documento, cambio, fecha_cambio)
        VALUES (NEW.id_documento, 'Se han actualizado campos del documento', NOW());
    END IF;
END $$

DELIMITER ;
