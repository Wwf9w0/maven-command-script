package model;

public enum MavenCommandType {

    CLEAN_INSTALL("clean install"),
    RESOLVE("resolve"),
    ANALYZE("analyze");
    private final String command;

    MavenCommandType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
