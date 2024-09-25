package cli.process;

import java.util.List;
import java.util.Scanner;

public interface IBuild {

    public List<String> build(Scanner scanner, String path);
}
