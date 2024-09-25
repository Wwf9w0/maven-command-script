package cli.process.impl;

import cli.Maven;
import cli.process.IProcess;
import model.MavenCommandType;
import service.MavenBuild;

import java.util.List;
import java.util.Scanner;

public class MavenProcessImpl implements IProcess {

    private static Maven maven;
    private static MavenBuild mavenBuild = new MavenBuild();

    public MavenProcessImpl(MavenBuild mavenBuild) {
        this.mavenBuild = mavenBuild;
    }

    @Override
    public void buildProcess(Scanner scanner, String path) {
        MavenCommandType commandType = mavenBuild.getMvnCommand(scanner);
        List<String> commandList = mavenBuild.makeCommandList(commandType);
        for (String s : commandList) {
            maven = new Maven(s, path);
            if (!maven.isSuccess()) {
                throw new RuntimeException("Error executing one of the mvn commands.! -> " + s);
            }
        }
    }

    @Override
    public void startProcess() {

    }
}
