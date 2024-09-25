import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.stream.Collectors;

public class MavenCommandRunner {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the file path of the project: ");
        String projectPath = scanner.nextLine();
        System.out.println("Which maven command you want to?");
        System.out.println("1. clean-install");
        System.out.println("2. resolve");
        System.out.println("3. analyze");
        System.out.println("4. import analyze");
        System.out.print("Make your choice (1-4): ");
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
            case 4 :
                importAnalyze(projectPath);
            default:
                System.out.println("Unknown! Process ending.");
                break;
        }
        scanner.close();
    }

    private static void importAnalyze(String path) throws IOException {
        path = path + "/src/main/java/";
        List<String> unUsedImports = findUnusedImports(path);
        String result = unUsedImports.stream()
                .map(importLine -> "+ " + importLine)
                .collect(Collectors.joining("\n"));
        System.out.println("Unused imports : \n" + result);
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

    public static List<String> findUnusedImports(String directoryPath) throws IOException {
        List<String> unusedImports = new ArrayList<>();
        DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath));

        for (Path entry : stream) {
            if (Files.isRegularFile(entry) && (entry.toString().endsWith(".java") || !entry.toString().contains("."))) {
                List<String> lines = Files.readAllLines(entry);
                List<String> imports = new ArrayList<>();

                for (String line : lines) {
                    if (line.startsWith("import ")) {
                        imports.add(line.trim());
                    }
                }

                for (String imp : imports) {
                    String className = extractClassName(imp);
                    List<String> lILines = lines.stream().filter(line -> !line.startsWith("import"))
                            .collect(Collectors.toList());
                    boolean isUsed = isClassUsedInMethods(lILines, className);
                    if (!isUsed) {
                        unusedImports.add(imp + " (File: " + entry.getFileName() + ")");
                    }
                }
            }
        }
        return unusedImports;
    }
    private static String extractClassName(String importLine) {
        String className = importLine.substring(importLine.lastIndexOf('.') + 1);
        if (className.endsWith(";")) {
            className = className.substring(0, className.length() - 1);
        }
        return className;
    }

    private static boolean isClassUsedInMethods(List<String> lines, String className) {
        for (String line : lines) {
            line = line.trim();
            if (line.contains(className)) {
                return true;
            }
        }
        return false;
    }
}
