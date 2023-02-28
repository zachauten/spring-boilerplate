# Spring Boilerplate

## Build
Build the docker image:
```
docker build \
  -t spring-app \
  --label org.opencontainers.image.revision=$(git rev-parse HEAD) \
  --label org.opencontainers.image.source=https://github.com/zachauten/spring-boilerplate \
  .
```

## Observability
### Commands to test open telemetry locally

Remember to set up a docker network
```
docker network create telemetry
```
Running Jaeger to test open telemetry spans
```
docker run -d \
--name jaeger \
--hostname jaeger \
--network telemetry \
-e COLLECTOR_OTLP_ENABLED=true \
-p 16686:16686 \
 -p 4317:4317 \
 -p 4318:4318 \
jaegertracing/all-in-one
```
Or, running the open telemetry collector:
```
docker run -d \
--name collector \
--hostname collector \
--network telemetry \
--mount type=bind,src=$(pwd)/config.yaml,target=/etc/otelcol-contrib/config.yaml \
--mount type=bind,src=/var/run/docker.sock,target=/var/run/docker.sock \
-p 4317:4317 \
-p 55680:55680 \
-p 55679:55679 \
-e DD_API_KEY=${DD_API_KEY} \
otel/opentelemetry-collector-contrib
```

Running application

Connecting to the collector: (docker)
```
docker run -d \
--name spring-app \
--hostname spring-app \
--network telemetry \
-e OTEL_EXPORTER_OTLP_ENDPOINT="http://collector:4317" \
-p 8080:8080 \
spring-app
```

Honeycomb.io: (on host)
```
OTEL_SERVICE_NAME=spring-service OTEL_EXPORTER_OTLP_HEADERS=x-honeycomb-team=$HONEYCOMB_API_KEY OTEL_EXPORTER_OTLP_ENDPOINT=https://api.honeycomb.io java -javaagent:opentelemetry-javaagent.jar -jar target/*.jar
```
