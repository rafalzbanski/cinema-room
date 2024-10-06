# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Package the application (skip tests to speed up build)
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/cinema-room-app-0.0.1-SNAPSHOT.jar cinema-room-app.jar

# Expose the port that the app runs on
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "cinema-room-app.jar"]
