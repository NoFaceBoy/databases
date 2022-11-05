package ua.lviv.iot.imdb.exception;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(Integer id){
        super("Could not find 'genre' with id=" + id);
    }
}
