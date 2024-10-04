FROM openjdk:openjdk:8
ENV SERVER_PORT 8090
ADD your_location/target mavenrunner-1.0-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "mavenrunner-1.0-SNAPSHOT.jar"]