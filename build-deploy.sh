#!/bin/bash

function compile() {
    echo "Compiling Spring Boot project with Maven..."
    mvn clean install || { echo "Compilation failed!"; exit 1; }
}

function find_jar() {
    echo "Locating the generated JAR file..."
    jar_file=$(find ./target -name "*.jar" | head -n 1)

    if [ -z "$jar_file" ]; then
        echo "JAR file not found!"
        exit 1
    else
        echo "JAR file found: $jar_file"

    jar_name=$(basename "$jar_file")
    echo "JAR file name: $jar_name"

    fi    
}

function create_docker_image() {
    local imageName=$1
    local desktop_path=~/Desktop/docker_images 

    if [ ! -d "$desktop_path" ]; then
        echo "docker_images folder not found on the Desktop. Creating it..."
        mkdir -p "$desktop_path" || { echo "Failed to create docker_images folder!"; exit 1; }
    fi

    echo "Building Docker image using Dockerfile: $imageName"
    docker build -t $imageName . || { echo "Failed to build Docker image!"; exit 1; }

    echo "Saving Docker image to: $desktop_path/$imageName.tar"
    docker save -o "$desktop_path/$imageName.tar" "$imageName" || { echo "Failed to save Docker image!"; exit 1; }
}

function run_docker_container() {
    local imageName=$1
    local containerName="spring-boot-container-001"

    echo "Running Docker image: $imageName"
    docker run -d --name $containerName -p 8080:8080 $imageName || { echo "Failed to run Docker container!"; exit 1; }
    echo "Docker container started. The application is accessible at http://localhost:8080."
}


function generate_dockerfile() {
    local jar_file=$1
    echo "Starting docker..."
    open /Applications/Docker.app || { echo "Failed to start Docker.app"; exit 1; }
    cat <<EOF > Dockerfile
# Using OpenJDK as the base image    
FROM openjdk:8

# Copying the application's jar file into the image
COPY $jar_file /$jar_name

# Command to run the application
ENTRYPOINT ["java", "-jar", "/$jar_name]
EOF
    echo "Dockerfile generated with JAR: $jar_name"
}


function main() {
    echo "Starting the process of compiling the project and creating the Docker image..."

    compile

    find_jar

    generate_dockerfile "$jar_file"

    local imageName="spring-boot-app-001"
    
    create_docker_image "$imageName"

    run_docker_container "$imageName"

    echo "Process completed successfully."
}

main