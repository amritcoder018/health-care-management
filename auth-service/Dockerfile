# Build Stage
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy parent pom
COPY ../pom.xml ./pom.xml

# Copy auth-service module
COPY ./ ../

# Move into auth-service
WORKDIR /app/auth-service

# Build only auth-service module
RUN mvn -DskipTests package

# Runtime Stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/auth-service/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
