public class BashCommand extends BashScript{

    String command = "";
    String argument = "";
    String argument2 = "";
    String argument3 = "";
    String argument4 = "";
    String argument5 = "";

    BashCommand(String command) {
        this.command = command;
    }

    BashCommand(String command, String argument) {
        this.command = command;
        this.argument = argument;
    }

    BashCommand(String command, String argument, String argument2) {
        this.command = command;
        this.argument = argument;
        this.argument2 = argument2;
    }

    BashCommand(String command, String argument, String argument2, String argument3) {
        this.command = command;
        this.argument = argument;
        this.argument2 = argument2;
        this.argument3 = argument3;
    }

    BashCommand(String command, String argument, String argument2, String argument3, String argument4) {
        this.command = command;
        this.argument = argument;
        this.argument2 = argument2;
        this.argument3 = argument3;
        this.argument4 = argument4;
    }

    BashCommand(String command, String argument, String argument2, String argument3, String argument4, String argument5) {
        this.command = command;
        this.argument = argument;
        this.argument2 = argument2;
        this.argument3 = argument3;
        this.argument4 = argument4;
        this.argument5 = argument5;
    }

    public String getCom() {
        return command;
    }

    public String getArg(Integer n) throws Exception {
        if (n == 1) {
            return argument;
        } else if (n == 2) {
            return argument2;
        } else if (n == 3) {
            return argument3;
        } else if (n == 4) {
            return argument4;
        } else if (n == 5) {
            return argument5;
        } else {
            throw new Exception("Argument out of bounds");
        }
    }
}
