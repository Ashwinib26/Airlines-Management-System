# Use an official Maven + JDK image
FROM maven:3.8.5-openjdk-17 AS build

# Set working directory
WORKDIR /app

# Copy source code
COPY . .

# Build Spring Boot application
RUN mvn clean package -DskipTests

# Use a smaller Java runtime for final image
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the jar from the previous image
COPY --from=build /app/target/*.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
