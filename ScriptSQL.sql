SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- Crear la tabla `categoria`
CREATE TABLE IF NOT EXISTS `doctic`.`categoria` (
  `id_categoria` INT NOT NULL,
  `categoria` VARCHAR(255) NOT NULL,
  `id_metacategoria` INT NULL,
  PRIMARY KEY (`id_categoria`),
  INDEX `fk_Categoria_Categoria1_idx` (`id_metacategoria` ASC) VISIBLE,
  CONSTRAINT `fk_Categoria_Categoria1`
    FOREIGN KEY (`id_metacategoria`)
    REFERENCES `doctic`.`categoria` (`id_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- Crear la tabla `usuario`
CREATE TABLE IF NOT EXISTS `doctic`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nombre_usuario` VARCHAR(50) NOT NULL,
  `correo_electronico` VARCHAR(150) NOT NULL,
  `ciudad` VARCHAR(100) NULL DEFAULT NULL,
  `departamento` VARCHAR(100) NULL DEFAULT NULL,
  `pregunta_secreta` VARCHAR(255) NOT NULL,
  `respuesta_secreta` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE INDEX `nombre_usuario` (`nombre_usuario` ASC) VISIBLE,
  UNIQUE INDEX `correo_electronico` (`correo_electronico` ASC) VISIBLE
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- Crear la tabla `documento`
CREATE TABLE IF NOT EXISTS `doctic`.`documento` (
  `id_documento` INT NOT NULL AUTO_INCREMENT,
  `nombre_documento` VARCHAR(255) NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `fecha_publicacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `url` VARCHAR(255) NOT NULL,
  `visibilidad` ENUM('publico', 'privado') NOT NULL,
  `valoracion` DECIMAL(2,1) NULL DEFAULT NULL,
  `id_categoria` INT NOT NULL,
  PRIMARY KEY (`id_documento`),
  INDEX `fk_documentos_Categoria1_idx` (`id_categoria` ASC) VISIBLE,
  CONSTRAINT `fk_documentos_Categoria1`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `doctic`.`categoria` (`id_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- Crear la tabla `comentario`
CREATE TABLE IF NOT EXISTS `doctic`.`comentario` (
  `id_comentario` INT NOT NULL AUTO_INCREMENT,
  `id_documento` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  `comentario` TEXT NOT NULL,
  `fecha_comentario` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_metacomentario` INT NULL,
  PRIMARY KEY (`id_comentario`),
  INDEX `id_documento` (`id_documento` ASC) VISIBLE,
  INDEX `id_usuario` (`id_usuario` ASC) VISIBLE,
  INDEX `fk_comentarios_comentarios1_idx` (`id_metacomentario` ASC) VISIBLE,
  CONSTRAINT `comentarios_ibfk_1`
    FOREIGN KEY (`id_documento`)
    REFERENCES `doctic`.`documento` (`id_documento`),
  CONSTRAINT `comentarios_ibfk_2`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `doctic`.`usuario` (`id_usuario`),
  CONSTRAINT `fk_comentarios_comentarios1`
    FOREIGN KEY (`id_metacomentario`)
    REFERENCES `doctic`.`comentario` (`id_comentario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- Crear la tabla `historial_contrasena`
CREATE TABLE IF NOT EXISTS `doctic`.`historial_contrasena` (
  `id_historial` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `contrasena` VARCHAR(255) NOT NULL,
  `fecha_cambio` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` ENUM('activa', 'inactiva') NULL,
  PRIMARY KEY (`id_historial`),
  INDEX `id_usuario` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `historial_contraseñas_ibfk_1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `doctic`.`usuario` (`id_usuario`)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- Crear la tabla `valoracion`
CREATE TABLE IF NOT EXISTS `doctic`.`valoracion` (
  `id_valoracion` INT NOT NULL,
  `estrellas` TINYINT(5) NULL,
  `fechavaloracion` DATE NULL,
  `id_usuario` INT NOT NULL,
  `id_documento` INT NOT NULL,
  PRIMARY KEY (`id_valoracion`),
  INDEX `fk_valoracion_usuarios1_idx` (`id_usuario` ASC) VISIBLE,
  INDEX `fk_valoracion_documentos1_idx` (`id_documento` ASC) VISIBLE,
  CONSTRAINT `fk_valoracion_usuarios1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `doctic`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_valoracion_documentos1`
    FOREIGN KEY (`id_documento`)
    REFERENCES `doctic`.`documento` (`id_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- Crear la tabla `autor_documento`
CREATE TABLE IF NOT EXISTS `doctic`.`autor_documento` (
  `id_usuario` INT NOT NULL,
  `usuarios_id_usuario` INT NOT NULL,
  `publico` ENUM("Si", "No") NULL,
  `id_autor_documento` VARCHAR(45) NOT NULL,
  INDEX `fk_autor_documento_documentos1_idx` (`id_usuario` ASC) VISIBLE,
  INDEX `fk_autor_documento_usuarios1_idx` (`usuarios_id_usuario` ASC) VISIBLE,
  PRIMARY KEY (`id_autor_documento`),
  CONSTRAINT `fk_autor_documento_documentos1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `doctic`.`documento` (`id_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_autor_documento_usuarios1`
    FOREIGN KEY (`usuarios_id_usuario`)
    REFERENCES `doctic`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- Crear la tabla `descarga`
CREATE TABLE IF NOT EXISTS `doctic`.`descarga` (
  `id_descarga` INT NOT NULL,
  `fecha_hora` DATETIME(3) NULL,
  `id_usuario` INT NOT NULL,
  `id_documento` INT NOT NULL,
  PRIMARY KEY (`id_descarga`),
  INDEX `fk_descarga_usuarios1_idx` (`id_usuario` ASC) VISIBLE,
  INDEX `fk_descarga_documentos1_idx` (`id_documento` ASC) VISIBLE,
  CONSTRAINT `fk_descarga_usuarios1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `doctic`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_descarga_documentos1`
    FOREIGN KEY (`id_documento`)
    REFERENCES `doctic`.`documento` (`id_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- Crear la tabla `vistopor`
CREATE TABLE IF NOT EXISTS `doctic`.`vistopor` (
  `id_vistopor` INT NOT NULL,
  `fecha_hora` DATETIME(3) NULL,
  `id_usuario` INT NOT NULL,
  `id_documento` INT NOT NULL,
  PRIMARY KEY (`id_vistopor`),
  INDEX `fk_vistopor_usuarios1_idx` (`id_usuario` ASC) VISIBLE,
  INDEX `fk_vistopor_documentos1_idx` (`id_documento` ASC) VISIBLE,
  CONSTRAINT `fk_vistopor_usuarios1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `doctic`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vistopor_documentos1`
    FOREIGN KEY (`id_documento`)
    REFERENCES `doctic`.`documento` (`id_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;