package model;

public enum Images {

    DOCKER_INFO("docker info", "docker info"),
    DOCKER_IMAGES("docker images", "docker images"),
    DOCKER_PULL("docker pull", ""),
    MYSQL("mysql", "docker pull mysql"),
    MONGODB("mongodb", "docker pull mongodb"),
    POSTGRES("postgres","docker pull postgres"),
    RABBITMQ("rabbitmq", "docker pull rabbitmq"),
    KAFKA("apache/kafka", "docker pull apache/kafka");

    private final String name;
    private final String command;

    Images(String name, String command) {
        this.name = name;
        this.command = command;
    }

    public String getName(){
        return name;
    }

    public String getCommand(){
        return command;
    }
}
