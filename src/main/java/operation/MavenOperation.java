package operation;

public interface MavenOperation {

    void cleanInstall();

    void verify();

    void pom();

    void resolve();
}
