#
# Build stage
#
FROM maven:3.8.5-openjdk-17 AS build

RUN mvn clean package -DskipTests

#
# Package stage
#
FROM eclipse-temurin:17-jre-alpine

COPY src srr
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080