package operation.impl;

import model.DockerFile;
import operation.DockerOperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BuildDockerFileImpl implements DockerOperation {

    @Override
    public String buildDockerFile(DockerFile file) {
        StringBuilder dockerfile = new StringBuilder();
        dockerfile.append("FROM openjdk:").append(file.getJavaVersion()).append("\n");
        dockerfile.append("ENV ").append("SERVER_PORT ").append(file.getPort()).append("\n");
        dockerfile.append("ADD ").append(file.getJarLocation()).append(" ").append(file.getJarName()).append("\n");
       // dockerfile.append("RUN").append("apk add tzdata && ln -sf /usr/share/zoneInfo/Europe/Istanbul /etc/localtime");
        dockerfile.append("EXPOSE ").append(file.getPort()).append("\n");
        dockerfile.append("ENTRYPOINT [")
                .append("\"java\", ")
                .append("\"-jar\", ")
                .append("\"").append(file.getJarName()).append("\"]");
        //  dockerfile.append("CMD [\"").append("java ").append("-jar ").append(file.getJarName()).append("\"]\n");
        return dockerfile.toString();
    }

    @Override
    public void createDockerFile(DockerFile dockerFile) {
        String dockerfile = buildDockerFile(dockerFile);
        String path = System.getProperty("user.dir");
        try {
            Files.write(Paths.get(path + "/Dockerfile"),
                    dockerfile.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("successfully create Dockerfile.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void runDockerFile(String imageName) {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("docker ").append("build ").append("-t ").append(imageName).append(" ").append(".");
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", builder.toString());
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }

            int exitTime = process.waitFor();
            if (exitTime == 0){
                System.out.println("build success.!");
            }else {
                throw new RuntimeException("build unsuccessful.!");
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("docker run ").append(imageName);

            processBuilder.command("bash", "-c", stringBuilder.toString());
            processBuilder.command("bash", "-c", "docker ps");
            Process newProcess = processBuilder.start();
            BufferedReader dockerReader = new BufferedReader(new InputStreamReader(newProcess.getInputStream()));
            String newLine;
            while ((newLine = dockerReader.readLine()) != null){
                System.out.println(newLine);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void runImage() {

    }

    @Override
    public void linkImage() {

    }
}
