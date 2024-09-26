package operation.rule;

public interface DockerImageRule {

    void pullImage();
    void startImage();
    void stopImage();
}
