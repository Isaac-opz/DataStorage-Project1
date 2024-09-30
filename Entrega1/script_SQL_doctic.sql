-- -----------------------------------------------------
-- Script SQL para la base de datos DocTIC
-- -----------------------------------------------------

-- Configuración inicial
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- Creación del esquema 'doctic'
CREATE SCHEMA IF NOT EXISTS `doctic` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `doctic`;

-- -----------------------------------------------------
-- Tabla 'usuario'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nombre_usuario` VARCHAR(50) NOT NULL,
  `correo_electronico` VARCHAR(150) NOT NULL,
  `ciudad` VARCHAR(100) DEFAULT NULL,
  `departamento` VARCHAR(100) DEFAULT NULL,
  `pregunta_secreta` VARCHAR(255) NOT NULL,
  `respuesta_secreta` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE INDEX `uq_nombre_usuario` (`nombre_usuario`),
  UNIQUE INDEX `uq_correo_electronico` (`correo_electronico`)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla 'historial_contrasena'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `historial_contrasena` (
  `id_historial` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `contrasena` VARCHAR(255) NOT NULL,
  `fecha_cambio` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` ENUM('activa', 'inactiva') NOT NULL,
  PRIMARY KEY (`id_historial`),
  UNIQUE KEY `uq_usuario_contrasena` (`id_usuario`, `contrasena`),
  INDEX `idx_id_usuario` (`id_usuario`),
  CONSTRAINT `fk_historial_contrasena_usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla 'categoria'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `categoria` (
  `id_categoria` INT NOT NULL AUTO_INCREMENT,
  `nombre_categoria` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- Insertar categorías predefinidas
INSERT INTO `categoria` (`nombre_categoria`) VALUES
('Desarrollo web'),
('Bases de datos relacionales/NoSQL'),
('Programación en un lenguaje determinado'),
('Analítica de datos'),
('Visualización de datos'),
('Inteligencia artificial'),
('Machine learning'),
('Otro');

-- -----------------------------------------------------
-- Tabla 'documento'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `documento` (
  `id_documento` INT NOT NULL AUTO_INCREMENT,
  `nombre_documento` VARCHAR(255) NOT NULL,
  `descripcion` TEXT DEFAULT NULL,
  `fecha_publicacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `url` VARCHAR(255) NOT NULL,
  `visibilidad` ENUM('publico', 'privado') NOT NULL,
  `valoracion` DECIMAL(2,1) DEFAULT 0,
  `id_categoria` INT NOT NULL,
  `num_descargas` INT DEFAULT 0,
  `num_vistas` INT DEFAULT 0,
  `num_comentarios` INT DEFAULT 0,
  PRIMARY KEY (`id_documento`),
  INDEX `idx_id_categoria` (`id_categoria`),
  CONSTRAINT `fk_documento_categoria`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `categoria` (`id_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla 'autor_documento'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `autor_documento` (
  `id_autor_documento` INT NOT NULL AUTO_INCREMENT,
  `id_documento` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  `publico` ENUM('Si', 'No') DEFAULT 'Si',
  PRIMARY KEY (`id_autor_documento`),
  INDEX `idx_id_documento` (`id_documento`),
  INDEX `idx_id_usuario` (`id_usuario`),
  CONSTRAINT `fk_autor_documento_documento`
    FOREIGN KEY (`id_documento`)
    REFERENCES `documento` (`id_documento`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_autor_documento_usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla 'comentario'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comentario` (
  `id_comentario` INT NOT NULL AUTO_INCREMENT,
  `id_documento` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  `comentario` TEXT NOT NULL,
  `fecha_comentario` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_metacomentario` INT DEFAULT NULL,
  PRIMARY KEY (`id_comentario`),
  INDEX `idx_id_documento` (`id_documento`),
  INDEX `idx_id_usuario` (`id_usuario`),
  INDEX `idx_id_metacomentario` (`id_metacomentario`),
  CONSTRAINT `fk_comentario_documento`
    FOREIGN KEY (`id_documento`)
    REFERENCES `documento` (`id_documento`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_comentario_usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_comentario_metacomentario`
    FOREIGN KEY (`id_metacomentario`)
    REFERENCES `comentario` (`id_comentario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla 'valoracion'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `valoracion` (
  `id_valoracion` INT NOT NULL AUTO_INCREMENT,
  `estrellas` TINYINT NOT NULL,
  `fecha_valoracion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_usuario` INT NOT NULL,
  `id_documento` INT NOT NULL,
  PRIMARY KEY (`id_valoracion`),
  UNIQUE KEY `uq_usuario_documento` (`id_usuario`, `id_documento`),
  INDEX `idx_id_usuario` (`id_usuario`),
  INDEX `idx_id_documento` (`id_documento`),
  CONSTRAINT `chk_estrellas_range` CHECK (`estrellas` BETWEEN 1 AND 5),
  CONSTRAINT `fk_valoracion_usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_valoracion_documento`
    FOREIGN KEY (`id_documento`)
    REFERENCES `documento` (`id_documento`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla 'descarga'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `descarga` (
  `id_descarga` INT NOT NULL AUTO_INCREMENT,
  `fecha_hora` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `id_usuario` INT NOT NULL,
  `id_documento` INT NOT NULL,
  PRIMARY KEY (`id_descarga`),
  INDEX `idx_id_usuario` (`id_usuario`),
  INDEX `idx_id_documento` (`id_documento`),
  CONSTRAINT `fk_descarga_usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_descarga_documento`
    FOREIGN KEY (`id_documento`)
    REFERENCES `documento` (`id_documento`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla 'vistopor'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vistopor` (
  `id_vistopor` INT NOT NULL AUTO_INCREMENT,
  `fecha_hora` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `id_usuario` INT NOT NULL,
  `id_documento` INT NOT NULL,
  PRIMARY KEY (`id_vistopor`),
  INDEX `idx_id_usuario` (`id_usuario`),
  INDEX `idx_id_documento` (`id_documento`),
  CONSTRAINT `fk_vistopor_usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_vistopor_documento`
    FOREIGN KEY (`id_documento`)
    REFERENCES `documento` (`id_documento`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- Restauración de la configuración inicial
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
