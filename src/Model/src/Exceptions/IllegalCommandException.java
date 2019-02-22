<<<<<<< HEAD:src/Model/IllegalCommandException.java
=======
package Exceptions;
>>>>>>> c3468487d69faec44fa7946c76c62a0c446d55be:src/Model/src/Exceptions/IllegalCommandException.java

public class IllegalCommandException extends RuntimeException {

    /**
     * Create an exception based on an issue in our code.
     */
    public IllegalCommandException (String message, Object values) {
        super(String.format(message, values));
    }

}
