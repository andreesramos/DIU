FROM openjdk:21
LABEL authors="andres"

COPY target/Tutorials-0.0.1-SNAPSHOT.jar /tutorialsapp.jar
CMD ["java", "-jar", "/tutorialsapp.jar"]
