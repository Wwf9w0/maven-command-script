package rule;

import cli.process.impl.run.ImportAnalyzeRunImpl;
import model.CommandRequest;

public class ImportAnalyzeRule implements ICommandRuleService{

    private static final ImportAnalyzeRunImpl importAnalyzeRunImpl = new ImportAnalyzeRunImpl();

    @Override
    public void runCommand(CommandRequest commandRequest) {
        importAnalyzeRunImpl.run("", commandRequest.getPath());
    }
}
