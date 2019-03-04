package Parsing;

import BackExternal.IllegalCommandException;
import Commands.CommandNode;
import BackExternal.ModelManager;

public class CommandClassFinder {

    public static CommandNode getObject(String classPath, String className, String language, ModelManager modelManager) {
        Class clazz = findReflectionClass(classPath, className);
        CommandNode o = instantiateReflectionClass(clazz,language, modelManager);
        return o;
    }

    private static Class findReflectionClass(String classPath, String classToFind) {
        Class clazz;
        try{
            clazz = Class.forName(classPath + classToFind);
        }
        catch (ClassNotFoundException e) {
            throw new IllegalCommandException(classToFind + " is not a valid command");
        }
        return clazz;
    }

    private static CommandNode instantiateReflectionClass(Class clazz, String language, ModelManager modelManager) {
        CommandNode instantiatedObject = null;
        try{
            instantiatedObject = (CommandNode) clazz.getDeclaredConstructor(String.class, ModelManager.class).newInstance(language, modelManager);
        }
        catch (Exception e) {
            System.out.println("Could not instantiate " + clazz);
        }
        if(instantiatedObject!=null) return instantiatedObject;
        else {
            throw new IllegalArgumentException();
        }
    }
}
