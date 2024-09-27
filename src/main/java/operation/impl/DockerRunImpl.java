package operation.impl;

import cli.process.impl.build.DockerBuildImpl;
import model.CommandRequest;
import operation.run.ICommandRunService;

import java.util.List;
import java.util.Scanner;

public class DockerRunImpl implements ICommandRunService {

    private static DockerBuildImpl dockerBuildImpl = new DockerBuildImpl();
    private static cli.process.impl.run.DockerRunImpl dockerRunImpl = new cli.process.impl.run.DockerRunImpl();
    @Override
    public void runCommand(CommandRequest commandRequest) {
       List<String> commandList = dockerBuildImpl.build(new Scanner(System.in), commandRequest.getPath());
       commandList.forEach(command -> {
           dockerRunImpl.run(command, commandRequest.getPath());
       });
    }
}
