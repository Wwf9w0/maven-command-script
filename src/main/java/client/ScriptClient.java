package client;

import model.CommandRequest;
import model.ScriptCommandType;
import operation.handler.CommandHandler;
import operation.operationv2.DockerFileBuildOperation;

import java.util.Scanner;

public class ScriptClient {
    private static Scanner SCANNER = new Scanner(System.in);

    private static CommandHandler commandHandler = new CommandHandler();

    private static DockerFileBuildOperation dockerFileBuildOperation = new DockerFileBuildOperation();

    public ScriptClient(CommandHandler commandHandler, DockerFileBuildOperation dockerFileBuildOperation) {
        this.commandHandler = commandHandler;
        this.dockerFileBuildOperation = dockerFileBuildOperation;
    }

    public ScriptClient() {
    }

    public static void run(String[] args) {
        mvnAndAnalyzeRun();
        dockerBuildAndCreateFile();
        dockerFileBuildOperation.runDockerFile();
    }

    private static void mvnAndAnalyzeRun() {
        System.out.print("Please enter the file path of the project: ");
        String projectPath = SCANNER.nextLine();
        build();
        int choice = SCANNER.nextInt();
        CommandRequest request = new CommandRequest();
        request.setNumber(choice);
        buildCommandRequest(request, projectPath);
        commandHandler.handle(request.getScriptCommandType()).runCommand(request);
    }

    private static void dockerBuildAndCreateFile() {
        System.out.println("Enter java_version and port..");
        System.out.println("javaVersion -> ");
        String javaVertsion = SCANNER.nextLine();
        System.out.println("port -> ");
        int port = SCANNER.nextInt();
        String dockerfile = dockerFileBuildOperation.buildDockerfile(javaVertsion, port);
        System.out.println(dockerfile);
        dockerFileBuildOperation.createDockerfile(javaVertsion, port);
    }

    private static void build() {
        //TODO refactor
        String stringBuilder = "Which command want to use ?" +
                "\n" +
                "1 - maven" +
                "\n" +
                "2 - import analyze" +
                "\n" +
                "3- docker" +
                "\n" +
                "Make your choice (1-3)";
        System.out.println(stringBuilder);
    }

    private static void buildCommandRequest(CommandRequest request, String path) {
        request.setPath(path);
        request.setScriptCommandType(ScriptCommandType.fromValue(request.getNumber()));
    }
}
