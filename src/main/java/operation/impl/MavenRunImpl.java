package operation.impl;

import cli.process.impl.MavenProcessImpl;
import model.CommandRequest;
import operation.run.ICommandRunService;

import java.util.List;
import java.util.Scanner;

public class MavenRunImpl implements ICommandRunService {

    private static MavenProcessImpl mavenProcessImpl = new MavenProcessImpl();

    private static cli.process.impl.run.MavenRunImpl mavenRunImpl = new cli.process.impl.run.MavenRunImpl();

    @Override
    public void runCommand(CommandRequest commandRequest) {
        List<String> commandList = mavenProcessImpl.buildProcess(new Scanner(System.in), commandRequest.getPath());
        for (String s : commandList) {
            mavenRunImpl.run(s, commandRequest.getPath());
        }
    }
}
