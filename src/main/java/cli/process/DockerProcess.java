package cli.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DockerProcess {

    private String name;
    private boolean isLatest;
    private boolean isSuccess;

    private String type;

    private String version;

    private final String LATEST = ":latest";

    private final String DOCKER_OPEN_COMMAND = "open /Applications/Docker.app";

    private final String DOCKER_PULL = "docker pull";


    public DockerProcess(boolean isLatest) {
        this.isLatest = isLatest;
        this.type = DOCKER_PULL;
        this.version = LATEST;
    }

    public void buildProcessAndRun(String command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            boolean healthCheck = dockerHealthCheck(processBuilder);
            if (!healthCheck) {
                newConnection(processBuilder);
            }
            processBuilder.command("bash", "-c", command);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
           // BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("success.!");
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean dockerHealthCheck(ProcessBuilder pb) {
        try {
            pb.command("bash", "-c", "docker info");
            Process process = pb.start();
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            if (errorReader.readLine() != null) {
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private void newConnection(ProcessBuilder pb) {
        try {
            pb.command("bash", "-c", DOCKER_OPEN_COMMAND);
            pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean hasAnError(StringBuilder sb) {
        return sb.toString().contains("ERROR") ? Boolean.FALSE : Boolean.TRUE;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

}
