package model;

public enum Images {

    DOCKER_INFO("docker info", "docker info", 1),
    DOCKER_IMAGES("docker images", "docker images", 2),
    DOCKER_PULL("docker pull", "", 3),
    MYSQL("mysql", "docker pull mysql", 4),
    MONGODB("mongodb", "docker pull mongodb", 5),
    POSTGRES("postgres","docker pull postgres", 6),
    RABBITMQ("rabbitmq", "docker pull rabbitmq", 7),
    KAFKA("apache/kafka", "docker pull apache/kafka", 8);

    private final String name;
    private final String command;

    private final int number;

    Images(String name, String command, int number) {
        this.name = name;
        this.command = command;
        this.number = number;
    }

    public String getName(){
        return name;
    }

    public String getCommand(){
        return command;
    }
}
