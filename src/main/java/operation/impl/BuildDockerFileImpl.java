package operation.impl;

import cli.process.DockerProcess;
import model.DockerFile;
import model.FileInfo;
import operation.DockerOperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.stream.Stream;

public class BuildDockerFileImpl implements DockerOperation {

    private static DockerProcess dockerProcess = new DockerProcess();

    public BuildDockerFileImpl(DockerProcess dockerProcess){
        this.dockerProcess = dockerProcess;
    }

    public BuildDockerFileImpl(){

    }

    @Override
    public String buildDockerFile(DockerFile file) {
        runMvnClean();
        FileInfo fileInfo = getJarFileName();
        StringBuilder dockerfile = new StringBuilder();
        dockerfile.append("FROM openjdk:").append(file.getJavaVersion()).append("\n");
        dockerfile.append("ENV ").append("SERVER_PORT ").append(file.getPort()).append("\n");
        dockerfile.append("ADD ").append(fileInfo.getRoot()).append("/target").append(" ").append(fileInfo.getFileName()).append("\n");
       // dockerfile.append("RUN").append("apk add tzdata && ln -sf /usr/share/zoneInfo/Europe/Istanbul /etc/localtime");
        dockerfile.append("EXPOSE ").append(file.getPort()).append("\n");
        dockerfile.append("ENTRYPOINT [")
                .append("\"java\", ")
                .append("\"-jar\", ")
                .append("\"").append(fileInfo.getFileName()).append("\"]");
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

    public FileInfo getJarFileName(){
        String root = System.getProperty("user.dir");
        Path currentPath = Paths.get(root + "/target");
        try (Stream<Path> files = Files.list(currentPath)){
            Optional<Path> jarFile = files
                    .filter(p -> p.toString().endsWith(".jar"))
                    .findFirst();
            String fileName = jarFile.map(Path::getFileName)
                    .map(Path::toString)
                    .orElseThrow(() -> new IOException("No JAR file found in target directory"));
            return new FileInfo(fileName, root);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    private void runMvnClean(){
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", "mvn clean install");
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
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
            doProcess(processBuilder);
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
               wait(exitTime);
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

    private void doProcess(ProcessBuilder processBuilder){
       boolean healthCheck =  dockerProcess.dockerHealthCheck(processBuilder);
       if (!healthCheck){
           dockerProcess.newConnection(processBuilder);
       }
    }

    @Override
    public void runImage() {

    }

    @Override
    public void linkImage() {

    }
}
