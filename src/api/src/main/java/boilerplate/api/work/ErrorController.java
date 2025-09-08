package app.work;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

  @WithSpan
  @GetMapping("throwError")
  public int throwError() {
    return 1 / 0;
  }
}
