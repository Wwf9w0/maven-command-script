package cli.process.impl.build;

import cli.process.IBuild;
import build.DockerBuild;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DockerBuildImpl implements IBuild {

    private static DockerBuild dockerBuild = new DockerBuild();

    public DockerBuildImpl(DockerBuild dockerBuild) {
        this.dockerBuild = dockerBuild;
    }

    public DockerBuildImpl(){

    }


    @Override
    public List<String> build(Scanner scanner, String path) {
        System.out.println("Which command want to you? ");
        System.out.println("1- pull image");
        System.out.println("2- docker info");
        System.out.println("3- docker images");
        int choice = scanner.nextInt();
        return Collections.singletonList(dockerBuild.buildCommand(choice, scanner));
    }
}
