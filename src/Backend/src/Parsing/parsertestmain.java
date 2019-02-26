package Parsing;

import Models.TurtleModel;
import Models.VariablesModel;

public class parsertestmain {
    public static void main (String[] args) {
        CommandParser cp = new CommandParser(new VariablesModel(), new TurtleModel(200,200,true,90,true));
        System.out.println(cp.parseCommand("make :random sum 1 random 100\n" +
                "fd :random\n", "English"));
//        System.out.println(cp.parseCommand("sum 10 10", "English"));


        //System.out.println(cp.parseCommand("rt for [ :x 10 40 10 ] [ sum :x 10 ]", "English"));
//        System.out.println(cp.parse("rt for [ :dist 10 40 10 ] [ \n" +
//                "  fd :dist\n" +
//                "]", "English"));
    }
}
