# Steg 1: Bygg appen
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Steg 2: Kör appen (Ubuntu-baserad Java + IPv4-fix)
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
# HÄR ÄR ÄNDRINGEN (IPv4-fixen):
ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "app.jar"]