FROM openjdk:17-jdk-alpine
LABEL authors="diego"
RUN apk --no-cache add curl maven
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:resolve
COPY src src
RUN mvn package -DskipTests
ENTRYPOINT ["java", "-jar", "target/iot-device-service-0.0.1-SNAPSHOT.jar"]