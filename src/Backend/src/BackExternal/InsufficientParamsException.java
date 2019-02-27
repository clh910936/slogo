package BackExternal;

public class InsufficientParamsException extends RuntimeException{

    /**
     * Create an exception based on an issue in our code.
     */
    public InsufficientParamsException() {
        super("Not enough parameters to execute command!");
    }
}
