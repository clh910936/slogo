package Exceptions;

public class IllegalLoopParamsException extends RuntimeException{
    /**
     * Create an exception based on an issue in our code.
     */
    public IllegalLoopParamsException() {
        super("Parameters are not correct for loop to execute");
    }
}
