package app.controllers.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import io.opentelemetry.instrumentation.annotations.WithSpan;

@RestControllerAdvice
public class ResponseTimeAdvice implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  @Nullable
  @WithSpan
  public Object beforeBodyWrite(@Nullable Object body, 
    MethodParameter returnType, 
    MediaType selectedContentType,
    Class<? extends HttpMessageConverter<?>> selectedConverterType, 
    ServerHttpRequest request, 
    ServerHttpResponse response) {
    var req = (ServletServerHttpRequest) request;
    var start = (long) req.getServletRequest().getAttribute("start");
    var duration = System.currentTimeMillis() - start;
    response.getHeaders().add("X-Response-Time", duration + "ms");
    return body;
  }
}
