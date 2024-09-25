package cli.process.impl.build;

import cli.process.IBuild;
import model.MavenCommandType;
import build.MavenBuild;

import java.util.List;
import java.util.Scanner;

public class MavenBuildImpl implements IBuild {

    private static MavenBuild mavenBuild = new MavenBuild();

    public MavenBuildImpl(MavenBuild mavenBuild){
        this.mavenBuild = mavenBuild;
    }

    public MavenBuildImpl() {
    }

    @Override
    public List<String> build(Scanner scanner, String path) {
        MavenCommandType commandType = mavenBuild.getMvnCommand(scanner);
        return mavenBuild.makeCommandList(commandType);
    }
}
