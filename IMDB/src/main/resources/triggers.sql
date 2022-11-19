USE `teliuk`;

DELIMITER //
DROP TRIGGER IF EXISTS check_if_country_exists //
CREATE TRIGGER check_if_country_exists
    BEFORE INSERT
    ON `teliuk`.`company`
    FOR EACH ROW
BEGIN
    IF
        (NOT EXISTS(
            SELECT id FROM `teliuk`.`country`
            WHERE id = NEW.country_id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No country with such id';
    END IF;
END //


DROP TRIGGER IF EXISTS check_if_country_exists //
CREATE TRIGGER check_if_country_exists
    BEFORE UPDATE
    ON `teliuk`.`company`
    FOR EACH ROW
BEGIN
    IF
        (NOT EXISTS(
            SELECT id FROM `teliuk`.`country`
            WHERE id = NEW.country_id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No country with such id';
    END IF;
END //


DROP TRIGGER IF EXISTS check_if_country_id_used //
CREATE TRIGGER check_if_country_id_used
    BEFORE UPDATE
    ON `teliuk`.`country`
    FOR EACH ROW
BEGIN
    IF
        (EXISTS(
            SELECT country_id FROM `teliuk`.`company`
            WHERE company.country_id = OLD.id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Can`t update row that currently exists in table';
    END IF;
END //


DROP TRIGGER IF EXISTS check_if_country_id_used //
CREATE TRIGGER check_if_country_id_used
    BEFORE DELETE
    ON `teliuk`.`country`
    FOR EACH ROW
BEGIN
    IF
        (EXISTS(
            SELECT country_id FROM `teliuk`.`company`
            WHERE company.country_id = OLD.id
        ))
    THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Can`t delete row that currently exists in table';
    END IF;
END //

DROP TRIGGER IF EXISTS check_description;
DELIMITER //
CREATE TRIGGER check_description
    BEFORE INSERT
    ON `teliuk`.`movie`
    FOR EACH ROW
    IF LENGTH(NEW.description) < 10 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Description must contain more then 10 symbols';
    END IF //;
DELIMITER ;

DROP TRIGGER IF EXISTS check_description;
DELIMITER //
CREATE TRIGGER check_description
    BEFORE UPDATE
    ON `teliuk`.`movie`
    FOR EACH ROW
    IF LENGTH(NEW.description) < 10 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Description must contain more then 10 symbols';
    END IF //;
DELIMITER ;


DROP TRIGGER IF EXISTS check_date_format;
DELIMITER //
CREATE TRIGGER check_date_format
    BEFORE INSERT
    ON `teliuk`.`director` FOR EACH ROW
BEGIN
    IF (NEW.birthdate NOT RLIKE '[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]')
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Invalid date format';
    END IF;
END //

DROP TRIGGER IF EXISTS check_date_format //
CREATE TRIGGER check_date_format
    BEFORE UPDATE
    ON `teliuk`.`director`
    FOR EACH ROW
BEGIN
    IF (NEW.birthdate NOT RLIKE '[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]')
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Invalid date format';
    END IF;
END //

DROP TRIGGER IF EXISTS check_date_format;
DELIMITER //
CREATE TRIGGER check_date_format
    BEFORE INSERT
    ON `teliuk`.`actor` FOR EACH ROW
BEGIN
    IF (NEW.birthdate NOT RLIKE '[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]')
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Invalid date format';
    END IF;
END //

DROP TRIGGER IF EXISTS check_date_format //
CREATE TRIGGER check_date_format
    BEFORE UPDATE
    ON `teliuk`.`actor`
    FOR EACH ROW
BEGIN
    IF (NEW.birthdate NOT RLIKE '[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]')
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Invalid date format';
    END IF;
END //

DROP TRIGGER IF EXISTS prevent_country_delete //
CREATE TRIGGER prevent_country_delete
    BEFORE DELETE
    ON `teliuk`.`country`
    FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Deletion of the country has been prevented`';
END //

DROP TRIGGER IF EXISTS check_director_names //
CREATE TRIGGER check_director_names
    BEFORE INSERT
    ON `teliuk`.`director`
    FOR EACH ROW
BEGIN
    IF NEW.first_name NOT IN ('Svitlana', 'Petro', 'Olha', 'Taras') THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Director name is not Svitlana, Petro, Olha, Taras';
    END IF;
END //
