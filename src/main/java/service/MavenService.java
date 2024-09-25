package service;

import model.MavenCommandType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MavenService {

    public  List<String> makeCommandList(MavenCommandType commandType) {
        List<String> commandList = new ArrayList<>();
        if (commandType.equals(MavenCommandType.CLEAN_INSTALL)) {
            commandList.add(MavenCommandType.CLEAN_INSTALL.getCommand());
        } else if (commandType.equals(MavenCommandType.RESOLVE)) {
            commandList.add("dependency:resolve");
            commandList.add("integration-test");
            commandList.add("verify");
            commandList.add("clean");
            commandList.add("install");
        } else if (commandType.equals(MavenCommandType.ANALYZE)) {
            commandList.add("dependency:analyze");
        }
        return commandList;
    }

    public  MavenCommandType getMvnCommand(Scanner scanner) {
        System.out.println("Which maven command you want to? ");
        System.out.println("1. clean-install");
        System.out.println("2. resolve");
        System.out.println("3. analyze");
        System.out.print("Make your choice (1-3): ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                return MavenCommandType.CLEAN_INSTALL;
            case 2:
                return MavenCommandType.RESOLVE;
            case 3:
                return MavenCommandType.ANALYZE;
        }
        scanner.close();
        return MavenCommandType.CLEAN_INSTALL;
    }
}
