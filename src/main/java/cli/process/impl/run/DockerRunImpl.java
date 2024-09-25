package cli.process.impl.run;

import cli.Docker;
import cli.process.IRun;

public class DockerRunImpl implements IRun {
    @Override
    public void run(String command, String path) {
        Docker docker_connection = new Docker(command);
        docker_connection.dockerProcessStart();
    }
}
