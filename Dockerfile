#Start with a base image containing Java runtime
FROM openjdk:11

# Add Maintainer Info
MAINTAINER Diego Gustavo Marquez Liñan

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/api-transaction.jar

# Add the application's jar to the container
ADD ${JAR_FILE} api-transaction.jar

# Run the jar file 
ENTRYPOINT ["java","-jar", "-Duser.timezone=America/Buenos_Aires", "-jar","/api-transaction.jar"]