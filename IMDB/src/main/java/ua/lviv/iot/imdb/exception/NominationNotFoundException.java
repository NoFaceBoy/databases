package ua.lviv.iot.imdb.exception;

public class NominationNotFoundException extends RuntimeException {
    public NominationNotFoundException(Integer id){
        super("Could not find 'nomination' with id=" + id);
    }
}
