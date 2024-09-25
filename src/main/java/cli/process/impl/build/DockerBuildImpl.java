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

    public DockerBuildImpl() {

    }

    @Override
    public List<String> build(Scanner scanner, String path) {
        print();
        int choice = scanner.nextInt();
        return Collections.singletonList(dockerBuild.buildCommand(choice));
    }

    private void print() {
        String print = "Which command want to you? " +
                "\n" +
                "1- pull image" +
                "\n" +
                "2- docker info" +
                "\n" +
                "3- docker images";
        System.out.println(print);
    }
}
