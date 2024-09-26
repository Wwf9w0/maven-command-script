FROM openjdk:openjdk:8
ENV SERVER_PORT 8099
ADD target/s.jar s.jar
EXPOSE 8099
ENTRYPOINT ["java", "-jar", "s.jar"]