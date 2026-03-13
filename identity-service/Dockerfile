# Build stage
FROM gradle:8-jdk21-alpine AS build
WORKDIR /app

# Copy the entire project
COPY . .

# Build the application
# We use --no-daemon to avoid persistent processes in the build container
RUN gradle :infrastructure:bootJar --no-daemon

# Run stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Expose the application port
EXPOSE 8080

# Copy the built JAR from the build stage
# The path depends on the project structure and build output
COPY --from=build /app/infrastructure/build/libs/infrastructure-*-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
