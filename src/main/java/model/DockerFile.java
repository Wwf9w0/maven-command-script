package model;

public class DockerFile {

    private String javaVersion;
    private int port;

    public DockerFile(String javaVersion,int port){
        this.javaVersion = javaVersion;
        this.port = port;
    }

    public DockerFile(){

    }

    public String getJavaVersion(){
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion){
        this.javaVersion = javaVersion;
    }

    public int getPort(){
        return port;
    }

    public void setPort(int port){
        this.port = port;
    }
}
