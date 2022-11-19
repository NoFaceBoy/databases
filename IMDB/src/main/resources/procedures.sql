USE teliuk;

DROP PROCEDURE IF EXISTS country_param_insert;
DELIMITER //
CREATE PROCEDURE country_param_insert(
    IN new_country_name VARCHAR(20),
    OUT new_id INT
)
BEGIN
    INSERT INTO teliuk.country (name) VALUE (new_country_name);
    SELECT id INTO new_id FROM teliuk.country WHERE name = new_country_name;
END //
DELIMITER ;


DROP PROCEDURE IF EXISTS count_average_budget;
DELIMITER //
CREATE PROCEDURE count_average_budget()
BEGIN
    SELECT getAverageBudget();
END //


DROP PROCEDURE IF EXISTS create_award_movie_relationship //
CREATE PROCEDURE create_award_movie_relationship(
    IN award_name VARCHAR(50),
    IN movie_name VARCHAR(50))
BEGIN
    DECLARE award_id, movie_id INT;
    SELECT id INTO award_id FROM `award` WHERE name = award_name;
    SELECT id INTO movie_id FROM `movie` WHERE name = movie_name;
    IF (award_id IS NULL)
    THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Award with such name was not found';
    END IF;
    IF (movie_id IS NULL)
    THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Movie with such name was not found';
    END IF;
    INSERT INTO `award_movie` (award_id, movie_id) VALUES (award_id, movie_id);
END //


DROP PROCEDURE IF EXISTS insert_numbered_records;
DELIMITER //
CREATE PROCEDURE insert_numbered_records()
BEGIN
    DECLARE number_of_inserts INT DEFAULT 1;
    WHILE number_of_inserts < 11
        DO
            INSERT INTO country(name) VALUE (
                CONCAT('Ukraine', number_of_inserts)
                );
            SET number_of_inserts = number_of_inserts + 1;
        END WHILE;
END //
DELIMITER ;
