# Stage 1: Build the application
FROM maven:3.8.6-openjdk-17 AS build
COPY . /home/app
WORKDIR /home/app
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
COPY --from=build /home/app/target/cinema-room-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
