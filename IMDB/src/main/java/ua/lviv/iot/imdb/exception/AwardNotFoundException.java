package ua.lviv.iot.imdb.exception;

public class AwardNotFoundException extends RuntimeException {
    public AwardNotFoundException(Integer id){
        super("Could not find 'award' with id=" + id);
    }
}
