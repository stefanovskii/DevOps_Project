FROM openjdk:latest
COPY target/lab-0.0.1-SNAPSHOT.jar lab-0.0.1-SNAPSHOT.jar
EXPOSE 80
CMD ["java", "-jar", "/lab-0.0.1-SNAPSHOT.jar"]
