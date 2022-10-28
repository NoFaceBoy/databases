package ua.lviv.iot.imdb.view;

import ua.lviv.iot.imdb.controller.ActorController;
import ua.lviv.iot.imdb.controller.AwardController;
import ua.lviv.iot.imdb.controller.CompanyController;
import ua.lviv.iot.imdb.controller.DirectorController;
import ua.lviv.iot.imdb.controller.GenreController;
import ua.lviv.iot.imdb.controller.MovieController;
import ua.lviv.iot.imdb.controller.RankingController;
import ua.lviv.iot.imdb.controller.RoleController;

import ua.lviv.iot.imdb.domain.Actor;
import ua.lviv.iot.imdb.domain.Award;
import ua.lviv.iot.imdb.domain.Company;
import ua.lviv.iot.imdb.domain.Director;
import ua.lviv.iot.imdb.domain.Genre;
import ua.lviv.iot.imdb.domain.Movie;
import ua.lviv.iot.imdb.domain.Ranking;
import ua.lviv.iot.imdb.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyView {

    @Autowired
    private ActorController actorController;
    @Autowired
    private AwardController awardController;
    @Autowired
    private CompanyController companyController;
    @Autowired
    private DirectorController directorController;
    @Autowired
    private GenreController genreController;
    @Autowired
    private MovieController movieController;
    @Autowired
    private RankingController rankingController;
    @Autowired
    private RoleController roleController;


    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final Actor nullActor = new Actor(null, null, null, null, null, null);
    private final Award nullAward = new Award(null, null, null, null);
    private final Company nullCompany = new Company(null, null, null);
    private final Director nullDirector = new Director(null, null, null, null, null);
    private final Genre nullGenre = new Genre(null, null);
    private final Movie nullMovie = new Movie(null, null, null, null, null, null, null, null, null);
    private final Ranking nullRanking = new Ranking(null, null, null, null, null);
    private final Role nullRole = new Role(null, null, null);

    public MyView() {
        menu = new LinkedHashMap<>();
        menu.put("A", "  A - Select all table");

        menu.put("1", "   1 - Table: Movie");
        menu.put("11", "  11 - Create Movie");
        menu.put("12", "  12 - Update Movie");
        menu.put("13", "  13 - Delete from Movie");
        menu.put("14", "  14 - Find all Movies");
        menu.put("15", "  15 - Find Movie by ID");
        menu.put("16", "  16 - Find Movie by name");
        menu.put("17", "  17 - Find Movie by year");

//        menu.put("2", "   2 - Table: Actor");
//        menu.put("21", "  21 - Create Actor");
//        menu.put("22", "  22 - Update Actor");
//        menu.put("23", "  23 - Delete from Actor");
//        menu.put("24", "  24 - Find all Actors");
//        menu.put("25", "  25 - Find Actor by ID");
//
//        menu.put("3", "   3 - Table: Award");
//        menu.put("31", "  31 - Create Award");
//        menu.put("32", "  32 - Update Award");
//        menu.put("33", "  33 - Delete from Award");
//        menu.put("34", "  34 - Find all Awards");
//        menu.put("35", "  35 - Find Award by ID");
//
//        menu.put("4", "   4 - Table: Company");
//        menu.put("41", "  41 - Create Company");
//        menu.put("42", "  42 - Update Company");
//        menu.put("43", "  43 - Delete from Company");
//        menu.put("44", "  44 - Find all Companies");
//        menu.put("45", "  45 - Find Company by ID");
//
//        menu.put("5", "   5 - Table: Director");
//        menu.put("51", "  51 - Create Director");
//        menu.put("52", "  52 - Update Director");
//        menu.put("53", "  53 - Delete from Director");
//        menu.put("54", "  54 - Find all Directors");
//        menu.put("55", "  55 - Find Director by ID");
//
//        menu.put("6", "   6 - Table: Genre");
//        menu.put("61", "  61 - Create Genre");
//        menu.put("62", "  62 - Update Genre");
//        menu.put("63", "  63 - Delete from Genre");
//        menu.put("64", "  64 - Find all Genres");
//        menu.put("65", "  65 - Find Genre by ID");
//
//        menu.put("7", "   7 - Table: Ranking");
//        menu.put("71", "  71 - Create Ranking");
//        menu.put("72", "  72 - Update Ranking");
//        menu.put("73", "  73 - Delete from Ranking");
//        menu.put("74", "  74 - Find all Rankings");
//        menu.put("75", "  75 - Find Ranking by ID");
//
//        menu.put("8", "   8 - Table: Role");
//        menu.put("81", "  81 - Create Role");
//        menu.put("82", "  82 - Update Role");
//        menu.put("83", "  83 - Delete from Role");
//        menu.put("84", "  84 - Find all Role");
//        menu.put("85", "  85 - Find Role by ID");

        menu.put("Q", "  Q - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("A", this::selectAllTable);

        methodsMenu.put("11", this::createMovie);
        methodsMenu.put("12", this::updateMovie);
        methodsMenu.put("13", this::deleteFromMovie);
        methodsMenu.put("14", this::findAllMovies);
        methodsMenu.put("15", this::findMovieById);
        methodsMenu.put("16", this::findMovieByMovieName);
        methodsMenu.put("17", this::findMovieByYear);
//
//        methodsMenu.put("21", this::createActor);
//        methodsMenu.put("22", this::updateActor);
//        methodsMenu.put("23", this::deleteFromActor);
//        methodsMenu.put("24", this::findAllActors);
//        methodsMenu.put("25", this::findActorById);
//
//        methodsMenu.put("31", this::createAward);
//        methodsMenu.put("32", this::updateAward);
//        methodsMenu.put("33", this::deleteFromAward);
//        methodsMenu.put("34", this::findAllAwards);
//        methodsMenu.put("35", this::findAwardById);
//
//        methodsMenu.put("41", this::createCompany);
//        methodsMenu.put("42", this::updateCompany);
//        methodsMenu.put("43", this::deleteFromCompany);
//        methodsMenu.put("44", this::findAllCompanies);
//        methodsMenu.put("45", this::findCompanyById);
//
//        methodsMenu.put("51", this::createDirector);
//        methodsMenu.put("52", this::updateDirector);
//        methodsMenu.put("53", this::deleteFromDirector);
//        methodsMenu.put("54", this::findAllDirectors);
//        methodsMenu.put("55", this::findDirectorById);
//
//        methodsMenu.put("61", this::createGenre);
//        methodsMenu.put("62", this::updateGenre);
//        methodsMenu.put("63", this::deleteFromGenre);
//        methodsMenu.put("64", this::findAllGenres);
//        methodsMenu.put("65", this::findGenreById);
//
//        methodsMenu.put("71", this::createRanking);
//        methodsMenu.put("72", this::updateRanking);
//        methodsMenu.put("73", this::deleteFromRanking);
//        methodsMenu.put("74", this::findAllRankings);
//        methodsMenu.put("75", this::findRankingById);
//
//        methodsMenu.put("81", this::createRole);
//        methodsMenu.put("82", this::updateRole);
//        methodsMenu.put("83", this::deleteFromRole);
//        methodsMenu.put("84", this::findAllRoles);
//        methodsMenu.put("85", this::findRoleById);
    }

    private void selectAllTable() {
        findAllMovies();
//        findAllActors();
//        findAllAwards();
//        findAllCompanies();
//        findAllDirectors();
//        findAllGenres();
//        findAllRankings();
//        findAllRoles();
    }

    // region BOOK ---------------------------------------------------
    private void createMovie() {
        System.out.println("Input 'genre_id': ");
        Integer genreId = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'ranking_id': ");
        Integer rankingId = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'directors_id': ");
        Integer directorsId = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'description': ");
        String description = input.nextLine();
        System.out.println("Input 'budget': ");
        Integer budget = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'year': ");
        Integer year = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'facts': ");
        String facts = input.nextLine();
        Movie movie = new Movie(null, genreId, rankingId, directorsId, name, description, budget, year, facts);

        int count = movieController.create(movie);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateMovie() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'genre_id': ");
        Integer genreId = Integer.valueOf((input.nextLine()));
        System.out.println("Input new 'ranking_id': ");
        Integer rankingId = Integer.valueOf((input.nextLine()));
        System.out.println("Input new 'directors_id': ");
        Integer directorsId = Integer.valueOf((input.nextLine()));
        System.out.println("Input new 'name': ");
        String name = input.nextLine();
        System.out.println("Input new 'description': ");
        String description = input.nextLine();
        System.out.println("Input new 'budget': ");
        Integer budget = Integer.valueOf((input.nextLine()));
        System.out.println("Input new 'year': ");
        Integer year = Integer.valueOf((input.nextLine()));
        System.out.println("Input new 'facts': ");
        String facts = input.nextLine();
        Movie movie = new Movie(null, genreId, rankingId, directorsId, name, description, budget, year, facts);
        ;

        int count = movieController.update(id, movie);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromMovie() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = movieController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllMovies() {
        System.out.println("\nTable: MOVIE");
        List<Movie> movies = movieController.findAll();
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    private void findMovieById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Movie> movie = movieController.findById(id);
        System.out.println(movie.orElse(nullMovie));
    }

    private void findMovieByMovieName() {
        System.out.println("Input 'name': ");
        String name = input.nextLine();

        Optional<Movie> book = movieController.findByMovieName(name);
        System.out.println(book.orElse(nullMovie));
    }

    private void findMovieByYear() {
        System.out.println("Input 'year': ");
        Integer year = Integer.valueOf((input.nextLine()));

        Optional<Movie> movie = movieController.findByYear(year);
        System.out.println(movie.orElse(nullMovie));
    }

    //endregion
    //-------------------------------------------------------------------------
    // region output
    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet())
            if (key.length() == 1) System.out.println(menu.get(key));
    }

    private void outputSubMenu(String fig) {

        System.out.println("\nSubMENU:");
        for (String key : menu.keySet())
            if (key.length() != 1 && key.substring(0, 1).equals(fig)) System.out.println(menu.get(key));
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubMenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
            }

            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (!keyMenu.equals("Q"));
    }

    //endregion
}
