package cli;

import cli.process.DockerProcess;

public class Docker {

    private String command;

    public Docker(String command){
        this.command = command;
    }

    public void dockerProcessStart(){
        DockerProcess dockerProcess = new DockerProcess();
        dockerProcess.buildProcessAndRun(command);
    }
}
