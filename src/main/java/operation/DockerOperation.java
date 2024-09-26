package operation;

import model.DockerFile;

import java.util.List;

public interface DockerOperation {

    String buildDockerFile(DockerFile dockerFile);

    void createDockerFile(DockerFile dockerFile);
    void runDockerFile(String imageName);
    void runImage();
    void linkImage();
}
