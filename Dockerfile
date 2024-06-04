# Use an official Java runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Make port 1686 available to the world outside this container
EXPOSE 1686

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
