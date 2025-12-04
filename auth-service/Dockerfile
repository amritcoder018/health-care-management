# Build Stage
FROM maven:3.9.2-eclipse-temurin-17 as build
WORKDIR /app

# Copy ALL required files: parent pom AND the auth-service folder
COPY ../pom.xml ../pom.xml
COPY ../auth-service ../auth-service

# Move into the auth-service folder
WORKDIR /app/auth-service

# Build the module (parent is already available above)
RUN mvn -DskipTests package

# Runtime Stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/auth-service/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]

