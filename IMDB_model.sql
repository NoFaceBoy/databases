-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2022-09-14 15:31:18.608

-- tables
-- Table: actor
CREATE TABLE actor (
    id int NOT NULL,
    roles_id int NOT NULL,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    gender varchar(20) NULL,
    birthdate date NOT NULL,
    CONSTRAINT actor_pk PRIMARY KEY (id)
);

-- Table: award
CREATE TABLE award (
    id int NOT NULL,
    movie_id int NOT NULL,
    name varchar(30) NOT NULL,
    year int NOT NULL,
    CONSTRAINT award_pk PRIMARY KEY (id)
);

-- Table: company
CREATE TABLE company (
    id int NOT NULL,
    movie_id int NOT NULL,
    name varchar(50) NOT NULL,
    CONSTRAINT company_pk PRIMARY KEY (id)
);

-- Table: critic_review
CREATE TABLE critic_review (
    id int NOT NULL,
    ranking_id int NOT NULL,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    rate int NOT NULL,
    message varchar(500) NULL,
    CONSTRAINT critic_review_pk PRIMARY KEY (id)
);

-- Table: director
CREATE TABLE director (
    id int NOT NULL,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    gender varchar(20) NULL,
    birthdate date NOT NULL,
    CONSTRAINT director_pk PRIMARY KEY (id)
);

-- Table: genre
CREATE TABLE genre (
    id int NOT NULL,
    type varchar(20) NOT NULL,
    CONSTRAINT genre_pk PRIMARY KEY (id)
);

-- Table: movie
CREATE TABLE movie (
    id int NOT NULL,
    genre_id int NOT NULL,
    ranking_id int NOT NULL,
    directors_id int NOT NULL,
    name varchar(100) NOT NULL,
    description varchar(300) NOT NULL,
    budget int NOT NULL,
    year int NOT NULL,
    facts varchar(500) NOT NULL,
    CONSTRAINT movie_pk PRIMARY KEY (id)
);

-- Table: nomination
CREATE TABLE nomination (
    id int NOT NULL,
    award_id int NOT NULL,
    type varchar(50) NOT NULL,
    CONSTRAINT nomination_pk PRIMARY KEY (id)
);

-- Table: ranking
CREATE TABLE ranking (
    id int NOT NULL,
    rating int NULL,
    number_of_user_rates int NULL,
    metascore int NULL,
    number_of_critic_rates int NULL,
    CONSTRAINT ranking_pk PRIMARY KEY (id)
);

-- Table: role
CREATE TABLE role (
    id int NOT NULL,
    movie_id int NOT NULL,
    role varchar(50) NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (id)
);

-- Table: user_review
CREATE TABLE user_review (
    id int NOT NULL,
    ranking_id int NOT NULL,
    rate int NOT NULL,
    message varchar(300) NULL,
    username varchar(20) NOT NULL,
    posted_at timestamp NOT NULL,
    CONSTRAINT user_review_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: actor_roles (table: actor)
ALTER TABLE actor ADD CONSTRAINT actor_roles FOREIGN KEY actor_roles (roles_id)
    REFERENCES role (id);

-- Reference: award_movie (table: award)
ALTER TABLE award ADD CONSTRAINT award_movie FOREIGN KEY award_movie (movie_id)
    REFERENCES movie (id);

-- Reference: company_movie (table: company)
ALTER TABLE company ADD CONSTRAINT company_movie FOREIGN KEY company_movie (movie_id)
    REFERENCES movie (id);

-- Reference: critic_review_ranking (table: critic_review)
ALTER TABLE critic_review ADD CONSTRAINT critic_review_ranking FOREIGN KEY critic_review_ranking (ranking_id)
    REFERENCES ranking (id);

-- Reference: movie_directors (table: movie)
ALTER TABLE movie ADD CONSTRAINT movie_directors FOREIGN KEY movie_directors (directors_id)
    REFERENCES director (id);

-- Reference: movie_genre (table: movie)
ALTER TABLE movie ADD CONSTRAINT movie_genre FOREIGN KEY movie_genre (genre_id)
    REFERENCES genre (id);

-- Reference: movie_ranking (table: movie)
ALTER TABLE movie ADD CONSTRAINT movie_ranking FOREIGN KEY movie_ranking (ranking_id)
    REFERENCES ranking (id);

-- Reference: nomination_award (table: nomination)
ALTER TABLE nomination ADD CONSTRAINT nomination_award FOREIGN KEY nomination_award (award_id)
    REFERENCES award (id);

-- Reference: review_ranking (table: user_review)
ALTER TABLE user_review ADD CONSTRAINT review_ranking FOREIGN KEY review_ranking (ranking_id)
    REFERENCES ranking (id);

-- Reference: roles_movie (table: role)
ALTER TABLE role ADD CONSTRAINT roles_movie FOREIGN KEY roles_movie (movie_id)
    REFERENCES movie (id);

-- End of file.

