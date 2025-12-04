# Build stage
FROM maven:3.9.2-eclipse-temurin-17 as build
WORKDIR /app

# copy the parent pom
COPY pom.xml ./parent-pom.xml

# copy child pom and src
COPY auth-service/pom.xml .
COPY auth-service/src ./src

RUN mvn -DskipTests package
