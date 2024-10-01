package client;

import model.CommandRequest;
import model.ScriptCommandType;
import operation.handler.CommandHandler;
import operation.operationv2.DockerFileBuildOperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        build();
        int choice = SCANNER.nextInt();
        CommandRequest request = new CommandRequest();
        request.setNumber(choice);
        buildCommandRequest(request);
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Which command want to use ?");
        stringBuilder.append("\n");
        stringBuilder.append("maven ?");
        stringBuilder.append("\n");
        stringBuilder.append("import analyz ?");
        stringBuilder.append("\n");
        stringBuilder.append("docker");
        stringBuilder.append("\n");
        stringBuilder.append("Make your choice (1-3)");
        System.out.println(stringBuilder);
    }

    private static void buildCommandRequest(CommandRequest request) {
        String path = getCurrentPath();
        request.setPath(path);
        request.setScriptCommandType(ScriptCommandType.fromValue(request.getNumber()));
    }

    private static String getCurrentPath() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", "pwd");
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("path : -> " + line);
            }
            return line;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
