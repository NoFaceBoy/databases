package ua.lviv.iot.imdb.exception;

public class ActorNotFoundException extends RuntimeException {
    public ActorNotFoundException(Integer id){
        super("Could not find 'book' with id=" + id);
    }
}
