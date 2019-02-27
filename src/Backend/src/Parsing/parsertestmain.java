package Parsing;

import Models.TurtleModel;
import Models.VariablesModel;

public class parsertestmain {
    public static void main (String[] args) {
<<<<<<< HEAD
        CommandParser cp = new CommandParser(new VariablesModel(), new TurtleModel(200,200,true,90,true));
        System.out.println(cp.parseCommand("# same as fd 100\n" +
                "fd fd 50\n" +
                "\n" +
                "# same as rt 100 fd 100\n" +
                "fd rt 100\n" +
                "\n" +
                "# same as fd 100\n" +
                "fd * greater? 5 3 100\n" +
                "\n" +
                "# same as fd 10 fd 20 fd 30 fd 40 rt 40\n" +
                "rt for [ :dist 10 40 10 ] [ \n" +
                "  fd :dist\n" +
                "]\n", "English"));
//        System.out.println(cp.parseCommand("sum 10 10", "English"));


=======
        CommandParser cp = new CommandParser(new VariablesModel(), new TurtleModel(0,0,true,90,true));
        //System.out.println(cp.parseCommand("towards 300 300", "English"));
        System.out.println(cp.parseCommand("left 110", "English"));
        System.out.println(cp.parseCommand("fd 20", "English"));
        System.out.println(cp.parseCommand("setxy 3 4", "English"));
        //System.out.println(cp.parseCommand("seth -90", "English"));
//        System.out.println(cp.parseCommand("left -360", "English"));
//        System.out.println(cp.parseCommand("right 30", "English"));
>>>>>>> 8bbba64743eb71cef94bd50532ce29cf6cc1285d
        //System.out.println(cp.parseCommand("rt for [ :x 10 40 10 ] [ sum :x 10 ]", "English"));
//        System.out.println(cp.parse("rt for [ :dist 10 40 10 ] [ \n" +
//                "  fd :dist\n" +
//                "]", "English"));
    }
}
