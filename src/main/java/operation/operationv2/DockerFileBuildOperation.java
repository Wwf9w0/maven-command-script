package operation.operationv2;

import model.DockerFile;
import model.FileInfo;
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

    public String buildDockerfile(String javaVersion, Integer port) {
        if (javaVersion == null){
            javaVersion = DEFAULT_JDK;
        }
        if (port == null){
            port = DEFAULT_PORT;
        }
        dockerFile = new DockerFile(javaVersion, port);
        return buildDockerFile.buildDockerFile(dockerFile);
    }

    public void createDockerfile(String javaVersion, Integer port){
        if (javaVersion == null){
            javaVersion = DEFAULT_JDK;
        }
        if (port == null){
            port = DEFAULT_PORT;
        }

        dockerFile = new DockerFile(javaVersion, port);
        buildDockerFile.createDockerFile(dockerFile);
    }

    public void runDockerFile(){
        FileInfo fileInfo = buildDockerFile.getJarFileName();
        buildDockerFile.runDockerFile(fileInfo.getFileName());
    }


}
