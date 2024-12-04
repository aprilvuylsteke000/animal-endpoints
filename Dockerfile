# Use a base image with Java runtime
FROM openjdk:17-jdk-slim

# Label the image
LABEL authors="aprilismaius"

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/animal-endpoints-0.0.1-SNAPSHOT.jar app.jar

# Expose the port Spring Boot will run on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]