package command;

import command.process.DockerProcess;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Docker {

    private String command;
    private final List<Images> IMAGES_LIST = Arrays.stream(Images.values()).collect(Collectors.toList());

    public Docker(String command){
        this.command = command;
    }

    public void dockerProcessStart(Images image){
        String name = image.getName();
        DockerProcess dockerProcess = new DockerProcess(name, Boolean.TRUE);
        dockerProcess.buildProcessAndRun(command);
    }

    public enum Images{

        MYSQL("mysql"),
        MONGODB("mongodb"),
        POSTGRES("postgres"),
        RABBITMQ("rabbitmq"),
        KAFKA("apache/kafka");

        private final String name;

        Images(String name) {
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }
}
