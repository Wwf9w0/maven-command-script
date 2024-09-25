package cli.process.impl;

import cli.process.IProcess;
import cli.process.impl.build.MavenBuildImpl;


import java.util.List;
import java.util.Scanner;

public class MavenProcessImpl implements IProcess {


    private static  MavenBuildImpl build = new MavenBuildImpl();

    public MavenProcessImpl(MavenBuildImpl build){
        this.build = build;
    }

    public MavenProcessImpl(){

    }

    @Override
    public List<String> buildProcess(Scanner scanner, String path) {
        return build.build(scanner,path);
    }
}
