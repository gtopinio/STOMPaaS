FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/STOMPaaS.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]