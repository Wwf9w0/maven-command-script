package client;

import cli.Maven;
import cli.process.ImportAnalyzeProcess;
import model.MavenCommandType;
import service.DockerBuild;
import service.MavenBuild;

import java.util.List;
import java.util.Scanner;

public class ScriptClient {
    private static Scanner SCANNER = new Scanner(System.in);

    private static Maven maven;

    private static ImportAnalyzeProcess analyzeProcess;
    private static DockerBuild dockerBuild = new DockerBuild();
    private static MavenBuild mavenBuild = new MavenBuild();

    public ScriptClient(Maven mavenCommand, DockerBuild dockerBuild, MavenBuild mavenBuild) {
        this.maven = mavenCommand;
        this.dockerBuild = dockerBuild;
        this.mavenBuild = mavenBuild;
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
            MavenCommandType commandType = mavenBuild.getMvnCommand(SCANNER);
            List<String> commandList = mavenBuild.makeCommandList(commandType);
            for (String s : commandList) {
                maven = new Maven(s, projectPath);
                if (!maven.isSuccess()) {
                    throw new RuntimeException("Error executing one of the mvn commands.! -> " + s);
                }
            }
        } else if (choice == 2) {
            analyzeProcess = new ImportAnalyzeProcess(projectPath);
            if (!analyzeProcess.isSuccess()) {
                throw new RuntimeException("an unknown error occurred while import analyze");
            }
        } else if (choice == 3) {
            System.out.println("Which command want to you? ");
            System.out.println("1- pull image");
            System.out.println("2- docker info");
            System.out.println("3- docker images");

            int choiceDockerProcess = SCANNER.nextInt();
            String command = dockerBuild.buildCommand(choiceDockerProcess, SCANNER);
            dockerBuild.runDocker(command);
        }
    }
}
