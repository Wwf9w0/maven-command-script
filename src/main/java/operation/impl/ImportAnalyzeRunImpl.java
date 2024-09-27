package operation.impl;

import model.CommandRequest;
import operation.run.ICommandRunService;

public class ImportAnalyzeRunImpl implements ICommandRunService {

    private static final cli.process.impl.run.ImportAnalyzeRunImpl importAnalyzeRunImpl = new cli.process.impl.run.ImportAnalyzeRunImpl();

    @Override
    public void runCommand(CommandRequest commandRequest) {
        importAnalyzeRunImpl.run("", commandRequest.getPath());
    }
}
