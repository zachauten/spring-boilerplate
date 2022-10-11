package app.controllers;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class HealthInterceptor implements HandlerInterceptor {
  
  private final Logger logger = Logger.getLogger(HealthController.class.toString());

  private long start;
  private long duration;

  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
    start = System.currentTimeMillis(); 
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) {
    duration = (System.currentTimeMillis() - start) / 1000L;
    res.setHeader("X-Response-Time", duration + "ms");
  }

  @Override
  public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception exception) {
    logger.info(String.format("%s %s - %sms", req.getMethod(), req.getRequestURI(), duration));
  }
}
