# syntax=docker/dockerfile:1
FROM maven:3.9.11-eclipse-temurin-21-noble AS build

COPY src/pom.xml src/pom.xml
COPY src/boilerplate.api/pom.xml src/boilerplate.api/pom.xml
COPY src/boilerplate.model/pom.xml src/boilerplate.model/pom.xml
COPY src/boilerplate.db/pom.xml src/boilerplate.db/pom.xml

RUN --mount=type=cache,target=/root/.m2,rw mvn -f src -B dependency:go-offline
COPY src src
RUN --mount=type=cache,target=/root/.m2,rw mvn -f src -B package

FROM eclipse-temurin:21-noble

WORKDIR /app
COPY --from=build src/boilerplate.api/target/api.jar ./api.jar

RUN curl -sSLO https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v2.19.0/opentelemetry-javaagent.jar

RUN groupadd -r nonroot && useradd --no-log-init -r -g nonroot nonroot
USER nonroot

ENV OTEL_SERVICE_NAME="spring-boilerplate"

ENTRYPOINT ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-jar", "api.jar"]

EXPOSE 8080
HEALTHCHECK CMD curl -f http://0.0.0.0:8080/health || exit 1
