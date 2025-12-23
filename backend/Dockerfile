FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY backend/pom.xml .
RUN mvn dependency:go-offline -B

COPY backend/src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

COPY --from=build /app/target/*.jar app.jar
# In development (docker-compose): volume mount will override these files
# In production (Render): these files will be available in the container
COPY data/*.json /app/data/

# Create non-root user
RUN groupadd -r spring && useradd -r -g spring spring
RUN chown -R spring:spring /app
USER spring:spring

EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]