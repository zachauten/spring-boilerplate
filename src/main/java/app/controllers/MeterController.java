package app.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import io.micrometer.core.instrument.*;

@Slf4j
@RestController
public class MeterController {
  
  /**
   * Gen an val between 0 and 100.
   * @return
   */
  @GetMapping("rand")
  public ResponseEntity<Double> rand() {
    var val = ThreadLocalRandom.current().nextDouble(0, 101);
    Supplier<Double> s = () -> val;
    List<Measurement> measurements = Arrays.asList(new Measurement(s::get, Statistic.VALUE));
    Meter meter = Meter.builder("testMeter", Meter.Type.OTHER, measurements)
      .description("Testing the micrometer shim")
      .baseUnit("things")
      .tag("tag", "val")
      .register(Metrics.globalRegistry);
    return ResponseEntity.ok(val);
  }
}
