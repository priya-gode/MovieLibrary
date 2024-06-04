# Use the official Tomcat image from the Docker Hub
FROM tomcat:9.0

# Remove the default ROOT webapp
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy the WAR file to the webapps directory of Tomcat
COPY target/MovieManagementSystem.war /usr/local/tomcat/webapps/ROOT.war

# Expose the port on which your Spring Boot app will run
EXPOSE 1686

# Start Tomcat
CMD ["catalina.sh", "run"]
