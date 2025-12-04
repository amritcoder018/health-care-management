# Build stage
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app

# Copy entire multi-module project
COPY . .

# Build only auth-service (and its dependencies/modules)
RUN mvn -pl auth-service -am -DskipTests package

# Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the auth-service jar from the build stage
COPY --from=build /app/auth-service/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
