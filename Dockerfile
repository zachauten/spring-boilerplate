FROM maven:3.8.6-eclipse-temurin-17 as build

COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src src
RUN --mount=type=cache,target=/root/.m2,rw mvn -B package

FROM eclipse-temurin:17

COPY --from=build target/*jar-with-dependencies.jar ./app.jar
RUN curl -sSLO 'https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.18.0/opentelemetry-javaagent.jar'

ENV OTEL_SERVICE_NAME="javalin-service"
ENV OTEL_TRACES_EXPORTER="logging"
ENV JAVA_OPTS="-javaagent:/app/opentelemetry-javaagent.jar"

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080
HEALTHCHECK CMD curl -f http://0.0.0.0:8080/health || exit 1

