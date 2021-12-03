FROM openjdk:8-jdk-alpine
MAINTAINER kelvin.li
COPY target/coding-challenge-0.0.1-SNAPSHOT.jar coding-challenge-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/coding-challenge-0.0.1-SNAPSHOT.jar"]
