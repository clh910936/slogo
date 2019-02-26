package Parsing;

import Turtle.TurtleModel;
import Variables.VariablesModel;

public class parsertestmain {
    public static void main (String[] args) {
        CommandParser cp = new CommandParser(new VariablesModel(), new TurtleModel(200,200,true,90,true));
        System.out.println(cp.parseCommand("towards 300 300", "English"));
        //System.out.println(cp.parseCommand("seth -90", "English"));
//        System.out.println(cp.parseCommand("left -360", "English"));
//        System.out.println(cp.parseCommand("right 30", "English"));

        //System.out.println(cp.parseCommand("rt for [ :x 10 40 10 ] [ sum :x 10 ]", "English"));
//        System.out.println(cp.parse("rt for [ :dist 10 40 10 ] [ \n" +
//                "  fd :dist\n" +
//                "]", "English"));
    }
}
