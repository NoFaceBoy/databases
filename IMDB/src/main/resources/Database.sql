DROP SCHEMA IF EXISTS `teliuk`;
-- -----------------------------------------------------
-- Schema teliuk
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `teliuk` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `teliuk`;

-- -----------------------------------------------------
-- Table `teliuk`.`country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teliuk`.`country`;

CREATE TABLE IF NOT EXISTS `teliuk`.`country`
(
    `id`   INT         NOT NULL,
    `name` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`)
);
-- -----------------------------------------------------
-- Table `teliuk`.`company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teliuk`.`company`;

CREATE TABLE IF NOT EXISTS `teliuk`.`company`
(
    `id`           INT         NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(50) NOT NULL,
    `country_name` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`, `country_name`),
    INDEX `fk_company_country1_idx` (`country_name` ASC) VISIBLE,
    CONSTRAINT `fk_company_country1`
        FOREIGN KEY (`country_name`)
            REFERENCES `teliuk`.`country` (`name`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `teliuk`.`director`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teliuk`.`director`;

CREATE TABLE IF NOT EXISTS `teliuk`.`director`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(25) NOT NULL,
    `last_name`  VARCHAR(25) NOT NULL,
    `gender`     VARCHAR(20) NULL DEFAULT NULL,
    `birthdate`  DATE        NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `director_first_name` (`first_name` ASC) VISIBLE,
    INDEX `director_last_name` (`last_name` ASC) VISIBLE
);
-- -----------------------------------------------------
-- Table `teliuk`.`genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teliuk`.`genre`;

CREATE TABLE IF NOT EXISTS `teliuk`.`genre`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `type` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`)
);
-- -----------------------------------------------------
-- Table `teliuk`.`ranking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teliuk`.`ranking`;

CREATE TABLE IF NOT EXISTS `teliuk`.`ranking`
(
    `id`                     INT NOT NULL AUTO_INCREMENT,
    `rating`                 INT NULL DEFAULT NULL,
    `number_of_user_rates`   INT NULL DEFAULT NULL,
    `metascore`              INT NULL DEFAULT NULL,
    `number_of_critic_rates` INT NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);
-- -----------------------------------------------------
-- Table `teliuk`.`movie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teliuk`.`movie`;

CREATE TABLE IF NOT EXISTS `teliuk`.`movie`
(
    `id`           INT          NOT NULL AUTO_INCREMENT,
    `genre_id`     INT          NOT NULL,
    `ranking_id`   INT          NOT NULL,
    `company_id`   INT          NOT NULL,
    `directors_id` INT          NOT NULL,
    `name`         VARCHAR(100) NOT NULL,
    `description`  VARCHAR(300) NOT NULL,
    `budget`       INT          NOT NULL,
    `year`         INT          NOT NULL,
    `facts`        VARCHAR(500) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `movie_company` (`company_id` ASC) VISIBLE,
    INDEX `movie_directors` (`directors_id` ASC) VISIBLE,
    INDEX `movie_genre` (`genre_id` ASC) VISIBLE,
    INDEX `movie_ranking` (`ranking_id` ASC) VISIBLE,
    INDEX `movie_year` (`year` ASC) VISIBLE,
    CONSTRAINT `movie_company`
        FOREIGN KEY (`company_id`)
            REFERENCES `teliuk`.`company` (`id`),
    CONSTRAINT `movie_directors`
        FOREIGN KEY (`directors_id`)
            REFERENCES `teliuk`.`director` (`id`),
    CONSTRAINT `movie_genre`
        FOREIGN KEY (`genre_id`)
            REFERENCES `teliuk`.`genre` (`id`),
    CONSTRAINT `movie_ranking`
        FOREIGN KEY (`ranking_id`)
            REFERENCES `teliuk`.`ranking` (`id`)
);
-- -----------------------------------------------------
-- Table `teliuk`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teliuk`.`role`;

CREATE TABLE IF NOT EXISTS `teliuk`.`role`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `movie_id` INT         NOT NULL,
    `name`     VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `roles_movie` (`movie_id` ASC) VISIBLE,
    CONSTRAINT `roles_movie`
        FOREIGN KEY (`movie_id`)
            REFERENCES `teliuk`.`movie` (`id`)
);
-- -----------------------------------------------------
-- Table `teliuk`.`actor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teliuk`.`actor`;

CREATE TABLE IF NOT EXISTS `teliuk`.`actor`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `roles_id`   INT         NOT NULL,
    `first_name` VARCHAR(25) NOT NULL,
    `last_name`  VARCHAR(25) NOT NULL,
    `gender`     VARCHAR(20) NULL DEFAULT NULL,
    `birthdate`  DATE        NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `actor_roles` (`roles_id` ASC) VISIBLE,
    INDEX `actor_first_name` (`first_name` ASC) VISIBLE,
    INDEX `actor_last_name` (`last_name` ASC) VISIBLE,
    CONSTRAINT `actor_roles`
        FOREIGN KEY (`roles_id`)
            REFERENCES `teliuk`.`role` (`id`)
);
-- -----------------------------------------------------
-- Table `teliuk`.`award`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teliuk`.`award`;

CREATE TABLE IF NOT EXISTS `teliuk`.`award`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL,
    PRIMARY KEY (`id`)
);
-- -----------------------------------------------------
-- Table `teliuk`.`award_movie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teliuk`.`award_movie`;

CREATE TABLE IF NOT EXISTS `teliuk`.`award_movie`
(
    `movie_id` INT NOT NULL,
    `award_id` INT NOT NULL,
    PRIMARY KEY (`movie_id`, `award_id`),
    INDEX `award_movie_award_fk` (`award_id` ASC) VISIBLE,
    CONSTRAINT `award_movie_award_fk`
        FOREIGN KEY (`award_id`)
            REFERENCES `teliuk`.`award` (`id`),
    CONSTRAINT `award_movie_movie_fk`
        FOREIGN KEY (`movie_id`)
            REFERENCES `teliuk`.`movie` (`id`)
);
-- -----------------------------------------------------
-- Table `teliuk`.`nomination`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teliuk`.`nomination`;

CREATE TABLE IF NOT EXISTS `teliuk`.`nomination`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `award_id` INT         NOT NULL,
    `type`     VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `nomination_award` (`award_id` ASC) VISIBLE,
    CONSTRAINT `nomination_award`
        FOREIGN KEY (`award_id`)
            REFERENCES `teliuk`.`award` (`id`)
);
