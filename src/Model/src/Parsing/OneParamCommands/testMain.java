package Parsing.OneParamCommands;


import Parsing.OneParamCommands.Turtle.Forward;

public class testMain {

    public static void main (String[] args) {
        Forward fd = new Forward();
        System.out.println(fd.isCommandReadyToRemove());
        fd.addParameterToCommand(25);
        System.out.println(fd.isCommandReadyToRemove());
        System.out.println(fd.executeCommand());
//        fd.addParameterToCommand(6);
//        System.out.println(fd.isCommandReadyToRemove());
    }
}
