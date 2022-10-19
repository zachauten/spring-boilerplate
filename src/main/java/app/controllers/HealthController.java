package app.controllers;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.opentelemetry.instrumentation.annotations.WithSpan;

@Controller
@RequestMapping("/")
public class HealthController {
  
  @RequestMapping("/health")
  public ResponseEntity<String> health() {
    return ResponseEntity.ok("Ok");
  }

  @WithSpan
  @RequestMapping("/delay")
  public ResponseEntity<String> delay() throws InterruptedException {
    var time = ThreadLocalRandom.current().nextInt(1000, 4000);
    Thread.sleep(time);
    return ResponseEntity.ok("Ok");
  }

  @WithSpan
  @RequestMapping("/span")
  public ResponseEntity<String> span() throws InterruptedException {
    return ResponseEntity.ok(String.format("id: %s, num: %s", getUUID(), getValue()));
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
