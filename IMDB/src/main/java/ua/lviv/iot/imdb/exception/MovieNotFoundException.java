package ua.lviv.iot.imdb.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(Integer id){
        super("Could not find 'movie' with id=" + id);
    }
}
