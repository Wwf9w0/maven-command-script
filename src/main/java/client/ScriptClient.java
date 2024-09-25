package client;

import command.Docker;
import command.ImportAnalyze;
import command.MavenCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScriptClient {
    private static Scanner SCANNER = new Scanner(System.in);

    private static MavenCommand mavenCommand;

    private static ImportAnalyze importAnalyze;

    private static Docker docker;

    public ScriptClient(MavenCommand mavenCommand) {
        this.mavenCommand = mavenCommand;
    }

    public static void run(String[] args) {

        System.out.print("Please enter the file path of the project: ");
        String projectPath = SCANNER.nextLine();

        System.out.println("Which command want to use ?");
        System.out.println("1 - maven");
        System.out.println("2 - import analyze");
        System.out.println("3 - docker");
        System.out.println("Make your choice (1-3)");
        int choice = SCANNER.nextInt();
        SCANNER.nextLine();

        if (choice == 1) {
            MavenCommandType commandType = getMvnCommand();
            List<String> commandList = makeCommandList(commandType);
            for (String s : commandList) {
                mavenCommand = new MavenCommand(s, projectPath);
                if (!mavenCommand.isSuccess()) {
                    throw new RuntimeException("Error executing one of the mvn commands.! -> " + s);
                }
            }
        } else if (choice == 2) {
            importAnalyze = new ImportAnalyze(projectPath);
            if (!importAnalyze.isSuccess()) {
                throw new RuntimeException("an unknown error occurred while import analyze");
            }
        }else if (choice == 3){
            String input = SCANNER.nextLine();
            runDocker(input);
        }
    }

    private static void runDocker(String command){
        Docker docker_connection = new Docker(command);
        docker_connection.dockerProcessStart(Docker.Images.RABBITMQ);
    }

    private static List<String> makeCommandList(MavenCommandType commandType) {
        List<String> commandList = new ArrayList<>();
        if (commandType.equals(MavenCommandType.CLEAN_INSTALL)) {
            commandList.add(MavenCommandType.CLEAN_INSTALL.getCommand());
        } else if (commandType.equals(MavenCommandType.RESOLVE)) {
            commandList.add("dependency:resolve");
            commandList.add("integration-test");
            commandList.add("verify");
            commandList.add("clean");
            commandList.add("install");
        } else if (commandType.equals(MavenCommandType.ANALYZE)) {
            commandList.add("dependency:analyze");
        }
        return commandList;
    }

    private static MavenCommandType getMvnCommand() {
        System.out.println("Which maven command you want to? ");
        System.out.println("1. clean-install");
        System.out.println("2. resolve");
        System.out.println("3. analyze");
        System.out.print("Make your choice (1-3): ");
        int choice = SCANNER.nextInt();
        switch (choice) {
            case 1:
                return MavenCommandType.CLEAN_INSTALL;
            case 2:
                return MavenCommandType.RESOLVE;
            case 3:
                return MavenCommandType.ANALYZE;
        }
        SCANNER.close();
        return MavenCommandType.CLEAN_INSTALL;
    }

    enum MavenCommandType {
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

}
