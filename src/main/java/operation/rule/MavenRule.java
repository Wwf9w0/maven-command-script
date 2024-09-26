package operation.rule;

import cli.process.impl.MavenProcessImpl;
import cli.process.impl.run.MavenRunImpl;
import model.CommandRequest;
import operation.rule.ICommandRuleService;

import java.util.List;
import java.util.Scanner;

public class MavenRule implements ICommandRuleService {

    private static MavenProcessImpl mavenProcessImpl = new MavenProcessImpl();

    private static MavenRunImpl mavenRunImpl = new MavenRunImpl();

    @Override
    public void runCommand(CommandRequest commandRequest) {
        List<String> commandList = mavenProcessImpl.buildProcess(new Scanner(System.in), commandRequest.getPath());
        for (String s : commandList) {
            mavenRunImpl.run(s, commandRequest.getPath());
        }
    }
}
