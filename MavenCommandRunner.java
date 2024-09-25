import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MavenCommandRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the file path of the project: ");
        String projectPath = scanner.nextLine();

        System.out.println("Which maven command you want to?");
        System.out.println("1. clean-install");
        System.out.println("2. resolve");
        System.out.println("3. analyze");
        System.out.print("Make your choice (1-3): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                runMavenCommand("clean", projectPath);
                runMavenCommand("install", projectPath);
                break;
            case 2:
                runMavenCommand("dependency:resolve", projectPath);
                runMavenCommand("test", projectPath);
                runMavenCommand("integration-test", projectPath);
                runMavenCommand("verify", projectPath);
                runMavenCommand("clean", projectPath);
                runMavenCommand("install", projectPath);
                break;
            case 3:
                runMavenCommand("dependency:analyze", projectPath);
                break;
            default:
                System.out.println("Unknown! Process ending.");
                break;
        }

        scanner.close();
    }

    private static void runMavenCommand(String mavenCommand, String projectPath) {
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
}
