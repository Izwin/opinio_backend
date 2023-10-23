#
# Build stage
#
FROM maven:3.8.5-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

#
# Package stage
#
FROM eclipse-temurin:17-jre-alpine

EXPOSE 8080
ENTRYPOINT ["java","-jar","target/OpinioBackend-0.0.1-SNAPSHOT.jar"]