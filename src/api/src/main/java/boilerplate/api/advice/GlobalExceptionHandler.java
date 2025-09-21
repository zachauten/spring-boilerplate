package boilerplate.api.advice;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

  private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @WithSpan
  @ExceptionHandler(value = {Exception.class})
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public String serverError(Exception exception) {
    log.error("Unknown exception", exception);
    return "Internal server error";
  }
}
