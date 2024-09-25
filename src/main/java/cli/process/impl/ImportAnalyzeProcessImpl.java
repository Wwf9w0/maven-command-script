package cli.process.impl;

import cli.process.IProcess;
import cli.process.ImportAnalyzeProcess;

import java.util.Scanner;

public class ImportAnalyzeProcessImpl implements IProcess {

    private static ImportAnalyzeProcess importAnalyzeProcess;

    public ImportAnalyzeProcessImpl(ImportAnalyzeProcess importAnalyzeProcess) {
        this.importAnalyzeProcess = importAnalyzeProcess;
    }

    @Override
    public void buildProcess(Scanner scanner, String path) {
        importAnalyzeProcess = new ImportAnalyzeProcess(path);
        if (!importAnalyzeProcess.isSuccess()) {
            throw new RuntimeException("an unknown error occurred while import analyze");
        }
    }

    @Override
    public void startProcess() {

    }
}
