package Parsing;

import BackExternal.IllegalCommandException;
import Commands.CommandsGeneral;
import Models.Turtle;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class CommandClassFinder {

    public static CommandsGeneral getObject(String classPath, String className, String language, VariablesModel variablesModel, Turtle turtle, UserCreatedCommandsModel userCreatedCommandsModel) {
        Class clazz = findReflectionClass(classPath, className);
        CommandsGeneral o = instantiateReflectionClass(clazz,language, variablesModel, turtle, userCreatedCommandsModel);
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

    private static CommandsGeneral instantiateReflectionClass(Class clazz, String language, VariablesModel variablesModel, Turtle turtle, UserCreatedCommandsModel userCreatedCommandsModel) {
        CommandsGeneral instantiatedObject = null;
        try{
            instantiatedObject = (CommandsGeneral) clazz.getDeclaredConstructor(String.class, Turtle.class, VariablesModel.class, UserCreatedCommandsModel.class).newInstance(language, turtle,variablesModel, userCreatedCommandsModel);
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
