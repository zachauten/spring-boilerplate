package app.controllers.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseTimeAdvice implements ResponseBodyAdvice {

  @Override
  public boolean supports(MethodParameter returnType, Class converterType) {
    return true;
  }

  @Override
  @Nullable
  public Object beforeBodyWrite(@Nullable Object body, 
    MethodParameter returnType, 
    MediaType selectedContentType,
    Class selectedConverterType, 
    ServerHttpRequest request, 
    ServerHttpResponse response) {
    var req = (ServletServerHttpRequest) request;
    var start = (long) req.getServletRequest().getAttribute("start");
    var duration = System.currentTimeMillis() - start;
    response.getHeaders().add("X-Response-Time", duration + "ms");
    return body;
  }
}
