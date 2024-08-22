# docker build -t spring-app .
# docker run --name spring-app -d -p 8080:8080 spring-app

FROM maven:3.9.8-eclipse-temurin-21 as build

COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2,rw mvn -B dependency:go-offline
COPY src src
RUN --mount=type=cache,target=/root/.m2,rw mvn -B package

FROM eclipse-temurin:21

WORKDIR /app
COPY --from=build target/*.jar ./app.jar

RUN curl -sSLO https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v2.5.0/opentelemetry-javaagent.jar

RUN groupadd -r nonroot && useradd --no-log-init -r -g nonroot nonroot
USER nonroot

ENV OTEL_EXPORTER_OTLP_ENDPOINT="http://collector:4318/"
ENV 
ENV SERVICE_NAME="spring-boilerplate"
ENV SERVICE_VERSION="alpha"

ENTRYPOINT ["java", "-javaagent", "opentelemetry-javaagent.jar", "-jar", "app.jar"]

EXPOSE 8080
HEALTHCHECK CMD curl -f http://0.0.0.0:8080/health || exit 1

