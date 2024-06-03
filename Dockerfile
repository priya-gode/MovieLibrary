# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application's JAR file to the container
COPY target/MovieManagementSystem-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the application runs on
EXPOSE 1686

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
