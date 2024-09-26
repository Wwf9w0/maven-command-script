FROM openjdk:openjdk:8
ENV SERVER_PORT 8090
ADD /Users/frixie/Documents/Projects/command_script_L1/maven-command-script/target mavenrunner-1.0-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "mavenrunner-1.0-SNAPSHOT.jar"]