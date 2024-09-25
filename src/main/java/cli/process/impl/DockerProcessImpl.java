package cli.process.impl;

import cli.process.IProcess;
import service.DockerBuild;

import java.util.Scanner;

public class DockerProcessImpl implements IProcess {

    private static DockerBuild dockerBuild = new DockerBuild();

    public DockerProcessImpl(DockerBuild dockerBuild){
        this.dockerBuild = dockerBuild;
    }
    @Override
    public void buildProcess(Scanner scanner, String path) {
        System.out.println("Which command want to you? ");
        System.out.println("1- pull image");
        System.out.println("2- docker info");
        System.out.println("3- docker images");
        int choice = scanner.nextInt();
        String command = dockerBuild.buildCommand(choice, scanner);
        dockerBuild.runDocker(command);
    }

    @Override
    public void startProcess() {

    }
}
