package cli;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Maven {

    private String mavenCommand;
    private String projectPath;

    private boolean isSuccess;

    public Maven(String command, String projectPath) {
        this.projectPath = projectPath;
        this.mavenCommand = command;
        try {
            runCommand(command, projectPath);
            this.isSuccess = true;
        } catch (Exception e) {
            this.isSuccess = false;
            throw new RuntimeException("Mvn run command exception + " + e.getMessage());
        }

    }

    private static void runCommand(String mavenCommand, String projectPath) {
        try {
            ProcessBuilder builder = new ProcessBuilder("mvn", mavenCommand);
            builder.directory(new File(projectPath));
            builder.redirectErrorStream(true);

            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Maven command successful: mvn " + mavenCommand);
            } else {
                System.out.println("Maven command unsuccessful: mvn " + mavenCommand);
            }

        } catch (Exception e) {
            System.out.println("Maven command not running: " + e.getMessage());
        }
    }

    public boolean isSuccess() {
        return isSuccess;
    }

}
