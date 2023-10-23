#
# Build stage
#
FROM maven:3.8.5-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM eclipse-temurin:17-jre-alpine
COPY --from=build /home/app/target/OpinioBackend-0.0.1.jar /usr/local/lib/OpinioBackend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/OpinioBackend.jar"]