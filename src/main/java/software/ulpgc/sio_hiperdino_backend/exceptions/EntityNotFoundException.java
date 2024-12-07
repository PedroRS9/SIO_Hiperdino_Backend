package software.ulpgc.sio_hiperdino_backend.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity) {
        super("Entity not found: " + entity);
    }
}
