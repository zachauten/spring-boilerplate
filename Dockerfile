# syntax=docker/dockerfile:1
FROM maven:3.9.11-eclipse-temurin-21-noble AS build

COPY pom.xml pom.xml
COPY src/api/pom.xml src/api/pom.xml
COPY src/model/pom.xml src/model/pom.xml
COPY src/db/pom.xml src/db/pom.xml

RUN --mount=type=cache,target=/root/.m2,rw mvn -B dependency:go-offline
COPY src src
RUN --mount=type=cache,target=/root/.m2,rw mvn -B package

FROM eclipse-temurin:21-noble

WORKDIR /app
COPY --from=build src/api/target/api.jar ./api.jar

RUN curl -sSLO https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v2.19.0/opentelemetry-javaagent.jar

RUN groupadd -r nonroot && useradd --no-log-init -r -g nonroot nonroot
USER nonroot

ENV OTEL_SERVICE_NAME="spring-boilerplate"

ENTRYPOINT ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-jar", "api.jar"]

EXPOSE 8080
HEALTHCHECK CMD curl -f http://0.0.0.0:8080/health || exit 1
