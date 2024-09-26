package operation;

import model.DockerFile;

import java.util.List;

public interface DockerOperation {

    String buildDockerFile(DockerFile dockerFile);
    void runDockerFile();
    void runImage();
    void linkImage();
}
