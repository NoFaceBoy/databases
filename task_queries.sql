use Labor_SQL;

-- 1. БД «Комп. фірма». Знайдіть усі записи таблиці Printer для
-- кольорових принтерів. Вихідні дані впорядкувати за спаданням за
-- стовпцем price.

SELECT * FROM Printer
WHERE color = "y"
ORDER BY price DESC;

-- 2. БД «Кораблі». З таблиці Ships вивести назви кораблів, що мають у
-- своїй назві дві літери 'e'.

SELECT name FROM Ships
WHERE name LIKE "%e%e";

-- 3. БД «Комп. фірма». Знайдіть номер моделі та виробника принтера,
-- яка має ціну вищу за 300 дол. Вивести: model, maker.

SELECT Product.model, price, maker FROM Product
JOIN Printer ON Product.model = Printer.model WHERE price > 300;

-- 4. БД «Кораблі». Вивести класи всіх кораблів США ('USA'). Якщо в БД
-- немає класів кораблів США, тоді вивести класи для всіх наявних у БД
-- країн. Вивести: country, class.

SELECT country, class FROM Classes
WHERE country = 'USA' OR NOT EXISTS(
SELECT country, class FROM Classes
);

-- 5. БД «Комп. фірма». Знайдіть виробників, які б випускали ПК зі
-- швидкістю 750 МГц та вище. Виведіть: maker.

SELECT maker FROM Product 
JOIN PC ON Product.model = PC.model WHERE speed>=750
GROUP BY maker ORDER BY maker;

-- 6. БД «Кораблі». Для таблиці Outcomes виведіть дані, а замість значень
-- стовпця result, виведіть еквівалентні їм надписи українською мовою.

SELECT ship, battle,
CASE
WHEN result = 'OK' THEN 'Чудовий стан'
WHEN result = 'sunk' THEN 'Потонув'
WHEN result = 'damaged' THEN 'Пошкоджений'
END AS translated_result
FROM Outcomes;

-- 7. БД «Комп. фірма». Знайдіть виробників найдешевших принтерів.
-- Вивести: maker, price.

SELECT maker, Printer.price FROM Product
JOIN Printer ON Product.model = Printer.model
ORDER BY price ASC;

-- 8. БД «Комп. фірма». Знайдіть максимальну ціну ПК, що випускаються кожним виробником. Вивести: maker, максимальна ціна. (Підказка:
-- використовувати підзапити в якості обчислювальних стовпців)

SELECT maker, COUNT(PC.price) FROM Product
JOIN PC ON Product.model = PC.model
GROUP BY maker;

-- 9. БД «Фірма прий. вторсировини». Приймаючи, що прихід та розхід
-- грошей на кожному пункті прийому може фіксуватися довільне число
-- разів на день (лише таблиці Income та Outcome), написати запит із
-- такими вихідними даними: point (пункт), date (дата), inc (прихід), out
-- (розхід), у якому в кожному пункті за кожну дату відповідає лише
-- одна стрічка. (Підказка: використовувати зовнішнє з’єднання та
-- оператор CASE)

SELECT SUM(sq.inc), sum(sq.outcome), sq.pt, sq.date FROM (
SELECT Income.point pt, inc, 0 outcome , Income.date date
FROM Income LEFT JOIN Outcome 
ON Income.date = Outcome.date AND Income.point = Outcome.point
UNION ALL
SELECT Outcome.point pt, 0, Outcome.out outcome, Outcome.date date
FROM Income RIGHT JOIN Outcome 
ON Income.date = Outcome.date AND Income.point = Outcome.point) AS sq
GROUP BY sq.pt, sq.date ORDER BY sq.date;

-- 10. БД «Комп. фірма». Для кожної моделі продукції з усієї БД виведіть
-- її найвищу ціну. Вивести: type, model, максимальна ціна. (Підказка:
-- використовувати оператор UNION)

SELECT Product.type, PC.model, MAX(PC.price) AS max_price FROM Product
JOIN PC ON Product.model = PC.model WHERE price
UNION
SELECT Product.type, Printer.model, MAX(Printer.price) FROM Product
JOIN Printer ON Product.model = Printer.model WHERE price
UNION
SELECT Product.type, Laptop.model, MAX(Laptop.price) FROM Product
JOIN Laptop ON Product.model = Laptop.model WHERE price;


