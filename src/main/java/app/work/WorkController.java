package app.work;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.metrics.*;
import io.opentelemetry.instrumentation.annotations.WithSpan;

@RestController
public class WorkController {

  private HttpClient client;
  private static final Logger log = LoggerFactory.getLogger(WorkController.class);

  private static final Meter meter =
      GlobalOpenTelemetry.getMeter("io.opentelemetry.example.metrics");
  private static final LongCounter sleepCounter = meter
    .counterBuilder("time_slept")
    .setDescription("Total time spent sleeping in /work")
    .setUnit("ms")
    .build();

  public WorkController(HttpClient client) {
    this.client = client;
  }  

  @WithSpan
  @GetMapping("foo")
  public ResponseEntity<String> foo() throws IOException, InterruptedException, URISyntaxException {
    var uri = new URI("http://bar:8080/bar");
    var req = HttpRequest.newBuilder(uri).GET().build();
    var res = this.client.send(req, BodyHandlers.ofString());
    var str = res.body();
    return ResponseEntity.ok("foo" + str);
  }

  @WithSpan
  @GetMapping("bar")
  public ResponseEntity<String> bar() {
    return ResponseEntity.ok("bar");
  }

  @WithSpan
  @PostMapping("work")
  public ResponseEntity<String> work() throws InterruptedException {
    var time = ThreadLocalRandom.current().nextInt(1000, 4000);
    var part = Math.floorDiv(time, 3);
    log.info("sleeping for " + time + "ms, " + part + "ms at a time");
    this.doSomeWork(part);
    log.info("done doing work");
    return ResponseEntity.ok("");
  }

  @WithSpan
  @GetMapping("span")
  public ResponseEntity<String> span() throws InterruptedException {
    return ResponseEntity.ok(String.format("id: %s, num: %s", getUUID(), getValue()));
  }

  @WithSpan
  private void doSomeWork(int time) throws InterruptedException {
    log.info("doing work for " + time + "ms");
    Thread.sleep(time);
    sleepCounter.add(time);
  }

  @WithSpan
  private UUID getUUID() throws InterruptedException {
    var time = ThreadLocalRandom.current().nextInt(500, getUpperBound());
    Thread.sleep(time);
    return UUID.randomUUID();
  }

  @WithSpan
  private int getUpperBound() {
    return 2000;
  }

  @WithSpan
  private double getValue() throws InterruptedException {
    var time = ThreadLocalRandom.current().nextInt(500, 2000);
    Thread.sleep(time);
    return Math.random();
  }

}
