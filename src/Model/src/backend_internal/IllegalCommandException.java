package backend_internal;

public class IllegalCommandException extends RuntimeException {

    /**
     * Create an exception based on an issue in our code.
     */
    public IllegalCommandException (String message, Object values) {
        super(String.format(message, values));
    }

}
