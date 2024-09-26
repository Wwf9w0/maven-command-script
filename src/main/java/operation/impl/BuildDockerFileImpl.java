package operation.impl;

import model.DockerFile;
import operation.DockerOperation;

public class BuildDockerFileImpl implements DockerOperation {

    @Override
    public String buildDockerFile(DockerFile file) {
        StringBuilder dockerfile = new StringBuilder();
        dockerfile.append("FROM openjdk:").append(file.getJavaVersion()).append("\n\n");
        dockerfile.append("ENV ").append("SERVER_PORT ").append(file.getPort());
        dockerfile.append("ADD ").append(file.getJarLocation()).append(" ").append(file.getJarName()).append("\n\n");
       // dockerfile.append("RUN").append("apk add tzdata && ln -sf /usr/share/zoneInfo/Europe/Istanbul /etc/localtime");
        dockerfile.append("EXPOSE ").append(file.getPort()).append("\n\n");
        dockerfile.append("ENTRYPOINT [")
                .append("\"java\", ")
                .append("\"-jar\", ")
                .append("\"").append(file.getJarName()).append("\"]\n");
        //  dockerfile.append("CMD [\"").append("java ").append("-jar ").append(file.getJarName()).append("\"]\n");
        return dockerfile.toString();
    }

    @Override
    public void runDockerFile() {

    }

    @Override
    public void runImage() {

    }

    @Override
    public void linkImage() {

    }
}
