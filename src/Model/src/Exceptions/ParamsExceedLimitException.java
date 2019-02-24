package Exceptions;

public class ParamsExceedLimitException extends RuntimeException {

    /**
     * Create an exception based on an issue in our code.
     */
    public ParamsExceedLimitException () {
        super("you're giving me too many parameters");
    }

}
