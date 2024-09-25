package build;

import model.Images;

import java.util.Scanner;

public class DockerBuild {

    public DockerBuild() {

    }

    public String buildCommand(int number) {
        Images image = choiceType(number);
        return choiceImage(image);
    }

    private String choiceImage(Images image) {
        print();
        switch (image) {
            case MYSQL:
                return Images.MYSQL.getCommand();
            case MONGODB:
                return Images.MONGODB.getCommand();
            case POSTGRES:
                return Images.POSTGRES.getCommand();
            case RABBITMQ:
                return Images.RABBITMQ.getCommand();
            case KAFKA:
                return Images.KAFKA.getCommand();
        }
        return Images.DOCKER_INFO.getCommand();
    }

    private void print() {
        String print = "Please choice a image." +
                "\n" +
                "1- mysql" +
                "\n" +
                "2- mongodb" +
                "\n" +
                "3- postgres" +
                "\n" +
                "4- rabbitmq" +
                "\n" +
                "5- apache/kafka";
        System.out.println(print);
    }

    private Images choiceType(int number) {
        switch (number) {
            case 1:
                return Images.DOCKER_INFO;
            case 2:
                return Images.DOCKER_IMAGES;
            case 3:
                return Images.DOCKER_PULL;
            case 4:
                return Images.MYSQL;
            case 5:
                return Images.MONGODB;
            case 6:
                return Images.POSTGRES;
            case 7:
                return Images.RABBITMQ;
            case 8:
                return Images.KAFKA;
        }
        return Images.DOCKER_INFO;
    }
}
