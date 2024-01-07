FROM openjdk:17
LABEL authors="dianaburuga"
WORKDIR /app

ARG JAR_FILE=target/CareerStartup-0.0.1-SNAPSHOT.jar
COPY $JAR_FILE app.jar
EXPOSE 8081 5005

ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]
