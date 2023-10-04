package app.work;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.metrics.*;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class WorkController {

  private static final Meter meter =
      GlobalOpenTelemetry.getMeter("io.opentelemetry.example.metrics");
  private static final LongCounter sleepCounter = meter
    .counterBuilder("time_slept")
    .setDescription("Total time spent sleeping in /work")
    .setUnit("ms")
    .build();


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
