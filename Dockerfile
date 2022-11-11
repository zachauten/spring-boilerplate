# docker build -t spring-app .
# docker run --name spring-app -d -p 8080:8080 spring-app

FROM maven:3.8.6-eclipse-temurin-17 as build

COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2,rw mvn -B dependency:go-offline
COPY src src
RUN --mount=type=cache,target=/root/.m2,rw mvn -B package

FROM eclipse-temurin:17

WORKDIR /app
COPY --from=build target/*.jar ./app.jar
RUN curl -sSLO https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.18.0/opentelemetry-javaagent.jar

ENV OTEL_SERVICE_NAME=spring-service
ENV OTEL_RESOURCE_ATTRIBUTES="service.version=0.1,deployment.environment=local"
ENV OTEL_LOGS_EXPORTER=otlp
ENV OTEL_EXPORTER_OTLP_HEADERS=
ENV OTEL_EXPORTER_OTLP_ENDPOINT=https://api.honeycomb.io

RUN groupadd -r nonroot && useradd --no-log-init -r -g nonroot nonroot
USER nonroot

ENTRYPOINT ["java", "-javaagent:opentelemetry-javaagent.jar", "-jar", "app.jar"]

EXPOSE 8080
HEALTHCHECK CMD curl -f http://0.0.0.0:8080/health || exit 1

