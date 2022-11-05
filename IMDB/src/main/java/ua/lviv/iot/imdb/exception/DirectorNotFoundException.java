package ua.lviv.iot.imdb.exception;

public class DirectorNotFoundException extends RuntimeException {
    public DirectorNotFoundException(Integer id){
        super("Could not find 'director' with id=" + id);
    }
}
