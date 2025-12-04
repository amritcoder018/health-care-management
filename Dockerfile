# Build stage
FROM maven:3.9.2-eclipse-temurin-17 AS build

WORKDIR /app

ARG MODULE

RUN echo "Building module: ${MODULE}"

COPY . .

RUN mvn -pl ${MODULE} -am -DskipTests package

# Runtime stage
FROM eclipse-temurin:17-jre

WORKDIR /app

ARG MODULE

COPY --from=build /app/${MODULE}/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
