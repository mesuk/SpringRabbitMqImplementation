FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} notificationService.jar

ENTRYPOINT ["java","-jar","/notificationService.jar"]