USE `teliuk`;

DELIMITER //
DROP TRIGGER IF EXISTS CheckIfCountryExistsBeforeCreate //
CREATE TRIGGER CheckIfCountryExistsBeforeCreate
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
        SET MESSAGE_TEXT = 'Foreign key error: No country with such id';
    END IF;
END //


DROP TRIGGER IF EXISTS CheckIfCountryExistsBeforeUpdate //
CREATE TRIGGER CheckIfCountryExistsBeforeUpdate
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
        SET MESSAGE_TEXT = 'Foreign key error: No country with such id';
    END IF;
END //


DROP TRIGGER IF EXISTS CheckIfCountryIdUsedBeforeUpdate //
CREATE TRIGGER CheckIfCountryIdUsedBeforeUpdate
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
        SET MESSAGE_TEXT = 'Foreign key error: Can`t update row with record present in related table';
    END IF;
END //


DROP TRIGGER IF EXISTS CheckIfCountryIdUsedBeforeDelete //
CREATE TRIGGER CheckIfCountryIdUsedBeforeDelete
    BEFORE DELETE
    ON `teliuk`.`country`
    FOR EACH ROW
BEGIN
    IF
        (EXISTS(
            SELECT country_id FROM `teliuk`.`company`
            WHERE company.country_id = OLD.id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Foreign key error: Can`t delete row with record present in related table';
    END IF;
END //