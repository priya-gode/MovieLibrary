
# Use the official Tomcat base image
FROM tomcat:9.0

# Set the working directory to /usr/local/tomcat/webapps
WORKDIR /usr/local/tomcat/webapps

# Copy the WAR file from the target directory to the Tomcat webapps directory
COPY target/MovieManagementSystem-0.0.1-SNAPSHOT/MovieManagementSystem-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

# Expose the desired port
EXPOSE 1686

# Change the Tomcat server.xml configuration to use port 1686
RUN sed -i 's/port="8080"/port="1686"/' /usr/local/tomcat/conf/server.xml

# Run Tomcat
CMD ["catalina.sh", "run"]