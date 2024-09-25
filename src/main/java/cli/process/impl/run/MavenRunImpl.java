package cli.process.impl.run;

import cli.process.IRun;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class MavenRunImpl implements IRun {

    @Override
    public void run(String command, String path) {
        try {
            ProcessBuilder builder = new ProcessBuilder("mvn", command);
            builder.directory(new File(path));
            builder.redirectErrorStream(true);

            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Maven command successful: mvn " + command);
            } else {
                System.out.println("Maven command unsuccessful: mvn " + command);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Maven command not running: " + e.getMessage());
        }

    }
}
