package cli.process.impl.run;

import cli.process.IRun;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImportAnalyzeRunImpl implements IRun {
    @Override
    public void run(String command, String path) {
        path = path + "/src/main/java/";
        List<String> unUsedImports = null;
        try {
            unUsedImports = findUnusedImports(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String result = unUsedImports.stream()
                .map(importLine -> "+ " + importLine)
                .collect(Collectors.joining("\n"));
        System.out.println("Unused imports : \n" + result);
    }

    private static List<String> findUnusedImports(String directoryPath) throws IOException {
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
