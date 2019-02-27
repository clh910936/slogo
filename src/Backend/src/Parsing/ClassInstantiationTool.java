package Parsing;

<<<<<<< HEAD
import Exceptions.IllegalCommandException;
=======
import BackExternal.IllegalCommandException;
import java.lang.reflect.InvocationTargetException;
>>>>>>> 8bbba64743eb71cef94bd50532ce29cf6cc1285d

public class ClassInstantiationTool {

    public static Object getObject(String classPath, String className) {
        Class clazz = findReflectionClass(classPath, className);
        Object o = instantiateReflectionClass(clazz);
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

    private static Object instantiateReflectionClass(Class clazz) {
        Object instantiatedObject = null;
        try{
            instantiatedObject = clazz.getDeclaredConstructor().newInstance();
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