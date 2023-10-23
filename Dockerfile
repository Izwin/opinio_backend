#
# Build stage
#
FROM maven:3.8.5-openjdk-17 AS build

COPY ./ ./

RUN mvn clean package -DskipTests

#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
COPY ./ ./
COPY target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]