package rule.handler;

import model.CommandType;
import rule.DockerRule;
import rule.ICommandRuleService;
import rule.MavenRule;

public class CommandHandler {

    private static MavenRule mavenRule = new MavenRule();
    private static DockerRule dockerRule;

    public ICommandRuleService handle(CommandType commandType){
        if (commandType.equals(CommandType.MAVEN)){
            return mavenRule;
        } else if (commandType.equals(CommandType.DOCKER)) {
            return dockerRule;
        } else if (commandType.equals(CommandType.IMPORT_ANALYZE)) {
            // TODO
        } else {
            throw new RuntimeException();
        }
        return null;
    }
}
