#
# Build stage
#
FROM maven:3.8.5-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM eclipse-temurin:17-jre-alpine

VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080