-- -----------------------------------------------------
-- Data for table `teliuk`.`director`
-- -----------------------------------------------------
INSERT INTO
    `teliuk`.`director` (`id`, `first_name`, `last_name`, `gender`, `birthdate`)
VALUES
    (1, 'George', 'Lucas', 'Male', '1944-05-14'),
    (2, 'Christopher', 'Nolan', 'Male', '1970-07-30'),
    (3, 'Stanley', 'Kubrick', 'Male', '1928-07-26'),
    (4, 'James', 'McTiernan', 'Male', '1951-01-08'),
    (5, 'Martin', 'Scorsese', 'Male', '1942-11-17'),
    (6, 'Ron', 'Howard', 'Male', '1954-03-01'),
    (7, 'James', 'Cameron', 'Male', '1954-08-16'),
    (8, 'Steven', 'Spielberg', 'Male', '1946-12-18'),
    (9, 'Quentin', 'Tarantino', 'Male', '1963-03-27'),
    (10, 'Ridley', 'Scott', 'Male', '1937-11-30');
-- -----------------------------------------------------
-- Data for table `teliuk`.`genre`
-- -----------------------------------------------------
INSERT INTO
    `teliuk`.`genre` (`id`, `type`)
VALUES
    (1, 'action'),
    (2, 'biography'),
    (3, 'comedy'),
    (4, 'adventure'),
    (5, 'thriller'),
    (6, 'horror'),
    (7, 'fantasy'),
    (8, 'romance'),
    (9, 'documentary'),
    (10, 'detective');
-- -----------------------------------------------------
-- Data for table `teliuk`.`company`
-- -----------------------------------------------------
INSERT INTO
    `teliuk`.`company` (`id`, `name`)
VALUES
    (1, 'Lucasfilm'),
    (2, 'Paramount Pictures'),
    (3, 'Warner Bros.'),
    (4, 'Twentieth Century Fox'),
    (5, 'Red Granite Pictures'),
    (6, 'Universal Pictures'),
    (7, 'Cinema \'84'),
    (8, 'Exclusive Media Group');
-- -----------------------------------------------------
-- Data for table `teliuk`.`movie`
-- -----------------------------------------------------
INSERT INTO
    `teliuk`.`movie` (`id`, `genre_id`, `ranking_id`, `company_id`, `directors_id`, `name`, `description`, `budget`, `year`, `facts`)
VALUES
    (1, 7, 1, 1, 1, 'Star Wars', 'Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire\'s world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vader.', 11000000, 1977, 'When C-3PO and R2-D2 are in the control room of the Death Star, the storm troopers barge in, and one hits his head on the door. This goof was highlighted in the remastered version with a comedy \"donk\" sound effect.'),
    (2, 4, 2, 2, 8, 'Raiders of the Lost Ark', 'Archaeology professor Indiana Jones ventures to seize a biblical artefact known as the Ark of the Covenant. While doing so, he puts up a fight against Renee and a troop of Nazis.', 18000000, 1981, 'In the flying scenes, the map lists several countries by their modern names instead of their 1936 names. Siam did not become Thailand until 1939. Transjordan did not become Jordan until 1949.'),
    (3, 4, 3, 2, 2, 'Interstellar', 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity\'s survival.', 165000000, 2014, 'Two characters sustain a fall from an ice plateau, on a steep ice ramp, onto a shadowy ice platform. A moment later, a panoramic shot shows them fighting on a very different place.'),
    (4, 6, 4, 3, 3, 'The Shining', 'A family heads to an isolated hotel for the winter where a sinister presence influences the father into violence, while his psychic son sees horrific forebodings from both past and future.', 19000000, 1980, 'When Wendy hits Jack over the head with the baseball bat on the staircase, the bat flexes, revealing it to be made of rubber.'),
    (5, 5, 5, 4, 4, 'Die Hard', 'An NYPD officer tries to save his wife and several others taken hostage by German terrorists during a Christmas party at the Nakatomi Plaza in Los Angeles.', 28000000, 1988, 'There is no ambulance in the truck when the terrorists first arrive, but Theo drives an ambulance out of the truck near the end of the movie (error acknowledged by filmmakers).'),
    (6, 3, 6, 5, 5, 'The Wolf of Wall Street', 'Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.', 100000000, 2013, 'During the Black Monday crash of 1987 all of the digital tickers in the background are green - which means the market is going up.'),
    (7, 2, 7, 6, 6, 'A Beautiful Mind', 'After John Nash, a brilliant but asocial mathematician, accepts secret work in cryptography, his life takes a turn for the nightmarish.', 58000000, 2001, 'There is no \"ceremony of the pens\" at Princeton University.'),
    (8, 4, 8, 6, 8, 'Jurassic Park', 'A pragmatic paleontologist touring an almost complete theme park on an island in Central America is tasked with protecting a couple of kids after a power failure causes the park\'s cloned dinosaurs to run loose.', 63000000, 1993, 'When they arrive at the T. rex cage right before the black out, we can clearly see the ground inside the cage is approximately flush with that outside the cage. But when the T. rex escapes and begins to terrorize the two trucks.'),
    (9, 1, 9, 7, 7, 'The Terminator', 'A human soldier is sent from 2029 to 1984 to stop an almost indestructible cyborg killing machine, sent from the same year, which has been programmed to execute a young woman whose unborn son is the key to humanity\'s future salvation.', 6400000, 1984, 'Newton\'s Third Law establishes that bullets and shotgun blasts do not have the momentum to knock a human body backwards, although the trauma of being shot may cause them to fall backward.'),
    (10, 2, 10, 8, 6, 'Rush', 'The merciless 1970s rivalry between Formula One rivals James Hunt and Niki Lauda.', 38000000, 2012 , 'Throughout the film Niki Lauda\'s nationality is shown in the stats abbreviated as AUS - this would be the international code for Australia. Austria\'s code is AUT.');
-- -----------------------------------------------------
-- Data for table `teliuk`.`role`
-- -----------------------------------------------------
INSERT INTO
    `teliuk`.`role` (`id`, `movie_id`, `name`)
VALUES
    (1, 1, 'Luke Skywalker'),
    (2, 1, 'Han Solo'),
    (3, 1, 'Darth Vader'),
    (4, 3, 'Cooper'),
    (5, 3, 'Brand'),
    (6, 3, 'Murph'),
    (7, 3, 'Donald'),
    (8, 9, 'The Terminator'),
    (9, 9, 'Sarah Connor'),
    (10, 9, 'Kyle Reese');
-- -----------------------------------------------------
-- Data for table `teliuk`.`actor`
-- -----------------------------------------------------
INSERT INTO
    `teliuk`.`actor` (`id`, `roles_id`, `first_name`, `last_name`, `gender`, `birthdate`)
VALUES
    (1, 1, 'Mark', 'Hamill', 'Male', '1951-09-25'),
    (2, 2, 'Harrison', 'Ford', 'Male', '1942-07-13'),
    (3, 3, 'David', 'Prowse', 'Male', '1935-07-01'),
    (4, 4, 'Matthew', 'McConaughley', 'Male', '1969-11-04'),
    (5, 5, 'Anne', 'Hathaway', 'Female', '1982-11-12'),
    (6, 6, 'Jessica', 'Chastain', 'Female', '1977-03-24'),
    (7, 7, 'John', 'Lithgow', 'Male', '1945-10-19'),
    (8, 8, 'Arnold', 'Schwarznegger', 'Male', '1947-07-30'),
    (9, 9, 'Linda', 'Hamilton', 'Female', '1956-09-26'),
    (10, 10, 'Michael', 'Biehn', 'Male', '1956-07-31');
-- -----------------------------------------------------
-- Data for table `teliuk`.`award`
-- -----------------------------------------------------
INSERT INTO
    `teliuk`.`award` (`id`, `name`)
VALUES
    (1, 'Saturn Award'),
    (2, 'OFTA Film Hall of Fame'),
    (3, 'Blue Ribbon Award'),
    (4, 'Oscar');
-- -----------------------------------------------------
-- Data for table `teliuk`.`award`
-- -----------------------------------------------------
INSERT INTO
    `teliuk`.`award_movie` (`award_id`, `movie_id`)
VALUES
    (1, 1),
    (2, 1),
    (4, 1),
    (4, 5),
    (4, 8),
    (3, 2),
    (2, 3);
-- -----------------------------------------------------
-- Data for table `teliuk`.`nomination`
-- -----------------------------------------------------
INSERT INTO
    `teliuk`.`nomination` (`id`, `award_id`, `type`)
VALUES
    (1, 1, 'Best DVD Collection'),
    (2, 2, 'Best Motion Picture'),
    (3, 3, 'Best Foreign Language Film'),
    (4, 4, 'Best Achievement in Visual Effects'),
    (5, 4, 'Best Art Direction-Set Decoration'),
    (6, 4, 'Best Costume Design'),
    (7, 4, 'Best Sound'),
    (8, 4, 'Best Film Editing'),
    (9, 4, 'Best Effects, Visual Effects'),
    (10, 4, 'Best Music, Original Score');