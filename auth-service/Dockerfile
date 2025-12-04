# Build Stage
FROM maven:3.9.2-eclipse-temurin-17 AS build

# We will work inside /app
WORKDIR /app

# Copy the parent pom (from repo root) into /app
COPY ../pom.xml ./pom.xml

# Copy THIS module (auth-service) into /app/auth-service
COPY . ./auth-service

# Run Maven from /app, building only auth-service (parent pom is in /app/pom.xml)
RUN mvn -pl auth-service -am -DskipTests package

# Runtime Stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the built jar from the auth-service module
COPY --from=build /app/auth-service/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]

