# ==========================================
# STAGE 1: Build the Java Spring Boot Application
# ==========================================
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /workspace

# Copy Maven POM and app sources
COPY app/pom.xml app/pom.xml
COPY app/src app/src

# Set working directory to the app folder and run package
WORKDIR /workspace/app
RUN mvn clean package -DskipTests

# ==========================================
# STAGE 2: Alpine JRE Runtime Image
# ==========================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the packaged jar from the builder stage
COPY --from=build /workspace/app/target/*.jar app.jar

# Expose server port (configured to 8080 in application.properties)
EXPOSE 8080

# Environment variables (overrideable on container run)
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Execute application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
