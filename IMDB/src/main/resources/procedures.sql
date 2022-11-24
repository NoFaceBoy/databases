USE teliuk;
DELIMITER //

-- Параметризована вставка нових значень у таблицю.
DROP PROCEDURE IF EXISTS country_param_insert //
CREATE PROCEDURE country_param_insert(
    IN new_country_name VARCHAR(20),
    OUT new_id INT
)
BEGIN
    INSERT INTO teliuk.country (name) VALUE (new_country_name);
    SELECT id INTO new_id FROM teliuk.country WHERE name = new_country_name;
END //

-- Функція, яка рахує Avg для стовпця таблиці у БД.
DROP PROCEDURE IF EXISTS count_average_budget //
CREATE PROCEDURE count_average_budget()
BEGIN
    SELECT getAverageBudget();
END //

-- Забезпечити реалізацію зв’язку М:М між 2ма таблицями, вставити в стикувальну таблицю відповідну стрічку за реально-існуючими значеннями в цих основних таблицях.
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

-- Пакет, який вставляє 10 стрічок у таблицю БД у форматі < Noname+№>.
DROP PROCEDURE IF EXISTS insert_numbered_records //
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

-- Використовуючи курсор, забезпечити динамічне створення таблиць з назвами+штамп часу, взятими зі стовпця з довільної таблиці БД, з випадковою кількістю стовпців (від 1 до 9).
DROP PROCEDURE IF EXISTS create_tables_using_cursor //
CREATE PROCEDURE create_tables_using_cursor()
BEGIN
    DECLARE amount INT;
    DECLARE done BOOL DEFAULT false;
    DECLARE movie_name VARCHAR(255);
    DECLARE movies_cursor CURSOR FOR SELECT `first_name` from `actor`;
    DECLARE CONTINUE HANDLER
        FOR NOT FOUND SET done = true;
    OPEN movies_cursor;
    create_tables: LOOP
        FETCH movies_cursor into movie_name;
        IF done THEN LEAVE create_tables;
        END IF;
        SET amount=FLOOR(RAND()*(9-1+1)+2);
        SET @temp_query = CONCAT('CREATE TABLE IF NOT EXISTS ', movie_name, NOW()+1, ' (');
        create_columns: LOOP
            IF amount <= 0 THEN LEAVE create_columns;
            END IF;

            SET @temp_query = CONCAT(@temp_query, ' column', amount, ' int');
            SET amount = amount - 1;
            IF amount != 0 THEN SET @temp_query = CONCAT(@temp_query, ',');
            END IF;
        END LOOP create_columns;
        SET @temp_query=CONCAT(@temp_query, ' );');
        PREPARE table_query FROM @temp_query;
        EXECUTE table_query;
    END LOOP create_tables;
    CLOSE movies_cursor;
END//
DELIMITER ;
