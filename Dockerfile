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

RUN groupadd -r nonroot && useradd --no-log-init -r -g nonroot nonroot
USER nonroot

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080
HEALTHCHECK CMD curl -f http://0.0.0.0:8080/health || exit 1

