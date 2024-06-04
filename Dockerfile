# Use an official Tomcat runtime as a parent image
FROM tomcat:9.0

# Remove the default ROOT webapp to replace it with your WAR
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy the WAR file to the webapps directory of Tomcat
COPY target/MovieManagementSystem-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 1686
EXPOSE 1686

# Start Tomcat
CMD ["catalina.sh", "run"]
