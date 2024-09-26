package operation.operationv2;

import model.DockerFile;
import operation.impl.BuildDockerFileImpl;

public class DockerFileBuildOperation {

    private static BuildDockerFileImpl buildDockerFile = new BuildDockerFileImpl();
    private static DockerFile dockerFile = new DockerFile();

    private static final String DEFAULT_JDK = "openjdk:8";
    private static final Integer DEFAULT_PORT = 8080;

    public DockerFileBuildOperation(BuildDockerFileImpl buildDockerFile, DockerFile dockerFile) {
        this.buildDockerFile = buildDockerFile;
        this.dockerFile = dockerFile;
    }

    public DockerFileBuildOperation(){

    }

    public String buildDockerfile(String javaVersion, String jarLocation, Integer port, String jarName) {
        if (javaVersion == null){
            javaVersion = DEFAULT_JDK;
        }
        if (port == null){
            port = DEFAULT_PORT;
        }
        dockerFile = new DockerFile(javaVersion, jarLocation, port, jarName);
        return buildDockerFile.buildDockerFile(dockerFile);
    }

    public void createDockerfile(String javaVersion, String jarLocation, Integer port, String jarName){
        if (javaVersion == null){
            javaVersion = DEFAULT_JDK;
        }
        if (port == null){
            port = DEFAULT_PORT;
        }

        dockerFile = new DockerFile(javaVersion, jarLocation, port, jarName);
        buildDockerFile.createDockerFile(dockerFile);
    }
}
