FROM openjdk:17-jdk-alpine
LABEL authors="diego"
RUN apk --no-cache add curl maven
EXPOSE 8761
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:resolve
COPY src src
RUN mvn package -DskipTests
ENTRYPOINT ["java", "-jar", "target/service-registry-0.0.1-SNAPSHOT.jar"]