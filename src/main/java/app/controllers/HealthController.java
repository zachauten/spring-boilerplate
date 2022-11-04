package app.controllers;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.opentelemetry.instrumentation.annotations.WithSpan;

@RestController()
public class HealthController {
  
  @GetMapping("health")
  public ResponseEntity<String> health() {
    return ResponseEntity.ok("Ok");
  }

  @GetMapping("delay")
  public ResponseEntity<String> delay() throws InterruptedException {
    var time = ThreadLocalRandom.current().nextInt(1000, 4000);
    Thread.sleep(time);
    return ResponseEntity.ok("Ok");
  }

  @GetMapping("span")
  public ResponseEntity<String> span() throws InterruptedException {
    return ResponseEntity.ok(String.format("id: %s, num: %s", getUUID(), getValue()));
  }

  private UUID getUUID() throws InterruptedException {
    var time = ThreadLocalRandom.current().nextInt(500, getUpperBound());
    Thread.sleep(time);
    return UUID.randomUUID();
  }

  private int getUpperBound() {
    return 2000;
  }

  private double getValue() throws InterruptedException {
    var time = ThreadLocalRandom.current().nextInt(500, 2000);
    Thread.sleep(time);
    return Math.random();
  }
}
