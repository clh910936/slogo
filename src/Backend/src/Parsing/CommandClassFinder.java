package Parsing;

import BackExternal.IllegalCommandException;
import Commands.CommandsGeneral;

public class CommandClassFinder {

    public static CommandsGeneral getObject(String classPath, String className, String language) {
        Class clazz = findReflectionClass(classPath, className);
        CommandsGeneral o = instantiateReflectionClass(clazz,language);
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

    private static CommandsGeneral instantiateReflectionClass(Class clazz, String language) {
        CommandsGeneral instantiatedObject = null;
        try{
            instantiatedObject = (CommandsGeneral) clazz.getDeclaredConstructor(String.class).newInstance(language);
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
