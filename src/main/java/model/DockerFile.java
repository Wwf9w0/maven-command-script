package model;

public class DockerFile {

    private String javaVersion;
    private String jarLocation;
    private int port;
    private String jarName;

    public DockerFile(String javaVersion, String jarLocation, int port, String jarName){
        this.javaVersion = javaVersion;
        this.jarLocation = jarLocation;
        this.port = port;
        this.jarName = jarName;
    }

    public DockerFile(){

    }

    public String getJavaVersion(){
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion){
        this.javaVersion = javaVersion;
    }

    public String getJarLocation(){
        return jarLocation;
    }


    public void setJarLocation(String jarLocation){
        this.jarLocation = jarLocation;
    }

    public int getPort(){
        return port;
    }

    public void setPort(int port){
        this.port = port;
    }

    public String getJarName(){
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }
}
