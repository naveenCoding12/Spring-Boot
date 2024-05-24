# Use OpenJDK 17 slim image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file built by Maven into the image
COPY target/*.jar app.jar

# Expose the port your application runs on
EXPOSE 8080

# Set the command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]
