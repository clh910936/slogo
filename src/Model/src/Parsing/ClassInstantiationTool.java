package Parsing;

import Exceptions.IllegalCommandException;
import java.lang.reflect.InvocationTargetException;

public class ClassInstantiationTool {

    private static Object getObject(String classPath, String className) {
        Class classToInstantiate = findReflectionClass(classPath, className);
        Object instantiatedObject = instantiateReflectionClass(classToInstantiate);
        return instantiatedObject;
    }

    private static Class findReflectionClass(String classPath, String classToFind) {
        Class foundClass;
        try{
            foundClass = Class.forName(classPath + classToFind);
        }
        catch (ClassNotFoundException e) {
            throw new IllegalCommandException(classToFind + " is not a valid command");
        }
        return foundClass;
    }

    private static Object instantiateReflectionClass(Class myClass) {
        Object instantiatedObject = null;
        try{
            instantiatedObject = myClass.getDeclaredConstructor().newInstance();
        }
        catch (NoSuchMethodException e){
            System.out.println(myClass + " has no constructor");
        }
        catch (InstantiationException e) {
            System.out.println("Could not instantiate " + myClass);
        }
        catch (IllegalAccessException e) {
            System.out.println("Could not instantiate " + myClass);
        }
        catch (InvocationTargetException e) {
            System.out.println("Could not instantiate " + myClass);
        }
        if(instantiatedObject!=null) return myClass;
        else {
            throw new IllegalArgumentException();
        }
    }
}
