use teliuk;

-- 1. Запит для фільмів випущених з 1985року до 1995року.

SELECT name, year FROM movie
WHERE year > 1985 AND year < 1995;

-- 2. Запит для ролі та інформації про актора.

SELECT role, first_name, last_name FROM role
INNER JOIN actor ON role.id = roles_id;

-- 3. Запит для відгуків до Зоряних Війн.

SELECT name, username, posted_at, rate, message FROM movie
JOIN ranking ON movie.ranking_id = ranking.id
JOIN user_review ON user_review.ranking_id = ranking.id
WHERE name = 'Star Wars'
ORDER BY posted_at;

-- 4. Запит для нагород отриманних Зоряними Війнами і вказаного імені режисера.

SELECT movie.name, award.name, type, first_name, last_name FROM movie
JOIN award ON movie.id = movie_id
JOIN nomination ON award.id  = award_id
JOIN director on director.id = directors_id
WHERE movie.name = 'Star Wars';

-- 5. Запит для сортування від найновішого фільму.

SELECT name, description, year FROM movie
ORDER BY year DESC;

-- 6.  Запит для отримання повної інформації про фільми.

SELECT name, description, type, year, rating FROM movie
JOIN genre ON genre_id = genre.id
JOIN ranking on ranking_id = ranking.id;

-- 7. Запит для відображення фільму з найбільшим і найменшим бюджетом.

SELECT name, budget FROM movie
WHERE budget = (SELECT MAX(budget) FROM movie)
UNION
SELECT name, budget FROM movie
WHERE budget = (SELECT MIN(budget) FROM movie);

-- 8. Запит для відображення фільмів в жанрі пригод.

SELECT name, type FROM movie
JOIN genre ON genre_id = genre.id
WHERE type = 'adventure';

-- 9. Запит для відображення актрис.

SELECT first_name, last_name, birthdate FROM actor
WHERE gender = 'Female'
ORDER BY first_name ASC;

-- 10. Запит для розрахунку середьної оцінки фільму від критиків.

SELECT name, AVG(rate) as average_rate FROM movie
JOIN ranking ON movie.ranking_id = ranking.id
JOIN critic_review ON critic_review.ranking_id = ranking.id
WHERE name = 'Interstellar';
