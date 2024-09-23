-- 1. Cantidad de Documentos por Categoría
SELECT 
    (SELECT c.categoria FROM categoria c WHERE c.id_categoria = d.id_categoria) AS categoria, 
    COUNT(d.id_documento) AS cantidad_documentos 
FROM 
    documento d 
GROUP BY 
    d.id_categoria
ORDER BY 
    cantidad_documentos DESC;

-- 2. Usuarios que han visto más documentos de una categoría específica (inteligencia artificial)
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
SELECT 
    nombre_documento
FROM 
    documento 
WHERE 
    id_documento NOT IN (SELECT id_documento FROM vistopor);

-- 4. Promedio de Valoración por Documento
SELECT 
    (SELECT d.nombre_documento FROM documento d WHERE d.id_documento = v.id_documento) AS nombre_documento, 
    AVG(v.estrellas) AS promedio_valoracion 
FROM 
    valoracion v 
GROUP BY 
    v.id_documento;

-- 5. Documentos Publicados en el Último Mes con sus Valoraciones
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
SELECT 
    nombre_documento 
FROM 
    documento 
WHERE 
    id_documento NOT IN (SELECT id_documento FROM comentario);

-- 10. Usuarios que han Visualizado y Luego Descargado Documentos
SELECT 
    (SELECT nombre_usuario FROM usuario u WHERE u.id_usuario = v.id_usuario) AS nombre_usuario
FROM 
    vistopor v
WHERE 
    id_documento IN (SELECT id_documento FROM descarga WHERE v.id_usuario = descarga.id_usuario);






