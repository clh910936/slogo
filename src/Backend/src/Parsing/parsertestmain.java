package Parsing;

import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class parsertestmain {
    public static void main (String[] args) {
        CommandParser cp = new CommandParser(new VariablesModel(), new TurtleModel(200,200,true,90,true), new UserCreatedCommandsModel());

//        System.out.println(cp.parseCommand("# same as fd 100\n" +
//                "fd fd 50\n" +
//                "\n" +
//                "# same as rt 100 fd 100\n" +
//                "fd rt 100\n" +
//                "\n" +
//                "# same as fd 100\n" +
//                "fd * greater? 5 3 100\n" +
//                "\n" +
//                "# same as fd 10 fd 20 fd 30 fd 40 rt 40\n" +
//                "rt for [ :dist 10 40 10 ] [ \n" +
//                "  fd :dist\n" +
//                "]\n", "English"));
//        System.out.println(cp.parseCommand("sum 10 10", "English"));


//        System.out.println(cp.parseCommand("sum 110 [ 1 2 3 ]", "English"));
        System.out.println(cp.parseCommand("to dash [ :count :size ]\n" +
                "[\n" +
                "  repeat :count\n" +
                "  [\n" +
                "    pu fd :size pd fd :size\n" +
                "  ]\n" +
                "] dash 20 8", "English"));
//        System.out.println(cp.parseCommand("setxy 3 4", "English"));
        //System.out.println(cp.parseCommand("seth -90", "English"));
//        System.out.println(cp.parseCommand("left -360", "English"));
//        System.out.println(cp.parseCommand("right 30", "English"));
//        System.out.println(cp.parseCommand("rt for [ :x 10 40 10 ] [ sum :x 10 ]", "English"));
//        System.out.println(cp.parse("rt for [ :dist 10 40 10 ] [ \n" +
//                "  fd :dist\n" +
//                "]", "English"));
    }
}
