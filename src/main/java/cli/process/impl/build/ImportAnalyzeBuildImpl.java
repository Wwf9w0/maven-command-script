package cli.process.impl.build;

import cli.process.IBuild;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ImportAnalyzeBuildImpl implements IBuild {
    @Override
    public List<String> build(Scanner scanner, String path) {
        return Collections.singletonList(path + "/src/main/java/");
    }
}
