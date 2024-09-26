package operation.rule;

import cli.process.impl.build.DockerBuildImpl;
import cli.process.impl.run.DockerRunImpl;
import model.CommandRequest;

import java.util.List;
import java.util.Scanner;

public class DockerRule implements ICommandRuleService {

    private static DockerBuildImpl dockerBuildImpl = new DockerBuildImpl();
    private static DockerRunImpl dockerRunImpl = new DockerRunImpl();
    @Override
    public void runCommand(CommandRequest commandRequest) {
       List<String> commandList = dockerBuildImpl.build(new Scanner(System.in), commandRequest.getPath());
       commandList.forEach(command -> {
           dockerRunImpl.run(command, commandRequest.getPath());
       });
    }
}
