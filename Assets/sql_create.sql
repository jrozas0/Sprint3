SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`USER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`USER` ;

CREATE TABLE IF NOT EXISTS `mydb`.`USER` (
  `nick` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `surname` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `age` INT(11) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `phone` INT(11) NOT NULL,
  `paymentData` VARCHAR(255) NOT NULL,
  `pic` BLOB NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));


-- -----------------------------------------------------
-- Table `mydb`.`CATEGORY`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`CATEGORY` ;

CREATE TABLE IF NOT EXISTS `mydb`.`CATEGORY` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `mydb`.`COURSE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`COURSE` ;

CREATE TABLE IF NOT EXISTS `mydb`.`COURSE` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `syllabus` VARCHAR(255) NOT NULL,
  `price` INT(11) NOT NULL,
  `promotionPrice` INT(4) NOT NULL,
  `Category_id` INT(11) NOT NULL,
  `highlighted` TINYINT(1) NOT NULL,
  `difficulty` BIGINT(20) NOT NULL,
  `owner` INT(11) NOT NULL,
  `picture` VARCHAR(45) NOT NULL,
  `duration` BIGINT(20) NOT NULL,
  `denied` TINYINT(1) NOT NULL,
  `valid` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Course_Category1_idx` (`Category_id` ASC),
  INDEX `fk_Course_User1_idx` (`owner` ASC),
  CONSTRAINT `fk_Course_Category1`
    FOREIGN KEY (`Category_id`)
    REFERENCES `mydb`.`CATEGORY` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Course_User1`
    FOREIGN KEY (`owner`)
    REFERENCES `mydb`.`USER` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`USERTEACHING`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`USERTEACHING` ;

CREATE TABLE IF NOT EXISTS `mydb`.`USERTEACHING` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `User_id` INT(11) NOT NULL,
  `Course_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_UserTeaching_user_idx` (`User_id` ASC),
  INDEX `fk_UserTeaching_Course1_idx` (`Course_id` ASC),
  CONSTRAINT `fk_UserTeaching_user`
    FOREIGN KEY (`User_id`)
    REFERENCES `mydb`.`USER` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserTeaching_Course1`
    FOREIGN KEY (`Course_id`)
    REFERENCES `mydb`.`COURSE` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`USERATTENDING`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`USERATTENDING` ;

CREATE TABLE IF NOT EXISTS `mydb`.`USERATTENDING` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `User_id` INT(11) NOT NULL,
  `Course_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_UserAttending_user1_idx` (`User_id` ASC),
  INDEX `fk_UserAttending_Course1_idx` (`Course_id` ASC),
  CONSTRAINT `fk_UserAttending_user1`
    FOREIGN KEY (`User_id`)
    REFERENCES `mydb`.`USER` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserAttending_Course1`
    FOREIGN KEY (`Course_id`)
    REFERENCES `mydb`.`COURSE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`USERWISHING`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`USERWISHING` ;

CREATE TABLE IF NOT EXISTS `mydb`.`USERWISHING` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `User_id` INT(11) NOT NULL,
  `Course_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_UserWishing_user1_idx` (`User_id` ASC),
  INDEX `fk_UserWishing_Course1_idx` (`Course_id` ASC),
  CONSTRAINT `fk_UserWishing_user1`
    FOREIGN KEY (`User_id`)
    REFERENCES `mydb`.`USER` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserWishing_Course1`
    FOREIGN KEY (`Course_id`)
    REFERENCES `mydb`.`COURSE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`SECTION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`SECTION` ;

CREATE TABLE IF NOT EXISTS `mydb`.`SECTION` (
  `id` INT(11) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `COURSE_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_SECTION_COURSE1_idx` (`COURSE_id` ASC),
  CONSTRAINT `fk_SECTION_COURSE1`
    FOREIGN KEY (`COURSE_id`)
    REFERENCES `mydb`.`COURSE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`MATERIAL`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`MATERIAL` ;

CREATE TABLE IF NOT EXISTS `mydb`.`MATERIAL` (
  `id` INT(11) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `file` BLOB NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `mydb`.`LESSON`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`LESSON` ;

CREATE TABLE IF NOT EXISTS `mydb`.`LESSON` (
  `id` INT(11) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `SECTION_id` INT(11) NOT NULL,
  `MATERIAL_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_LESSON_SECTION1_idx` (`SECTION_id` ASC),
  INDEX `fk_LESSON_MATERIAL1_idx` (`MATERIAL_id` ASC),
  CONSTRAINT `fk_LESSON_SECTION1`
    FOREIGN KEY (`SECTION_id`)
    REFERENCES `mydb`.`SECTION` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LESSON_MATERIAL1`
    FOREIGN KEY (`MATERIAL_id`)
    REFERENCES `mydb`.`MATERIAL` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`CERTIFICATES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`CERTIFICATES` ;

CREATE TABLE IF NOT EXISTS `mydb`.`CERTIFICATES` (
  `id` INT(11) NOT NULL,
  `User_id` INT(11) NOT NULL,
  `certificateHash` VARCHAR(255) NOT NULL,
  `courseSeed` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Certificates_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_Certificates_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `mydb`.`USER` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`INTEREST`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`INTEREST` ;

CREATE TABLE IF NOT EXISTS `mydb`.`INTEREST` (
  `id` INT(11) NOT NULL,
  `interest` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `mydb`.`USERINTEREST`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`USERINTEREST` ;

CREATE TABLE IF NOT EXISTS `mydb`.`USERINTEREST` (
  `id` INT(11) NOT NULL,
  `USER_id` INT(11) NOT NULL,
  `INTEREST_id` INT(11) NOT NULL,
  INDEX `fk_USERINTEREST_USER1_idx` (`USER_id` ASC),
  PRIMARY KEY (`id`),
  INDEX `fk_USERINTEREST_INTEREST1_idx` (`INTEREST_id` ASC),
  CONSTRAINT `fk_USERINTEREST_USER1`
    FOREIGN KEY (`USER_id`)
    REFERENCES `mydb`.`USER` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USERINTEREST_INTEREST1`
    FOREIGN KEY (`INTEREST_id`)
    REFERENCES `mydb`.`INTEREST` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
