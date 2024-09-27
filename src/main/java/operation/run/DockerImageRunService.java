package operation.run;

public interface DockerImageRunService {

    void pullImage();
    void startImage();
    void stopImage();
}
