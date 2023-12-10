FROM tomcat:10.1.0-M17-jdk17-temurin
LABEL authors="dianaburuga"
MAINTAINER ulbs.com
WORKDIR /app

ARG JAR_FILE=target/CareerStartup-0.0.1-SNAPSHOT.jar
COPY $JAR_FILE app.jar
EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar"]