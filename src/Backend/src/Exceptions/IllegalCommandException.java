package Exceptions;

public class IllegalCommandException extends RuntimeException {

    /**
     * Create an exception based on an issue in our code.
     */
    public IllegalCommandException (String message) {
        super(String.format(message));
    }

}
