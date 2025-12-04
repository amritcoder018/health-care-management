# Build stage
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Work in /app
WORKDIR /app

# Copy EVERYTHING from the repo root into Docker
COPY .. .

# Now the structure inside Docker is:
# /app/pom.xml
# /app/auth-service/pom.xml
# /app/doctor-service
# /app/patient-service
# /app/laboratory-service
# /app/api-gateway-service
# /app/payment-service

# Build ONLY auth-service
RUN mvn -pl auth-service -am -DskipTests package

# Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/auth-service/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
