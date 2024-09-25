package service;

import cli.Docker;
import model.Images;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class DockerBuild {

    public  void runDocker(String command) {
        Docker docker_connection = new Docker(command);
        docker_connection.dockerProcessStart();
    }
    public String buildCommand(int number, Scanner scanner) {
        Images image = choiceType(number);
        return choiceImage(image, scanner);
    }

    private String getImageList(Images image){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", Images.DOCKER_IMAGES.getCommand());
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line);
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

    private String choiceImage(Images image, Scanner scanner) {
        System.out.println("Please choice a image");
        System.out.println("1- mysql");
        System.out.println("2- mongodb");
        System.out.println("3- postgres");
        System.out.println("4- rabbitmq");
        System.out.println("5- apache/kafka");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                return Images.MYSQL.getCommand();
            case 2:
                return Images.MONGODB.getCommand();
            case 3:
                return Images.POSTGRES.getCommand();
            case 4:
                return Images.RABBITMQ.getCommand();
            case 5:
                return Images.KAFKA.getCommand();
        }
        return Images.DOCKER_INFO.getCommand();
    }

    private Images choiceType(int number) {
        switch (number) {
            case 1:
                return Images.DOCKER_PULL;
            case 2:
                return Images.DOCKER_INFO;
            case 3:
                return Images.DOCKER_IMAGES;
        }
        return Images.DOCKER_INFO;
    }
}
