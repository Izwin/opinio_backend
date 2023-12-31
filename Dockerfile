#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
COPY ./ ./
COPY target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]