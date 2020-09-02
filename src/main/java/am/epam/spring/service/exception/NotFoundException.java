package am.epam.spring.service.exception;

/**
 * @author Martin Mirzoyan
 */
public class NotFoundException extends Exception{
    public NotFoundException() {
        super("Not Found!");
    }
}
