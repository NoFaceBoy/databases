USE `teliuk`;
DELIMITER //

-- Перевірка чи існує країна перед створенням.
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

-- Перевірка чи існує країна перед оновленням.
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

-- Перевірка чи використовується країна перед оновленням
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

-- Перевірка чи використовується країна перед видаленням
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

-- Перевірка чи опис фільму містить < 10 символів перед створенням.
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

-- Перевірка чи опис фільму містить < 10 символів перед оновленням.
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

-- Заборона на видалення країни.
DROP TRIGGER IF EXISTS prevent_country_delete;
DELIMITER //
CREATE TRIGGER prevent_country_delete
    BEFORE DELETE
    ON `teliuk`.`country`
    FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Deletion of the country has been prevented`';
END //

-- Перевірка чи ім'я директора дорівнює заданим.
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
