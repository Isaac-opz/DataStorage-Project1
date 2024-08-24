-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema doctic
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema doctic
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `doctic` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
-- -----------------------------------------------------
-- Schema new_schema1
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema doctic
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema doctic
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `doctic` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `doctic` ;
USE `doctic` ;

-- -----------------------------------------------------
-- Table `doctic`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doctic`.`categoria` (
  `idCategoria` INT NOT NULL,
  `Categoria` ENUM('desarrollo web', 'bases de datos relacionales/NoSQL', 'programación en un lenguaje determinado', 'analítica de datos', 'visualización de datos', 'inteligencia artificial', 'machine learning', 'Otro') NOT NULL,
  `Categoria_idCategoria` INT NULL,
  PRIMARY KEY (`idCategoria`),
  INDEX `fk_Categoria_Categoria1_idx` (`Categoria_idCategoria` ASC) VISIBLE,
  CONSTRAINT `fk_Categoria_Categoria1`
    FOREIGN KEY (`Categoria_idCategoria`)
    REFERENCES `doctic`.`categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doctic`.`documento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doctic`.`documento` (
  `id_documento` INT NOT NULL AUTO_INCREMENT,
  `nombre_documento` VARCHAR(255) NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `fecha_publicacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `url` VARCHAR(255) NOT NULL,
  `visibilidad` ENUM('publico', 'privado') NOT NULL,
  `valoracion` DECIMAL(2,1) NULL DEFAULT NULL,
  `Categoria_idCategoria` INT NOT NULL,
  PRIMARY KEY (`id_documento`),
  INDEX `fk_documentos_Categoria1_idx` (`Categoria_idCategoria` ASC) VISIBLE,
  CONSTRAINT `fk_documentos_Categoria1`
    FOREIGN KEY (`Categoria_idCategoria`)
    REFERENCES `doctic`.`categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `doctic`.`usuario`
-- -----------------------------------------------------
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
  UNIQUE INDEX `correo_electronico` (`correo_electronico` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `doctic`.`comentario`
-- -----------------------------------------------------
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
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `doctic`.`historial_contrasena`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doctic`.`historial_contrasena` (
  `id_historial` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `contrasena` VARCHAR(255) NOT NULL,
  `fecha_cambio` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` ENUM("activa", "inactiva") NULL,
  PRIMARY KEY (`id_historial`),
  INDEX `id_usuario` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `historial_contraseñas_ibfk_1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `doctic`.`usuario` (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `doctic`.`Contrasena`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doctic`.`Contrasena` (
  `idContrasena` INT NOT NULL,
  PRIMARY KEY (`idContrasena`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doctic`.`Valoracion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doctic`.`Valoracion` (
  `idValoracion` INT NOT NULL,
  `Estrellas` TINYINT(5) NULL,
  `Fechavaloracion` DATE NULL,
  `usuarios_id_usuario` INT NOT NULL,
  `documentos_id_documento` INT NOT NULL,
  PRIMARY KEY (`idValoracion`),
  INDEX `fk_Valoracion_usuarios1_idx` (`usuarios_id_usuario` ASC) VISIBLE,
  INDEX `fk_Valoracion_documentos1_idx` (`documentos_id_documento` ASC) VISIBLE,
  CONSTRAINT `fk_Valoracion_usuarios1`
    FOREIGN KEY (`usuarios_id_usuario`)
    REFERENCES `doctic`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Valoracion_documentos1`
    FOREIGN KEY (`documentos_id_documento`)
    REFERENCES `doctic`.`documento` (`id_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doctic`.`AutorDocumento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doctic`.`AutorDocumento` (
  `id_usuario` INT NOT NULL,
  `usuarios_id_usuario` INT NOT NULL,
  `publico` ENUM("Si", "No") NULL,
  `id_autordocumento` VARCHAR(45) NOT NULL,
  INDEX `fk_AutorDocumento_documentos1_idx` (`id_usuario` ASC) VISIBLE,
  INDEX `fk_AutorDocumento_usuarios1_idx` (`usuarios_id_usuario` ASC) VISIBLE,
  PRIMARY KEY (`id_autordocumento`),
  CONSTRAINT `fk_AutorDocumento_documentos1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `doctic`.`documento` (`id_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AutorDocumento_usuarios1`
    FOREIGN KEY (`usuarios_id_usuario`)
    REFERENCES `doctic`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doctic`.`descarga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doctic`.`descarga` (
  `iddescarga` INT NOT NULL,
  ` fecha_hora` DATETIME(3) NULL,
  `usuarios_id_usuario` INT NOT NULL,
  `documentos_id_documento` INT NOT NULL,
  PRIMARY KEY (`iddescarga`),
  INDEX `fk_descarga_usuarios1_idx` (`usuarios_id_usuario` ASC) VISIBLE,
  INDEX `fk_descarga_documentos1_idx` (`documentos_id_documento` ASC) VISIBLE,
  CONSTRAINT `fk_descarga_usuarios1`
    FOREIGN KEY (`usuarios_id_usuario`)
    REFERENCES `doctic`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_descarga_documentos1`
    FOREIGN KEY (`documentos_id_documento`)
    REFERENCES `doctic`.`documento` (`id_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doctic`.`vistopor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doctic`.`vistopor` (
  `idvistopor` INT NOT NULL,
  ` fecha_hora` DATETIME(3) NULL,
  `usuarios_id_usuario` INT NOT NULL,
  `documentos_id_documento` INT NOT NULL,
  PRIMARY KEY (`idvistopor`),
  INDEX `fk_vistopor_usuarios1_idx` (`usuarios_id_usuario` ASC) VISIBLE,
  INDEX `fk_vistopor_documentos1_idx` (`documentos_id_documento` ASC) VISIBLE,
  CONSTRAINT `fk_vistopor_usuarios1`
    FOREIGN KEY (`usuarios_id_usuario`)
    REFERENCES `doctic`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vistopor_documentos1`
    FOREIGN KEY (`documentos_id_documento`)
    REFERENCES `doctic`.`documento` (`id_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
