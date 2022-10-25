package app.interceptors;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import io.opentelemetry.instrumentation.annotations.WithSpan;

public class ResponseTimeInterceptor implements HandlerInterceptor {
  
  private final Logger logger = Logger.getLogger(ResponseTimeInterceptor.class.toString());

  @Override
  @WithSpan
  public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
    var start = System.currentTimeMillis(); 
    req.setAttribute("start", start);
    return true;
  }

  @Override
  @WithSpan
  public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception exception) {
    var duration = res.getHeader("X-Response-Time");
    logger.info(String.format("%s %s - %s", req.getMethod(), req.getRequestURI(), duration));
  }
}
