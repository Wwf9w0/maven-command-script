package operation.handler;

import model.ScriptCommandType;
import operation.impl.DockerRunImpl;
import operation.run.ICommandRunService;
import operation.impl.ImportAnalyzeRunImpl;
import operation.impl.MavenRunImpl;

public class CommandHandler {

    private static final MavenRunImpl MAVEN_RUN = new MavenRunImpl();
    private static final DockerRunImpl DOCKER_RUN = new DockerRunImpl();

    private static final ImportAnalyzeRunImpl IMPORT_ANALYZE_RUN = new ImportAnalyzeRunImpl();

    public ICommandRunService handle(ScriptCommandType scriptCommandType){
        if (scriptCommandType.equals(ScriptCommandType.MAVEN)){
            return MAVEN_RUN;
        } else if (scriptCommandType.equals(ScriptCommandType.DOCKER)) {
            return DOCKER_RUN;
        } else if (scriptCommandType.equals(ScriptCommandType.IMPORT_ANALYZE)) {
            return IMPORT_ANALYZE_RUN;
        } else {
            throw new RuntimeException();
        }
    }
}
