package client;


import model.CommandRequest;
import model.CommandType;
import rule.handler.CommandHandler;

import java.util.Scanner;

public class ScriptClient {
    private static Scanner SCANNER = new Scanner(System.in);


    private static CommandHandler commandHandler = new CommandHandler();

    public ScriptClient() {
    }


    public static void run(String[] args) {
        System.out.print("Please enter the file path of the project: ");
        String projectPath = SCANNER.nextLine();
        build();
        int choice = SCANNER.nextInt();
        CommandRequest request = new CommandRequest();
        request.setNumber(choice);
        buildCommandRequest(request, projectPath);
        commandHandler.handle(request.getCommandType()).runCommand(request);
    }

    private static void build(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Which command want to use ?");
        stringBuilder.append("\n");
        stringBuilder.append("1 - maven");
        stringBuilder.append("\n");
        stringBuilder.append("2 - import analyze");
        stringBuilder.append("\n");
        stringBuilder.append("3- docker");
        stringBuilder.append("\n");
        stringBuilder.append("Make your choice (1-3)");
        System.out.println(stringBuilder);
    }

    private static void buildCommandRequest(CommandRequest request,String path){
        request.setPath(path);
        request.setCommandType(CommandType.fromValue(request.getNumber()));
    }
}
