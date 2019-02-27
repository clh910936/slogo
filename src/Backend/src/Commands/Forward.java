<<<<<<< HEAD:src/Model/src/Parsing/Commands/Forward.java
package Parsing.Commands;
=======
package Commands;
>>>>>>> master:src/Backend/src/Commands/Forward.java

public class Forward extends OneParamCommand {

    public Forward() {
        super();
    }

    @Override
    public double executeCommand() {
        this.turtle.moveForward(input);
        return input;
    }
}
