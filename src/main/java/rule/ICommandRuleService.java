package rule;

import model.CommandRequest;

public interface ICommandRuleService {

    void runCommand(CommandRequest commandRequest);
}