package rule.handler;

import model.CommandType;
import rule.DockerRule;
import rule.ICommandRuleService;
import rule.ImportAnalyzeRule;
import rule.MavenRule;

public class CommandHandler {

    private static final MavenRule mavenRule = new MavenRule();
    private static final DockerRule dockerRule = new DockerRule();

    private static final ImportAnalyzeRule importAnalyzeRule = new ImportAnalyzeRule();

    public ICommandRuleService handle(CommandType commandType){
        if (commandType.equals(CommandType.MAVEN)){
            return mavenRule;
        } else if (commandType.equals(CommandType.DOCKER)) {
            return dockerRule;
        } else if (commandType.equals(CommandType.IMPORT_ANALYZE)) {
            return importAnalyzeRule;
        } else {
            throw new RuntimeException();
        }
    }
}
