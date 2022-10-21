# Spring Boilerplate

## Observability

### Commands to test open telemetry locally

Remember to set up a docker network
```
docker network create otel
```
Running Jaeger to test open telemetry spans

```
docker run -d --name jaeger   -e COLLECTOR_OTLP_ENABLED=true   -p 16686:16686   -p 4317:4317   -p 4318:4318 --network otel --hostname jaeger jaegertracing/all-in-one
```

Running application
```
docker run --name spring-app -d -p 8080:8080 -e OTEL_EXPORTER_OTLP_ENDPOINT="http://jaeger:4317" --network otel --hostname spring-app spring-app
```
