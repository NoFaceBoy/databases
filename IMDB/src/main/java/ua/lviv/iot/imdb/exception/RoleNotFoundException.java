package ua.lviv.iot.imdb.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Integer id){
        super("Could not find 'role' with id=" + id);
    }
}
