package model;

public class CommandRequest {

    private String path;

    private int number;
    private ScriptCommandType scriptCommandType;

    public CommandRequest() {

    }

    public CommandRequest(String path, ScriptCommandType scriptCommandType) {
        this.path = path;
        this.scriptCommandType = scriptCommandType;

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }



    public ScriptCommandType getScriptCommandType() {
        return scriptCommandType;
    }

    public void setScriptCommandType(ScriptCommandType scriptCommandType) {
        this.scriptCommandType = scriptCommandType;
    }

    public int getNumber(){
        return number;
    }

    public void setNumber(int number){
        this.number = number;
    }
}
