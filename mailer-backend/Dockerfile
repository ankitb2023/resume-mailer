# Use OpenJDK base image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy all files from backend folder to /app
COPY . .

# Package the application
RUN ./mvnw clean package -DskipTests

# Expose port (default for Spring Boot)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/*.jar"]