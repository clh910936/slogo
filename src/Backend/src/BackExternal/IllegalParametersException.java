package BackExternal;

public class IllegalParametersException extends RuntimeException{

    /**
     * Create an exception based on an issue in our code.
     */
    public IllegalParametersException() {
        super("Wrong parameters inputted!");
    }
}
