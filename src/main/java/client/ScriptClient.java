package client;

import cli.Maven;
import cli.process.impl.DockerProcessImpl;
import cli.process.impl.ImportAnalyzeProcessImpl;
import cli.process.impl.MavenProcessImpl;

import java.util.Scanner;

public class ScriptClient {
    private static Scanner SCANNER = new Scanner(System.in);

    private static Maven maven;

    private static MavenProcessImpl mavenProcess;

    private static ImportAnalyzeProcessImpl importAnalyzeProcess;

    private static DockerProcessImpl dockerProcess;

    public ScriptClient(Maven maven, DockerProcessImpl dockerProcess, MavenProcessImpl mavenProcess, ImportAnalyzeProcessImpl importAnalyzeProcess) {
        this.maven = maven;
        this.dockerProcess = dockerProcess;
        this.mavenProcess = mavenProcess;
        this.importAnalyzeProcess = importAnalyzeProcess;
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
            mavenProcess.buildProcess(SCANNER, projectPath);
        } else if (choice == 2) {
            importAnalyzeProcess.buildProcess(SCANNER, projectPath);
        } else if (choice == 3) {
            dockerProcess.buildProcess(SCANNER, projectPath);
        }
    }
}
