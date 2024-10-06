# Stage 1: Build the application
FROM maven:3.8.8-openjdk-17-slim AS build
COPY . /home/app
WORKDIR /home/app
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
COPY --from=build /home/app/target/cinema-room-0.0.1-SNAPSHOT.jar app.jar

# Expose the PORT environment variable
ENV PORT 8080
EXPOSE $PORT

# Set the entry point to use the PORT environment variable
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]
