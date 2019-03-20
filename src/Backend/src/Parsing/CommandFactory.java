package Parsing;

import BackExternal.IllegalCommandException;
import Commands.CommandNode;
import BackExternal.ModelManager;

/**
 * This class is a utility class used to obtain a CommandNode object
 * based on the string name of the command (not user-defined command).
 * It will handle any input and throw an exception if the command
 * does not exist or if the path is wrong.
 * This class depends on the CommandNode class and its subclasses and the ModelManager class
 * to pass into the constructor of the CommandNode class.
 */

public class CommandFactory {

    private CommandFactory() {
    }

    /**
     * @author Christina Chen
     * returns CommandNode object associated with the string name of a command (not user-defined)
     * so that the command can ultimately be executed
     * @param classPath
     * @param className
     * @param modelManager used in constructor of CommandNode
     * @return CommandNode object
     */
    public static CommandNode getObject(String classPath, String className, ModelManager modelManager) throws IllegalCommandException {
        try {
            Class clazz = findReflectionClass(classPath, className);
            CommandNode o = instantiateReflectionClass(clazz, modelManager);
            return o;
        }
        catch (IllegalCommandException e) {
            throw e;
        }
    }

    private static Class findReflectionClass(String classPath, String classToFind) throws IllegalCommandException {
        Class clazz;
        try{
            clazz = Class.forName(classPath + classToFind);
        }
        catch (ClassNotFoundException e) {
            throw new IllegalCommandException(classToFind + " is not a valid command");
        }
        return clazz;
    }

    private static CommandNode instantiateReflectionClass(Class clazz, ModelManager modelManager) {
        try{
            CommandNode instantiatedObject = (CommandNode) clazz.getDeclaredConstructor(ModelManager.class).newInstance(modelManager);
            return instantiatedObject;
        }
        catch (Exception e) {
            // shouldn't ever happen
            throw new IllegalArgumentException();
        }
    }
}
