# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application's WAR file to the container
COPY target/MovieManagementSystem-0.0.1-SNAPSHOT.war app.war

# Expose the port that the application 
EXPOSE 1686

# Run the WAR file
ENTRYPOINT ["java", "-jar", "app.war"]
