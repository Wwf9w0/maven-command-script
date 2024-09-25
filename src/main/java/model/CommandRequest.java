package model;

public class CommandRequest {

    private String path;

    private int number;
    private CommandType commandType;

    public CommandRequest() {

    }

    public CommandRequest(String path, CommandType type) {
        this.path = path;
        this.commandType = type;

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }



    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public int getNumber(){
        return number;
    }

    public void setNumber(int number){
        this.number = number;
    }
}
